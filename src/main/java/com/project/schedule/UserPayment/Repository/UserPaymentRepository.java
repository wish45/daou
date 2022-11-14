package com.project.schedule.UserPayment.Repository;

import com.project.schedule.UserPayment.Entity.CardUsages;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPaymentRepository extends JpaRepository<CardUsages, Long> {

}
