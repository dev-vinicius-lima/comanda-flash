package com.vinicius_lima.comanda_flash.services;

import com.vinicius_lima.comanda_flash.dto.EmailSendDTO;
import com.vinicius_lima.comanda_flash.entities.EmailSend;
import com.vinicius_lima.comanda_flash.repositories.EmailSendRepository;
import com.vinicius_lima.comanda_flash.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailSendService {

    @Autowired
    private EmailSendRepository repository;

    public void insert(EmailSendDTO dto) {
        EmailSend emailSend = new EmailSend(dto.getEmail(), dto.getName());
        repository.save(emailSend);
    }

    public List<EmailSendDTO> findAll() {
        List<EmailSend> emails = repository.findAll();
        return emails.stream().map(EmailSendDTO::new).toList();
    }

    public EmailSendDTO findById(Long id) {
        EmailSend emailSend = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Email não encontrado"));
        return new EmailSendDTO(emailSend);
    }

    public void delete(Long id) {
        if (repository.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Email não encontrado");
        }
        repository.deleteById(id);
    }

    public EmailSendDTO update(Long id, EmailSendDTO emailSend) {
        EmailSend entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Email não encontrado"));
        entity.setEmail(emailSend.getEmail());
        entity.setName(emailSend.getName());
        return new EmailSendDTO(repository.save(entity));
    }
}
