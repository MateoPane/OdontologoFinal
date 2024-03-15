package com.dh.ClinicMVC.dao.implementacion;

import com.dh.ClinicMVC.dao.IDao;
import com.dh.ClinicMVC.model.Turno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TurnoDaoList implements IDao<Turno> {
    private List<Turno> turnoList;

    @Autowired
    public TurnoDaoList(){
        this.turnoList = new ArrayList<>();
    }
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
        Turno turnoAEliminar= null;
        for (Turno t: turnoList) {
            if(t.getId().equals(id)){
                turnoAEliminar = t;
                break;
            }
        }
        if (turnoAEliminar == null) {
            throw new RuntimeException("Turno con id " + id + " no encontrado");
        }
            turnoList.remove(turnoAEliminar);
    }

    @Override
    public void actualizar(Turno turno) {
        for(Turno turnoDado : turnoList){
            if (turnoDado.getId().equals(turno.getId())) {
                turnoDado.setFecha(turno.getFecha());
                turnoDado.setOdontologo(turno.getOdontologo());
                turnoDado.setPaciente(turno.getPaciente());
                break;
            }
        }

    }

    @Override
    public List<Turno> listarTodos() {
        return new ArrayList<>(turnoList);
    }
}
