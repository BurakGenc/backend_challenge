package com.spring.challange.webApi.controllers;

import com.spring.challange.data.CustomerData;
import com.spring.challange.response.ApiResponse;
import com.spring.challange.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomersController {
    @Autowired
    CustomerService customerService;


    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse> getAll() {
        try {
            List<CustomerData> customers = customerService.getAll();
            ApiResponse apiResponse = new ApiResponse(true, "List of customers retrieved successfully", customers);
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        } catch (RuntimeException e) {
            ApiResponse apiResponse =new ApiResponse(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(apiResponse);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getCustomerById(@PathVariable Integer id) {
        try {
            CustomerData customer = customerService.findById(id);
            ApiResponse response = new ApiResponse(true, "Customer found", customer);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (RuntimeException e) {
            ApiResponse response = new ApiResponse(false, "Failed to find customer with id " + id, null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> add(@RequestBody CustomerData customerData) {
        try {
            customerService.add(customerData);
            ApiResponse apiResponse = new ApiResponse(true, "Customer added successfully", customerData);
            return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
        } catch (RuntimeException e) {
            ApiResponse apiResponse =new ApiResponse(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(apiResponse);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable int id, @RequestBody CustomerData customerData) {
        try {
            customerService.update(id, customerData);
            ApiResponse apiResponse = new ApiResponse(true, "Customer updated successfully", null);
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        } catch (RuntimeException e) {
            ApiResponse apiResponse =new ApiResponse(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(apiResponse);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable int id) {
        try {
            customerService.delete(id);
            ApiResponse apiResponse = new ApiResponse(true, "Customer deleted successfully", null);
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        } catch (RuntimeException e) {
            ApiResponse apiResponse =new ApiResponse(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(apiResponse);
        }
    }

    @GetMapping("/noOrders")
    public ResponseEntity<ApiResponse> getCustomersWithNoOrders() {
        try {
            List<CustomerData> customers = customerService.findCustomersWithNoOrders();
            ApiResponse response = new ApiResponse(true, "List of customers with no orders retrieved successfully", customers);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (RuntimeException e) {
            ApiResponse response =new ApiResponse(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(response);
        }
    }
    @GetMapping("/findByName")
    public ResponseEntity<ApiResponse> findCustomersByName(@RequestParam("name") String name) {
        try {
            List<CustomerData> customers = customerService.findByCustomerNameContaining(name);
            ApiResponse response = new ApiResponse(true, "Customers found successfully", customers);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (RuntimeException e) {
            ApiResponse response = new ApiResponse(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
