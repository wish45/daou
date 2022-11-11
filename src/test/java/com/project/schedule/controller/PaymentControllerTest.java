package com.project.schedule.controller;

import com.project.schedule.entity.PaymentData;
import com.project.schedule.service.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest(PaymentController.class)
class PaymentControllerTest {
    @MockBean
    private PaymentService paymentService;

    @MockBean
    private PaymentData paymentData;

    @Autowired
    //private MockMvc mockMvc;

    @BeforeEach
    void init(){
        PaymentData DTO = new PaymentData();
        DTO.setRegDate("2022-07-22 00");
        DTO.setRegistCount(32);
        DTO.setLeaveCount(4);
        DTO.setPayAmount(45100);
        DTO.setUsedAmount(27300);
        DTO.setSalesAmount(20000);
    }

    @Test
    void successGetselectPayment(){
        //given
       /* given(paymentService.selectPayment(paymentData))
                .willReturn(PaymentDataDTO.builder()
                        .)*/
        //when
        //then
    }
}