package com.dh.ClinicMVC.controller;

import com.dh.ClinicMVC.model.Odontologo;
import com.dh.ClinicMVC.service.IOdontologoService;
import com.dh.ClinicMVC.service.implementation.OdontologoService;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

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
}