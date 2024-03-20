package com.dh.ClinicMVC.service;

import com.dh.ClinicMVC.entity.TurnoDTO;
import java.util.Set;

public interface ITurnoService {
    void guardar(TurnoDTO turnoDTO);

    Set<TurnoDTO> listarTodos();

    TurnoDTO buscarPorId(Long id);

    void eliminar(Long id);

    void actualizar(TurnoDTO turnoDTO);
}

