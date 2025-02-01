package com.vinicius_lima.comanda_flash.services;

import com.vinicius_lima.comanda_flash.dto.CategoryDTO;
import com.vinicius_lima.comanda_flash.dto.ProductDTO;
import com.vinicius_lima.comanda_flash.entities.Category;
import com.vinicius_lima.comanda_flash.entities.Product;
import com.vinicius_lima.comanda_flash.repositories.CategoryRepository;
import com.vinicius_lima.comanda_flash.repositories.ProductRepository;
import com.vinicius_lima.comanda_flash.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private StockServiceImpl stockService;

    @Transactional
    public ProductDTO insert(ProductDTO dto) {
        Product entity = new Product();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new ProductDTO(entity);
    }


    @Transactional(readOnly = true)
    public Page<ProductDTO> findAllPaged(Pageable pageable) {
        Page<Product> list = repository.findAll(pageable);
        return list.map(ProductDTO::new);
    }

    @Transactional(readOnly = true)
    public ProductDTO getProductById(Long id) {
        Product entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
        return new ProductDTO(entity);
    }

    public void deleteProduct(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO dto) {
        try {
            Product entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new ProductDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found =>" + id);
        }
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Id not found => " + id);
        }
        repository.deleteById(id);
    }

    private void copyDtoToEntity(ProductDTO dto, Product entity) {
        entity.setName(dto.getName());
        entity.setUnitPrice(dto.getUnitPrice());
        entity.setImgUrl(dto.getImgUrl());

        entity.getCategories().clear();
        for (CategoryDTO catDTO : dto.getCategories()) {
            Category category = categoryRepository.getReferenceById(catDTO.getId());
            entity.getCategories().add(category);
        }
    }

    @Transactional
    public void processSale(Long productId, Integer quantity) {
        ProductDTO product = getProductById(productId);
        stockService.subtractStock(productId, quantity);
    }

    public boolean verifyLowStock(Long id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));


        return Optional.ofNullable(product.getLowStockThreshold())
                .map(threshold -> product.getStock() < threshold)
                .orElse(false);
    }

    public void restock(Long productId, Integer quantity) {
        stockService.addStock(productId, quantity);
    }
}