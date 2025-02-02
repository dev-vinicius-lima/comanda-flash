package com.vinicius_lima.comanda_flash.dto;

import com.vinicius_lima.comanda_flash.entities.EmailSend;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class EmailSendDTO {

    @Email(message = "por favor, insira um email válido.")
    @NotEmpty(message = "por favor, insira um email.")
    private String email;
    @Size(min = 3, message = "por favor, insira um nome com no mínimo 3 caracteres.")
    @NotEmpty(message = "por favor, insira um nome.")
    private String name;

    public EmailSendDTO() {

    }

    public EmailSendDTO(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public EmailSendDTO(EmailSend entity) {
        email = entity.getEmail();
        name = entity.getName();
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
