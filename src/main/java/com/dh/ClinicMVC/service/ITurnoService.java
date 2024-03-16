package com.dh.ClinicMVC.service;

import com.dh.ClinicMVC.entity.Turno;

import java.util.List;
import java.util.Optional;

public interface ITurnoService {
    Turno guardar(Turno turno);

    List<Turno> listarTodos();

    Optional<Turno> buscarPorId(Long id);

    void eliminar(Long id);

    void actualizar(Turno turno);
}

