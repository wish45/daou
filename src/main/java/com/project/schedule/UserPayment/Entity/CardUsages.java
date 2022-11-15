package com.project.schedule.UserPayment.Entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@Entity
@Builder
public class CardUsages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date created_at;

    @Enumerated(EnumType.STRING)
    private Category category;

    private Long amount;

    private Long merchant_id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Merchants merchants;

    public CardUsages(){

    }

    public CardUsages(Long id, Date created_at, Category category, Long amount, Long merchant_id, Merchants merchants) {
        this.id = id;
        this.created_at = created_at;
        this.category = category;
        this.amount = amount;
        this.merchant_id = merchant_id;
        this.merchants = merchants;
    }

    public void cardUsages(Long amount){
        System.out.println("cardUsages : "+amount);
        if(this.amount - amount<0){
            throw new IllegalStateException("사용가능한 금액을 초과하였습니다. ");
        }
        this.amount = this.amount - amount;
    }

    public Long getAmount(){
        return this.amount;
    }
    

    
}
