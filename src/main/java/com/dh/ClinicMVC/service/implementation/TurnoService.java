package com.dh.ClinicMVC.service.implementation;

import com.dh.ClinicMVC.entity.Turno;
import com.dh.ClinicMVC.repository.ITurnoRepository;
import com.dh.ClinicMVC.service.ITurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnoService implements ITurnoService {

    private ITurnoRepository turnoRepository;
@Autowired
    public TurnoService(ITurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }

    @Override
    public Turno guardar(Turno turno) {
        return turnoRepository.save(turno);
    }

    @Override
    public List<Turno> listarTodos() {
        return turnoRepository.findAll();
    }

    @Override
    public Optional<Turno> buscarPorId(Long id) {
        Optional<Turno> turnoOptional = turnoRepository.findById(id);
        if (turnoOptional.isPresent()) {
            return turnoOptional;
        }else {
            return null;
        }
    }

    @Override
    public void eliminar(Long id) {
        turnoRepository.deleteById(id);
    }

    @Override
    public void actualizar(Turno turno) {
        turnoRepository.save(turno);
    }
}