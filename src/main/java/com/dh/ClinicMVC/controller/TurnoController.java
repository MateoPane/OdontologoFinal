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
//    private static final Logger LOGGER = Logger.getLogger(TurnoController.class);
    @Autowired
    private ITurnoService turnoService;
    @Autowired
    private IOdontologoService odontologoService;
    @Autowired
    private IPacienteService pacienteService;

    @PostMapping("/guardar")
    public ResponseEntity<TurnoDTO> guardar(@RequestBody TurnoRequestDTO turnoDTO) {
        ResponseEntity<TurnoDTO> response;
        if (odontologoService.buscarPorId(turnoDTO.getOdontologoId()) != null &&
                pacienteService.buscarPorId(turnoDTO.getPacienteId()) != null) {
            response = ResponseEntity.ok(turnoService.guardar(turnoDTO));
        } else {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//            LOGGER.info("Hubo inconvenientes a la hora de guardar el turno.");
        }
//        LOGGER.info("Se guardo correctamente.");
        return response;
    }

    @GetMapping("/{id}")
    public TurnoDTO buscarPorId(@PathVariable Long id) {
//        LOGGER.info("Se encontro el id: " + id);
        return turnoService.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
//        LOGGER.info("Se elimino correctamente.");
        turnoService.eliminar(id);
    }

    @GetMapping("/listar")
    public Set<TurnoDTO> listarTodos() {
//        LOGGER.info("...Preguntar");
        return turnoService.listarTodos();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@RequestBody TurnoDTO turnoDTO) {
        turnoService.actualizar(turnoDTO);
//        LOGGER.info("Se actualizo el turno: " + turnoDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}


