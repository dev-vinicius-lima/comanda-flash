package com.vinicius_lima.comanda_flash.services;

import com.vinicius_lima.comanda_flash.entities.Product;
import com.vinicius_lima.comanda_flash.repositories.ProductRepository;
import com.vinicius_lima.comanda_flash.services.exceptions.ResourceNotFoundException;
import com.vinicius_lima.comanda_flash.utils.InfoEmailSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StockServiceImpl implements StockService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private EmailService emailService;

    @Override
    public void subtractStock(Long productId, Integer quantity) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));

        if (product.getStock() < quantity) {
            throw new IllegalArgumentException("Quantidade insuficiente em estoque");
        }

        product.setStock(product.getStock() - quantity);

        if (product.getStock() < product.getLowStockThreshold()) {
            InfoEmailSend infoEmailSend = new InfoEmailSend("Maila Gomes", "mailagomes04@gmail.com", "Estoque baixo - Comanda Flash");
            generateLowStockAlert(product, infoEmailSend);
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

    private void generateLowStockAlert(Product product, InfoEmailSend infoEmailSend) {
        String message = String.format(
                "Prezado(a), " + infoEmailSend.getResponsibleName() + " \n\n" +
                        "Gostaríamos de informar que o estoque do produto " + product.getName() + " está abaixo do nível ideal.\n\n" +
                        "Detalhes do Produto:\n" +
                        "- Nome do Produto: " + product.getName() + "\n" +
                        "- Quantidade Atual em Estoque: " + product.getStock() + " unidades. \n" +
                        "- Nível de Estoque Ideal: " + product.getLowStockThreshold() + " unidades.\n\n" +
                        "Recomendamos que sejam tomadas as devidas providências para reabastecer o estoque o mais breve possível, a fim de evitar a interrupção nas vendas e garantir a satisfação dos nossos clientes.\n\n" +
                        "Caso tenha alguma dúvida ou precise de assistência, não hesite em nos contatar.\n\n" +
                        "Atenciosamente,\n" +
                        "Desenvolvedor Vinicius Lima\n" +
                        "cel: (92) 98505-1739 \n" +
                        "Comanda Flash. \n");
        emailService.sendEmail(infoEmailSend.getResponsibleEmail(), infoEmailSend.getTitleEmail(), message);

    }
}