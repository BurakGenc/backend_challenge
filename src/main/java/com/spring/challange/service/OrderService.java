package com.spring.challange.service;

import com.spring.challange.data.CustomerData;
import com.spring.challange.data.OrderData;
import com.spring.challange.entities.Order;

import java.util.Date;
import java.util.List;

public interface OrderService {
    List<OrderData> getAll();
     OrderData findById(int id);
    void add(OrderData orderData);
    void update(int id, OrderData orderData) ;
    void delete(int id) ;
     List<OrderData> getOrdersAfterDate(Date startDate);


}
