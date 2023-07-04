package com.example.zalik_rsd.repository;

import com.example.zalik_rsd.modal.FarmEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FarmRepository extends JpaRepository<FarmEntity, Long> {

    List<FarmEntity> findByEnterpriseName(String enterpriseNameFilter);
}
