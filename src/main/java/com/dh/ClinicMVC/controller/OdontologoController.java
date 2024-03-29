package com.dh.ClinicMVC.controller;

import com.dh.ClinicMVC.entity.DTO.OdontologoDTO;
import com.dh.ClinicMVC.service.IOdontologoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    private static final Logger LOGGER = Logger.getLogger(OdontologoController.class);
    @Autowired
    IOdontologoService odontologoService;
    @PostMapping("/guardar")
    public ResponseEntity<?> guardar(@RequestBody OdontologoDTO odontologoDTO) {
        LOGGER.info("Se guardo correctamente.");
        odontologoService.guardar(odontologoDTO);
        String guardadoExitoso = "Se guardo correctamente el odontologo.";
        return ResponseEntity.ok().body(guardadoExitoso);

    }
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        LOGGER.info("Buscando odontólogo con ID: " + id);
        OdontologoDTO odontologoDTO = odontologoService.buscarPorId(id);
        if (odontologoDTO != null) {
            return ResponseEntity.ok(odontologoDTO);
        } else {
            String mensajeError = "No se encontró el odontólogo con ID: " + id;
            LOGGER.error(mensajeError);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensajeError);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        LOGGER.info("Se elimino correctamente.");
        odontologoService.eliminar(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @GetMapping("/listar")
    public ResponseEntity<Set<OdontologoDTO>> listarTodos(){
        return ResponseEntity.ok(odontologoService.listarTodos());
    }
    @PutMapping
    public ResponseEntity<String> actualizar(@RequestBody OdontologoDTO odontologoDTO) {
        ResponseEntity<String> response;
        OdontologoDTO odontologoBuscado = odontologoService.buscarPorId(odontologoDTO.getId());
        if (odontologoBuscado != null) {
            odontologoService.actualizar(odontologoDTO);
            response = ResponseEntity.ok("Se actualizó el odontologo con id " + odontologoDTO.getId());
        } else {
            response = ResponseEntity.ok("No se puede actualizar el odontologo");
        }
        return response;

    }
}