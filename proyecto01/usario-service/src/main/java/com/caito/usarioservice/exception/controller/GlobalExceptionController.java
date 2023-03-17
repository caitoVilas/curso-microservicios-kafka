package com.caito.usarioservice.exception.controller;

import com.caito.usarioservice.exception.NotFoundException;
import com.caito.usarioservice.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionController {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponse> handlerNotFoundException(NotFoundException notFoundException){
        String mensaje = notFoundException.getMessage();
        ApiResponse response = ApiResponse.builder()
                .message(mensaje)
                .success(true)
                .status(HttpStatus.NOT_FOUND)
                .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
