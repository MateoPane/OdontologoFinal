package com.dh.ClinicMVC.dao.implementacion;

import com.dh.ClinicMVC.dao.BD;
import com.dh.ClinicMVC.dao.IDao;
import com.dh.ClinicMVC.model.Turno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TurnoDaoH2 implements IDao<Turno> {
    private static final String INSERT_TURNO = "INSERT INTO TURNOS (FECHA, ODONTOLOGO_ID, PACIENTE_ID) VALUES (?, ?, ?)";
    private static final String SELECT_ALL = "SELECT * FROM TURNOS";
    private static final String SELECT_ID = "SELECT * FROM TURNOS WHERE ID = ?";
    private static final String DELETE_TURNO = "DELETE FROM TURNOS WHERE ID = ?";
    private static final String UPDATE_TURNO = "UPDATE TURNOS SET FECHA=?, ODONTOLOGO_ID=?, PACIENTE_ID=? WHERE ID=?";

    @Override
    public Turno guardar(Turno turno) {
        Connection connection = null;

        try {
            connection = BD.getConnection();
            PreparedStatement psInsert = connection.prepareStatement(INSERT_TURNO, Statement.RETURN_GENERATED_KEYS);
            psInsert.setDate(1, java.sql.Date.valueOf(turno.getFecha()));
            psInsert.setInt(2, turno.getOdontologo().getId());
            psInsert.setInt(3, turno.getPaciente().getId());

            psInsert.execute();

            ResultSet rs = psInsert.getGeneratedKeys();
            while (rs.next()) {
                turno.setId(rs.getInt(1));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return turno;
    }

    @Override
    public List<Turno> listarTodos() {
        Connection connection = null;
        List<Turno> turnoList = new ArrayList<>();
        Turno turno = null;

        try {
            connection = BD.getConnection();
            PreparedStatement psSelect = connection.prepareStatement(SELECT_ALL);
            ResultSet rs = psSelect.executeQuery();

            while (rs.next()) {
                turno = new Turno();
                turno.setId(rs.getInt(1));
                turno.setFecha(rs.getDate(2).toLocalDate());

                turnoList.add(turno);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return turnoList;
    }

    @Override
    public Turno buscarPorId(Integer id) {
        Connection connection = null;
        Turno turno = null;

        try {
            connection = BD.getConnection();
            PreparedStatement psSearchById = connection.prepareStatement(SELECT_ID);
            psSearchById.setInt(1, id);
            ResultSet rs = psSearchById.executeQuery();

            while (rs.next()) {
                turno = new Turno();
                turno.setId(rs.getInt(1));
                turno.setFecha(rs.getDate(2).toLocalDate());
                // Puedes cargar más atributos según sea necesario (odontólogo, paciente, etc.)
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return turno;
    }

    @Override
    public void eliminar(Integer id) {
        Connection connection = null;

        try {
            connection = BD.getConnection();
            PreparedStatement psDelete = connection.prepareStatement(DELETE_TURNO);
            psDelete.setInt(1, id);

            int rowsAffected = psDelete.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Turno eliminado con éxito. ID: " + id);
            } else {
                System.out.println("No se encontró el turno con ID: " + id + " para eliminar.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void actualizar(Turno turno) {
        Connection connection = null;

        try {
            connection = BD.getConnection();
            PreparedStatement psUpdate = connection.prepareStatement(UPDATE_TURNO);
            psUpdate.setDate(1, java.sql.Date.valueOf(turno.getFecha()));
            psUpdate.setInt(2, turno.getOdontologo().getId());
            psUpdate.setInt(3, turno.getPaciente().getId());
            psUpdate.setInt(4, turno.getId());

            psUpdate.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}


