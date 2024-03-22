package com.dh.ClinicMVC.controller;
import com.dh.ClinicMVC.entity.DTO.TurnoDTO;
import com.dh.ClinicMVC.entity.DTO.TurnoRequestDTO;
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
    public ResponseEntity<TurnoDTO> guardar(@RequestBody TurnoDTO turnoDTO) {
        ResponseEntity<TurnoDTO> response;
        if (odontologoService.buscarPorId(turnoDTO.getOdontologo().getId()) != null &&
                pacienteService.buscarPorId(turnoDTO.getPaciente().getId()) != null) {
            response = ResponseEntity.ok(turnoService.guardar(turnoDTO));
        }else {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;
    }
    @GetMapping("/{id}")
    public TurnoDTO buscarPorId(@PathVariable Long id) {
        return turnoService.buscarPorId(id);
    }
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id){
        turnoService.eliminar(id);
    }
    @GetMapping("/listar")
    public Set<TurnoDTO> listarTodos() {
        return turnoService.listarTodos();
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@RequestBody TurnoDTO turnoDTO) {
        turnoService.actualizar(turnoDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/guardarPorId")
    public ResponseEntity<TurnoDTO> guardarConId(@RequestBody TurnoRequestDTO turno) {
        ResponseEntity<TurnoDTO> response;
        if (odontologoService.buscarPorId(turno.getOdontologoId()) != null &&
                pacienteService.buscarPorId(turno.getPacienteId()) != null) {
            response = ResponseEntity.ok(turnoService.guardarPorId(turno));
        }else {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;
    }

}


