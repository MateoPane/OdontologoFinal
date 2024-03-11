package com.dh.ClinicMVC.dao.implementacion;

import com.dh.ClinicMVC.dao.IDao;
import com.dh.ClinicMVC.model.Turno;

import java.util.ArrayList;
import java.util.List;

public class TurnoDaoList implements IDao<Turno> {
    private List<Turno> turnoList = new ArrayList<>();
    @Override
    public Turno guardar(Turno turno) {
        turnoList.add(turno);
        return turno;
    }

    @Override
    public Turno buscarPorId(Integer id) {
        Turno turnoADevolver = null;
        for (Turno t: turnoList) {
            if(t.getId().equals(id)){
                turnoADevolver = t;
                return turnoADevolver;
            }
        }
        return turnoADevolver;

    }

    @Override
    public void eliminar(Integer id) {
        
    }

    @Override
    public void actualizar(Turno turno) {

    }

    @Override
    public List<Turno> listarTodos() {
        return null;
    }
}
