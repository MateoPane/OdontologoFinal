package com.dh.ClinicMVC.controller;

import com.dh.ClinicMVC.model.Odontologo;
import com.dh.ClinicMVC.model.Paciente;
import com.dh.ClinicMVC.service.IOdontologoService;
import com.dh.ClinicMVC.service.implementation.OdontologoService;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    private IOdontologoService odontologoService;

    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @PostMapping("/guardar")
    public Odontologo guardar(@RequestBody Odontologo odontologo) {
        return odontologoService.guardar(odontologo);
    }
    @GetMapping("/{id}")
    public Odontologo buscarPorId(@PathVariable Integer id) {
        Odontologo odontologo = odontologoService.buscarPorId(id);
        return odontologo;
    }
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        odontologoService.eliminar(id);
    }
    @GetMapping("/listar")
    public List<Odontologo> listarTodos(){
        return odontologoService.listarTodos();
    }
}