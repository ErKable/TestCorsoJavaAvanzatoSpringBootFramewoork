package it.cgmconsulting.capriello.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
//classe che ci fa intercettare le eccezioni dalle chiamate
//effettuate dai vari controller
public class ExceptionManagment {
    @ExceptionHandler({ResourceNotFoundException.class})
    //l'annotazione punta all'eccezione che deve catturare
    public ResponseEntity<String> handleResourceNotFoundExcpetion(ResourceNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}