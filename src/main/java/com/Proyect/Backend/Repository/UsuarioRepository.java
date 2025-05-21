package com.Proyect.Backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Proyect.Backend.Model.usuarios;

public interface UsuarioRepository extends JpaRepository<usuarios, Long> {
    // Puedes agregar consultas personalizadas si las necesitas
}
