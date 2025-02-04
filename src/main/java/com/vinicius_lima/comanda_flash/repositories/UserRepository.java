package com.vinicius_lima.comanda_flash.repositories;


import com.vinicius_lima.comanda_flash.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, String> {
    UserDetails findByLogin(String login);
}

