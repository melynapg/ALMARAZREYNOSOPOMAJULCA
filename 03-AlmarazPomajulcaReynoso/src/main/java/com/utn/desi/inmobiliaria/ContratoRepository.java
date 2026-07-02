package com.utn.desi.inmobiliaria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.utn.desi.inmobiliaria.entity.Contrato;

@Repository
public interface ContratoRepository extends JpaRepository<Contrato, Long> {
    List<Contrato> findByEliminadoFalse();
    // sirve para ver si la propiedad tiene un contrato vigente ahora mismo
    boolean existsByPropiedadIdAndEstadoAndEliminadoFalse(Long propiedadId, String estado);
}