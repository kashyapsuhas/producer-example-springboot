package com.suhas.kafka.producerexamplespringboot.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionData implements Serializable {

    private String customerName;
    private double amountDebited;
    private static final long serialVersionUID = 1L;

}


