package com.dh.ClinicMVC.controller;

import com.dh.ClinicMVC.model.Odontologo;
import com.dh.ClinicMVC.service.IOdontologoService;
import com.dh.ClinicMVC.service.implementation.OdontologoService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/odontologo")
public class indexOdontologoController {

    private IOdontologoService odontologoService;

    public indexOdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }


    @PostMapping("/registrar")
    public Odontologo guardar(@RequestBody Odontologo odontologo){
        return odontologoService.guardar(odontologo);
    }
    //métodos -> endpoints -> GET/POST/PUT/DELETE
    @GetMapping("/id")
    public String buscarOdontologoPorId(Model model, @RequestParam("id") Integer id) {
        Odontologo odontologo = odontologoService.buscarPorId(id);
        model.addAttribute("nombre", odontologo.getNombre());
        model.addAttribute("apellido", odontologo.getApellido());
        model.addAttribute("matricula", odontologo.getMatricula());
        return "buscarOdontologo";
    }
    @DeleteMapping("/id")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Integer id) {
        odontologoService.eliminar(id);
        return ResponseEntity.ok("Odontólogo eliminado correctamente");
    }
    @PutMapping("/id")
    public ResponseEntity<String> actualizarOdontologo(@PathVariable Integer id, @RequestBody Odontologo odontologo) {
        odontologo.setId(id);
        odontologoService.actualizar(odontologo);
        return ResponseEntity.ok("Odontólogo actualizado correctamente");
    }
}

