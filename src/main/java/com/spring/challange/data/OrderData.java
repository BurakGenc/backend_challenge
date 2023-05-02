package com.spring.challange.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spring.challange.entities.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderData {

    private int id;

    private Date createDate;

    private BigDecimal totalPrice;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "orders"})
    private Customer customer;
}
