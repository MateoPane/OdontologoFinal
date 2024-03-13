package com.dh.ClinicMVC.dao.implementacion;

import com.dh.ClinicMVC.dao.BD;
import com.dh.ClinicMVC.dao.IDao;
import com.dh.ClinicMVC.model.Odontologo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDaoH2 implements IDao<Odontologo> {

    private static final Logger LOGGER = Logger.getLogger(OdontologoDaoH2.class);

    private static final String INSERT_ODONTOLOGOS = "INSERT INTO ODONTOLOGOS (NOMBRE, APELLIDO, MATRICULA) VALUES (?,?,?)";
    private static final String SELECT_ALL = "SELECT * FROM ODONTOLOGOS";
    private static final String SELECT_BY_ID = "SELECT * FROM ODONTOLOGOS WHERE ID = ?";
    private static final String DELETE_ODONTOLOGO = "DELETE FROM ODONTOLOGOS WHERE ID = ?";
    private static final String UPDATE_ODONTOLOGO = "UPDATE ODONTOLOGOS SET NOMBRE=?, APELLIDO=?, MATRICULA=? WHERE ID = ?";
    @Override
    public Odontologo guardar(Odontologo odontologo) {
        LOGGER.info("Estamos guardando un odontologo");
        Connection connection = null;

        try {
            connection = BD.getConnection();

            PreparedStatement psInsert = connection.prepareStatement(INSERT_ODONTOLOGOS, Statement.RETURN_GENERATED_KEYS);
            psInsert.setString(1, odontologo.getNombre());
            psInsert.setString(2, odontologo.getApellido());
            psInsert.setString(3, odontologo.getMatricula());

            psInsert.execute();

            ResultSet rs = psInsert.getGeneratedKeys();
            while (rs.next()) {
                odontologo.setId(rs.getInt(1));
            }

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return odontologo;
    }

    @Override
    public Odontologo buscarPorId(Integer id) {
        Connection conexion = null;
        Odontologo odontologo = null;
        try {
            conexion = BD.getConnection();
            PreparedStatement psSearchByID =  conexion.prepareStatement(SELECT_BY_ID);
            psSearchByID.setInt(1, id);
            ResultSet rs  = psSearchByID.executeQuery();

            while (rs.next()){
                odontologo = new Odontologo();
                odontologo.setId(rs.getInt(1));
                odontologo.setNombre(rs.getString(2));
                odontologo.setApellido(rs.getString(3));
                odontologo.setMatricula(rs.getString(4));
                LOGGER.info("El ID del odontologo asignado es: " + rs.getInt(1));
                LOGGER.info("El Nombre del odontologo es: " + rs.getString(2));
                LOGGER.info("Con numero de matricula:  " + rs.getString(4));
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                conexion.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return odontologo;
    }

    @Override
    public void eliminar(Integer id) {
        Connection connection = null;
        try {
            connection = BD.getConnection();

            PreparedStatement psDelete = connection.prepareStatement(DELETE_ODONTOLOGO);
            psDelete.setInt(1, id);
            psDelete.executeUpdate();
        } catch (Exception e) {
            LOGGER.error("Error al eliminar el odontologo con ID " + id + ": " + e.getMessage());
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
    public void actualizar(Odontologo odontologo) {
    Connection connection = null;
    try {
        connection = BD.getConnection();
        PreparedStatement psUpdate = connection.prepareStatement(UPDATE_ODONTOLOGO);
        psUpdate.setString(1, odontologo.getNombre());
        psUpdate.setString(2, odontologo.getApellido());
        psUpdate.setString(3, odontologo.getMatricula());
        psUpdate.setInt(4, odontologo.getId());

        psUpdate.executeUpdate();

        }catch (Exception e) {
        LOGGER.error("Error al actualizar el Odontologo con ID " + odontologo.getId() + ": " + e.getMessage());
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
    public List<Odontologo> listarTodos() {
        LOGGER.info("Estamos consultando todos los Odontologos.");
        Connection conexion = null;
        List<Odontologo>  listaOdontologos = new ArrayList<>();
        Odontologo odontologo =  null;
        try {
            conexion = BD.getConnection();
            PreparedStatement psUpdateById = conexion.prepareStatement(SELECT_ALL);
            ResultSet rs = psUpdateById.executeQuery();

            while (rs.next())  {
                odontologo = new Odontologo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));

                listaOdontologos.add(odontologo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                conexion.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return listaOdontologos;
    }

}
