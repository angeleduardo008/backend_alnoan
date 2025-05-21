package com.Proyect.Backend.Model;

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

    @Column(name = "imagen_principal")
    private String imagenPrincipal;

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

}
