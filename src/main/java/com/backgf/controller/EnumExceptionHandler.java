package com.backgf.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EnumExceptionHandler {
    @ExceptionHandler(HttpMessageConversionException.class)
    public ResponseEntity<?> handleEnumConversionError(HttpMessageConversionException ex) {
        return ResponseEntity
            .badRequest()
            .body(Map.of(
                "message", "Valor inválido para o campo tipo. Os valores permitidos são: AÇÃO, FUNDO, TÍTULO.",
                "error", ex.getMessage()
            ));
    }
}
