package com.vinicius_lima.comanda_flash.controllers;

import com.vinicius_lima.comanda_flash.dto.TableDTO;
import com.vinicius_lima.comanda_flash.services.TableService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tables")
public class TableController {

    @Autowired
    private TableService service;

    @GetMapping
    public ResponseEntity<List<TableDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/open")
    public ResponseEntity<List<TableDTO>> findAllOpenTables() {
        List<TableDTO> dtos = service.findAllOpenTables();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TableDTO> findById(@PathVariable Long id) {
        TableDTO tableDTO = service.findById(id);
        return ResponseEntity.ok(tableDTO);
    }

    @PostMapping
    public ResponseEntity<TableDTO> create(@Valid @RequestBody TableDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TableDTO> update(@Valid @PathVariable Long id, @RequestBody TableDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
