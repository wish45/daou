package com.project.daou.controller;

import com.project.daou.dto.PaymentDataDTO;
import com.project.daou.entity.PaymentData;
import com.project.daou.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }


    //조회
    @GetMapping
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<PaymentDataDTO> selectPayment(@Validated @RequestBody PaymentData paymentDataDTO) {
        paymentService.selectPayment(paymentDataDTO);
        return new ResponseEntity<>(new PaymentDataDTO(), HttpStatus.OK);
    }

    //등록
    @PostMapping
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<PaymentDataDTO> addPayment(@Validated @RequestBody PaymentData paymentDataDTO) {
        paymentService.addPayment(paymentDataDTO);
        return new ResponseEntity<>(new PaymentDataDTO(), HttpStatus.CREATED);
    }

    //삭제
    @DeleteMapping("/deletePayment")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<PaymentDataDTO> deletePayment(@Validated @RequestBody PaymentData paymentDataDTO) {
        paymentService.deletePayment(paymentDataDTO);
        return new ResponseEntity<>(new PaymentDataDTO(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //수정
    @PatchMapping("/updatePayment")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<PaymentData> updateMemberInfo(@Validated @RequestBody PaymentData paymentDataDTO) {
        paymentService.updatePayment(paymentDataDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(paymentDataDTO);
    }
}
