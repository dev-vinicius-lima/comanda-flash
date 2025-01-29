package com.vinicius_lima.comanda_flash.services;

import com.vinicius_lima.comanda_flash.dto.CustomerDTO;
import com.vinicius_lima.comanda_flash.entities.Customer;
import com.vinicius_lima.comanda_flash.repositories.CustomerRepository;
import com.vinicius_lima.comanda_flash.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Transactional(readOnly = true)
    public Page<CustomerDTO> findAllPaged(Pageable pageable) {
        Page<Customer> list = customerRepository.findAll(pageable);
        return list.map(CustomerDTO::new);
    }

    @Transactional(readOnly = true)
    public CustomerDTO findById(Long id) {
        Optional<Customer> obj = customerRepository.findById(id);
        Customer entity = obj.orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado."));
        return new CustomerDTO(entity);
    }

    @Transactional
    public CustomerDTO create(CustomerDTO dto) {
        Customer customer = new Customer();
        customer.setName(dto.getName());
        verifyExistName(customer, customer.getName());
        customer = customerRepository.save(customer);
        return new CustomerDTO(customer);
    }

    @Transactional
    public CustomerDTO update(Long id, CustomerDTO dto) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado."));
        customer.setName(dto.getName());
        verifyExistName(customer, customer.getName());
        customer = customerRepository.save(customer);
        return new CustomerDTO(customer);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado.");
        }

        customerRepository.deleteById(id);
    }

    private void verifyExistName(Customer customer, String name) {
        if (name == null || name.isEmpty()) {
            customer.setName("Cliente sem nome");
            customer.setAnonymous(true);
        } else {
            customer.setName(name);
            customer.setAnonymous(false);
        }
    }


}