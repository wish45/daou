package com.project.schedule.service;

import com.project.schedule.entity.PaymentData;
import com.project.schedule.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Stream;

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

    private static Stream<PaymentData> testDto() {
        return Stream.of(PaymentData.builder()
                .leaveCount(new Random().nextInt(100))
                .registCount(new Random().nextInt(100))
                .payAmount((long)Math.abs(new Random().nextInt()))
                .salesAmount((long)Math.abs(new Random().nextInt()))
                .usedAmount((long)Math.abs(new Random().nextInt()))
                .regDate("2022-07-22 00")
                .build());
    }



    @Test
    @DisplayName("결제금액 추가 - 성공")
    void addPayment() {
        //given
        PaymentData DTO = new PaymentData();
        DTO.setPayAmount(45100);
        DTO.setRegDate("2022-07-22 00");
        DTO.setLeaveCount(4);
        DTO.setUsedAmount(27300);
        DTO.setRegistCount(32);
        DTO.setSalesAmount(95000);

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
        DTO.setPayAmount(45100);
        DTO.setLeaveCount(4);
        DTO.setUsedAmount(27300);
        DTO.setRegistCount(32);
        DTO.setSalesAmount(95000);

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
        DTO.setRegistCount(0);
        DTO.setPayAmount(45100);
        DTO.setLeaveCount(4);
        DTO.setUsedAmount(27300);
        DTO.setSalesAmount(95000);

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
        DTO.setRegistCount(32);
        DTO.setLeaveCount(0);
        DTO.setPayAmount(45100);
        DTO.setUsedAmount(27300);
        DTO.setSalesAmount(95000);

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
        DTO.setRegistCount(32);
        DTO.setLeaveCount(4);
        DTO.setPayAmount(0);
        DTO.setUsedAmount(27300);
        DTO.setSalesAmount(95000);

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
        DTO.setRegistCount(32);
        DTO.setLeaveCount(4);
        DTO.setPayAmount(45100);
        DTO.setUsedAmount(0);
        DTO.setSalesAmount(95000);

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
        DTO.setRegistCount(32);
        DTO.setLeaveCount(4);
        DTO.setPayAmount(45100);
        DTO.setUsedAmount(27300);
        DTO.setSalesAmount(0);

        //when
        Set<ConstraintViolation<PaymentData>> validate = validator.validate(DTO);

        //then
        validate.forEach(error -> {
            assertThat(error.getMessage()).isEqualTo("매출금액을 입력해주세요");
        });
    }

    @ParameterizedTest
    @MethodSource("testDto")
    @DisplayName("결제금액 조회 테스트")
    void selectPayment() {
        //given

        //when

        //then
        
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