package com.vinicius_lima.comanda_flash.services;

import com.vinicius_lima.comanda_flash.entities.Product;
import com.vinicius_lima.comanda_flash.repositories.ProductRepository;
import com.vinicius_lima.comanda_flash.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StockServiceImpl implements StockService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void subtractStock(Long productId, Integer quantity) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));

        if (product.getStock() < quantity) {
            throw new IllegalArgumentException("Quantidade insuficiente em estoque");
        }

        product.setStock(product.getStock() - quantity);
        productRepository.save(product);

        if (checkLowStock(productId)) {
            generateLowStockAlert(product);
        }
    }

    @Override
    public void addStock(Long productId, Integer quantity) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));

        product.setStock(product.getStock() + quantity);
        productRepository.save(product);
    }

    @Override
    public boolean checkLowStock(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));

        return product.getStock() <= product.getLowStockThreshold();
    }

    private void generateLowStockAlert(Product product) {
        String message = String.format("ALERTA: Estoque baixo para produto %s. Quantidade atual: %d", product.getName(), product.getStock());

        // Aqui você pode implementar diferentes formas de notificação:
        // - Email
        // - Mensagem em fila (RabbitMQ, Kafka)
        // - Log com nível ALERT
        System.out.println(message);

        // Exemplo usando Spring Boot Mail
        // mailSender.send(new SimpleMailMessage() {{
        //     setSubject("Alerta de Estoque Baixo");
        //     setText(message);
        //     setTo("estoque@empresa.com");
        // }});
    }
}