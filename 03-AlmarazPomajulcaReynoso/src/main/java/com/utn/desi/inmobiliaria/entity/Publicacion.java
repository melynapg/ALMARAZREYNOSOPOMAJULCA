package com.utn.desi.inmobiliaria.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "publicaciones")
public class Publicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "propiedad_id")
    private Propiedad propiedad;

    private double precioMensual;
    private String condiciones;
    private LocalDate fechaPublicacion;
    private String estado; // ACTIVA, PAUSADA, FINALIZADA
    private boolean eliminada;

    public Publicacion() {}

    // getters y setters
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
    public double getPrecioMensual() {
    	return precioMensual;
    }
    public void setPrecioMensual(double precioMensual) {
    	this.precioMensual = precioMensual;
    }
    public String getCondiciones() {
    	return condiciones;
    }
    public void setCondiciones(String condiciones) {
    	this.condiciones = condiciones;
    }
    public LocalDate getFechaPublicacion() {
    	return fechaPublicacion;
    }
    public void setFechaPublicacion(LocalDate fechaPublicacion) {
    	this.fechaPublicacion = fechaPublicacion;
    }
    public String getEstado() {
    	return estado;
    }
    public void setEstado(String estado) {
    	this.estado = estado;
    }
    public boolean isEliminada() {
    	return eliminada;
    }
    public void setEliminada(boolean eliminada) {
    	this.eliminada = eliminada;
    }
}