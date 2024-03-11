package com.dh.ClinicMVC.model;

import java.time.LocalDate;

public class Turno {
    private Integer id;
    private LocalDate fecha;
    private Odontologo odontologo;
    private Paciente paciente;

    public Turno(Integer id, LocalDate fecha, Odontologo odontologo, Paciente paciente) {
            this.id = id;
            this.fecha = fecha;
            this.odontologo = odontologo;
            this.paciente = paciente;
    }

    public Turno() {
    }

    public Integer getId() {
            return id;
    }

    public void setId(Integer id) {
            this.id = id;
    }

    public LocalDate getFecha() {
            return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Odontologo getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    }
