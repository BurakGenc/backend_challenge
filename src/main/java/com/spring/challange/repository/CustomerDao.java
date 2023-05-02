package com.spring.challange.repository;

import com.spring.challange.entities.Customer;
import com.spring.challange.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerDao extends JpaRepository<Customer,Integer> {

    @Query("SELECT c FROM Customer c WHERE c.orders IS EMPTY")
    List<Customer> findCustomersWithNoOrders();
    @Query("SELECT c FROM Customer c WHERE c.name LIKE %:keyword%")
    List<Customer> findCustomersByNameContaining(@Param("keyword") String keyword);


}
