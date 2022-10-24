package com.project.schedule.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payment_data")
public class PaymentData {
    @Id
    @Column(name = "reg_date")
    private String regDate;

    @Column(name = "regist_count")
    private String registCount;

    @Column(name = "leave_count")
    private String leaveCount;

    @Column(name = "pay_amount")
    private String payAmount;

    @Column(name = "used_amount")
    private String usedAmount;

    @Column(name = "sales_amount")
    private String salesAmount;


}
