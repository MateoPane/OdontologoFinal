package com.dh.ClinicMVC.service;

import com.dh.ClinicMVC.entity.DTO.OdontologoDTO;
import java.util.Set;

public interface IOdontologoService {
    void guardar (OdontologoDTO odontologoDTO);

    Set<OdontologoDTO> listarTodos();

    OdontologoDTO buscarPorId(Long id);
    void eliminar(Long id);
    void actualizar(OdontologoDTO odontologoDTO);

}
