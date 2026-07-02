package com.utn.desi.inmobiliaria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import com.utn.desi.inmobiliaria.entity.Contrato;
import com.utn.desi.inmobiliaria.entity.Propiedad;
import com.utn.desi.inmobiliaria.entity.HistorialEstadoPropiedad;

@Service
public class ContratoService {

    @Autowired
    private ContratoRepository contratoRepository;

    @Autowired
    private PropiedadRepository propiedadRepository;

    @Autowired
    private HistorialEstadoPropiedadRepository historialPropiedadRepository;

    public List<Contrato> listarTodos() {
        return contratoRepository.findByEliminadoFalse();
    }

    public Contrato buscarPorId(Long id) {
        return contratoRepository.findById(id).orElse(null);
    }

    public void guardar(Contrato contrato) {
        if (contrato.getMontoMensual() <= 0) {
            throw new RuntimeException("El monto del contrato debe ser positivo.");
        }
        if (contrato.getFechaFin().isBefore(contrato.getFechaInicio())) {
            throw new RuntimeException("La fecha de fin no puede ser anterior a la de inicio.");
        }

        Propiedad prop = propiedadRepository.findById(contrato.getPropiedad().getId()).orElse(null);
        if (prop == null || prop.isEliminada()) {
            throw new RuntimeException("Propiedad invalida o eliminada.");
        }

        if (contrato.getId() == null) {
            contrato.setEliminado(false);
            contrato.setEstado("VIGENTE");

            // IMPACTO AUTOMATICO EN EPIC 1: Si se activa el contrato, la propiedad pasa a ALQUILADA
            String estadoViejoProp = prop.getEstadoDisponibilidad();
            prop.setEstadoDisponibilidad("ALQUILADA");
            propiedadRepository.save(prop);

            // Guardamos el log de cambio de estado de la propiedad
            if (!estadoViejoProp.equals("ALQUILADA")) {
                HistorialEstadoPropiedad log = new HistorialEstadoPropiedad(prop, estadoViejoProp, "ALQUILADA", LocalDateTime.now());
                historialPropiedadRepository.save(log);
            }
        } else {
            // Validamos transiciones si se esta editando el contrato
            Contrato viejoContrato = contratoRepository.findById(contrato.getId()).orElse(null);
            if (viejoContrato != null && !viejoContrato.getEstado().equals(contrato.getEstado())) {
                
                // Si pasa a FINALIZADO o RESCINDIDO, la propiedad se libera y vuelve a DISPONIBLE
                if ("FINALIZADO".equals(contrato.getEstado()) || "RESCINDIDO".equals(contrato.getEstado())) {
                    String estadoViejoProp = prop.getEstadoDisponibilidad();
                    prop.setEstadoDisponibilidad("DISPONIBLE");
                    propiedadRepository.save(prop);

                    HistorialEstadoPropiedad log = new HistorialEstadoPropiedad(prop, estadoViejoProp, "DISPONIBLE", LocalDateTime.now());
                    historialPropiedadRepository.save(log);
                }
            }
        }

        contratoRepository.save(contrato);
    }

    public void eliminarLogica(Long id) {
        Contrato con = contratoRepository.findById(id).orElse(null);
        if (con != null) {
            con.setEliminado(true);
            contratoRepository.save(con);
        }
    }
}