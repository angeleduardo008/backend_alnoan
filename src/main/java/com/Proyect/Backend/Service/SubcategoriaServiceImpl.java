package com.Proyect.Backend.Service;

import com.Proyect.Backend.Model.Subcategoria;
import com.Proyect.Backend.Repository.SubcategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubcategoriaServiceImpl implements SubCategoriaService {

    @Autowired
    private SubcategoriaRepository subcategoriaRepository;

    @Override
    public List<Subcategoria> obtenerTodas() {
        return subcategoriaRepository.findAll();
    }

    @Override
    public Optional<Subcategoria> obtenerPorId(Long id) {
        return subcategoriaRepository.findById(id);
    }

    @Override
    public Subcategoria guardar(Subcategoria subcategoria) {
        return subcategoriaRepository.save(subcategoria);
    }

    @Override
    public Subcategoria actualizar(Long id, Subcategoria subcategoria) {
        if (subcategoriaRepository.existsById(id)) {
            subcategoria.setId(id);
            return subcategoriaRepository.save(subcategoria);
        }
        return null;
    }

    @Override
    public void eliminar(Long id) {
        subcategoriaRepository.deleteById(id);
    }
}
