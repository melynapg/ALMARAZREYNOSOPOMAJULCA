package com.utn.desi.inmobiliaria;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.utn.desi.inmobiliaria.entity.Contrato;

@RestController
@RequestMapping("/contratos")
public class ContratoController {

    @Autowired
    private ContratoService contratoService;

    @GetMapping("/listar")
    public List<Contrato> listar() {
        return contratoService.listarTodos();
    }

    @PostMapping("/guardar")
    public String guardar(@RequestBody Contrato contrato) {
        try {
            contratoService.guardar(contrato);
            return "Contrato procesado y registrado con exito.";
        } catch (Exception e) {
            return "No se pudo registrar el contrato: " + e.getMessage();
        }
    }

    @GetMapping("/buscar/{id}")
    public Contrato buscar(@PathVariable("id") Long id) {
        return contratoService.buscarPorId(id);
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Long id) {
        contratoService.eliminarLogica(id);
        return "El contrato se elimino del sistema.";
    }
}