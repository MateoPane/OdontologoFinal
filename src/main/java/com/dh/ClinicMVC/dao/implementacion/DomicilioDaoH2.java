package com.dh.ClinicMVC.dao.implementacion;

import com.dh.ClinicMVC.entity.Domicilio;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class DomicilioDaoH2 implements IDao<Domicilio> {

    private static final Logger LOGGER = Logger.getLogger(DomicilioDaoH2.class);

    private static final String INSERT_DOMICILIO = "INSERT INTO DOMICILIOS (CALLE, NUMERO, LOCALIDAD, PROVINCIA) VALUES (?,?,?,?)";
    private static final String SELECT_ALL = "SELECT * FROM DOMICILIOS";
    private static final String SELECT_ID = "SELECT * FROM DOMICILIOS WHERE ID = ?";
    private static final String DELETE_DOMICILIO = "DELETE FROM DOMICILIOS WHERE ID = ?";
    private static final String UPDATE_DOMICILIO = "UPDATE DOMICILIOS SET CALLE=?, NUMERO=?, LOCALIDAD=?, PROVINCIA=? WHERE ID = ?";

    @Override
    public Domicilio guardar(Domicilio domicilio) {
        LOGGER.info("Estamos guardando un domicilio");
        Connection connection = null;

        try {

            connection = BD.getConnection();
            PreparedStatement psInsert = connection.prepareStatement(INSERT_DOMICILIO, Statement.RETURN_GENERATED_KEYS);
            psInsert.setString(1, domicilio.getCalle());
            psInsert.setInt(2, domicilio.getNumero());
            psInsert.setString(3, domicilio.getLocalidad());
            psInsert.setString(4, domicilio.getProvincia());

            psInsert.execute();

            ResultSet resultSet = psInsert.getGeneratedKeys();
            while (resultSet.next()) {
                domicilio.setId(resultSet.getInt(1));
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
        LOGGER.info("Este es el id: " + domicilio.getId());
        return domicilio;
    }

    @Override
    public Domicilio buscarPorId(Integer id) {
        Connection connection = null;
        Domicilio domicilio = null;
        try {
            connection = BD.getConnection();
            PreparedStatement slId = connection.prepareStatement(SELECT_ID);
            slId.setInt(1, id);
            ResultSet rs = slId.executeQuery();
            while (rs.next()){
                domicilio = new Domicilio();
                domicilio.setId(rs.getInt(1));
                domicilio.setCalle(rs.getString(2));
                domicilio.setNumero(rs.getInt(3));
                domicilio.setLocalidad(rs.getString(4));
                domicilio.setProvincia(rs.getString(5));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        LOGGER.info("El domicilio encontrado es: " + domicilio);
        return domicilio;
    }

    @Override
    public void eliminar(Integer id) {
        Connection connection = null;

        try {
            connection = BD.getConnection();
            PreparedStatement psDelete = connection.prepareStatement(DELETE_DOMICILIO);
            psDelete.setInt(1, id);

            psDelete.executeUpdate();

        } catch (Exception e) {
            LOGGER.error("Error al eliminar el domicilio con ID: " + id + ": " + e.getMessage());
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
    public void actualizar(Domicilio domicilio) {
        Connection connection = null;

        try {
            connection = BD.getConnection();
            PreparedStatement psUpdate = connection.prepareStatement(UPDATE_DOMICILIO);
            psUpdate.setString(1, domicilio.getCalle());
            psUpdate.setInt(2, domicilio.getNumero());
            psUpdate.setString(3, domicilio.getLocalidad());
            psUpdate.setString(4, domicilio.getProvincia());
            psUpdate.setInt(5, domicilio.getId());

            psUpdate.executeUpdate();

        } catch (Exception e) {
            LOGGER.error("Error al actualizar el domicilio con ID: " + domicilio.getId() + ": " + e.getMessage());
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
    public List<Domicilio> listarTodos() {
        LOGGER.info("Estamos consultando todos los domicilios");
        Connection connection = null;
        List<Domicilio> domicilioList = new ArrayList<>();
        Domicilio domicilio = null;

        try {
            connection = BD.getConnection();
            PreparedStatement psSelect = connection.prepareStatement(SELECT_ALL);
            ResultSet rs = psSelect.executeQuery();

            while (rs.next()) {
                //completamos el domicilio
                domicilio = new Domicilio(rs.getInt(1), rs.getString(2), rs.getInt(3),
                        rs.getString(4), rs.getString(5));

                //lo guardamos en la lista
                domicilioList.add(domicilio);
            }

        } catch (Exception e) {
            LOGGER.error("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        LOGGER.info("Esta es la lista que se está devolviendo??? " + domicilioList);
        return domicilioList;
    }
}
