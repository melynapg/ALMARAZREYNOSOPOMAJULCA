package com.utn.desi.inmobiliaria;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.utn.desi.inmobiliaria.entity.Publicacion;

@RestController
@RequestMapping("/publicaciones")
public class PublicacionController {

    @Autowired
    private PublicacionService publicacionService;

    @GetMapping("/listar")
    public List<Publicacion> listar() {
        return publicacionService.listarActivas();
    }

    @PostMapping("/guardar")
    public String guardar(@RequestBody Publicacion publicacion) {
        try {
            publicacionService.guardar(publicacion);
            return "La publicacion se guardo de diez.";
        } catch (Exception e) {
            return "Che, hubo un problema al publicar: " + e.getMessage();
        }
    }

    @GetMapping("/buscar/{id}")
    public Publicacion buscar(@PathVariable("id") Long id) {
        return publicacionService.buscarPorId(id);
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Long id) {
        publicacionService.eliminarLogica(id);
        return "Publicacion dada de baja e inactivada correctamente.";
    }
}
