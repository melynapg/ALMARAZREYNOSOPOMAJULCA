package com.utn.desi.inmobiliaria.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "historial_estados_publicaciones")
public class HistorialEstadoPublicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "publicacion_id")
    private Publicacion publicacion;

    private String estadoAnterior;
    private String estadoNuevo;
    private LocalDateTime fechaCambio;

    public HistorialEstadoPublicacion() {}

    public HistorialEstadoPublicacion(Publicacion publicacion, String estadoAnterior, String estadoNuevo, LocalDateTime fechaCambio) {
        this.publicacion = publicacion;
        this.estadoAnterior = estadoAnterior;
        this.estadoNuevo = estadoNuevo;
        this.fechaCambio = fechaCambio;
    }

    public Long getId() { 
    	return id;
    }
    public void setId(Long id) {
    	this.id = id;
    }
    public Publicacion getPublicacion() {
    	return publicacion;
    	}
    public void setPublicacion(Publicacion publicacion) { 
    	this.publicacion = publicacion; }
    public String getEstadoAnterior() {
    	return estadoAnterior;
    }
    public void setEstadoAnterior(String estadoAnterior) {
    	this.estadoAnterior = estadoAnterior;
    }
    public String getEstadoNuevo() {
    	return estadoNuevo;
    }
    public void setEstadoNuevo(String estadoNuevo) {
    	this.estadoNuevo = estadoNuevo;
    }
    public LocalDateTime getFechaCambio() { 
    	return fechaCambio; 
    }
    public void setFechaCambio(LocalDateTime fechaCambio) {
    	this.fechaCambio = fechaCambio;
    }
}