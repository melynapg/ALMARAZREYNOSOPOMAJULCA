package com.utn.desi.inmobiliaria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.utn.desi.inmobiliaria.entity.Propiedad;

@Repository
public interface PropiedadRepository extends JpaRepository<Propiedad, Long> {
    List<Propiedad> findByEliminadaFalse();
    boolean existsByDireccionAndCiudadAndEliminadaFalse(String direccion, String ciudad);
}