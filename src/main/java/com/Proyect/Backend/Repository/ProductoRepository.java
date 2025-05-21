package com.Proyect.Backend.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Proyect.Backend.Model.productos;

public interface ProductoRepository extends JpaRepository<productos, Long> {
  @Query(value = "SELECT p.* FROM productos p JOIN categorias c ON p.categoria_id = c.id WHERE c.nombre = ?1", nativeQuery = true)
  List<productos> findByCategoriaNombre(String nombreCategoria);
}
