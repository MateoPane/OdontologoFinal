package com.dh.ClinicMVC.repository;

import com.dh.ClinicMVC.entity.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IOdontologoRepository extends JpaRepository<Odontologo, Long> {
    Odontologo guardar (Odontologo odontologo);

    List<Odontologo> listarTodos();

    Optional<Odontologo> buscarPorId(Integer id);
    void eliminar(Integer id);
    void actualizar(Odontologo odontologo);
}
