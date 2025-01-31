package com.vinicius_lima.comanda_flash.services;

import com.vinicius_lima.comanda_flash.dto.CustomerOrderDTO;
import com.vinicius_lima.comanda_flash.dto.TableDTO;
import com.vinicius_lima.comanda_flash.entities.CustomerOrder;
import com.vinicius_lima.comanda_flash.entities.OrderItem;
import com.vinicius_lima.comanda_flash.entities.Table;
import com.vinicius_lima.comanda_flash.repositories.TableRepository;
import com.vinicius_lima.comanda_flash.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TableService {
    @Autowired
    private TableRepository tableRepository;

    public TableDTO insert(TableDTO dto) {
        Table entity = new Table();
        entity.setNumber(dto.getNumber());
        entity.setStatus(dto.getStatus().toString());
        entity = tableRepository.save(entity);
        return new TableDTO(entity);
    }

    public List<TableDTO> findAll() {
        List<Table> entityList = tableRepository.findAll();
        return entityList.stream().map(TableDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public List<TableDTO> findAllOpenTables() {
        return tableRepository.findByHasOpenOrders().stream().map(TableDTO::new).collect(Collectors.toList());
    }

    public TableDTO findById(Long id) {
        Table entity = findTableById(id);
        TableDTO dto = new TableDTO(entity);

        return dto;
    }


    public TableDTO update(Long id, TableDTO dto) {
        try {
            Table entity = findTableById(id);
            entity.setNumber(dto.getNumber());
            entity.setStatus((dto.getStatus().toString()));
            entity = tableRepository.save(entity);
            return new TableDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found => " + id);
        }
    }

    public void delete(Long id) {
        tableRepository.deleteById(id);
    }

    private Table findTableById(Long id) {
        return tableRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id not found => " + id));
    }

}