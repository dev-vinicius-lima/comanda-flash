package com.vinicius_lima.comanda_flash.repositories;

import com.vinicius_lima.comanda_flash.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
