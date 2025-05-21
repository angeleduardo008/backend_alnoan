package com.Proyect.Backend.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.Proyect.Backend.Model.usuarios;
import com.Proyect.Backend.Repository.UsuarioRepository;

@Service
public class UsuarioService {
    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public List<usuarios> listarTodos() {
        return repository.findAll();
    }

    public Optional<usuarios> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public usuarios guardar(usuarios usuario) {
        return repository.save(usuario);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
