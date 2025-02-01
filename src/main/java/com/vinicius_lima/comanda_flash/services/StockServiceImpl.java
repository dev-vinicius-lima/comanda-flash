package com.vinicius_lima.comanda_flash.services;

import com.vinicius_lima.comanda_flash.entities.EmailSend;
import com.vinicius_lima.comanda_flash.entities.Product;
import com.vinicius_lima.comanda_flash.repositories.EmailSendRepository;
import com.vinicius_lima.comanda_flash.repositories.ProductRepository;
import com.vinicius_lima.comanda_flash.services.exceptions.ResourceNotFoundException;
import com.vinicius_lima.comanda_flash.utils.InfoEmailSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.List;

@Service
@Transactional
public class StockServiceImpl implements StockService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private EmailSendRepository emailSendRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private TemplateEngine templateEngine;

    @Override
    public void subtractStock(Long productId, Integer quantity) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));

        if (product.getStock() < quantity) {
            throw new IllegalArgumentException("Quantidade insuficiente em estoque");
        }

        product.setStock(product.getStock() - quantity);

        if (product.getStock() < product.getLowStockThreshold()) {
            generateLowStockAlert(product);
        }

        productRepository.save(product);
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
        List<EmailSend> emailSends = emailSendRepository.findAll();
        for (EmailSend emailSend : emailSends) {
            Context context = new Context();
            context.setVariable("responsibleName", emailSend.getName());
            context.setVariable("productName", product.getName());
            context.setVariable("currentStock", product.getStock());
            context.setVariable("lowStockThreshold", product.getLowStockThreshold());

            String body = templateEngine.process("email_template.html", context);

            emailService.sendEmail(emailSend.getEmail(), "Estoque baixo - Comanda Flash", body);
        }
    }
}