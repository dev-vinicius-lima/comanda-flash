package com.vinicius_lima.comanda_flash.services;

import com.vinicius_lima.comanda_flash.dto.TableDTO;
import com.vinicius_lima.comanda_flash.entities.Table;
import com.vinicius_lima.comanda_flash.repositories.TableRepository;
import com.vinicius_lima.comanda_flash.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TableService {
    @Autowired
    private TableRepository tableRepository;

    public TableDTO insert(TableDTO dto) {
        Table entity = new Table();
        entity.setNumber(dto.getNumber());
        entity.setStatus(dto.getStatus());
        entity = tableRepository.save(entity);
        return new TableDTO(entity);
    }

    public List<TableDTO> findAll() {
        List<Table> entityList = tableRepository.findAll();
        return entityList.stream().map(TableDTO::new).toList();
    }

    public Table findById(Long id) {
        return tableRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Table not found"));
    }

    public TableDTO update(Long id, TableDTO dto) {
        try {
            Table entity = findById(id);
            entity.setNumber(dto.getNumber());
            entity.setStatus(dto.getStatus());
            entity = tableRepository.save(entity);
            return new TableDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found => " + id);
        }
    }

    public void delete(Long id) {
        tableRepository.deleteById(id);
    }
}