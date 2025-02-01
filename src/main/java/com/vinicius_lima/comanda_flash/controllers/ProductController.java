package com.vinicius_lima.comanda_flash.controllers;

import com.vinicius_lima.comanda_flash.dto.ProductDTO;
import com.vinicius_lima.comanda_flash.dto.StockStatusDTO;
import com.vinicius_lima.comanda_flash.entities.Product;
import com.vinicius_lima.comanda_flash.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    public ResponseEntity<Page<ProductDTO>> findAllPaged(Pageable pageable) {
        Page<ProductDTO> list = service.findAllPaged(pageable);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
        ProductDTO dto = service.getProductById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}/stock-status")
    public ResponseEntity<StockStatusDTO> checkStockStatus(@PathVariable Long id) {
        boolean isLowStock = service.verifyLowStock(id);
        ProductDTO product = service.getProductById(id);

        StockStatusDTO dto = new StockStatusDTO(
                product.getStock(),
                product.getLowStockThreshold(),
                isLowStock
        );

        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> insert(@Valid @RequestBody ProductDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable Long id, @Valid @RequestBody ProductDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}/sale")
    public ResponseEntity<Void> processSale(@PathVariable Long id,
                                            @RequestParam Integer quantity) {
        service.processSale(id, quantity);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
