package com.dh.ClinicMVC.repository;

import com.dh.ClinicMVC.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ITurnoRepository extends JpaRepository<Turno, Long> {
    Turno guardar(Turno turno);

    List<Turno> listarTodos();

    Optional<Turno> buscarPorId(Long id);

    void eliminar(Long id);

    void actualizar(Turno turno);
}
