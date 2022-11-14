package com.project.schedule.UserPayment.dto;

import com.project.schedule.UserPayment.Entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class UserPaymentDto {

    private Long id;

    private Date created_at;

    private Category category;

    private Long amount;

    private Long merchant_id;


   public void cardUsages(Long amount){
       if(this.amount - amount<0){
           throw new RuntimeException("foo");
       }

       this.amount = this.amount - amount;
   }
}
