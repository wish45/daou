package com.project.daou.repository;

import com.project.daou.entity.PaymentData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentData, String> {
}
