package com.spring.challange.repository;

import com.spring.challange.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface OrderDao extends JpaRepository<Order,Integer> {

    @Query("SELECT o FROM Order o WHERE o.createDate >= :startDate")
    List<Order> findByCreationDateAfter(@Param("startDate") Date startDate);

}
