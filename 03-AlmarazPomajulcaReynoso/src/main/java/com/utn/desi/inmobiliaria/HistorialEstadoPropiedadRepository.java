package com.utn.desi.inmobiliaria;

import org.springframework.data.jpa.repository.JpaRepository;
import com.utn.desi.inmobiliaria.entity.HistorialEstadoPropiedad;

public interface HistorialEstadoPropiedadRepository extends JpaRepository<HistorialEstadoPropiedad, Long> {
}