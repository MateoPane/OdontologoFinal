package com.dh.ClinicMVC.repository;

import com.dh.ClinicMVC.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IPacienteRepository extends JpaRepository<Paciente, Long> {

}
