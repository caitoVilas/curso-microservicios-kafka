package com.caito.usarioservice.repository;

import com.caito.usarioservice.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRpository extends JpaRepository<Usuario, Long> {
}
