package com.vinicius_lima.comanda_flash.repositories;

import com.vinicius_lima.comanda_flash.entities.Category;
import com.vinicius_lima.comanda_flash.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
