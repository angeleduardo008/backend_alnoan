package com.Proyect.Backend.Model;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.persistence.*;
@Entity
@Table(name = "productos")
public class productos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long producto_id;
    @ManyToOne
    @JoinColumn(name = "categoria_id", referencedColumnName = "id")
    private categorias categoria;
    private String nombre;
    private String descripcion;
    private double precio;
    private int stock;
    private String descripcionLarga;
    private String dimensiones;

    @ManyToOne
    @JoinColumn(name = "subcategoria_id")
    private Subcategoria subcategoria; // Esta propiedad debe existir

    @Column(name = "imagen_principal")
    private String imagenPrincipal;
    
    @Column(name = "video")
    private String video;

    

    public String getDescripcionLarga() {
        return descripcionLarga;
    }

    public void setDescripcionLarga(String descripcionLarga) {
        this.descripcionLarga = descripcionLarga;
    }

    public String getDimensiones() {
        return dimensiones;
    }

    public void setDimensiones(String dimensiones) {
        this.dimensiones = dimensiones;
    }

    public String getVideo() {
    return video;
}

    public void setVideo(String video) {
    this.video = video;
}
    public long getId() {
        return producto_id;
    }
    public void setId(long id) {
        this.producto_id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getImagenPrincipal() {
        return imagenPrincipal;
    }

    public void setImagenPrincipal(String imagenPrincipal) {
        this.imagenPrincipal = imagenPrincipal;
    }
@Bean
public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**")
                    .allowedOrigins("http://localhost:5500") // Ajusta el puerto según tu servidor frontend
                    .allowedMethods("GET", "POST", "PUT", "DELETE");
        }
    };
}

public void setGarantia(String garantia) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'setGarantia'");
}

public void setSubcategoriaId(Long subcategoriaId) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'setSubcategoriaId'");
}
}
