package com.spring.challange.service.impl;

import com.spring.challange.data.CustomerData;
import com.spring.challange.data.OrderData;
import com.spring.challange.entities.Customer;
import com.spring.challange.entities.Order;
import com.spring.challange.repository.OrderDao;
import com.spring.challange.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    OrderDao orderDao;

    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public List<OrderData> getAll() {
        List<OrderData> orders = new ArrayList<>();
        List<Order> orderList = orderDao.findAll();

        if (orderList.isEmpty()) {
            throw new RuntimeException("No orders found");
        }

        for (Order order : orderList) {

            OrderData orderData = new OrderData(order.getId(), order.getCreateDate(), order.getTotalPrice(),order.getCustomer());
            orders.add(orderData);
        }

        return orders;
    }

    public OrderData findById(int id) {
        Optional<Order> optionalOrder = orderDao.findById(id);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            OrderData orderData = new OrderData(order.getId(), order.getCreateDate(), order.getTotalPrice(), order.getCustomer());
            return orderData;
        } else {
            throw new RuntimeException("Order not found with id: " + id);
        }
    }

    @Override
    public void add(OrderData orderData) {
        if (orderData == null) {
            throw new RuntimeException("Order data cannot be null");
        }

        Order order = new Order();
        order.setCreateDate(orderData.getCreateDate());
        order.setCustomer(orderData.getCustomer());
        order.setTotalPrice(orderData.getTotalPrice());
        this.orderDao.save(order);
    }

    @Override
    public void update(int id, OrderData orderData) {
        if (orderData == null) {
            throw new RuntimeException("Order data cannot be null");
        }
        Optional<Order> optionalOrder = orderDao.findById(id);
        if (!optionalOrder.isPresent()) {
            throw new RuntimeException("Customer not found with id: " + id);
        }
            Order order = optionalOrder.get();
            order.setCreateDate(orderData.getCreateDate());
            order.setCustomer(orderData.getCustomer());
            order.setTotalPrice(orderData.getTotalPrice());
            orderDao.save(order);

    }

    @Override
    public void delete(int id) {
        Optional<Order> optionalOrder = orderDao.findById(id);
        if (!optionalOrder.isPresent()) {
            throw new RuntimeException("Order not found with id: " + id);
        }
        orderDao.delete(optionalOrder.get());
    }

    public List<OrderData> getOrdersAfterDate(Date startDate) {
        if (startDate == null) {
            throw new RuntimeException("Start date cannot be null");
        }
        List<Order> orders = orderDao.findByCreationDateAfter(startDate);
        if (orders == null || orders.isEmpty()) {
            throw new RuntimeException("No orders found after the given date");
        }
        List<OrderData> orderDataList = new ArrayList<>();
        for (Order order : orders) {
            OrderData orderData = new OrderData();
            orderData.setId(order.getId());
            orderData.setCreateDate(order.getCreateDate());
            orderData.setCustomer(order.getCustomer());
            orderDataList.add(orderData);
        }
        return orderDataList;
    }
}
