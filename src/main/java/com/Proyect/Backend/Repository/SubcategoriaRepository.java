package com.Proyect.Backend.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.Proyect.Backend.Model.Subcategoria;
public interface SubcategoriaRepository extends JpaRepository<Subcategoria, Long> {
    List<Subcategoria> findByCategoriaId(Long categoriaId);
}