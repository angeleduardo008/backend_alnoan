package com.Proyect.Backend.Controller;

import java.util.List;
import com.Proyect.Backend.Model.carrito_items;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Proyect.Backend.Service.CarritoService;

@RestController
@RequestMapping("/api/carrito")
@CrossOrigin(origins = "https://localhost:5500") //frontend
public class CarritoController {
    @Autowired
    private final CarritoService service;
    
    public CarritoController(CarritoService service) {
        this.service = service;
    }

    @GetMapping("/{usuarioId}")
    public List<carrito_items> obtenerCarrito(@PathVariable Long usuarioId) {
        return service.listarPorUsuario(usuarioId);
    }

    @PostMapping
    public carrito_items agregarAlCarrito(@RequestBody carrito_items item) {
        return service.agregarAlCarrito(item);
    }

    @DeleteMapping("/{itemId}")
    public void eliminarItem(@PathVariable Long itemId) {
        service.eliminarDelCarrito(itemId);
    }

    @DeleteMapping("/vaciar/{usuarioId}")
    public void vaciarCarrito(@PathVariable Long usuarioId) {
        service.vaciarCarritoPorUsuario(usuarioId);
    }
}
