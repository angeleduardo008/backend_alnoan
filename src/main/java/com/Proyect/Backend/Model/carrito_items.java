

 package com.Proyect.Backend.Model;

 import jakarta.persistence.*;

 @Entity
 @Table(name = "carrito_items")
 public class carrito_items {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

     @Column(name = "usuario_id", nullable = false)
     private Long usuarioId;

     @Column(name = "producto_id", nullable = false)
     private Long productoId;

     @Column(nullable = false)
     private int cantidad;

     // Getters y setters
     public Long getId() { return id; }
     public void setId(Long id) { this.id = id; }

     public Long getUsuarioId() { return usuarioId; }
     public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }

     public Long getProductoId() { return productoId; }
     public void setProductoId(Long productoId) { this.productoId = productoId; }

     public int getCantidad() { return cantidad; }
     public void setCantidad(int cantidad) { this.cantidad = cantidad; }
 }
