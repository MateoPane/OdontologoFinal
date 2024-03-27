package com.dh.ClinicMVC.controller;

import com.dh.ClinicMVC.entity.DTO.OdontologoDTO;
import com.dh.ClinicMVC.entity.DTO.PacienteDTO;
import com.dh.ClinicMVC.entity.Odontologo;
import com.dh.ClinicMVC.service.IOdontologoService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class OdontologoControllerTest {
    @Autowired
    private IOdontologoService odontologoService;

    //    @BeforeAll
//    public static void creandoOdontologo() {
//
//    }
    @Test
    void guardar() {
        OdontologoDTO odontologoDTO = new OdontologoDTO();
        odontologoDTO.setId(1L);
        odontologoDTO.setNombre("Marina");
        odontologoDTO.setApellido("bar");
        odontologoDTO.setMatricula("mat123");
        odontologoService.guardar(odontologoDTO);
        OdontologoDTO odontologoDTO1 = odontologoService.buscarPorId(odontologoDTO.getId());
        assertTrue(odontologoDTO1 != null);
    }

    @Test
    void buscarPorId() {

    }
}