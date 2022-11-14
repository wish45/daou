package com.project.schedule.UserPayment.Service;

import com.project.schedule.UserPayment.Entity.CardUsages;
import com.project.schedule.UserPayment.Repository.UserPaymentRepository;
import org.redisson.api.RLock;
import org.springframework.stereotype.Service;
import org.redisson.api.RedissonClient;

import javax.transaction.Transactional;
import java.util.concurrent.TimeUnit;

@Service
public class UserPaymentService {
    private RedissonClient redissonClient;
    private UserPaymentRepository userPaymentRepository;

    public UserPaymentService(RedissonClient redissonClient, UserPaymentRepository userPaymentRepository) {
        this.redissonClient = redissonClient;
        this.userPaymentRepository = userPaymentRepository;
    }

    public void useCard(CardUsages cardUsages){
        RLock lock = redissonClient.getLock(cardUsages.getId().toString());
        try{
            boolean available = lock.tryLock(5,1, TimeUnit.SECONDS);
            if(!available){
                System.out.println("lock 획득 실패");
                return;
            }
            this.cardUsages(cardUsages);


        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
            
    }

    @Transactional
    public void cardUsages(CardUsages cardUsages) {
        CardUsages dto = userPaymentRepository.findById(cardUsages.getId()).orElseThrow();
        dto.cardUsages(cardUsages.getAmount());
        userPaymentRepository.save(dto);
    }

}
