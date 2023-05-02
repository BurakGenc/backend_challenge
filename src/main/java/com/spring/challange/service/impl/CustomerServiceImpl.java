package com.spring.challange.service.impl;

import com.spring.challange.data.CustomerData;
import com.spring.challange.data.OrderData;
import com.spring.challange.entities.Customer;
import com.spring.challange.entities.Order;
import com.spring.challange.repository.CustomerDao;
import com.spring.challange.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    CustomerDao customerDao;

    public CustomerServiceImpl(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public List<CustomerData> getAll() {
        List<CustomerData> customers = new ArrayList<>();
        List<Customer> customerList = customerDao.findAll();
        if (customerList.isEmpty()) {
            throw new RuntimeException("Customer list could not be fetched.");
        }
        for (Customer customer : customerList) {
            CustomerData customerData = new CustomerData(customer.getId(), customer.getName(), customer.getAge(), customer.getOrders());
            customers.add(customerData);
        }
        return customers;
    }

    @Override
    public CustomerData findById(int id) {
        Optional<Customer> optionalCustomer = customerDao.findById(id);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            CustomerData customerData = new CustomerData(customer.getId(), customer.getName(), customer.getAge(), customer.getOrders());
            return customerData;
        } else {
            throw new RuntimeException("Order not found with id: " + id);
        }
    }

    @Override
    public void add(CustomerData customerData) {
        if (customerData == null) {
            throw new RuntimeException("Customer data cannot be null.");
        }
        Customer customer = new Customer();
        customer.setName(customerData.getName());
        customer.setAge(customerData.getAge());
        this.customerDao.save(customer);
    }

    @Override
    public void update(int id, CustomerData customerData) {
        if (customerData == null) {
            throw new IllegalArgumentException("Customer data cannot be null.");
        }
        Optional<Customer> optionalCustomer = customerDao.findById(id);
        if (!optionalCustomer.isPresent()) {
            throw new RuntimeException("Customer not found with id: " + id);
        }
        Customer customer = optionalCustomer.get();
        customer.setName(customerData.getName());
        customer.setAge(customerData.getAge());
        customerDao.save(customer);
    }

    @Override
    public void delete(int id) {
        Optional<Customer> optionalCustomer = customerDao.findById(id);
        if (!optionalCustomer.isPresent()) {
            throw new RuntimeException("Customer not found with id: " + id);
        }
        customerDao.delete(optionalCustomer.get());
    }

    @Override
    public List<CustomerData> findCustomersWithNoOrders() {
        List<CustomerData> customers = new ArrayList<>();
        List<Customer> customerList = customerDao.findCustomersWithNoOrders();
        if (customerList.isEmpty()) {
            throw new RuntimeException("Customer list with no orders could not be fetched.");
        }
        for (Customer customer : customerList) {
            CustomerData customerData = new CustomerData(customer.getId(), customer.getName(), customer.getAge(), customer.getOrders());
            customers.add(customerData);
        }
        return customers;
    }

    @Override
    public List<CustomerData> findByCustomerNameContaining(String keyword) {
        List<Customer> allCustomers = customerDao.findCustomersByNameContaining(keyword);
        if (allCustomers == null || allCustomers.isEmpty()) {
            throw new RuntimeException("No customers found with the given keyword");
        }
        List<CustomerData> filteredCustomers = new ArrayList<>();

        for (Customer customer : allCustomers) {
            CustomerData customerData = new CustomerData();
            customerData.setId(customer.getId());
            customerData.setName(customer.getName());
            customerData.setAge(customer.getAge());
            customerData.setOrders(customer.getOrders());
            filteredCustomers.add(customerData);
        }

        return filteredCustomers;
    }



}
