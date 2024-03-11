package com.dh.ClinicMVC.service;

import com.dh.ClinicMVC.model.Odontologo;

import java.util.List;

public interface IOdontologoService {
    Odontologo guardar (Odontologo odontologo);

    List<Odontologo> listarTodos();

    Odontologo buscarPorId(Integer id);
    void eliminar(Integer id);
    void actualizar(Odontologo odontologo);

}
