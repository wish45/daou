package com.project.schedule.repository;

import com.project.schedule.entity.PaymentData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentData, String> {
}
