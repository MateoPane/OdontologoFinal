package com.dh.ClinicMVC.controller;

import com.dh.ClinicMVC.model.Turno;
import com.dh.ClinicMVC.service.IOdontologoService;
import com.dh.ClinicMVC.service.IPacienteService;
import com.dh.ClinicMVC.service.ITurnoService;
import com.dh.ClinicMVC.service.implementation.TurnoService;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/turnos")
public class TurnoController {
    private static final Logger LOGGER = Logger.getLogger(TurnoController.class);
    private ITurnoService turnoService;
    private IOdontologoService odontologoService;
    private IPacienteService pacienteService;

    public TurnoController(TurnoService turnoService, IOdontologoService odontologoService, IPacienteService pacienteService) {
        this.turnoService = turnoService;
        this.odontologoService = odontologoService;
        this.pacienteService = pacienteService;
    }

    @PostMapping
    public ResponseEntity<Turno> guardar(@RequestBody Turno turno) {
        ResponseEntity<Turno> response;

        LOGGER.info("Estamos guardando un turno" + turno);
        if (odontologoService.buscarPorId(turno.getOdontologo().getId()) != null &&
                pacienteService.buscarPorId(turno.getPaciente().getId()) != null) {

            response = ResponseEntity.ok(turnoService.guardar(turno));
        } else {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;
    }
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id){
        turnoService.eliminar(id);
    }
    @GetMapping
    public ResponseEntity<List<Turno>> listarTodos(){
        List<Turno> turnos = turnoService.listarTodos();
        return ResponseEntity.ok(turnos);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Turno> buscarPorId(@PathVariable Integer id) {
        Turno turno = turnoService.buscarPorId(id);
        if (turno != null) {
            return ResponseEntity.ok(turno);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Turno> actualizar(@PathVariable Integer id, @RequestBody Turno turnoNuevo) {
        Turno turnoDado = turnoService.buscarPorId(id);
        if (turnoDado != null) {
            turnoService.actualizar(turnoNuevo);
            return ResponseEntity.ok(turnoNuevo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}


