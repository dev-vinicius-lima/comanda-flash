package com.vinicius_lima.comanda_flash.controllers;

import com.vinicius_lima.comanda_flash.dto.ProductDTO;
import com.vinicius_lima.comanda_flash.dto.StockStatusDTO;
import com.vinicius_lima.comanda_flash.entities.Product;
import com.vinicius_lima.comanda_flash.services.ProductService;
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

@Tag(name = "Products", description = "Gerenciamento de Produtos")
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @Operation(
            summary = "Lista paginada de produtos",
            description = "Retorna uma página de produtos com suporte a paginação e ordenação"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping()
    public ResponseEntity<Page<ProductDTO>> findAllPaged(Pageable pageable) {
        Page<ProductDTO> list = service.findAllPaged(pageable);
        return ResponseEntity.ok(list);
    }


    @Operation(
            summary = "Busca produto por ID",
            description = "Retorna os dados de um produto específico"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
        ProductDTO dto = service.getProductById(id);
        return ResponseEntity.ok(dto);
    }

    @Operation(
            summary = "Verifica status de estoque do produto",
            description = "Retorna informações sobre o nível de estoque do produto"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/{id}/stock-status")
    public ResponseEntity<StockStatusDTO> checkStockStatus(@PathVariable Long id) {
        boolean isLowStock = service.verifyLowStock(id);
        ProductDTO product = service.getProductById(id);

        StockStatusDTO dto = new StockStatusDTO(
                product.getStock(),
                product.getLowStockThreshold(),
                isLowStock
        );

        return ResponseEntity.ok(dto);
    }


    @Operation(
            summary = "Cria um novo produto",
            description = "Adiciona um novo produto ao sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Produto criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping
    public ResponseEntity<ProductDTO> insert(@Valid @RequestBody ProductDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @Operation(
            summary = "Atualiza um produto existente",
            description = "Altera os dados de um produto específico"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable Long id, @Valid @RequestBody ProductDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @Operation(
            summary = "Processa uma venda do produto",
            description = "Atualiza o estoque do produto devido a uma venda"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Venda processada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Quantidade inválida"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PutMapping("/{id}/sale")
    public ResponseEntity<Void> processSale(@PathVariable Long id,
                                            @RequestParam Integer quantity) {
        service.processSale(id, quantity);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Remove um produto",
            description = "Exclui um produto do sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Produto excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
