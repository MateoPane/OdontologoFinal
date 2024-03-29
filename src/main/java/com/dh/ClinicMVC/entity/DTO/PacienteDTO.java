package com.dh.ClinicMVC.entity.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class PacienteDTO {

    private Long id;
    private String nombre;
    private String apellido;
    private LocalDate fechaIngreso;
}
