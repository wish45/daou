package com.project.daou.service;

import com.project.daou.entity.PaymentData;
import com.project.daou.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
class PaymentServiceTest {

    private static ValidatorFactory factory;
    private static Validator validator;

    @InjectMocks
    private PaymentService paymentService;

    @Spy
    private PaymentRepository paymentRepository;

    @BeforeAll
    public static void init(){
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }



    @Test
    @DisplayName("결제금액 추가 - 성공")
    void addPayment() {
        //given
        PaymentData DTO = new PaymentData();
        DTO.setPayAmount("45,100");
        DTO.setRegDate("2022-07-22 00");
        DTO.setLeaveCount("4");
        DTO.setUsedAmount("27,300");
        DTO.setRegistCount("32");
        DTO.setSalesAmount("95,000");

        //when
        paymentService.addPayment(DTO);

        //then
        verify(paymentRepository).save(any(PaymentData.class));
    }

    @Test
    @DisplayName("결제금액 추가 - 날짜 실패 테스트")
    void validRegDateTest(){
        //given
        PaymentData DTO = new PaymentData();
        DTO.setRegDate("");
        DTO.setPayAmount("45,100");
        DTO.setLeaveCount("4");
        DTO.setUsedAmount("27,300");
        DTO.setRegistCount("32");
        DTO.setSalesAmount("95,000");

        //when
        Set<ConstraintViolation<PaymentData>> validate = validator.validate(DTO);

        //then
        validate.forEach(error -> {
            assertThat(error.getMessage()).isEqualTo("날짜를 입력해주세요");
        });
    }
    @Test
    @DisplayName("결제금액 추가 - 가입자수 실패 테스트")
    void validRegCountTest(){
        //given
        PaymentData DTO = new PaymentData();
        DTO.setRegDate("2022-07-22 00");
        DTO.setRegistCount("");
        DTO.setPayAmount("45,100");
        DTO.setLeaveCount("4");
        DTO.setUsedAmount("27,300");
        DTO.setSalesAmount("95,000");

        //when
        Set<ConstraintViolation<PaymentData>> validate = validator.validate(DTO);

        //then
        validate.forEach(error -> {
            assertThat(error.getMessage()).isEqualTo("가입자수를 입력해주세요");
        });
    }

    @Test
    @DisplayName("결제금액 추가 - 탈퇴자수 실패 테스트")
    void validLeaveCountTest(){
        //given
        PaymentData DTO = new PaymentData();
        DTO.setRegDate("2022-07-22 00");
        DTO.setRegistCount("32");
        DTO.setLeaveCount("");
        DTO.setPayAmount("45,100");
        DTO.setUsedAmount("27,300");
        DTO.setSalesAmount("95,000");

        //when
        Set<ConstraintViolation<PaymentData>> validate = validator.validate(DTO);

        //then
        validate.forEach(error -> {
            assertThat(error.getMessage()).isEqualTo("탈퇴자수를 입력해주세요");
        });
    }
    @Test
    @DisplayName("결제금액 추가 - 결제금액 실패 테스트")
    void validPayAmountTest(){
        //given
        PaymentData DTO = new PaymentData();
        DTO.setRegDate("2022-07-22 00");
        DTO.setRegistCount("32");
        DTO.setLeaveCount("4");
        DTO.setPayAmount("");
        DTO.setUsedAmount("27,300");
        DTO.setSalesAmount("95,000");

        //when
        Set<ConstraintViolation<PaymentData>> validate = validator.validate(DTO);

        //then
        validate.forEach(error -> {
            assertThat(error.getMessage()).isEqualTo("결제금액을 입력해주세요");
        });
    }
    @Test
    @DisplayName("결제금액 추가 - 사용금액 실패 테스트")
    void validUsedAmountTest(){
        //given
        PaymentData DTO = new PaymentData();
        DTO.setRegDate("2022-07-22 00");
        DTO.setRegistCount("32");
        DTO.setLeaveCount("4");
        DTO.setPayAmount("45,100");
        DTO.setUsedAmount("");
        DTO.setSalesAmount("95,000");

        //when
        Set<ConstraintViolation<PaymentData>> validate = validator.validate(DTO);

        //then
        validate.forEach(error -> {
            assertThat(error.getMessage()).isEqualTo("사용금액을 입력해주세요");
        });
    }
    @Test
    @DisplayName("결제금액 추가 - 매출금액 실패 테스트")
    void validSalesAmountTest(){
        //given
        PaymentData DTO = new PaymentData();
        DTO.setRegDate("2022-07-22 00");
        DTO.setRegistCount("32");
        DTO.setLeaveCount("4");
        DTO.setPayAmount("45,100");
        DTO.setUsedAmount("27,300");
        DTO.setSalesAmount("");

        //when
        Set<ConstraintViolation<PaymentData>> validate = validator.validate(DTO);

        //then
        validate.forEach(error -> {
            assertThat(error.getMessage()).isEqualTo("매출금액을 입력해주세요");
        });
    }
    @Test
    @DisplayName("결제금액 조회 테스트")
    void selectPayment() {

    }
    @Test
    @DisplayName("결제금액 삭제 테스트")
    void deletePayment() {

    }

    @Test
    @DisplayName("결제금액 수정 테스트")
    void updatePayment() {
       
    }
}