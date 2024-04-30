package com.educandoweb.course.resources;

import com.educandoweb.course.entities.Order;
import com.educandoweb.course.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderResource {

    @Autowired
    private OrderService orderService;

    @GetMapping
  public ResponseEntity<List<Order>> findAll() {

        List<Order> orders = orderService.findAll();
        return ResponseEntity.ok(orders);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<Order> findById(@PathVariable int id) {
        Order obj = orderService.findById((long) id);
        return ResponseEntity.ok(obj);

    }



}
