package com.Proyect.Backend.Controller;

import com.Proyect.Backend.Model.Subcategoria;
import com.Proyect.Backend.Service.SubCategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subcategorias")
@CrossOrigin(origins = "*") // Opcional: permitir peticiones desde el frontend
public class SubCategoriaController {

    @Autowired
    private SubCategoriaService subcategoriaService;

    @GetMapping
    public List<Subcategoria> listarTodas() {
        return subcategoriaService.obtenerTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subcategoria> obtenerPorId(@PathVariable Long id) {
        return subcategoriaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Subcategoria crear(@RequestBody Subcategoria subcategoria) {
        return subcategoriaService.guardar(subcategoria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Subcategoria> actualizar(@PathVariable Long id, @RequestBody Subcategoria subcategoria) {
        Subcategoria actualizada = subcategoriaService.actualizar(id, subcategoria);
        if (actualizada != null) {
            return ResponseEntity.ok(actualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        subcategoriaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
