package com.Proyect.Backend.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.Proyect.Backend.Model.resena;
import java.util.List;

public interface ResenaRepository extends JpaRepository<resena, Long> {
    List<resena> findByProductoId(Long productoId);
}


