package com.dh.ClinicMVC.repository;

import com.dh.ClinicMVC.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPacienteRepository extends JpaRepository<Paciente, Long> {

}
