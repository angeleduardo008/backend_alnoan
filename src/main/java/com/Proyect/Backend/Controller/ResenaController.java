package com.Proyect.Backend.Controller;

import com.Proyect.Backend.DTO.ResenaRequest;
import com.Proyect.Backend.Model.resena;
import com.Proyect.Backend.Service.ResenaServiceImpl;
import com.Proyect.Backend.Service.ResenaService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/resenas")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class ResenaController {

    private final ResenaService service;
    private final ResenaServiceImpl serviceImpl;

    public ResenaController(ResenaService service, ResenaServiceImpl serviceImpl) {
        this.service = service;
        this.serviceImpl = serviceImpl;
    }

    @GetMapping
    public List<resena> listar() {
        return service.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<resena> obtener(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/producto/{productoId}")
    public List<resena> listarPorProducto(@PathVariable Long productoId) {
        return service.buscarPorProductoId(productoId);
    }

    // ✅ Método para recibir DTO
    @PostMapping
    public ResponseEntity<resena> guardarDesdeDTO(@RequestBody ResenaRequest request) {
        return ResponseEntity.ok(serviceImpl.guardarDesdeDTO(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<resena> actualizar(@PathVariable Long id, @RequestBody resena resena) {
        Optional<resena> existente = service.buscarPorId(id);
        if (existente.isEmpty()) return ResponseEntity.notFound().build();

        resena.setId(id);
        return ResponseEntity.ok(service.guardar(resena));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
