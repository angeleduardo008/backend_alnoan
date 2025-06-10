package com.Proyect.Backend.Controller;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.Proyect.Backend.Model.productos;
import com.Proyect.Backend.Service.ProductoService;@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost:5500"})
public class ProductoController {
    private final ProductoService service;
    public ProductoController(ProductoService service) {
        this.service = service;
    }
    // Listar todos los productos
    @GetMapping
    public List<productos> listar() {
        return service.listarTodos();
    }
    // Obtener un producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<productos> obtener(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    // Guardar producto (JSON plano, sin imagen/video)
    @PostMapping
    public productos guardar(@RequestBody productos producto) {
        return service.guardar(producto);
    }
    // Actualizar producto (JSON plano, sin imagen/video)
    @PutMapping("/{id}")
    public ResponseEntity<productos> actualizar(@PathVariable Long id, @RequestBody productos producto) {
        if (!service.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        producto.setId(id);
        return ResponseEntity.ok(service.guardar(producto));
    }
    // Eliminar producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!service.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
    // Buscar productos por nombre de categoría
    @GetMapping("/categoria/nombre/{nombre}")
    public List<productos> productosPorNombreCategoria(@PathVariable String nombre) {
        return service.buscarPorNombreCategoria(nombre);
    }
    // Guardar producto con imagen y/o video
    @PostMapping("/guardar-con-imagen")
    public ResponseEntity<productos> guardarConImagenYVideo(
        @RequestParam("nombre") String nombre,
        @RequestParam("descripcion") String descripcion,
        @RequestParam("precio") double precio,
        @RequestParam("stock") int stock,
        @RequestParam(value = "imagen", required = false) MultipartFile imagen,
        @RequestParam(value = "video", required = false) MultipartFile video
    ) throws IOException {

        productos producto = new productos();
        producto.setNombre(nombre);
        producto.setDescripcion(descripcion);
        producto.setPrecio(precio);
        producto.setStock(stock);

        if (imagen != null && !imagen.isEmpty()) {
            if (!imagen.getContentType().startsWith("image/")) {
                return ResponseEntity.badRequest().build();
            }
            producto.setImagenPrincipal(guardarArchivo(imagen, "src/main/resources/static/uploads/"));
        }

        if (video != null && !video.isEmpty()) {
            if (!video.getContentType().startsWith("video/")) {
                return ResponseEntity.badRequest().build();
            }
            producto.setVideo(guardarArchivo(video, "src/main/resources/static/videos/"));
        }

        return ResponseEntity.ok(service.guardar(producto));
    }

    // Actualizar producto con imagen y/o video
    @PutMapping("/actualizar-con-imagen/{id}")
    public ResponseEntity<productos> actualizarConImagenYVideo(
        @PathVariable Long id,
        @RequestParam("nombre") String nombre,
        @RequestParam("descripcion") String descripcion,
        @RequestParam("precio") double precio,
        @RequestParam("stock") int stock,
        @RequestParam(value = "imagen", required = false) MultipartFile imagen,
        @RequestParam(value = "video", required = false) MultipartFile video
    ) throws IOException {

        Optional<productos> existente = service.buscarPorId(id);
        if (existente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        productos producto = existente.get();
        producto.setNombre(nombre);
        producto.setDescripcion(descripcion);
        producto.setPrecio(precio);
        producto.setStock(stock);

        if (imagen != null && !imagen.isEmpty()) {
            if (!imagen.getContentType().startsWith("image/")) {
                return ResponseEntity.badRequest().build();
            }

            eliminarArchivo("src/main/resources/static/uploads/", producto.getImagenPrincipal());
            producto.setImagenPrincipal(guardarArchivo(imagen, "src/main/resources/static/uploads/"));
        }

        if (video != null && !video.isEmpty()) {
            if (!video.getContentType().startsWith("video/")) {
                return ResponseEntity.badRequest().build();
            }

            eliminarArchivo("src/main/resources/static/videos/", producto.getVideo());
            producto.setVideo(guardarArchivo(video, "src/main/resources/static/videos/"));
        }

        return ResponseEntity.ok(service.guardar(producto));
    }

    // ===================== MÉTODOS AUXILIARES =====================

    private String guardarArchivo(MultipartFile archivo, String carpeta) throws IOException {
        String nombreOriginal = archivo.getOriginalFilename();
        String nombreUnico = System.currentTimeMillis() + "_" + nombreOriginal;
        Path ruta = Paths.get(carpeta + nombreUnico);

        Files.createDirectories(Paths.get(carpeta));
        Files.list(Paths.get(carpeta))
            .filter(path -> path.getFileName().toString().endsWith("_" + nombreOriginal))
            .forEach(path -> {
                try {
                    Files.deleteIfExists(path);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        Files.write(ruta, archivo.getBytes());
        return nombreUnico;
    }

    private void eliminarArchivo(String carpeta, String nombreArchivo) {
        try {
            if (nombreArchivo != null) {
                Path ruta = Paths.get(carpeta + nombreArchivo);
                Files.deleteIfExists(ruta);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
