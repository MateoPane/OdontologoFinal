package com.dh.ClinicMVC.controller;

import com.dh.ClinicMVC.entity.DTO.OdontologoDTO;
import com.dh.ClinicMVC.exception.ResourceNotFoundException;
import com.dh.ClinicMVC.service.IOdontologoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OdontologoControllerTest {
    @Autowired
    private IOdontologoService odontologoService;

    @Autowired
    private OdontologoController odontologoController;

    @Test
    void guardar() {
        OdontologoDTO odontologo = new OdontologoDTO();
        odontologo.setId(2L);
        odontologo.setNombre("Marina");
        odontologo.setApellido("bar");
        odontologo.setMatricula("mat123");

        odontologoController.guardar(odontologo);

        OdontologoDTO odontologoDTO1 = odontologoService.buscarPorId(odontologo.getId());
        assertTrue(odontologoDTO1 != null);
        assertEquals(odontologo.getId(), odontologoDTO1.getId());
        assertEquals(odontologo.getApellido(), odontologoDTO1.getApellido());
        assertEquals(odontologo.getNombre(), odontologoDTO1.getNombre());
        assertEquals(odontologo.getMatricula(), odontologoDTO1.getMatricula());
    }

    @Test
    void buscarPorId() {
        OdontologoDTO odontologo = new OdontologoDTO();
        odontologo.setId(3L);
        odontologo.setNombre("Marina");
        odontologo.setApellido("bar");
        odontologo.setMatricula("mat123");

        odontologoService.guardar(odontologo);
        OdontologoDTO odontologoDTO1 = odontologoService.buscarPorId(odontologo.getId());

        assertEquals(odontologo.getId(), odontologoDTO1.getId());
        assertEquals(odontologo.getApellido(), odontologoDTO1.getApellido());
        assertEquals(odontologo.getNombre(), odontologoDTO1.getNombre());
        assertEquals(odontologo.getMatricula(), odontologoDTO1.getMatricula());
    }

    @Test
    void eliminar() {
        OdontologoDTO odontologo = new OdontologoDTO();
        odontologo.setId(1L);
        odontologo.setNombre("Marina");
        odontologo.setApellido("bar");
        odontologo.setMatricula("mat123");

        odontologoService.guardar(odontologo);
        odontologoController.eliminar(odontologo.getId());

        assertThrows(ResourceNotFoundException.class, () -> {
            odontologoService.buscarPorId(odontologo.getId());
        });
    }


    @Test
    void listarTodos() {
        OdontologoDTO odontologo = new OdontologoDTO();
        odontologo.setId(5L);
        odontologo.setNombre("Marina");
        odontologo.setApellido("bar");
        odontologo.setMatricula("mat123");

        odontologoService.guardar(odontologo);

        Set<OdontologoDTO> odontologoDTOS = odontologoService.listarTodos();

        assertTrue(odontologoDTOS.size() != 0);
    }

    @Test
    void actualizar() {
        OdontologoDTO odontologo = new OdontologoDTO();
        odontologo.setId(1L);
        odontologo.setNombre("Marina");
        odontologo.setApellido("bar");
        odontologo.setMatricula("mat123");

        odontologoService.guardar(odontologo);
        OdontologoDTO odontologoDTOEncontrado = odontologoService.buscarPorId(odontologo.getId());

        odontologoDTOEncontrado.setApellido("Perez");
        odontologoDTOEncontrado.setNombre("Rodrigo");
        odontologoService.actualizar(odontologoDTOEncontrado);

        OdontologoDTO odontologoDTOActualizado = odontologoService.buscarPorId(odontologoDTOEncontrado.getId());

        assertEquals("Perez", odontologoDTOActualizado.getApellido());
        assertEquals("Rodrigo", odontologoDTOActualizado.getNombre());
    }
}