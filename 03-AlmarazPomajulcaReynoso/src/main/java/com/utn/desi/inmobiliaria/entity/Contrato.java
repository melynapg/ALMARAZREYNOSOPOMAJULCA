package com.utn.desi.inmobiliaria.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "contratos")
public class Contrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "propiedad_id")
    private Propiedad propiedad;

    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private double montoMensual;
    private String inquilinoNombre;
    private String inquilinoDni;
    private String estado; // VIGENTE, FINALIZADO, RESCINDIDO
    private boolean eliminado;

    public Contrato() {}

    // getters y setters basicos
    public Long getId() { 
    	return id; }
    public void setId(Long id) {
    	this.id = id; 
    	}
    public Propiedad getPropiedad() {
    	return propiedad;
    }
    public void setPropiedad(Propiedad propiedad) {
    	this.propiedad = propiedad;  
     }
    public LocalDate getFechaInicio() { 
    	return fechaInicio; 
    }
    public void setFechaInicio(LocalDate fechaInicio) { 
    	this.fechaInicio = fechaInicio;
    }
    public LocalDate getFechaFin() { 
    	return fechaFin; 
    	}
    public void setFechaFin(LocalDate fechaFin) { 
    	this.fechaFin = fechaFin;
     }
    public double getMontoMensual() { 
    	return montoMensual;
    }
    public void setMontoMensual(double montoMensual) {
    	this.montoMensual = montoMensual;
    	}
    public String getInquilinoNombre() {
    	return inquilinoNombre; 
    }
    public void setInquilinoNombre(String inquilinoNombre) { 
    	this.inquilinoNombre = inquilinoNombre;
    }
    public String getInquilinoDni() {
    	return inquilinoDni;
    }
    public void setInquilinoDni(String inquilinoDni) {
    	this.inquilinoDni = inquilinoDni;
    }
    public String getEstado() {
    	return estado; 
    }
    public void setEstado(String estado) {
    	this.estado = estado;
      }
    public boolean isEliminado() { 
    	return eliminado; 
    	}
    public void setEliminado(boolean eliminado) {
    	this.eliminado = eliminado;
    }
}