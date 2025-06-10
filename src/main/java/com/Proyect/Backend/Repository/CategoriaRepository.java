package com.Proyect.Backend.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Proyect.Backend.Model.categorias;
@Repository
public interface CategoriaRepository extends JpaRepository<categorias, Long> {
}
