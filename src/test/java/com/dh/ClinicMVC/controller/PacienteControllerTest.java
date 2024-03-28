package com.dh.ClinicMVC.controller;

import com.dh.ClinicMVC.entity.DTO.PacienteDTO;
import com.dh.ClinicMVC.service.IPacienteService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PacienteControllerTest {
    @Autowired
    private IPacienteService pacienteService;

    @Test
    void guardar() {
        PacienteDTO pacienteDTO = new PacienteDTO();
        pacienteDTO.setId(1L);
        pacienteDTO.setNombre("Mateo");
        pacienteDTO.setApellido("Pane");
        pacienteDTO.setFechaIngreso(LocalDate.now());
        pacienteService.guardar(pacienteDTO);
        PacienteDTO pacienteDTO1 = pacienteService.buscarPorId(pacienteDTO.getId());

        assertTrue(pacienteDTO1 != null);
    }

    @Test
    void buscarPorId() {
        PacienteDTO pacienteDTO = new PacienteDTO();
        pacienteDTO.setId(1L);
        pacienteDTO.setNombre("Mateo");
        pacienteDTO.setApellido("Pane");
        pacienteDTO.setFechaIngreso(LocalDate.now());
        pacienteService.guardar(pacienteDTO);
        PacienteDTO pacienteDTO1 = pacienteService.buscarPorId(pacienteDTO.getId());

        assertEquals(pacienteDTO1, pacienteDTO);

    }

    @Test
    void eliminar() {
        PacienteDTO pacienteDTO = new PacienteDTO();
        pacienteDTO.setId(1L);
        pacienteDTO.setNombre("Mateo");
        pacienteDTO.setApellido("Pane");
        pacienteDTO.setFechaIngreso(LocalDate.now());

        pacienteService.guardar(pacienteDTO);
        pacienteService.eliminar(pacienteDTO.getId());
        PacienteDTO pacienteDTOEliminado = pacienteService.buscarPorId(pacienteDTO.getId());

        assertTrue(pacienteDTOEliminado == null);
    }

    @Test
    void listarTodos() {
        PacienteDTO pacienteDTO = new PacienteDTO();
        pacienteDTO.setId(1L);
        pacienteDTO.setNombre("Mateo");
        pacienteDTO.setApellido("Pane");
        pacienteDTO.setFechaIngreso(LocalDate.now());

        pacienteService.guardar(pacienteDTO);

        Set<PacienteDTO> pacienteDTOS = pacienteService.listarTodos();

        assertTrue(pacienteDTOS.size() != 0);
    }
    @Test
    void actualizar() {
        PacienteDTO pacienteDTO = new PacienteDTO();
        pacienteDTO.setId(1L);
        pacienteDTO.setNombre("Mateo");
        pacienteDTO.setApellido("Pane");
        pacienteDTO.setFechaIngreso(LocalDate.now());

        pacienteService.guardar(pacienteDTO);
        PacienteDTO pacienteDTO1 = pacienteService.buscarPorId(pacienteDTO.getId());

        pacienteDTO1.setApellido("Ramirez");
        pacienteService.actualizar(pacienteDTO1);

        PacienteDTO pacienteDTOActualizado = pacienteService.buscarPorId(pacienteDTO1.getId());

        assertEquals("Ramirez", pacienteDTOActualizado.getApellido());
        assertEquals("Mateo", pacienteDTOActualizado.getNombre());
    }
}