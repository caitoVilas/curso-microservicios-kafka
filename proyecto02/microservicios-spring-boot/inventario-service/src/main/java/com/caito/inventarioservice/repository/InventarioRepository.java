package com.caito.inventarioservice.repository;

import com.caito.inventarioservice.entity.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Long> {
    List<Inventario> findByCodigoSkuIn(List<String> codigoSku);
}
