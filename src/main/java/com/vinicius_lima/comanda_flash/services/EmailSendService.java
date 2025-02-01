package com.vinicius_lima.comanda_flash.services;

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

    public void insert(EmailSend emailSend) {
        repository.save(emailSend);
    }

    public List<EmailSend> findAll() {
        return repository.findAll();
    }

    public EmailSend findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Email não encontrado"));
    }

    public void delete(Long id) {
        if (repository.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Email não encontrado");
        }
        repository.deleteById(id);
    }

}
