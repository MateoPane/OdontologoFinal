package com.dh.ClinicMVC.entity.DTO;

import com.dh.ClinicMVC.entity.Odontologo;
import com.dh.ClinicMVC.entity.Paciente;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TurnoRequestDTO {
    private LocalDate fecha;
    private Long odontologoId;
    private Long pacienteId;
}
