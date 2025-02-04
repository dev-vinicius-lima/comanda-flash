package com.vinicius_lima.comanda_flash.dto;

import com.vinicius_lima.comanda_flash.enums.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
}
