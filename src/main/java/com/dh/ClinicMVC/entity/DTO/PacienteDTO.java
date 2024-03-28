package com.dh.ClinicMVC.entity.DTO;

import com.dh.ClinicMVC.entity.Domicilio;
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
    private String dni;
    private Domicilio domicilio;

    @Override
    public String toString() {
        return "PacienteDTO{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", fechaIngreso=" + fechaIngreso +
                ", dni='" + dni + '\'' +
                ", domicilio=" + domicilio +
                '}';
    }
}
