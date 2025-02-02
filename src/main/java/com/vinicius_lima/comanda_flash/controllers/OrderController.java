package com.vinicius_lima.comanda_flash.controllers;

import com.vinicius_lima.comanda_flash.dto.ClosedOrderDTO;
import com.vinicius_lima.comanda_flash.dto.CustomerDTO;
import com.vinicius_lima.comanda_flash.dto.CustomerInsertOrderDTO;
import com.vinicius_lima.comanda_flash.dto.CustomerOrderDTO;
import com.vinicius_lima.comanda_flash.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @GetMapping
    public ResponseEntity<Page<CustomerOrderDTO>> findAllPaged(Pageable pageable) {
        Page<CustomerOrderDTO> orderList = service.findAllPaged(pageable);
        return ResponseEntity.ok().body(orderList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerOrderDTO> findById(@PathVariable Long id) {
        CustomerOrderDTO orderDTO = service.findById(id);

        return ResponseEntity.ok().body(orderDTO);
    }

    @PostMapping("/open")
    public ResponseEntity<CustomerInsertOrderDTO> openOrder(@RequestParam(value = "number", defaultValue = "0") Integer tableNumber, @Valid @RequestBody CustomerDTO dto) {
        CustomerInsertOrderDTO orderDTO = service.openOrder(tableNumber, dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(orderDTO.getIdOrder()).toUri();

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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{orderId}/products/{productId}")
    public ResponseEntity<Void> deleteProductFromOrder(@PathVariable Long orderId, @PathVariable Long productId) {
        service.deleteProductFromOrder(orderId, productId);
        return ResponseEntity.noContent().build();
    }

}
