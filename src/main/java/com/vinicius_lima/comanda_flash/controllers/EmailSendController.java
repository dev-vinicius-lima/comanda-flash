package com.vinicius_lima.comanda_flash.controllers;

import com.vinicius_lima.comanda_flash.dto.EmailSendDTO;
import com.vinicius_lima.comanda_flash.services.EmailSendService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Tag(name = "Emails", description = "Gerenciamento de Emails")
@RestController
@RequestMapping("/emails")
public class EmailSendController {
    @Autowired
    private EmailSendService service;

    @Operation(
            summary = "Lista todos os emails cadastrados",
            description = "Retorna uma lista com todos os emails registrados no sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping
    public ResponseEntity<List<EmailSendDTO>> findAll() {
        List<EmailSendDTO> emails = service.findAll();
        return ResponseEntity.ok().body(emails);
    }


    @Operation(
            summary = "Busca email por ID",
            description = "Retorna os dados de um email específico"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "404", description = "Email não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EmailSendDTO> findById(@PathVariable Long id) {
        EmailSendDTO emailSend = service.findById(id);
        return ResponseEntity.ok(emailSend);
    }

    @Operation(
            summary = "Cadastra um novo email",
            description = "Adiciona um novo email ao sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Email cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping
    public ResponseEntity<EmailSendDTO> insert(@Valid @RequestBody EmailSendDTO emailSend) {
        EmailSendDTO email = new EmailSendDTO(emailSend.getEmail(), emailSend.getName());
        service.insert(email);
        return ResponseEntity.created(URI.create("/")).body(emailSend);
    }

    @Operation(
            summary = "Atualiza um email existente",
            description = "Altera os dados de um email específico"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Email não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PutMapping("/{id}")
    public ResponseEntity<EmailSendDTO> update(@Parameter(description = "ID do email para atualização") @PathVariable Long id, @Valid @RequestBody EmailSendDTO emailSend) {
        EmailSendDTO result = service.update(id, emailSend);
        return ResponseEntity.ok().body(result);
    }

    @Operation(
            summary = "Remove um email",
            description = "Exclui um email do sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Email excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Email não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@Parameter(description = "ID do email para exclusão")
                                       @PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
