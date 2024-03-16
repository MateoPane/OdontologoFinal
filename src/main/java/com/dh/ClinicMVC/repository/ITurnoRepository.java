package com.dh.ClinicMVC.repository;

import com.dh.ClinicMVC.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITurnoRepository extends JpaRepository<Turno, Integer> {
}
