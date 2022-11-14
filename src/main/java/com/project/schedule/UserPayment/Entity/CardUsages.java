package com.project.schedule.UserPayment.Entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    //@ManyToOne
    //private Merchants merchants;

    public CardUsages(){

    }

    public CardUsages(Long id, Date created_at, Category category, Long amount, Long merchant_id) {
        this.id = id;
        this.created_at = created_at;
        this.category = category;
        this.amount = amount;
        this.merchant_id = merchant_id;
    }

    public void cardUsages(Long amount){
        System.out.println("cardUsages : "+amount);
        if(this.amount - amount<0){
            throw new RuntimeException("foo");
        }

        this.amount = this.amount - amount;
    }
    

    
}
