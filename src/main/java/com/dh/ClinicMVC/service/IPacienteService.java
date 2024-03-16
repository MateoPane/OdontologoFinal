package com.dh.ClinicMVC.service;
import com.dh.ClinicMVC.entity.Paciente;

import java.util.List;
import java.util.Optional;

public interface IPacienteService {
    Paciente guardar(Paciente paciente);
    List<Paciente> listarTodos();

    Optional<Paciente> buscarPorId(Integer id);
    void eliminar(Integer id);
    void actualizar(Paciente paciente);
}
