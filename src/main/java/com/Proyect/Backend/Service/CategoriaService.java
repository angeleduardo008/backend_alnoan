package com.Proyect.Backend.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.Proyect.Backend.Model.categorias;
import com.Proyect.Backend.Repository.CategoriaRepository;

@Service
public class CategoriaService {
    private final CategoriaRepository repository;

    public CategoriaService(CategoriaRepository repository) {
        this.repository = repository;
    }

    public List<categorias> listarTodas() {
        return repository.findAll();
    }

    public Optional<categorias> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public categorias guardar(categorias categoria) {
        return repository.save(categoria);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}