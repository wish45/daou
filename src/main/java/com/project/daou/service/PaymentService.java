package com.project.daou.service;

import com.project.daou.entity.PaymentData;
import com.project.daou.repository.PaymentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }


    public PaymentData selectPayment(PaymentData paymentDTO) {
        return paymentRepository.findById(paymentDTO.getRegDate()).orElseThrow(() -> new IllegalArgumentException("no such data"));
    }

    @Transactional
    public PaymentData addPayment(PaymentData paymentDTO) {
        if (paymentDTO == null) throw new IllegalArgumentException("todo item cannot be null");
        return paymentRepository.save(paymentDTO);
    }

    public void deletePayment(PaymentData paymentDTO) {
        paymentRepository.deleteById(paymentDTO.getRegDate());
    }

    public PaymentData updatePayment(PaymentData paymentDTO) {
        PaymentData updatedDTO = selectPayment(paymentDTO);
        updatedDTO.setPayAmount(paymentDTO.getPayAmount());
        updatedDTO.setLeaveCount(paymentDTO.getLeaveCount());
        updatedDTO.setUsedAmount(paymentDTO.getUsedAmount());
        updatedDTO.setLeaveCount(paymentDTO.getLeaveCount());
        updatedDTO.setSalesAmount(paymentDTO.getSalesAmount());
        updatedDTO.setRegistCount(paymentDTO.getRegistCount());
        return paymentRepository.save(updatedDTO);
    }
}

