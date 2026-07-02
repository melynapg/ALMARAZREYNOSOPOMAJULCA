package com.utn.desi.inmobiliaria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import com.utn.desi.inmobiliaria.entity.Propiedad;
import com.utn.desi.inmobiliaria.entity.HistorialEstadoPropiedad;

@Service
public class PropiedadService {

    @Autowired
    private PropiedadRepository propiedadRepository;

    @Autowired
    private HistorialEstadoPropiedadRepository historialRepository;

    @Autowired
    private ContratoRepository contratoRepository;

    public List<Propiedad> listarActivas() {
        return propiedadRepository.findByEliminadaFalse();
    }

    public Propiedad buscarPorId(Long id) {
        return propiedadRepository.findById(id).orElse(null);
    }

    public void guardar(Propiedad propiedad) {
        // chequeos basicos de los datos ingresados
        if (propiedad.getCantidadAmbientes() <= 0) {
            throw new RuntimeException("Che, la cantidad de ambientes tiene que ser mayor a cero.");
        }
        if (propiedad.getMetrosCuadrados() <= 0) {
            throw new RuntimeException("Los metros cuadrados no pueden ser negativos o cero.");
        }

        String estadoAnterior = "NUEVA";

        if (propiedad.getId() == null) {
            // si es un alta nueva, validamos que no este duplicada la direccion en la ciudad
            if (propiedadRepository.existsByDireccionAndCiudadAndEliminadaFalse(propiedad.getDireccion(), propiedad.getCiudad())) {
                throw new RuntimeException("Ya existe una propiedad activa cargada con esa misma direccion y ciudad.");
            }
            propiedad.setEliminada(false);
            if (propiedad.getEstadoDisponibilidad() == null) {
                propiedad.setEstadoDisponibilidad("DISPONIBLE"); // arranca disponible por defecto
            }
        } else {
            // si es modificacion, rescatamos el estado viejo antes de salvar los cambios
            Propiedad vieja = propiedadRepository.findById(propiedad.getId()).orElse(null);
            if (vieja != null) {
                estadoAnterior = vieja.getEstadoDisponibilidad();
            }
        }

        Propiedad guardada = propiedadRepository.save(propiedad);

        // si cambio el estado, se guarda el registro en el historial
        if (!estadoAnterior.equals(guardada.getEstadoDisponibilidad())) {
            HistorialEstadoPropiedad log = new HistorialEstadoPropiedad(guardada, estadoAnterior, guardada.getEstadoDisponibilidad(), LocalDateTime.now());
            historialRepository.save(log);
        }
    }

    public void eliminarLogica(Long id) {
        Propiedad propiedad = propiedadRepository.findById(id).orElse(null);
        if (propiedad != null) {
            // regla: no se puede borrar si tiene un contrato vigente/activo
            boolean tieneContrato = contratoRepository.existsByPropiedadIdAndEstadoAndEliminadoFalse(id, "VIGENTE");
            if (tieneContrato) {
                throw new RuntimeException("No podes dar de baja esta propiedad porque tiene un contrato vigente activo.");
            }
            
            propiedad.setEliminada(true);
            propiedadRepository.save(propiedad);
        }
    }
}