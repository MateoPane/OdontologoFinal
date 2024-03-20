package com.dh.ClinicMVC.controller;
import com.dh.ClinicMVC.entity.Paciente;
import com.dh.ClinicMVC.service.IPacienteService;
import com.dh.ClinicMVC.service.implementation.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private IPacienteService pacienteService;

    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping
    public Paciente guardar(@RequestBody Paciente paciente) {
        return pacienteService.guardar(paciente);
    }
    @GetMapping("/{id}")
    public Optional<Paciente> buscarPorId(@PathVariable Long id) {
        Optional<Paciente> paciente = pacienteService.buscarPorId(id);
        return paciente;
    }
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        pacienteService.eliminar(id);
    }
    @GetMapping("/listar")
    public List<Paciente> listarTodos(){
        return pacienteService.listarTodos();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizar(@PathVariable Long id, @RequestBody Paciente paciente) {
        ResponseEntity<String> response;
        Optional<Paciente> pacienteOptional = pacienteService.buscarPorId(id);
        if (pacienteOptional.isPresent()){
            pacienteService.actualizar(paciente);
            response = ResponseEntity.ok("Se actualizo el paciente con id " + paciente.getId());
        }else
            response = ResponseEntity.ok("No se puede actualizar el paciente");
        return response;
    }
}
