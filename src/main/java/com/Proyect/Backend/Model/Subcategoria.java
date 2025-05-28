package com.Proyect.Backend.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "subcategorias")
public class Subcategoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private categorias categoria;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public categorias getCategoria() {
        return categoria;
    }

    public void setCategoria(categorias categoria) {
        this.categoria = categoria;
    }
    
}
