package com.Proyect.Backend.Controller;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.Proyect.Backend.Model.productos;
import com.Proyect.Backend.Service.ProductoService;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "http://127.0.0.1:5500") // frontend
public class ProductoController {

    private final ProductoService service;

    public ProductoController(ProductoService service) {
        this.service = service;
    }

    // -------------------- CRUD estándar --------------------

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

    // -------------------- Guardar con imagen --------------------

    @PostMapping("/guardar-con-imagen")
    public ResponseEntity<productos> guardarConImagen(
        @RequestParam("nombre") String nombre,
        @RequestParam("descripcion") String descripcion,
        @RequestParam("precio") double precio,
        @RequestParam("stock") int stock,
        @RequestParam(value = "imagen", required = false) MultipartFile imagen) throws IOException {

        productos producto = new productos();
        producto.setNombre(nombre);
        producto.setDescripcion(descripcion);
        producto.setPrecio(precio);
        producto.setStock(stock);

        if (imagen != null && !imagen.isEmpty()) {
            String nombreArchivo = guardarImagenEnBackend(imagen);
            producto.setImagenPrincipal(nombreArchivo);
        }

        return ResponseEntity.ok(service.guardar(producto));
    }

    @PostMapping("/actualizar-con-imagen/{id}")
    public ResponseEntity<productos> actualizarConImagen(
        @PathVariable int id,
        @RequestParam("nombre") String nombre,
        @RequestParam("descripcion") String descripcion,
        @RequestParam("precio") double precio,
        @RequestParam("stock") int stock,
        @RequestParam(value = "imagen", required = false) MultipartFile imagen) throws IOException {

        Optional<productos> existente = service.buscarPorId((long) id);
        if (existente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        productos producto = existente.get();
        producto.setNombre(nombre);
        producto.setDescripcion(descripcion);
        producto.setPrecio(precio);
        producto.setStock(stock);

        if (imagen != null && !imagen.isEmpty()) {
            String nombreArchivo = guardarImagenEnBackend(imagen);
            producto.setImagenPrincipal(nombreArchivo);
        }

        return ResponseEntity.ok(service.guardar(producto));
    }

    private String guardarImagenEnBackend(MultipartFile imagen) throws IOException {
        String carpeta = "src/main/resources/static/uploads/";
        String nombreArchivo = System.currentTimeMillis() + "_" + imagen.getOriginalFilename();
        Path ruta = Paths.get(carpeta + nombreArchivo);

        Files.createDirectories(ruta.getParent()); // crea carpeta si no existe
        Files.write(ruta, imagen.getBytes());

        return nombreArchivo;
    }

    // -------------------- Mostrar imagen de forma segura (evita CORB) --------------------

    @GetMapping("/imagen/{nombre}")
    public ResponseEntity<byte[]> mostrarImagen(@PathVariable String nombre) throws IOException {
        Path ruta = Paths.get("static/uploads", nombre);
        if (!Files.exists(ruta)) {
            return ResponseEntity.notFound().build();
        }

        byte[] imagenBytes = Files.readAllBytes(ruta);
        String contentType = Files.probeContentType(ruta);

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_TYPE, contentType != null ? contentType : MediaType.IMAGE_JPEG_VALUE)
            .body(imagenBytes);
    }
}
