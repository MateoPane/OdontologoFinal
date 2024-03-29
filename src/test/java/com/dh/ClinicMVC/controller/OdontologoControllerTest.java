package com.dh.ClinicMVC.controller;
import com.dh.ClinicMVC.entity.DTO.OdontologoDTO;
import com.dh.ClinicMVC.entity.DTO.PacienteDTO;
import com.dh.ClinicMVC.service.IOdontologoService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OdontologoControllerTest {
    @Autowired
    private IOdontologoService odontologoService;
    private OdontologoDTO odontologoDTO;
    @BeforeEach
    void setup() {
        this.odontologoDTO = new OdontologoDTO();
        this.odontologoDTO.setId(1L);
        this.odontologoDTO.setNombre("Marina");
        this.odontologoDTO.setApellido("bar");
        this.odontologoDTO.setMatricula("mat123");
    }
    @Test
    void guardar() {
        odontologoService.guardar(this.odontologoDTO);
        OdontologoDTO odontologoDTO1 = odontologoService.buscarPorId(this.odontologoDTO.getId());
        assertTrue(odontologoDTO1 != null);
    }

    @Test
    void buscarPorId() {
    odontologoService.guardar(this.odontologoDTO);
    OdontologoDTO odontologoDTO1 = odontologoService.buscarPorId(this.odontologoDTO.getId());

    assertEquals(this.odontologoDTO, odontologoDTO1);
    }
    @Test
    void eliminar() {
        odontologoService.guardar(this.odontologoDTO);
        odontologoService.eliminar(odontologoDTO.getId());
        OdontologoDTO odontologoDTOEliminado = odontologoService.buscarPorId(this.odontologoDTO.getId());

        assertNull(odontologoDTOEliminado);
    }

    @Test
    void listarTodos() {
        odontologoService.guardar(this.odontologoDTO);

        Set<OdontologoDTO> odontologoDTOS = odontologoService.listarTodos();

        assertTrue(odontologoDTOS.size() != 0);
    }
    @Test
    void actualizar() {
        odontologoService.guardar(this.odontologoDTO);
        OdontologoDTO odontologoDTOEncontrado = odontologoService.buscarPorId(this.odontologoDTO.getId());

        odontologoDTOEncontrado.setApellido("Perez");
        odontologoDTOEncontrado.setNombre("Rodrigo");
        odontologoService.actualizar(odontologoDTOEncontrado);

        OdontologoDTO odontologoDTOActualizado = odontologoService.buscarPorId(odontologoDTOEncontrado.getId());

        assertEquals("Perez", odontologoDTOActualizado.getApellido());
        assertEquals("Rodrigo", odontologoDTOActualizado.getNombre());
    }
}