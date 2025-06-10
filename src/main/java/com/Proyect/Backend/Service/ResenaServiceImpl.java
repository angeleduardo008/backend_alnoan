package com.Proyect.Backend.Service;

import com.Proyect.Backend.DTO.ResenaRequest;
import com.Proyect.Backend.Model.productos;
import com.Proyect.Backend.Model.resena;
import com.Proyect.Backend.Model.usuarios;
import com.Proyect.Backend.Repository.ProductoRepository;
import com.Proyect.Backend.Repository.ResenaRepository;
import com.Proyect.Backend.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ResenaServiceImpl implements ResenaService {

    @Autowired
    private ResenaRepository resenaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<resena> listarTodas() {
        return resenaRepository.findAll();
    }

    @Override
    public Optional<resena> buscarPorId(Long id) {
        return resenaRepository.findById(id);
    }

    @Override
    public List<resena> buscarPorProductoId(Long productoId) {
        return resenaRepository.findByProductoId(productoId);
    }

    @Override
    public resena guardar(resena resena) {
        return resenaRepository.save(resena);
    }

    public resena guardarDesdeDTO(ResenaRequest request) {
        productos producto = productoRepository.findById(request.getProductoId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        usuarios usuario = new usuarios();
        usuario.setNombre(request.getNombreUsuario());
        usuarioRepository.save(usuario);

        resena nueva = new resena();
        nueva.setProducto(producto);
        nueva.setUsuario(usuario);
        nueva.setComentario(request.getComentario());
        nueva.setCalificacion(request.getCalificacion());
        nueva.setFecha(LocalDate.now());

        return resenaRepository.save(nueva);
    }

    @Override
    public void eliminar(Long id) {
        resenaRepository.deleteById(id);
    }
}
