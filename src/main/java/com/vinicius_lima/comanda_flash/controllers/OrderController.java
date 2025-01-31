package com.vinicius_lima.comanda_flash.controllers;

import com.vinicius_lima.comanda_flash.dto.ClosedOrderDTO;
import com.vinicius_lima.comanda_flash.dto.CustomerDTO;
import com.vinicius_lima.comanda_flash.dto.CustomerOrderDTO;
import com.vinicius_lima.comanda_flash.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService service;

    @PostMapping("/open")
    public ResponseEntity<CustomerOrderDTO> openOrder(@RequestParam(value = "number") Integer tableNumber, @RequestBody CustomerDTO dto) {
        CustomerOrderDTO orderDTO = service.openOrder(tableNumber, dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(orderDTO.getId()).toUri();

        return ResponseEntity.created(uri).body(orderDTO);
    }

    @PostMapping("/{orderId}/products")
    public ResponseEntity<CustomerOrderDTO> addProductToOrder(@PathVariable Long orderId, @RequestBody Map<String, Object> payload) {
        Long productId = ((Number) payload.get("productId")).longValue();
        int quantity = ((Number) payload.get("quantity")).intValue();
        CustomerOrderDTO updatedOrder = service.addProductToOrder(orderId, productId, quantity);
        return ResponseEntity.ok().body(updatedOrder);
    }

    @PostMapping("/close/{id}")
    public ResponseEntity<ClosedOrderDTO> closeOrder(@PathVariable Long id) {
        ClosedOrderDTO closedOrderDTO = service.closeOrder(id);
        return ResponseEntity.ok(closedOrderDTO);
    }

}
