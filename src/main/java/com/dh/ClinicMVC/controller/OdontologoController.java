package com.dh.ClinicMVC.controller;

import com.dh.ClinicMVC.entity.DTO.OdontologoDTO;
import com.dh.ClinicMVC.service.IOdontologoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
//    private static final Logger LOGGER = Logger.getLogger(OdontologoController.class);
    @Autowired
    IOdontologoService odontologoService;
    @PostMapping("/guardar")
    public void guardar(@RequestBody OdontologoDTO odontologoDTO) {
        odontologoService.guardar(odontologoDTO);
//        LOGGER.info("Se guardo correctamente.");
    }
    @GetMapping("/{id}")
    public OdontologoDTO buscarPorId(@PathVariable Long id) {
//        LOGGER.info("Se encontro el id: " + id);
        return odontologoService.buscarPorId(id);
    }
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
//        LOGGER.info("Se elimino correctamente.");
        odontologoService.eliminar(id);
    }
    @GetMapping("/listar")
    public Set<OdontologoDTO> listarTodos(){
        return odontologoService.listarTodos();
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@RequestBody OdontologoDTO odontologoDTO) {
        odontologoService.actualizar(odontologoDTO);
//        LOGGER.info("Se actualizo el turno: " + odontologoDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}