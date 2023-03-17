package com.caito.calificacionservice.exceptions.controller;

import com.caito.calificacionservice.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionController {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String, Object>> notFoundException(NotFoundException notFoundException){
        Map<String, Object> map = new HashMap();
        map.put("message", notFoundException.getMessage());
        map.put("success", false);
        map.put("status", HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
    }
}
