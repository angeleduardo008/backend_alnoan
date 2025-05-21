package com.Proyect.Backend.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.*;


import com.Proyect.Backend.Model.productos;
import com.Proyect.Backend.Service.ProductoService;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    private final ProductoService service;

    public ProductoController(ProductoService service) {
        this.service = service;
    }

    @GetMapping
    public List<productos> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<productos> obtener(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public productos guardar(@RequestBody productos producto) {
        return service.guardar(producto);
    }

    @PutMapping("/{id}")
    public productos actualizar(@PathVariable int id, @RequestBody productos producto) {
        producto.setId(id);
        return service.guardar(producto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

    @GetMapping("/categoria/nombre/{nombre}")
    public List<productos> productosPorNombreCategoria(@PathVariable String nombre) {
        return service.buscarPorNombreCategoria(nombre);
    }

}
