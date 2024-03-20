package com.dh.ClinicMVC.service;
import com.dh.ClinicMVC.entity.DTO.PacienteDTO;

import java.util.Set;

public interface IPacienteService {
    void guardar(PacienteDTO pacienteDTO);

    Set<PacienteDTO> listarTodos();

    PacienteDTO buscarPorId(Long id);

    void eliminar(Long id);

    void actualizar(PacienteDTO pacienteDTO);

}