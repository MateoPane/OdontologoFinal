package com.dh.ClinicMVC.controller;

import com.dh.ClinicMVC.entity.Odontologo;
import com.dh.ClinicMVC.service.IOdontologoService;
import com.dh.ClinicMVC.service.implementation.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    private IOdontologoService odontologoService;

    @Autowired
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @PostMapping("/guardar")
    public Odontologo guardar(@RequestBody Odontologo odontologo) {
        return odontologoService.guardar(odontologo);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarPorId(@PathVariable Long id) {
        Optional<Odontologo> odontologoOptional = odontologoService.buscarPorId(id);
        if (odontologoOptional.isPresent()) {
            Odontologo odontologo = odontologoOptional.get();
            return ResponseEntity.ok(odontologo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        odontologoService.eliminar(id);
    }
    @GetMapping("/listar")
    public List<Odontologo> listarTodos(){
        return odontologoService.listarTodos();
    }
    @PutMapping
    public ResponseEntity<String> actualizar(@RequestBody Odontologo odontologo) {
        ResponseEntity<String> response;
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorId(odontologo.getId());
        if (odontologoBuscado != null){
            odontologoService.actualizar(odontologo);
            response = ResponseEntity.ok("Se actualizo el odontologo con id " + odontologo.getId());
        }else {
           response = ResponseEntity.ok().body("No se puede actualizar el odontologo");
        }
        return response;
    }
}