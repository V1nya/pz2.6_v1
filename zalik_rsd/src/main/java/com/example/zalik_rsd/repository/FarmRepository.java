package com.example.zalik_rsd.repository;

import com.example.zalik_rsd.modal.FarmEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FarmRepository extends JpaRepository<FarmEntity, Long> {
}
