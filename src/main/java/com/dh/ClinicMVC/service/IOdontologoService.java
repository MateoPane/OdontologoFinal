package com.dh.ClinicMVC.service;

import com.dh.ClinicMVC.entity.Odontologo;

import java.util.List;
import java.util.Optional;

public interface IOdontologoService {
    Odontologo guardar (Odontologo odontologo);

    List<Odontologo> listarTodos();

    Optional<Odontologo> buscarPorId(Integer id);
    void eliminar(Integer id);
    void actualizar(Odontologo odontologo);

}
