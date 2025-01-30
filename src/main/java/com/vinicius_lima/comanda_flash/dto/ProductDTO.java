package com.vinicius_lima.comanda_flash.dto;

import com.vinicius_lima.comanda_flash.entities.Category;
import com.vinicius_lima.comanda_flash.entities.Product;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ProductDTO {
    private Long id;
    @Size(min = 3, max = 60, message = "O nome deve ter entre 3 e 60 caracteres.")
    @NotBlank(message = "O nome do produto é obrigatório")
    private String name;

    @Positive(message = "O preço deve ser positivo.")
    @NotNull(message = "O preço do produto é obrigatório")
    private Double unitPrice;

    private String imgUrl;

    private String createdAt;
    private String updatedAt;

    @NotEmpty(message = "A categoria do produto é obrigatória")
    private List<CategoryDTO> categories = new ArrayList<>();

    public ProductDTO() {
    }

    public ProductDTO(Product product) {
        this.id = product.getId();
        name = product.getName();
        unitPrice = product.getUnitPrice();
        imgUrl = product.getImgUrl();
        createdAt = formatDate(product.getCreatedAt());
        updatedAt = formatDate(product.getUpdatedAt());

        if (product.getCategories() != null) {
            product.getCategories().forEach(cat -> this.categories.add(new CategoryDTO(cat)));
        }
    }

    public ProductDTO(Product product, Set<Category> categories) {
        this(product);
        categories.forEach(cat -> this.categories.add(new CategoryDTO(cat)));
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    private String formatDate(LocalDateTime dateTime) {
        if (dateTime != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss");
            return dateTime.format(formatter);
        }
        return null;
    }

    public List<CategoryDTO> getCategories() {
        return categories;
    }

    public String getCreatedAt() {
        return createdAt;
    }


    public String getUpdatedAt() {
        return updatedAt;
    }


}