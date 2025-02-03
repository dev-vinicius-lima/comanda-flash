package com.vinicius_lima.comanda_flash.controllers;

import com.vinicius_lima.comanda_flash.dto.TableDTO;
import com.vinicius_lima.comanda_flash.services.TableService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Tag(name = "Tables", description = "Gerenciamento de Mesas")
@RestController
@RequestMapping("/tables")
public class TableController {

    @Autowired
    private TableService service;

    @Operation(summary = "Lista todas as mesas cadastradas", description = "Retorna uma lista com todas as mesas do sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping
    public ResponseEntity<List<TableDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @Operation(summary = "Lista apenas as mesas abertas", description = "Retorna uma lista com todas as mesas que estão em uso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/open")
    public ResponseEntity<List<TableDTO>> findAllOpenTables() {
        List<TableDTO> dtos = service.findAllOpenTables();
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Busca mesa por ID", description = "Retorna os dados de uma mesa específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "404", description = "Mesa não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TableDTO> findById(@Parameter(description = "ID da mesa para busca") @PathVariable Long id) {
        TableDTO tableDTO = service.findById(id);
        return ResponseEntity.ok(tableDTO);
    }


    @Operation(summary = "Cria uma nova mesa", description = "Adiciona uma nova mesa ao sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Mesa criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping
    public ResponseEntity<TableDTO> create(@Valid @RequestBody TableDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @Operation(summary = "Atualiza uma mesa existente", description = "Altera os dados de uma mesa específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Mesa não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PutMapping("/{id}")
    public ResponseEntity<TableDTO> update(@Parameter(description = "ID da mesa para atualização") @Valid @PathVariable Long id, @RequestBody TableDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Remove uma mesa", description = "Exclui uma mesa do sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Mesa excluída com sucesso"),
            @ApiResponse(responseCode = "404", description = "Mesa não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@Parameter(description = "ID da mesa para exclusão") @PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
