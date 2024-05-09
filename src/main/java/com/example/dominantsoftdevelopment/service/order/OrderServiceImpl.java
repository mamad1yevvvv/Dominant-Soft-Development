package com.example.dominantsoftdevelopment.service.order;

import com.example.dominantsoftdevelopment.dto.*;
import com.example.dominantsoftdevelopment.exceptions.RestException;
import com.example.dominantsoftdevelopment.model.OrderItem;
import com.example.dominantsoftdevelopment.model.Orders;
import com.example.dominantsoftdevelopment.model.Product;
import com.example.dominantsoftdevelopment.model.User;
import com.example.dominantsoftdevelopment.model.enums.Roles;
import com.example.dominantsoftdevelopment.repository.OrderItemRepository;
import com.example.dominantsoftdevelopment.repository.OrderRepository;
import com.example.dominantsoftdevelopment.repository.ProductRepository;
import com.example.dominantsoftdevelopment.repository.UserRepository;
import com.example.dominantsoftdevelopment.utils.CommonUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final OrderItemRepository orderItemRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public ApiResult<Boolean> makeOrder(AddOrderDTO addOrderDTO) {
        Orders order = new Orders();
        orderRepository.save(order);
        OrderItem orderItem = new OrderItem();
        Product product;
        Long productId;
        double totalPrice=0;

        for (AddOrderItemDTO itemDTO : addOrderDTO.getOrderItems()) {
            totalPrice = 0;
            productId = itemDTO.getProductId();
            product = productRepository.findById(productId)
                    .orElseThrow(() -> RestException.restThrow("Product not found"));

            orderItem.setOrderId(order);
            orderItem.setProduct(product);
            orderItem.setPrice(product.getPrice());
//            orderItem.setProductCount(itemDTO.getProductCount());

            orderItemRepository.save(orderItem);
            totalPrice += product.getPrice();
        }
        order.setTotalPrice(totalPrice);
        order.setCustomer(CommonUtils.getCurrentUserFromContext());
        order.setPayType(addOrderDTO.getPayType());
        orderRepository.save(order);

        return ApiResult.successResponse(true);
    }

    @Override
    public ApiResult<OrderDTO> getOrder(Long orderId) {
        Orders order = orderRepository
                .findById(orderId)
                .orElseThrow(() -> RestException.restThrow("Order not found"));
        User user = CommonUtils.getCurrentUserFromContext();
        //||
        if (!order.getCustomer().getId().equals(user.getId()) && !user.getRoles().equals(Roles.ADMIN)) {
            throw RestException.restThrow("Forbidden (NO WAY)", HttpStatus.FORBIDDEN);
        }
        List<OrderItem> items = getOrderItemsByOrderId(order);
        List<OrderItemDTO> list = items.stream()
                .map(orderItem -> modelMapper.map(orderItem, OrderItemDTO.class))
                .toList();
        OrderDTO orderDTO = OrderDTO.builder()
                .createdAt(order.getCreatedAt())
                .id(order.getId())
                .orderItems(list)
                .totalPrice(order.getTotalPrice())
                .build();
        return ApiResult.successResponse(orderDTO);
    }

    private List<OrderItem> getOrderItemsByOrderId(Orders order) {
        return orderItemRepository.findByOrderId(order);
    }

    @Override
    public ApiResult<List<OrderDTO>> getUserOrders(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> RestException.restThrow("User not found"));
        List<Orders> orders = orderRepository
                .findAllByDeletedFalseAndCustomerId(userId).orElse(List.of());

        if (orders.isEmpty()) {
            return ApiResult.successResponse(List.of());
        }
        List<OrderDTO> orderDTOS = new ArrayList<>();

        for (Orders order : orders) {
            List<OrderItem> items = getOrderItemsByOrderId(order);
            List<OrderItemDTO> list = items.stream()
                    .map(orderItem -> modelMapper.map(orderItem, OrderItemDTO.class))
                    .toList();
            OrderDTO orderDTO = OrderDTO.builder()
                    .createdAt(order.getCreatedAt())
                    .id(order.getId())
                    .orderItems(list)
                    .totalPrice(order.getTotalPrice())
                    .build();
            orderDTOS.add(orderDTO);

        }

        return ApiResult.successResponse(orderDTOS);
    }

    @Override
    public ApiResult<Boolean> delete(Long orderId) {
        Orders order = orderRepository.findById(orderId)
                .orElseThrow(() -> RestException.restThrow("Order not found"));
        order.setDeleted(true);
        orderRepository.save(order);
        return ApiResult.successResponse(true);
    }

    @Override
    public Boolean isOwner(Long orderId, String username) {
        Orders order = orderRepository.findById(orderId)
                .orElseThrow(() -> RestException.restThrow("Order not found"));
        return order.getCustomer().getUsername().equals(username);
    }
}
