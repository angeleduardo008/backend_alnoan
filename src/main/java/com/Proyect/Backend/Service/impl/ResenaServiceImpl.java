package com.Proyect.Backend.Service.impl;

import com.Proyect.Backend.Model.resena;
import com.Proyect.Backend.Repository.ResenaRepository;
import com.Proyect.Backend.Service.ResenaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResenaServiceImpl implements ResenaService {

    private final ResenaRepository repository;

    public ResenaServiceImpl(ResenaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<resena> listarTodas() {
        return repository.findAll();
    }

    @Override
    public Optional<resena> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<resena> buscarPorProductoId(Long productoId) {
        return repository.findByProductoId(productoId);
    }

    @Override
    public resena guardar(resena resena) {
        return repository.save(resena);
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
