package com.dh.ClinicMVC.entity.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties
public class DomicilioRequestDTO {
    private String calle;
    private Integer numero;
    private String localidad;
    private String provincia;
}
