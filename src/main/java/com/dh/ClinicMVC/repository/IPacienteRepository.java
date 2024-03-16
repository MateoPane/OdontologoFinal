package com.dh.ClinicMVC.repository;

import com.dh.ClinicMVC.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IPacienteRepository extends JpaRepository<Paciente, Long> {
    Paciente guardar(Paciente paciente);
    List<Paciente> listarTodos();

    Optional<Paciente> buscarPorId(Long id);
    void eliminar(Long id);
    void actualizar(Paciente paciente);
}
