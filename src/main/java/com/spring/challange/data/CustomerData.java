package com.spring.challange.data;

import com.spring.challange.entities.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerData {
    private int id;
    private String name;
    private int age;
    private List<Order> orders;

}
