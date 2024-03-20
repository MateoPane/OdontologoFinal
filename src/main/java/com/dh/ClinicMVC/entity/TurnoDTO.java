package com.dh.ClinicMVC.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class TurnoDTO {
    private Long id;

    private LocalDate fecha;
    private Odontologo odontologo;
    private Paciente paciente;
}
