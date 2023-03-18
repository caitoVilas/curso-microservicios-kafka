package com.caito.authservice.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "auth_user")
@Data
public class AuthUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
}
