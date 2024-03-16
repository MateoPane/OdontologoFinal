package com.dh.ClinicMVC.service.implementation;

import com.dh.ClinicMVC.dao.IDao;
import com.dh.ClinicMVC.dao.implementacion.PacienteDaoH2;
import com.dh.ClinicMVC.model.Paciente;
import com.dh.ClinicMVC.service.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService implements IPacienteService {
    private IDao<Paciente> iDao;

    @Autowired
    public PacienteService(PacienteDaoH2 pacienteDaoH2) {
        this.iDao = pacienteDaoH2;
    }

    @Override
    public Paciente guardar(Paciente paciente) {
        return iDao.guardar(paciente);
    }

    @Override
    public List<Paciente> listarTodos() {
        return iDao.listarTodos();
    }

    @Override
    public Paciente buscarPorId(Integer id) {
        return iDao.buscarPorId(id);
    }

    @Override
    public void eliminar(Integer id) {
        iDao.eliminar(id);
    }

    @Override
    public void actualizar(Paciente paciente) {
        iDao.actualizar(paciente);
    }
}
