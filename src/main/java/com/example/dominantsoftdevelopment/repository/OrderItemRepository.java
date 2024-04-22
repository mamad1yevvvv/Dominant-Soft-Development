package com.example.dominantsoftdevelopment.repository;

import com.example.dominantsoftdevelopment.model.OrderItem;
import com.example.dominantsoftdevelopment.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByOrderId(Orders orderId);
}
