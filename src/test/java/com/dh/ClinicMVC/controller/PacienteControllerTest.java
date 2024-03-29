package com.dh.ClinicMVC.controller;

import com.dh.ClinicMVC.entity.DTO.DomicilioRequestDTO;
import com.dh.ClinicMVC.entity.DTO.PacienteDTO;
import com.dh.ClinicMVC.entity.Domicilio;
import com.dh.ClinicMVC.service.IPacienteService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PacienteControllerTest {
    @Autowired
    private IPacienteService pacienteService;

    private PacienteDTO pacienteDTO;
    @BeforeEach
    void setup(){
        this.pacienteDTO = new PacienteDTO();
        this.pacienteDTO.setId(1L);
        this.pacienteDTO.setNombre("Mateo");
        this.pacienteDTO.setApellido("Pane");
        this.pacienteDTO.setFechaIngreso(LocalDate.now());
        this.pacienteDTO.setDni("44220152");
        this.pacienteDTO.setDomicilio(pacienteDTO.getDomicilio());
    }
    @Test
    void guardar() {
        pacienteService.guardar(this.pacienteDTO);
        PacienteDTO pacienteDTO1 = pacienteService.buscarPorId(this.pacienteDTO.getId());

        assertTrue(pacienteDTO1 != null);
    }

    @Test
    void buscarPorId() {
        pacienteService.guardar(pacienteDTO);
        PacienteDTO pacienteDTO1 = pacienteService.buscarPorId(pacienteDTO.getId());

        assertEquals(pacienteDTO, pacienteDTO1);

    }

    @Test
    void eliminar() {
        pacienteService.guardar(this.pacienteDTO);
        pacienteService.eliminar(this.pacienteDTO.getId());
        PacienteDTO pacienteDTOEliminado = pacienteService.buscarPorId(this.pacienteDTO.getId());

        assertNull(pacienteDTOEliminado);
    }

    @Test
    void listarTodos() {
        pacienteService.guardar(this.pacienteDTO);

        Set<PacienteDTO> pacienteDTOS = pacienteService.listarTodos();

        assertTrue(pacienteDTOS.size() != 0);
    }
    @Test
    void actualizar() {
        pacienteService.guardar(this.pacienteDTO);
        PacienteDTO pacienteDTOEncontrado = pacienteService.buscarPorId(this.pacienteDTO.getId());

        pacienteDTOEncontrado.setApellido("Ramirez");
        pacienteDTOEncontrado.setNombre("Mateo");
        pacienteService.actualizar(pacienteDTOEncontrado);

        PacienteDTO pacienteDTOActualizado = pacienteService.buscarPorId(pacienteDTOEncontrado.getId());

        assertEquals("Ramirez", pacienteDTOActualizado.getApellido());
        assertEquals("Mateo", pacienteDTOActualizado.getNombre());
    }
}