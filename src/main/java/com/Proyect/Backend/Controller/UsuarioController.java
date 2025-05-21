package com.Proyect.Backend.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.*;
import com.Proyect.Backend.Model.usuarios;
import com.Proyect.Backend.Service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping
    public List<usuarios> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<usuarios> obtener(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public usuarios guardar(@RequestBody usuarios usuario) {
        return service.guardar(usuario);
    }

    @PutMapping("/{id}")
    public usuarios actualizar(@PathVariable Long id, @RequestBody usuarios usuario) {
        usuario.setId(id);
        return service.guardar(usuario);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
