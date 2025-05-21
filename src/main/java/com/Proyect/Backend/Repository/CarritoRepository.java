package com.Proyect.Backend.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Proyect.Backend.Model.carrito_items;

public interface CarritoRepository extends JpaRepository<carrito_items, Long> {
    List<carrito_items> findByUsuarioId(Long usuarioId);
}
