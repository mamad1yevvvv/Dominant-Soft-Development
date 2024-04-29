package com.example.dominantsoftdevelopment.service.order;

import com.example.dominantsoftdevelopment.dto.AddOrderDTO;
import com.example.dominantsoftdevelopment.dto.ApiResult;
import com.example.dominantsoftdevelopment.dto.OrderDTO;

import java.util.List;

public interface OrderService {

    ApiResult<Boolean> makeOrder(AddOrderDTO addOrderDTO);

    ApiResult<List<OrderDTO>> getUserOrders(Long userId);

    ApiResult<OrderDTO> getOrder(Long orderId);

    ApiResult<Boolean> delete(Long orderId);

    Boolean isOwner(Long userId, String username);
//    ApiResult<List<OrderItemDTO>> getOrderItems(Long orderId);
}
