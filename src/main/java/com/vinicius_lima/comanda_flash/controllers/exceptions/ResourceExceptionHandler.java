package com.vinicius_lima.comanda_flash.controllers.exceptions;

import com.vinicius_lima.comanda_flash.enums.StatusText;
import com.vinicius_lima.comanda_flash.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.Arrays;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {

        ValidationError error = new ValidationError();
        error.setTimestamp(Instant.now());
        error.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
        error.setError("Validation Exception");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());
        for (FieldError f : e.getBindingResult().getFieldErrors()) {
            error.addError(f.getField(), f.getDefaultMessage());
        }

        return ResponseEntity.status(error.getStatus()).body(error);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ValidationError> enumExeption(HttpMessageNotReadableException e, HttpServletRequest request) {
        ValidationError error = new ValidationError();
        error.setTimestamp(Instant.now());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setError("Enum Exception");
        error.setMessage("Status inválido, envie somente os status que estão a seguir = " + getValidEnumValues());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(error.getStatus()).body(error);
    }

    private String getValidEnumValues() {
        return Arrays.toString(StatusText.values());
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ValidationError> notFound(ResourceNotFoundException e, HttpServletRequest request) {

        ValidationError error = new ValidationError();
        error.setTimestamp(Instant.now());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setError("Not Found");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());

        return ResponseEntity.status(error.getStatus()).body(error);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ValidationError> dataIntegrityViolation(DataIntegrityViolationException e, HttpServletRequest request) {
        ValidationError error = new ValidationError();
        error.setTimestamp(Instant.now());
        error.setStatus(HttpStatus.CONFLICT.value());
        error.setError("Conflict");
        if (e.getMessage().contains("Unique index")) {
            error.setMessage("Não é possível criar a mesa. Uma mesa com os mesmos dados já existe. " + "Por favor, verifique os dados e tente novamente.");
        } else {
            error.setMessage("Não é possível excluir o cliente. O cliente possui pedidos associados. " + "Por favor, remova os pedidos antes de tentar excluir o cliente.");
        }
        error.setPath(request.getRequestURI());

        return ResponseEntity.status(error.getStatus()).body(error);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(PropertyReferenceException.class)
    public ResponseEntity<ValidationError> handlePropertyReference(PropertyReferenceException e, HttpServletRequest request) {
        ValidationError error = new ValidationError();
        error.setTimestamp(Instant.now());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setError("Bad Request");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());

        return ResponseEntity.status(error.getStatus()).body(error);
    }

}
