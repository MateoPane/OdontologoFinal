package com.dh.ClinicMVC.controller;

import com.dh.ClinicMVC.model.Turno;
import com.dh.ClinicMVC.service.IOdontologoService;
import com.dh.ClinicMVC.service.IPacienteService;
import com.dh.ClinicMVC.service.ITurnoService;
import com.dh.ClinicMVC.service.implementation.OdontologoService;
import com.dh.ClinicMVC.service.implementation.PacienteService;
import com.dh.ClinicMVC.service.implementation.TurnoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    private ITurnoService turnoService;
    private IOdontologoService odontologoService;
    private IPacienteService pacienteService;

    public TurnoController(TurnoService turnoService, OdontologoService odontologoService, PacienteService pacienteService) {
        this.turnoService = turnoService;
        this.odontologoService = odontologoService;
        this.pacienteService = pacienteService;
    }

    @PostMapping
    public ResponseEntity<Turno> guardar(@RequestBody Turno turno) {
        ResponseEntity<Turno> response;

        if (odontologoService.buscarPorId(turno.getOdontologo().getId()) != null &&
                pacienteService.buscarPorId(turno.getPaciente().getId()) != null) {

            response = ResponseEntity.ok(turnoService.guardar(turno));
        } else {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;
    }
}
