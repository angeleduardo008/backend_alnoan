package com.Proyect.Backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Proyect.Backend.Model.categorias;

public interface CategoriaRepository extends JpaRepository<categorias, Long> {
}
