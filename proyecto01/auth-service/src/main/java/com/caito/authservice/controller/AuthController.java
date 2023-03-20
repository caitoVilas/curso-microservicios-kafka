package com.caito.authservice.controller;

import com.caito.authservice.dto.AuthUserDTO;
import com.caito.authservice.dto.NewUserDTO;
import com.caito.authservice.dto.RequestDTO;
import com.caito.authservice.dto.TokenDTO;
import com.caito.authservice.entity.AuthUser;
import com.caito.authservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    private ResponseEntity<TokenDTO> login(@RequestBody AuthUserDTO dto){
        TokenDTO tokenDTO = authService.login(dto);
        if (tokenDTO == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return ResponseEntity.ok(tokenDTO);
    }

    @PostMapping("/validate")
    public ResponseEntity<TokenDTO> validate(@RequestParam String token,
                                             @RequestBody RequestDTO requestDTO){
        TokenDTO tokenDTO = authService.validate(token, requestDTO);
        if (tokenDTO == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(tokenDTO);
    }

    @PostMapping("/create")
    public ResponseEntity<AuthUser> create(@RequestBody NewUserDTO dto){
        AuthUser authUser = authService.create(dto);
        if (authUser == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(authUser);
    }
}

