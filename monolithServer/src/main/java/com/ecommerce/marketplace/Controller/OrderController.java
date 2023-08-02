package com.ecommerce.marketplace.Controller;

import com.ecommerce.marketplace.Service.Implementation.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("")
    public ResponseEntity<?> listAll() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

}
