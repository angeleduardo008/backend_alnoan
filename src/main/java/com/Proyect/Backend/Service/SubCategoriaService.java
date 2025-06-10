package com.Proyect.Backend.Service;

import com.Proyect.Backend.Model.Subcategoria;
import java.util.List;
import java.util.Optional;

public interface SubCategoriaService {
    List<Subcategoria> obtenerTodas();
    Optional<Subcategoria> obtenerPorId(Long id);
    Subcategoria guardar(Subcategoria subcategoria);
    Subcategoria actualizar(Long id, Subcategoria subcategoria);
    void eliminar(Long id);
}
