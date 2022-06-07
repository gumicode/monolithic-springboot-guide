package com.example.monolithic.domain.alert.repository;

import com.example.monolithic.domain.alert.entity.Alert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertRepository extends JpaRepository<Alert, Long> {
}
