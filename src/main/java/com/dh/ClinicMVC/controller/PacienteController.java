package com.dh.ClinicMVC.controller;
import com.dh.ClinicMVC.entity.DTO.PacienteDTO;
import com.dh.ClinicMVC.exception.ResourceNotFoundException;
import com.dh.ClinicMVC.service.IPacienteService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
        LOGGER.info("Se guardo correctamente.");
         pacienteService.guardar(pacienteDTO);
         String guardadoExitoso = "Se guardo correctamente el paciente.";
         return ResponseEntity.ok().body(guardadoExitoso);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> buscarPorId(@PathVariable Long id)  {
        PacienteDTO pacienteDTO = pacienteService.buscarPorId(id);
        LOGGER.info("Se encontro el id: " + id);
        return ResponseEntity.ok(pacienteDTO);
    }
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        LOGGER.info("Se elimino correctamente.");
        pacienteService.eliminar(id);
    }
    @GetMapping("/listar")
    public Set<PacienteDTO> listarTodos(){
        return pacienteService.listarTodos();
    }

    @PutMapping
    public ResponseEntity<String> actualizar(@PathVariable Long id, @RequestBody PacienteDTO pacienteDTO) {
        ResponseEntity<String> response;
        PacienteDTO pacienteOptional = pacienteService.buscarPorId(id);
        if (pacienteOptional != null){
            pacienteService.actualizar(pacienteDTO);
            response = ResponseEntity.ok("Se actualizo el paciente con id " + pacienteDTO.getId());
        }else
            response = ResponseEntity.ok("No se puede actualizar el paciente");
        return response;
    }
}

