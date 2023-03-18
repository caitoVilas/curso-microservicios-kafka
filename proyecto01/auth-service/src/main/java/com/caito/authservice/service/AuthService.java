package com.caito.authservice.service;

import com.caito.authservice.dto.AuthUserDTO;
import com.caito.authservice.dto.TokenDTO;
import com.caito.authservice.entity.AuthUser;
import com.caito.authservice.repository.AuthUserRepository;
import com.caito.authservice.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private AuthUserRepository authUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtProvider jwtProvider;

    public AuthUser create(AuthUserDTO dto){
        Optional<AuthUser> user = authUserRepository.findByUsername(dto.getUsername());
        if (user.isPresent())
            return null;
        AuthUser authUser = new AuthUser();
        authUser.setUsername(dto.getUsername());
        authUser.setPassword(passwordEncoder.encode(dto.getPassword()));
        return authUserRepository.save(authUser);
    }

    public TokenDTO login(AuthUserDTO dto){
        Optional<AuthUser> user = authUserRepository.findByUsername(dto.getUsername());
        if (!user.isPresent())
            return null;
        if (passwordEncoder.matches(dto.getPassword(), user.get().getPassword())){
            return new TokenDTO(jwtProvider.createToken(user.get()));
        }
        return null;
    }

    public TokenDTO validate(String token){
        if (!jwtProvider.validate(token))
            return null;
        String username = jwtProvider.getUsernameFromToken(token);
        if (!authUserRepository.findByUsername(username).isPresent())
            return null;
        return new TokenDTO(token);
    }
}
