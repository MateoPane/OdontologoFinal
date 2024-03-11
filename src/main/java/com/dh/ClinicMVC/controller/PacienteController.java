package com.dh.ClinicMVC.controller;
import com.dh.ClinicMVC.model.Paciente;
import com.dh.ClinicMVC.service.IPacienteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private IPacienteService pacienteService;

    public PacienteController(IPacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping
    public Paciente guardar(@RequestBody Paciente paciente) {
        return pacienteService.guardar(paciente);
    }
    @GetMapping("/{id}")
    public Paciente buscarPorId(@PathVariable Integer id) {
        Paciente paciente = pacienteService.buscarPorId(id);
        return paciente;
    }
    @DeleteMapping("/{id}")
    public void Paciente(@PathVariable Integer id) {
        pacienteService.eliminar(id);
    }
    @GetMapping("/listar")
    public List<Paciente> listarTodos(){
        return pacienteService.listarTodos();
    }
}
