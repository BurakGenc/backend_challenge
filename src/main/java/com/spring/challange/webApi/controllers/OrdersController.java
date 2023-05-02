package com.spring.challange.webApi.controllers;

import com.spring.challange.data.OrderData;
import com.spring.challange.response.ApiResponse;
import com.spring.challange.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrdersController {
    OrderService orderService;

    @Autowired
    public OrdersController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse> getAllOrders() {
        try {
            List<OrderData> orders = orderService.getAll();
            ApiResponse apiResponse = new ApiResponse(true, "Orders retrieved successfully", orders);
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        } catch (RuntimeException e) {
            ApiResponse apiResponse = new ApiResponse(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getOrderById(@PathVariable Integer id) {
        try {
            OrderData order = orderService.findById(id);
            ApiResponse response = new ApiResponse(true, "Order found", order);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (RuntimeException e) {
            ApiResponse response = new ApiResponse(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> add(@RequestBody OrderData order) {
        try {
            orderService.add(order);
            ApiResponse response = new ApiResponse(true, "Order added successfully", null);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            ApiResponse response = new ApiResponse(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("update/{id}")
    public ResponseEntity<ApiResponse> updateOrder(@PathVariable Integer id, @RequestBody OrderData orderData) {
        try {
            orderService.update(id, orderData);
            ApiResponse apiResponse = new ApiResponse(true, "Order updated successfully", orderData);
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        } catch (RuntimeException e) {
            ApiResponse apiResponse = new ApiResponse(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<ApiResponse> deleteOrder(@PathVariable Integer id) {
        try {
            orderService.delete(id);
            ApiResponse response = new ApiResponse(true, "Order deleted successfully", null);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        } catch (RuntimeException e) {
            ApiResponse response = new ApiResponse(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/afterDate")
    public ResponseEntity<ApiResponse> getOrdersAfterDate(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate) {
        try {
            List<OrderData> orders = orderService.getOrdersAfterDate(startDate);
            ApiResponse response = new ApiResponse(true, "Orders found", orders);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (RuntimeException e) {
            ApiResponse response = new ApiResponse(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
