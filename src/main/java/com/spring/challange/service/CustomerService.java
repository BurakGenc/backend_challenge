package com.spring.challange.service;

import com.spring.challange.data.CustomerData;
import com.spring.challange.data.OrderData;

import java.util.List;

public interface CustomerService {

    List<CustomerData> getAll();
    CustomerData findById(int id);
     void add(CustomerData customerData);
     void update(int id, CustomerData customerData) ;
     void delete(int id) ;

    List<CustomerData> findCustomersWithNoOrders();

    List<CustomerData> findByCustomerNameContaining(String keyword);
}
