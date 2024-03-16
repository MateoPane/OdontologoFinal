package com.dh.ClinicMVC.dao.implementacion;

import com.dh.ClinicMVC.entity.Domicilio;
import com.dh.ClinicMVC.entity.Paciente;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PacienteDaoH2 implements IDao<Paciente> {

    private static final Logger LOGGER = Logger.getLogger(DomicilioDaoH2.class);

    private static final String INSERT_PACIENTES = "INSERT INTO PACIENTES (NOMBRE, APELLIDO, DNI, FECHA_INGRESO, DOMICILIO_ID) VALUES (?,?,?,?,?)";
    private static final String SELECT_ID = "SELECT * FROM PACIENTES WHERE ID = ?";
    private static final String DELETE_PACIENTE = "DELETE FROM PACIENTES WHERE ID = ?";
    private static final String UPDATE_PACIENTE = "UPDATE PACIENTES SET NOMBRE=?, APELLIDO=?, DNI=?, FECHA_INGRESO=?, DOMICILIO_ID=? WHERE ID=?";
    private static final String SELECT_ALL = "SELECT * FROM PACIENTES";
    @Override
    public Paciente guardar(Paciente paciente) {
        LOGGER.info("Estamos guardando un domicilio");
        Connection connection = null;

        try {
            DomicilioDaoH2 domicilioDaoH2 = new DomicilioDaoH2();
            domicilioDaoH2.guardar(paciente.getDomicilio());

            connection = BD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PACIENTES, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, paciente.getNombre());
            preparedStatement.setString(2, paciente.getApellido());
            preparedStatement.setString(3, paciente.getDni());
            preparedStatement.setDate(4, Date.valueOf(paciente.getFechaIngreso()));
            preparedStatement.setInt(5, paciente.getDomicilio().getId());

            preparedStatement.execute();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            while(rs.next()) {
                paciente.setId(rs.getInt(1));
            }
            LOGGER.info("SE CREO EL PACIENTE "+ paciente.getId());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return paciente;
    }
    @Override
    public Paciente buscarPorId(Integer id) {
        LOGGER.info("Buscando paciente por id: " + id);
        Connection connection = null;
        Paciente paciente = null;
        try {
            connection = BD.getConnection();
            PreparedStatement ps = connection.prepareStatement(SELECT_ID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            DomicilioDaoH2 domicilioDaoH2 = new DomicilioDaoH2();
            while (rs.next()) {
                Domicilio domicilio = domicilioDaoH2.buscarPorId(rs.getInt(6));
                LOGGER.info("El nombre del paciente encontrado: " + rs.getString(2));
                LOGGER.info("De apellido: " + rs.getString(3));
                LOGGER.info("Con numero de documento: " + rs.getString(4));
                LOGGER.info("Ingreso el: " + rs.getDate(5));

                paciente = new Paciente(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getDate(5).toLocalDate(), domicilio);
            }

        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (paciente == null) {
            LOGGER.info("No se encontro ningun paciente con ID: " + id);
        }
        return paciente;
    }

    @Override
    public void eliminar(Integer id) {
        Connection connection = null;
        try {
            connection = BD.getConnection();
            PreparedStatement psDelete = connection.prepareStatement(DELETE_PACIENTE);
            psDelete.setInt(1, id);
            psDelete.executeUpdate();
            LOGGER.info("Paciente eliminado con éxito. ID: " + id);
        } catch (Exception e) {
            LOGGER.error("Error al eliminar el paciente con ID " + id + ": " + e.getMessage());
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
    public void actualizar(Paciente paciente) {
        Connection connection = null;
        try {
            connection = BD.getConnection();
            PreparedStatement psUpdate = connection.prepareStatement(UPDATE_PACIENTE);
            psUpdate.setString(1, paciente.getNombre());
            psUpdate.setString(2, paciente.getApellido());
            psUpdate.setString(3, paciente.getDni());
            psUpdate.setDate(4, java.sql.Date.valueOf(paciente.getFechaIngreso()));
            psUpdate.setInt(5, paciente.getDomicilio().getId());
            psUpdate.setInt(6, paciente.getId());

            psUpdate.executeUpdate();
        } catch (Exception e) {
            LOGGER.error("Error al actualizar el paciente con ID " + paciente.getId() + ": " + e.getMessage());
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
    public List<Paciente> listarTodos() {
        Connection connection = null;
        List<Paciente> pacientes = new ArrayList<>();

        try {
            connection = BD.getConnection();
            PreparedStatement psSelectAll = connection.prepareStatement(SELECT_ALL);
            ResultSet rs = psSelectAll.executeQuery();

            while (rs.next()) {
                Paciente paciente = new Paciente();
                paciente.setId(rs.getInt(1));
                paciente.setNombre(rs.getString(2));
                paciente.setApellido(rs.getString(3));
                paciente.setDni(rs.getString(4));
                paciente.setFechaIngreso(rs.getDate(5).toLocalDate());

                // Aquí puedes cargar el domicilio usando su ID
                DomicilioDaoH2 domicilioDaoH2 = new DomicilioDaoH2();
                Domicilio domicilio = domicilioDaoH2.buscarPorId(rs.getInt(6));
                paciente.setDomicilio(domicilio);

                pacientes.add(paciente);
            }

        } catch (Exception e) {
            LOGGER.error("Error al listar todos los pacientes: " + e.getMessage());
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
        return pacientes;
    }
}
