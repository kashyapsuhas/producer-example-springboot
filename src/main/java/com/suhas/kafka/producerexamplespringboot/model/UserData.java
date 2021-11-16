package com.suhas.kafka.producerexamplespringboot.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class UserData implements Serializable {
    private String customerName;
    private double amountDeposited;
    private double goldInGrams;
    private static final long serialVersionUID = 1L;

}


