package com.Proyect.Backend.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.Proyect.Backend.Model.productos;
import com.Proyect.Backend.Repository.ProductoRepository;
@Service
public class ProductoService {
    private final ProductoRepository repository;

    public ProductoService(ProductoRepository repository) {
        this.repository = repository;
    }

    public List<productos> listarTodos() {
        return repository.findAll();
    }

    public Optional<productos> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public productos guardar(productos producto) {
        return repository.save(producto);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    public List<productos> buscarPorNombreCategoria(String nombre) {
    return repository.findByCategoriaNombre(nombre);
}
}
