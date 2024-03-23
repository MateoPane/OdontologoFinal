package com.dh.ClinicMVC.service;

import com.dh.ClinicMVC.entity.DTO.TurnoDTO;
import com.dh.ClinicMVC.entity.DTO.TurnoRequestDTO;

import java.util.Set;

public interface ITurnoService {


    Set<TurnoDTO> listarTodos();

    TurnoDTO buscarPorId(Long id);

    void eliminar(Long id);

    void actualizar(TurnoDTO turnoDTO);

    TurnoDTO guardar(TurnoRequestDTO turnoDTO);

}

