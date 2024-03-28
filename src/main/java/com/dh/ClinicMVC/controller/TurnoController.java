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
    public ResponseEntity<?> guardar(@RequestBody TurnoRequestDTO turnoDTO) {
        TurnoDTO turnoGuardado;
        try {
            turnoGuardado = turnoService.guardar(turnoDTO);
            return ResponseEntity.ok(turnoGuardado);
        } catch (Exception e) {
            LOGGER.error("Error al guardar el turno: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
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

    @PutMapping
    public ResponseEntity<TurnoDTO> actualizar(@RequestBody TurnoDTO turnoDTO) {
        turnoService.actualizar(turnoDTO);
        return ResponseEntity.ok(turnoDTO);
    }
//    @PutMapping
//    public ResponseEntity<String> actualizar(@RequestBody TurnoDTO turnoDTO) {
//        ResponseEntity<String> response;
//        TurnoDTO turnoDTOBuscar = turnoService.buscarPorId(turnoDTO.getId());
//        if (turnoDTOBuscar !=null) {
//            turnoService.actualizar(turnoDTO);
//            response = ResponseEntity.ok("Se actualizo el turno con id " + turnoDTO.getId());
//        }else {
//            response = ResponseEntity.ok("No se pudo actualizar el turno");
//        }
//        return response;
//    }

}


