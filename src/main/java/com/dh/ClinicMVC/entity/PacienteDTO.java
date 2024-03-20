package com.dh.ClinicMVC.entity;

import lombok.Getter;
import lombok.Setter;

public class PacienteDTO {
    @Setter
    @Getter

    private Long id;
    private String nombre;
    private String apellido;
}
