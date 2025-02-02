package com.vinicius_lima.comanda_flash.controllers;

import com.vinicius_lima.comanda_flash.dto.EmailSendDTO;
import com.vinicius_lima.comanda_flash.services.EmailSendService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/emails")
public class EmailSendController {
    @Autowired
    private EmailSendService service;

    @Transactional(readOnly = true)
    @GetMapping
    public ResponseEntity<List<EmailSendDTO>> findAll() {
        List<EmailSendDTO> emails = service.findAll();
        return ResponseEntity.ok().body(emails);
    }

    @Transactional(readOnly = true)
    @GetMapping("/{id}")
    public ResponseEntity<EmailSendDTO> findById(@PathVariable Long id) {
        EmailSendDTO emailSend = service.findById(id);
        return ResponseEntity.ok(emailSend);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<EmailSendDTO> insert(@Valid @RequestBody EmailSendDTO emailSend) {
        EmailSendDTO email = new EmailSendDTO(emailSend.getEmail(), emailSend.getName());
        service.insert(email);
        return ResponseEntity.created(URI.create("/")).body(emailSend);
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<EmailSendDTO> update(@PathVariable Long id, @Valid @RequestBody EmailSendDTO emailSend) {
        EmailSendDTO result = service.update(id, emailSend);
        return ResponseEntity.ok().body(result);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
