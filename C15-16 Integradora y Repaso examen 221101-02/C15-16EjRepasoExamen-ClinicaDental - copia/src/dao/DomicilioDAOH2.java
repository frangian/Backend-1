package dao;

import model.Domicilio;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DomicilioDAOH2 implements iDao<Domicilio>{
    private static final String SQL_INSERT = "" +
            "INSERT INTO DOMICILIOS (CALLE,NUMERO,LOCALIDAD,PROVINCIA) " +
            "VALUES (?,?,?,?);";
    private static final String SQL_BUSCAR = "SELECT * FROM DOMICILIOS WHERE ID = ?;";
    private static final String SQL_ACTUALIZAR = "UPDATE DOMICILIOS SET CALLE = ?, NUMERO = ?, LOCALIDAD = ?, PROVINCIA = ? WHERE ID = ?;";
    private static final String SQL_ELIMINAR = "DELETE FROM DOMICILIOS WHERE ID = ?;";
    private static final String SQL_BUSCAR_TODOS = "SELECT * FROM DOMICILIOS;";
    private static final Logger LOGGER = Logger.getLogger(DomicilioDAOH2.class);

    @Override
    public Domicilio guardar(Domicilio domicilio) {
        LOGGER.info("Se esta registrando un domicilio nuevo");
        Connection connection = null;
        try{
            connection=BD.getConnection();
            PreparedStatement psInsert =connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            psInsert.setString(1,domicilio.getCalle());
            psInsert.setInt(2,domicilio.getNumero());
            psInsert.setString(3,domicilio.getLocalidad());
            psInsert.setString(4,domicilio.getProvincia());
            psInsert.execute();
            ResultSet rs = psInsert.getGeneratedKeys();
            while (rs.next()){
                domicilio.setID(rs.getInt(1));
            }

            LOGGER.info("Se registraron los datos del nuevo domicilio correctamente");
        }
        catch (Exception e){
            LOGGER.error("Se produjo un error al registrar el domicilio: "+e.getMessage());
            e.printStackTrace();
        }
        finally {
            try{
                connection.close();
            }
            catch (Exception ex){
                LOGGER.error("Se produjo un error al cerrar la conexion de la registracion: "+ex.getMessage());
                ex.printStackTrace();
            }
        }
        return domicilio;
    }

    @Override
    public Domicilio buscar(int id) {
        LOGGER.info("Se esta realizando una busqueda del domicilio: "+id);
        Domicilio respuesta = null;

        Connection connection = null;
        try{
            connection=BD.getConnection();
            PreparedStatement psBuscar =connection.prepareStatement(SQL_BUSCAR);
            psBuscar.setInt(1,id);
            ResultSet rs = psBuscar.executeQuery();
            while (rs.next()){
                respuesta = new Domicilio(rs.getInt(1)+ rs.getString(2),rs.getInt(3),rs.getString(4),rs.getString(5));
            }

            LOGGER.info("Se realizo la busqueda del domicilio: "+id+" correctamente");
        }
        catch (Exception e){
            LOGGER.error("Se produjo un error al buscar el domicilio: "+id +": "+ e.getMessage());
            e.printStackTrace();
        }
        finally {
            try{
                connection.close();
            }
            catch (Exception ex){
                LOGGER.error("Se produjo un errror al cerrar la conexion de la busqueda: "+ex.getMessage());
                ex.printStackTrace();
            }
        }
        return respuesta;
    }

    @Override
    public void actualizar(Domicilio domicilio) {
        LOGGER.info("Se esta actualizando un domicilio");
        Connection connection = null;
        try{
            connection=BD.getConnection();
            PreparedStatement psActualizar =connection.prepareStatement(SQL_ACTUALIZAR);
            psActualizar.setString(1, domicilio.getCalle());
            psActualizar.setInt(2,domicilio.getNumero());
            psActualizar.setString(3, domicilio.getLocalidad());
            psActualizar.setString(4, domicilio.getProvincia());
            psActualizar.setInt(5,domicilio.getID());
            psActualizar.execute();

            LOGGER.info("Se actualizo el domicilio correctamente");
        }
        catch (Exception e){
            LOGGER.error("Se produjo un error al actualizar el domicilio: "+e.getMessage());
            e.printStackTrace();
        }
        finally {
            try{
                connection.close();
            }
            catch (Exception ex){
                LOGGER.error("Se produjo un errror al cerrar la conexion de la actualizacion: "+ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void eliminar(int id) {
        LOGGER.info("Se esta eliminando un domicilio");
        Connection connection = null;
        try{
            connection=BD.getConnection();
            PreparedStatement psDelete =connection.prepareStatement(SQL_ELIMINAR);
            psDelete.setInt(1,id);
            psDelete.execute();

            LOGGER.info("Se elimino el domicilio correctamente");
        }
        catch (Exception e){
            LOGGER.error("Se produjo un error al eliminar el domicilio: "+e.getMessage());
            e.printStackTrace();
        }
        finally {
            try{
                connection.close();
            }
            catch (Exception ex){
                LOGGER.error("Se produjo un errror al cerrar la conexion de la eliminacion: "+ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    @Override
    public List<Domicilio> buscarTodo() {
        List<Domicilio> listaDomicilios= new ArrayList<>();
        LOGGER.info("Se esta realizando una busqueda de todos los domicilios");
        Connection connection = null;
        try{
            connection=BD.getConnection();
            PreparedStatement psBuscarTodos =connection.prepareStatement(SQL_BUSCAR_TODOS);
            ResultSet rs = psBuscarTodos.executeQuery();
            while (rs.next()){
                Domicilio domicilio = new Domicilio(rs.getInt(1)+ rs.getString(2),rs.getInt(3),rs.getString(4),rs.getString(5));
                listaDomicilios.add(domicilio);
            }

            LOGGER.info("Se realizo la busqueda de todos los domicilios correctamente");
        }
        catch (Exception e){
            LOGGER.error("Se produjo un error al buscar todos los domicilios: "+e.getMessage());
            e.printStackTrace();
        }
        finally {
            try{
                connection.close();
            }
            catch (Exception ex){
                LOGGER.error("Se produjo un errror al cerrar la conexion de la busqueda de todos los domicilios: "+ex.getMessage());
                ex.printStackTrace();
            }
        }
        return listaDomicilios;
    }
}
