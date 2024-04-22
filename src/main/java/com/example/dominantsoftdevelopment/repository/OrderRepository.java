package com.example.dominantsoftdevelopment.repository;

import com.example.dominantsoftdevelopment.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
    Optional<List<Orders>> findAllByDeletedFalseAndCustomerId(Long userId);
    List<Orders> findAllByDeletedFalse();
}
