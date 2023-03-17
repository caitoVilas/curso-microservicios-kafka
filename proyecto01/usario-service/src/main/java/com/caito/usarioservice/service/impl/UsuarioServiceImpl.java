package com.caito.usarioservice.service.impl;

import com.caito.usarioservice.entity.Calificacion;
import com.caito.usarioservice.entity.Hotel;
import com.caito.usarioservice.entity.Usuario;
import com.caito.usarioservice.exception.NotFoundException;
import com.caito.usarioservice.external.service.HotelService;
import com.caito.usarioservice.repository.UsuarioRpository;
import com.caito.usarioservice.service.contract.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioRpository usuarioRpository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private HotelService hotelService;


    @Override
    public Usuario saveUsuario(Usuario usuario) {
        log.info("inicio servicio guardar usuario");
        log.info("guardando usuario...");
        return usuarioRpository.save(usuario);
    }

    @Override
    public List<Usuario> getAll() {
        log.info("inicio servicio ver todos los usuarios");
        log.info("buscando usuarios...");
        return usuarioRpository.findAll();
    }

    @Override
    public Usuario getUsuario(Long usuarioId) {
        log.info("inicio buscar usuario por id");
        Usuario usuario = usuarioRpository.findById(usuarioId).orElseThrow(()->{
            log.error("usuario no encontrado");
            throw new NotFoundException("usuario no encontrado");
        });
        Calificacion[] cal = restTemplate.getForObject(
                "http://CALIFICACION-SERVICE/calificaciones/usuario/" + usuario.getUsuarioId(),
                Calificacion[].class);
        List<Calificacion> calificaciones = Arrays.stream(cal).collect(Collectors.toList());
        log.info("{}", calificaciones);
        List<Calificacion> list = calificaciones.stream().map(c ->{
           /* ResponseEntity<Hotel> forEntity = restTemplate.getForEntity(
                    "http://HOTEL-SERVICE/hoteles/" + c.getHotelId(),
                    Hotel.class);*/
            Hotel hotel = hotelService.getHotel(c.getHotelId());
            //Hotel hotel = forEntity.getBody();
            c.setHotel(hotel);
            return c;
        }).collect(Collectors.toList());

        usuario.setCalificaciones(list);
        return usuario;
    }
}
