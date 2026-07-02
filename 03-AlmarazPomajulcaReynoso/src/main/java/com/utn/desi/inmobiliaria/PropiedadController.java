package com.utn.desi.inmobiliaria;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.utn.desi.inmobiliaria.entity.Propiedad;

@RestController
@RequestMapping("/propiedades")
public class PropiedadController {

    @Autowired
    private PropiedadService propiedadService;

    @GetMapping("/listar")
    public List<Propiedad> listarPropiedades() {
        return propiedadService.listarActivas();
    }

    @PostMapping("/guardar")
    public String guardarPropiedad(@RequestBody Propiedad propiedad) {
        try {
            propiedadService.guardar(propiedad);
            return "Propiedad procesada correctamente en el sistema.";
        } catch (Exception e) {
            return "Error al intentar guardar: " + e.getMessage();
        }
    }

    @GetMapping("/buscar/{id}")
    public Propiedad buscarPorId(@PathVariable("id") Long id) {
        return propiedadService.buscarPorId(id);
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarPropiedad(@PathVariable("id") Long id) {
        try {
            propiedadService.eliminarLogica(id);
            return "La propiedad fue dada de baja de forma logica con éxito.";
        } catch (Exception e) {
            return "No se pudo dar de baja: " + e.getMessage();
        }
    }
}