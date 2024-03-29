package com.dh.ClinicMVC.controller;
import com.dh.ClinicMVC.entity.DTO.PacienteDTO;
import com.dh.ClinicMVC.entity.DTO.TurnoDTO;
import com.dh.ClinicMVC.service.IPacienteService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private static final Logger LOGGER = Logger.getLogger(PacienteController.class);
    @Autowired
    private IPacienteService pacienteService;


    @PostMapping("/guardar")
    public ResponseEntity<?> guardar(@RequestBody PacienteDTO pacienteDTO) {
        LOGGER.info("Guardando paciente...");
        pacienteService.guardar(pacienteDTO);
        String guardadoExitoso = "Se guardo correctamente el paciente.";
        return ResponseEntity.ok().body(guardadoExitoso);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> buscarPorId(@PathVariable Long id) {
        PacienteDTO pacienteDTO = pacienteService.buscarPorId(id);
        LOGGER.info("Se encontro el id " + id);
        return ResponseEntity.ok(pacienteDTO);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        LOGGER.info("Se elimino correctamente.");
        pacienteService.eliminar(id);
    }

    @GetMapping("/listar")
    public Set<PacienteDTO> listarTodos() {
        return pacienteService.listarTodos();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody PacienteDTO pacienteDTO) {
        try {
            pacienteDTO.setId(id);
            pacienteService.actualizar(pacienteDTO);
            return ResponseEntity.ok("Paciente actualizado correctamente");
        } catch (Exception e) {
            LOGGER.error("Error al actualizar el paciente: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}

