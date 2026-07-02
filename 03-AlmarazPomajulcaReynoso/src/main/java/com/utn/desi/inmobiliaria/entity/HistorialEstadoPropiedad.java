package com.utn.desi.inmobiliaria.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "historial_estados_propiedades")
public class HistorialEstadoPropiedad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "propiedad_id")
    private Propiedad propiedad;

    private String estadoAnterior;
    private String estadoNuevo;
    private LocalDateTime fechaCambio;

    // constructor vacio para hibernate
    public HistorialEstadoPropiedad() {}

    public HistorialEstadoPropiedad(Propiedad propiedad, String estadoAnterior, String estadoNuevo, LocalDateTime fechaCambio) {
        this.propiedad = propiedad;
        this.estadoAnterior = estadoAnterior;
        this.estadoNuevo = estadoNuevo;
        this.fechaCambio = fechaCambio;
    }

    // getters y setters basicos
    public Long getId() {
    	return id;
    }
    public void setId(Long id) {
    	this.id = id;
    }
    public Propiedad getPropiedad() {
    	return propiedad;
    }
    public void setPropiedad(Propiedad propiedad) {
    	this.propiedad = propiedad;
    }
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