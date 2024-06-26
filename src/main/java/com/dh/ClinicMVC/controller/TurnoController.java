package com.dh.ClinicMVC.controller;

import com.dh.ClinicMVC.entity.DTO.TurnoDTO;
import com.dh.ClinicMVC.entity.DTO.TurnoRequestDTO;
import com.dh.ClinicMVC.exception.ResourceNotFoundException;
import com.dh.ClinicMVC.service.IOdontologoService;
import com.dh.ClinicMVC.service.IPacienteService;
import com.dh.ClinicMVC.service.ITurnoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@RestController
@RequestMapping("/turnos")
public class TurnoController {
    private static final Logger LOGGER = Logger.getLogger(TurnoController.class);
    @Autowired
    private ITurnoService turnoService;
    @Autowired
    private IOdontologoService odontologoService;
    @Autowired
    private IPacienteService pacienteService;

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarTurno(@RequestBody TurnoRequestDTO turnoRequestDTO) {
        try {
            TurnoDTO turnoDTO = turnoService.guardar(turnoRequestDTO);
            return ResponseEntity.ok(turnoDTO);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurnoDTO> buscarPorId(@PathVariable Long id) {
        TurnoDTO turnoDTO = turnoService.buscarPorId(id);
        return ResponseEntity.ok(turnoDTO);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        turnoService.eliminar(id);
    }

    @GetMapping("/listar")
    public ResponseEntity<Set<TurnoDTO>> listarTodos() {
        return ResponseEntity.ok(turnoService.listarTodos());
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarTurno(@PathVariable Long id, @RequestBody TurnoRequestDTO turnoRequestDTO) {
        try {
            TurnoDTO turnoDTO = turnoService.actualizar(id, turnoRequestDTO);
            return ResponseEntity.ok("Turno actualizado correctamente");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}






