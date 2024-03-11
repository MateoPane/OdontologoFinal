package com.dh.ClinicMVC.service;
import com.dh.ClinicMVC.model.Paciente;

import java.util.List;

public interface IPacienteService {
    Paciente guardar(Paciente paciente);
    List<Paciente> listarTodos();

    Paciente buscarPorId(Integer id);
    void eliminar(Integer id);
    void actualizar(Paciente paciente);
}
