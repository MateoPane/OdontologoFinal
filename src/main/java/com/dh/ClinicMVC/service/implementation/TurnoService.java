package com.dh.ClinicMVC.service.implementation;

import com.dh.ClinicMVC.dao.IDao;
import com.dh.ClinicMVC.dao.implementacion.TurnoDaoH2;
import com.dh.ClinicMVC.model.Turno;
import com.dh.ClinicMVC.service.ITurnoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoService implements ITurnoService {

    private IDao<Turno> iDao;

    public TurnoService() {
        iDao = new TurnoDaoH2();
    }

    @Override
    public Turno guardar(Turno turno) {
        return iDao.guardar(turno);
    }

    @Override
    public List<Turno> listarTodos() {
        return iDao.listarTodos();
    }

    @Override
    public Turno buscarPorId(Integer id) {
        return iDao.buscarPorId(id);
    }

    @Override
    public void eliminar(Integer id) {
        iDao.eliminar(id);
    }

    @Override
    public void actualizar(Turno turno) {
        iDao.actualizar(turno);
    }
}