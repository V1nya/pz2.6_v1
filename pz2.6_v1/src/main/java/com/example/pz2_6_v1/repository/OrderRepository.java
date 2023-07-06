package com.example.pz2_6_v1.repository;

import com.example.pz2_6_v1.modal.Medicine;
import com.example.pz2_6_v1.modal.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByMedicine(Medicine medicine);
}
