package com.Proyect.Backend.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.*;

import com.Proyect.Backend.Model.categorias;
import com.Proyect.Backend.Service.CategoriaService;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    private final CategoriaService service;

    public CategoriaController(CategoriaService service) {
        this.service = service;
    }

    @GetMapping
    public List<categorias> listar() {
        return service.listarTodas();
    }

    @GetMapping("/{id}")
    public Optional<categorias> obtener(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public categorias guardar(@RequestBody categorias categoria) {
        return service.guardar(categoria);
    }

    @PutMapping("/{id}")
    public categorias actualizar(@PathVariable Long id, @RequestBody categorias categoria) {
        categoria.setId(id);
        return service.guardar(categoria);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
