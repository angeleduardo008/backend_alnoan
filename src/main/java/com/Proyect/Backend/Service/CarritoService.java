package com.Proyect.Backend.Service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.Proyect.Backend.Model.carrito_items;
import com.Proyect.Backend.Repository.CarritoRepository;

@Service
public class CarritoService {
    private final CarritoRepository repository;

    public CarritoService(CarritoRepository repository) {
        this.repository = repository;
    }

    public List<carrito_items> listarPorUsuario(Long usuarioId) {
        return repository.findByUsuarioId(usuarioId);
    }

    public carrito_items agregarAlCarrito(carrito_items item) {
        return repository.save(item);
    }

    public void eliminarDelCarrito(Long id) {
        repository.deleteById(id);
    }

    public void vaciarCarritoPorUsuario(Long usuarioId) {
        repository.findByUsuarioId(usuarioId).forEach(item -> repository.deleteById(item.getId()));
    }
}
