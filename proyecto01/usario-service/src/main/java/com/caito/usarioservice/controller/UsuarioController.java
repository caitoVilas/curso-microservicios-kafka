package com.caito.usarioservice.controller;

import com.caito.usarioservice.entity.Usuario;
import com.caito.usarioservice.service.contract.UsuarioService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@Slf4j
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario){
        Usuario usuarioNuevo = usuarioService.saveUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    int cantidadReintentos = 1;
    @GetMapping("/{usuarioId}")
    //@CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
    @Retry(name = "ratingHotelService",fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<Usuario> getusuario(@PathVariable Long usuarioId){
        log.info("mostrar un solo usario: UsuarioController");
        log.info("cantidad de reintentos:  {}", cantidadReintentos );
        cantidadReintentos++;
        Usuario usuario = usuarioService.getUsuario(usuarioId);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping
    //@CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<List<Usuario>> getusuarios(){
        List<Usuario> usuarios = usuarioService.getAll();
        return ResponseEntity.ok(usuarios);
    }

    public ResponseEntity<Usuario> ratingHotelFallback(Long usuarioId, Exception e){
        log.info("el respaldo se ejecuta porque el servicio esta inactivo", e.getMessage());
        Usuario usuario = new Usuario();
        usuario.setEmail("root@gmail.com");
        usuario.setNombre("root");
        usuario.setInformacion("este usuario se crea cuando un servicio se cae");
        usuario.setUsuarioId(9999L);
        return ResponseEntity.ok(usuario);
    }
}
