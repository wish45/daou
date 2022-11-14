package com.project.schedule.UserPayment.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@Entity
public class Merchants {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String businessId;

    private String taxType;

    private Long categoryId;

    //@OneToMany(mappedBy = "merchants")
    //private List<CardUsages> members = new ArrayList<CardUsages>();


}
