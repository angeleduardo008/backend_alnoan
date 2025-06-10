package com.Proyect.Backend.Service;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.Proyect.Backend.Model.productos;
import com.Proyect.Backend.Model.Subcategoria;
import com.Proyect.Backend.Model.categorias;
import com.Proyect.Backend.Repository.CategoriaRepository;
import com.Proyect.Backend.Repository.SubcategoriaRepository;
import com.Proyect.Backend.Repository.ProductoRepository;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;
    private final SubcategoriaRepository subcategoriaRepository;

    public ProductoService(
        ProductoRepository productoRepository,
        CategoriaRepository categoriaRepository,
        SubcategoriaRepository subcategoriaRepository) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
        this.subcategoriaRepository = subcategoriaRepository;
    }

    public List<productos> listarTodos() {
        return productoRepository.findAll();
    }

    public Optional<productos> buscarPorId(Long id) {
        return productoRepository.findById(id);
    }

    public productos guardar(productos producto) {
        return productoRepository.save(producto);
    }

    public void eliminar(Long id) {
        productoRepository.deleteById(id);
    }

    public List<productos> buscarPorNombreCategoria(String nombre) {
        return productoRepository.findByCategoriaNombre(nombre);
    }

    public List<productos> buscarPorNombreSubcategoria(String nombre) {
        return productoRepository.findByCategoriaNombre(nombre);
    }

    public Optional<categorias> buscarCategoriaPorId(Long id) {
        return categoriaRepository.findById(id);
    }

    public Optional<Subcategoria> buscarSubcategoriaPorId(Long id) {
        return subcategoriaRepository.findById(id);
    }
}
