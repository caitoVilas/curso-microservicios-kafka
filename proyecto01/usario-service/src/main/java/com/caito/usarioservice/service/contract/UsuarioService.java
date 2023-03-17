package com.caito.usarioservice.service.contract;

import com.caito.usarioservice.entity.Usuario;

import java.util.List;

public interface UsuarioService {
    Usuario saveUsuario(Usuario usuario);
    List<Usuario> getAll();
    Usuario getUsuario(Long usuarioId);
}
