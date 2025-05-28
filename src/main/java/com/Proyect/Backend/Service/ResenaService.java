package com.Proyect.Backend.Service;

import com.Proyect.Backend.Model.resena;

import java.util.List;
import java.util.Optional;

public interface ResenaService {
    List<resena> listarTodas();
    Optional<resena> buscarPorId(Long id);
    List<resena> buscarPorProductoId(Long productoId);
    resena guardar(resena resena);
    void eliminar(Long id);
}
