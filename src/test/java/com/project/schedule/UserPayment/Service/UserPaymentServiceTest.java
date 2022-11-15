package com.project.schedule.UserPayment.Service;

import com.project.schedule.UserPayment.Entity.CardUsages;
import com.project.schedule.UserPayment.Entity.Category;
import com.project.schedule.UserPayment.Entity.Merchants;
import com.project.schedule.UserPayment.Repository.UserPaymentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserPaymentServiceTest {

    @Autowired
    private UserPaymentService userPaymentService;

    @Autowired
    private UserPaymentRepository userPaymentRepository;

    @BeforeEach
    public void before(){
        Date Date = null;
        CardUsages usage = new CardUsages(1L,Date,Category.PAY,10000L,1234L,new Merchants());
        userPaymentRepository.saveAndFlush(usage);
    }


    @AfterEach
    public void after(){
        userPaymentRepository.deleteAll();
    }


    private static Stream<CardUsages> dummyDto() {
        Date Date = null;
        Merchants asd = new Merchants();
        return Stream.of(CardUsages.builder()
                .id(1L)
                .amount(1L)
                        .created_at(Date)
                        .merchant_id(1234L)
                        .category(Category.PAY)
                        //.merchants(asd)
                .build());
    }

    @Test
    //@ParameterizedTest
    //@MethodSource("dummyDto")
    @DisplayName("비동기 성공 테스트")
    public void test() throws InterruptedException {
        int threadCont = 100;
        ExecutorService excutorService = Executors.newFixedThreadPool(32);
        CountDownLatch latch = new CountDownLatch(threadCont);
        Date Date = null;
        CardUsages dto = new CardUsages(1L,Date,Category.PAY,100L,1234L,new Merchants());

        for(int i=0; i<threadCont; i++){
            excutorService.submit(()->{
                try{
                    userPaymentService.useCard(dto);
                }finally {
                    latch.countDown();
                }
            });
        }
        latch.await();


        CardUsages DTO = userPaymentRepository.findById(1L).orElseThrow();
        assertEquals(0L, DTO.getAmount());
    }
}