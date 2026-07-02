package com.utn.desi.inmobiliaria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.utn.desi.inmobiliaria.entity.Publicacion;

@Repository
public interface PublicacionRepository extends JpaRepository<Publicacion, Long> {
    List<Publicacion> findByEliminadaFalse();
    // busca si la propiedad ya tiene alguna publicacion activa dando vueltas
    boolean existsByPropiedadIdAndEstadoAndEliminadaFalse(Long propiedadId, String estado);
}