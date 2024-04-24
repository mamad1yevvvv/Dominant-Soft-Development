package com.example.dominantsoftdevelopment.controller.order;

import com.example.dominantsoftdevelopment.dto.AddOrderDTO;
import com.example.dominantsoftdevelopment.dto.ApiResult;
import com.example.dominantsoftdevelopment.dto.OrderDTO;
import com.example.dominantsoftdevelopment.service.order.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PreAuthorize(value = "hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    @PostMapping
    public HttpEntity<ApiResult<Boolean>> makeOrders(@RequestBody @Valid AddOrderDTO addOrderDTO) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(orderService.makeOrder(addOrderDTO));
    }

    @PreAuthorize(value = "hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    @GetMapping("/{orderId}")
    public HttpEntity<ApiResult<OrderDTO>> getOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderService.getOrder(orderId));
    }

    @PreAuthorize(value = "hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    @GetMapping("/list/{userId}")
    public HttpEntity<ApiResult<List<OrderDTO>>> getUserOrders(@PathVariable Long userId){
        return ResponseEntity.ok(orderService.getUserOrders(userId));
    }

    @PreAuthorize(value = "hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public HttpEntity<ApiResult<Boolean>> deleteOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderService.delete(orderId));
    }
    /* @GetMapping("/item/{orderId}")
     @Operation(summary = "This API is used for getting order-items by orderID")
    public ResponseEntity<ApiResult<List<OrderItemDTO>>> getOrderItems(@PathVariable Long orderId) {
         return ResponseEntity.ok(orderService.getOrderItems(orderId));
    }*/
}
