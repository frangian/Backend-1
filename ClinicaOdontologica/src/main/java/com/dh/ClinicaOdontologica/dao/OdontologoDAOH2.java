package com.dh.ClinicaOdontologica.dao;

import com.dh.ClinicaOdontologica.entity.Odontologo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDAOH2 implements IDao <Odontologo>{

    private static final Logger LOGGER= Logger.getLogger(OdontologoDAOH2.class);
    private static final String SQL_INSERT="INSERT INTO ODONTOLOGOS (APELLIDO," +
            "NOMBRE,MATRICULA) VALUES (?,?,?)";
    private static final String SQL_SELECT="SELECT * FROM ODONTOLOGOS WHERE ID=?";
    private static final String SQL_UPDATE="UPDATE ODONTOLOGOS SET APELLIDO=?, NOMBRE=?, MATRICULA=? WHERE ID=?";
    private static final String SQL_DELETE="DELETE FROM ODONTOLOGOS WHERE ID=?";
    private static final String SQL_SELECT_ALL="SELECT * FROM ODONTOLOGOS";
    @Override
    public Odontologo guardar(Odontologo odontologo) {
        LOGGER.info("Se inicia el registro de un odontologo nuevo"+odontologo.getMatricula());
        Connection connection=null;
        try{
            connection=BD.getConnection();
            PreparedStatement psInsert=connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            psInsert.setString(1,odontologo.getApellido());
            psInsert.setString(2,odontologo.getNombre());
            psInsert.setString(3, odontologo.getMatricula());
            psInsert.execute();
            ResultSet clave=psInsert.getGeneratedKeys();
            while(clave.next()){
                odontologo.setId(clave.getInt(1));
            }
            LOGGER.info("Se registraron los datos del nuevo odontologo "+odontologo.getMatricula()+" correctamente");
        }
        catch (Exception e){
            LOGGER.error("Error al registrar el odontologo: "+e.getMessage());
            e.printStackTrace();
        }
        finally {
            try{
                connection.close();
            }
            catch (SQLException ex){
                LOGGER.error("Error al cerrar conexion de registracion del odontologo: "+odontologo.getMatricula()+" - "+ex.getMessage());
                ex.printStackTrace();
            }
        }
        return odontologo;
    }

    @Override
    public Odontologo buscar(Integer id) {
        LOGGER.info("Iniciando la busqueda del odontologo con id= "+id);
        Connection connection=null;
        Odontologo odontologo=null;
        try{
            connection=BD.getConnection();
            PreparedStatement psSelect=connection.prepareStatement(SQL_SELECT);
            psSelect.setInt(1,id);
            ResultSet rs= psSelect.executeQuery();
            while (rs.next()){
                odontologo=new Odontologo(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4));
            }
            LOGGER.info("Se realizo la busqueda del odontologo "+odontologo.getMatricula()+" correctamente");
        }
        catch (Exception e){
            LOGGER.error("Error al buscar el odontologo: "+odontologo.getMatricula()+" - "+e.getMessage());
            e.printStackTrace();
        }
        finally {
            try{
                connection.close();
            }
            catch (SQLException ex){
                LOGGER.error("Error al cerrar conexion de busquda del odontologo: "+odontologo.getMatricula()+" - "+ex.getMessage());
                ex.printStackTrace();
            }
        }
        return odontologo;
    }

    @Override
    public void actualizar(Odontologo odontologo) {
        LOGGER.info("Se inicia la actualizacion del odontologo: "+odontologo.getMatricula());
        Connection connection=null;
        try{
            connection=BD.getConnection();
            PreparedStatement psUpdate=connection.prepareStatement(SQL_UPDATE);
            psUpdate.setString(1,odontologo.getApellido());
            psUpdate.setString(2,odontologo.getNombre());
            psUpdate.setString(3, odontologo.getMatricula());
            psUpdate.setInt(4, odontologo.getId());
            psUpdate.execute();
            LOGGER.info("Se actualizaron los datos del odontologo: "+odontologo.getMatricula()+" correctamente");
        }
        catch (Exception e){
            LOGGER.error("Error al actualizar el odontologo: "+odontologo.getMatricula()+" - "+e.getMessage());
            e.printStackTrace();
        }
        finally {
            try{
                connection.close();
            }
            catch (SQLException ex){
                LOGGER.error("Error al cerrar conexion de actualizacion del odontologo: "+odontologo.getMatricula()+" - "+ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void eliminar(Integer id) {
        LOGGER.info("Se inicia la eliminacion del odontologo: "+id);
        Connection connection=null;
        try{
            connection=BD.getConnection();
            PreparedStatement psDelete=connection.prepareStatement(SQL_DELETE);
            psDelete.setInt(1, id);
            psDelete.execute();
            LOGGER.info("Se eliminaron los datos del odontologo: "+id+" correctamente");
        }
        catch (Exception e){
            LOGGER.error("Error al eliminar el odontologo: "+id+" - "+e.getMessage());
            e.printStackTrace();
        }
        finally {
            try{
                connection.close();
            }
            catch (SQLException ex){
                LOGGER.error("Error al cerrar conexion de eliminacion del odontologo: "+id+" - "+ex.getMessage());
                ex.printStackTrace();
            }
        }

    }

    @Override
    public List<Odontologo> buscarTodo() {
        LOGGER.info("Se esta realizando una busqueda de todos los odontologos");
        List<Odontologo> listaOdontologos = new ArrayList<>();
        Connection connection = null;
        try{
            connection=BD.getConnection();
            PreparedStatement psBuscarTodos =connection.prepareStatement(SQL_SELECT_ALL);
            ResultSet rs = psBuscarTodos.executeQuery();
            while (rs.next()){
                Odontologo odontologo = new Odontologo(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4));
                listaOdontologos.add(odontologo);
            }

            LOGGER.info("Se realizo la busqueda de todos los odontologos correctamente");
        }
        catch (Exception e){
            LOGGER.error("Se produjo un error al buscar todos los odontologos: "+e.getMessage());
            e.printStackTrace();
        }
        finally {
            try{
                connection.close();
            }
            catch (Exception ex){
                LOGGER.error("Se produjo un error al cerrar la conexion de la busqueda de todos los odontologos: "+ex.getMessage());
                ex.printStackTrace();
            }
        }
        return listaOdontologos;

    }

    @Override
    public Odontologo buscarXString(String valor) {
        return null;
    }
}
