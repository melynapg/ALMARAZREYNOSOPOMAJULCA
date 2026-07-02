package com.utn.desi.inmobiliaria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import com.utn.desi.inmobiliaria.entity.Publicacion;
import com.utn.desi.inmobiliaria.entity.Propiedad;
import com.utn.desi.inmobiliaria.entity.HistorialEstadoPublicacion;

@Service
public class PublicacionService {

    @Autowired
    private PublicacionRepository publicacionRepository;

    @Autowired
    private PropiedadRepository propiedadRepository;

    @Autowired
    private HistorialEstadoPublicacionRepository historialRepository;

    public List<Publicacion> listarActivas() {
        return publicacionRepository.findByEliminadaFalse();
    }

    public Publicacion buscarPorId(Long id) {
        return publicacionRepository.findById(id).orElse(null);
    }

    public void guardar(Publicacion publicacion) {
        if (publicacion.getPrecioMensual() <= 0) {
            throw new RuntimeException("El precio de alquiler tiene que ser mayor a 0.");
        }

        // verificar que la propiedad asociada exista de verdad
        Propiedad prop = propiedadRepository.findById(publicacion.getPropiedad().getId()).orElse(null);
        if (prop == null || prop.isEliminada()) {
            throw new RuntimeException("La propiedad seleccionada no existe o fue dada de baja.");
        }

        // regla estricta: solo se publica si la propiedad esta DISPONIBLE
        if (!"DISPONIBLE".equals(prop.getEstadoDisponibilidad()) && publicacion.getId() == null) {
            throw new RuntimeException("No se puede publicar porque la propiedad no esta en estado DISPONIBLE.");
        }

        String estadoAnterior = "NUEVA";

        if (publicacion.getId() == null) {
            // controlar que no haya otra publicacion ya activa para la misma propiedad
            if (publicacionRepository.existsByPropiedadIdAndEstadoAndEliminadaFalse(prop.getId(), "ACTIVA")) {
                throw new RuntimeException("Esta propiedad ya tiene una publicacion ACTIVA vigente.");
            }
            publicacion.setEliminada(false);
            publicacion.setFechaPublicacion(LocalDate.now());
            publicacion.setEstado("ACTIVA");
        } else {
            Publicacion vieja = publicacionRepository.findById(publicacion.getId()).orElse(null);
            if (vieja != null) {
                estadoAnterior = vieja.getEstado();
            }
        }

        Publicacion guardada = publicacionRepository.save(publicacion);

        // logear el cambio de estado de la publicacion si corresponde
        if (!estadoAnterior.equals(guardada.getEstado())) {
            HistorialEstadoPublicacion log = new HistorialEstadoPublicacion(guardada, estadoAnterior, guardada.getEstado(), LocalDateTime.now());
            historialRepository.save(log);
        }
    }

    public void eliminarLogica(Long id) {
        Publicacion pub = publicacionRepository.findById(id).orElse(null);
        if (pub != null) {
            pub.setEliminada(true);
            pub.setEstado("FINALIZADA");
            publicacionRepository.save(pub);
        }
    }
}