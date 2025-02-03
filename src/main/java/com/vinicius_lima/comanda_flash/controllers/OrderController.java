package com.vinicius_lima.comanda_flash.controllers;

import com.vinicius_lima.comanda_flash.dto.ClosedOrderDTO;
import com.vinicius_lima.comanda_flash.dto.CustomerDTO;
import com.vinicius_lima.comanda_flash.dto.CustomerInsertOrderDTO;
import com.vinicius_lima.comanda_flash.dto.CustomerOrderDTO;
import com.vinicius_lima.comanda_flash.services.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Map;

@Tag(name = "Orders", description = "Gerenciamento de Pedidos")
@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService service;

    @Operation(
            summary = "Lista paginada de pedidos",
            description = "Retorna uma página de pedidos com suporte a paginação e ordenação"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping
    public ResponseEntity<Page<CustomerOrderDTO>> findAllPaged(Pageable pageable) {
        Page<CustomerOrderDTO> orderList = service.findAllPaged(pageable);
        return ResponseEntity.ok().body(orderList);
    }

    @Operation(
            summary = "Busca pedido por ID",
            description = "Retorna os dados de um pedido específico"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CustomerOrderDTO> findById(@PathVariable Long id) {
        CustomerOrderDTO orderDTO = service.findById(id);

        return ResponseEntity.ok().body(orderDTO);
    }

    @Operation(
            summary = "Abre um novo pedido",
            description = "Cria um novo pedido para uma mesa específica"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pedido criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Mesa não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("/open")
    public ResponseEntity<CustomerInsertOrderDTO> openOrder(@RequestParam(value = "number", defaultValue = "0") Integer tableNumber, @Valid @RequestBody CustomerDTO dto) {
        CustomerInsertOrderDTO orderDTO = service.openOrder(tableNumber, dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(orderDTO.getIdOrder()).toUri();

        return ResponseEntity.created(uri).body(orderDTO);
    }

    @Operation(
            summary = "Adiciona produto ao pedido",
            description = "Adiciona um produto específico a um pedido existente"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto adicionado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Quantidade inválida"),
            @ApiResponse(responseCode = "404", description = "Pedido ou produto não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("/{orderId}/products")
    public ResponseEntity<CustomerOrderDTO> addProductToOrder(@PathVariable Long orderId, @RequestBody Map<String, Object> payload) {
        Long productId = ((Number) payload.get("productId")).longValue();
        int quantity = ((Number) payload.get("quantity")).intValue();
        CustomerOrderDTO updatedOrder = service.addProductToOrder(orderId, productId, quantity);
        return ResponseEntity.ok().body(updatedOrder);
    }

    @Operation(
            summary = "Fecha um pedido",
            description = "Finaliza um pedido existente e calcula o total"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido fechado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("/close/{id}")
    public ResponseEntity<ClosedOrderDTO> closeOrder(@PathVariable Long id) {
        ClosedOrderDTO closedOrderDTO = service.closeOrder(id);
        return ResponseEntity.ok(closedOrderDTO);
    }

    @Operation(
            summary = "Remove um pedido",
            description = "Exclui um pedido do sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Pedido excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Remove produto de um pedido",
            description = "Remove um produto específico de um pedido existente"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Produto removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pedido ou produto não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @DeleteMapping("/{orderId}/products/{productId}")
    public ResponseEntity<Void> deleteProductFromOrder(@PathVariable Long orderId, @PathVariable Long productId) {
        service.deleteProductFromOrder(orderId, productId);
        return ResponseEntity.noContent().build();
    }

}
