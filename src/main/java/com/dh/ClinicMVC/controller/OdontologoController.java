package com.dh.ClinicMVC.controller;

import com.dh.ClinicMVC.entity.OdontologoDTO;
import com.dh.ClinicMVC.service.IOdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    @Autowired
    IOdontologoService odontologoService;
    @PostMapping("/guardar")
    public void guardar(@RequestBody OdontologoDTO odontologoDTO) {
        odontologoService.guardar(odontologoDTO);
    }
    @GetMapping("/{id}")
    public OdontologoDTO buscarPorId(@PathVariable Long id) {
        return odontologoService.buscarPorId(id);
    }
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        odontologoService.eliminar(id);
    }
    @GetMapping("/listar")
    public Set<OdontologoDTO> listarTodos(){
        return odontologoService.listarTodos();
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@RequestBody OdontologoDTO odontologoDTO) {
        odontologoService.actualizar(odontologoDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}