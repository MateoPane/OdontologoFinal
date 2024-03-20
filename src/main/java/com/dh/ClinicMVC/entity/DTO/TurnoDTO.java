package com.dh.ClinicMVC.entity.DTO;

import com.dh.ClinicMVC.entity.Odontologo;
import com.dh.ClinicMVC.entity.Paciente;
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
