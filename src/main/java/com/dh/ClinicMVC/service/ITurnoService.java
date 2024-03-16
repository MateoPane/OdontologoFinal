package com.dh.ClinicMVC.service;

import com.dh.ClinicMVC.entity.Turno;

import java.util.List;
import java.util.Optional;

public interface ITurnoService {
    Turno guardar(Turno turno);

    List<Turno> listarTodos();

    Optional<Turno> buscarPorId(Integer id);

    void eliminar(Integer id);

    void actualizar(Turno turno);
}

