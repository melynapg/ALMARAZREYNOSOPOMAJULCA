package com.utn.desi.inmobiliaria.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "propiedades")
public class Propiedad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String direccion;
    private String ciudad; // <--- AGREGADA ACÁ
    private String tipo; // CASA, DEPARTAMENTO, LOCAL, OTRO
    private int cantidadAmbientes;
    private double metrosCuadrados;
    private String descripcion;
    private String comodidades;
    private String estadoDisponibilidad; // DISPONIBLE, RESERVADA, ALQUILADA, INACTIVA
    private boolean eliminada; // Para la baja lógica

    // --- CONSTRUCTOR VACÍO ---
    public Propiedad() {
    }

    // --- GETTERS Y SETTERS ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getCiudad() { return ciudad; } // <--- GETTER AGREGADO
    public void setCiudad(String ciudad) { this.ciudad = ciudad; } // <--- SETTER AGREGADO

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public int getCantidadAmbientes() { return cantidadAmbientes; }
    public void setCantidadAmbientes(int cantidadAmbientes) { this.cantidadAmbientes = cantidadAmbientes; }

    public double getMetrosCuadrados() { return metrosCuadrados; }
    public void setMetrosCuadrados(double metrosCuadrados) { this.metrosCuadrados = metrosCuadrados; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getComodidades() { return comodidades; }
    public void setComodidades(String comodidades) { this.comodidades = comodidades; }

    public String getEstadoDisponibilidad() { return estadoDisponibilidad; }
    public void setEstadoDisponibilidad(String estadoDisponibilidad) { this.estadoDisponibilidad = estadoDisponibilidad; }

    public boolean isEliminada() { return eliminada; }
    public void setEliminada(boolean eliminada) { this.eliminada = eliminada; }
}