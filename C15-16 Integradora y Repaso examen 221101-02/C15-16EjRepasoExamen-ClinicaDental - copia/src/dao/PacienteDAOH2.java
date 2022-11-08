package dao;

import model.Domicilio;
import model.Paciente;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAOH2 implements iDao<Paciente>{
    private static final String SQL_INSERT = "" +
            "INSERT INTO PACIENTES (NOMBRE,APELLIDO,DNI,FECHAINGRESO) " +
            "VALUES (?,?,?,?);";
    private static final String SQL_BUSCAR = "SELECT * FROM PACIENTES WHERE ID = ?;";
    private static final String SQL_ACTUALIZAR = "UPDATE PACIENTES SET NOMBRE = ?, APELLIDO = ?, DNI = ?, FECHAINGRESO = ?, DOMICILIO_ID = ? WHERE ID = ?;";
    private static final String SQL_ELIMINAR = "DELETE FROM PACIENTES WHERE ID = ?;";
    private static final String SQL_BUSCAR_TODOS = "SELECT * FROM PACIENTES;";
    private static final Logger LOGGER = Logger.getLogger(DomicilioDAOH2.class);
    @Override
    public Paciente guardar(Paciente paciente) {
        LOGGER.info("Se inicia el registro de un paciente nuevo");
        Connection connection = null;
        try{
            connection=BD.getConnection();
//            Domicilio domicilio = null;
            PreparedStatement psBuscar =connection.prepareStatement(SQL_BUSCAR);
            psBuscar.setInt(1,1);
            ResultSet rs = psBuscar.executeQuery();
            DomicilioDAOH2 daoAux = new DomicilioDAOH2();
//            while (rs.next()) {
                Domicilio domicilio = daoAux.buscar(rs.getInt(1));
//            }

            PreparedStatement psInsert =connection.prepareStatement(SQL_INSERT,Statement.RETURN_GENERATED_KEYS);
            psInsert.setString(1,paciente.getNombre());
            psInsert.setString(2,paciente.getApellido());
            psInsert.setString(3,paciente.getDni());
            psInsert.setObject(4,paciente.getFechaIngreso());
            psInsert.setInt(5,domicilio.getID());
            psInsert.execute();

            ResultSet rs1 = psInsert.getGeneratedKeys();
            while (rs1.next()){
                LOGGER.info(rs1);
                paciente.setID(rs1.getInt(1));
            }
            LOGGER.info("Se registraron los datos del nuevo paciente correctamente");
        }
        catch (Exception e){
            LOGGER.error("Se produjo un error al registrar el paciente: "+e.getMessage());
            e.printStackTrace();
        }
        finally {
            try{
                connection.close();
            }
            catch (Exception ex){
                LOGGER.error("Se produjo un errror al cerrar la conexion de la registracion: "+ex.getMessage());
                ex.printStackTrace();
            }
        }
        return paciente;
    }
    @Override
    public Paciente buscar(int id) {
        LOGGER.info("Se esta realizando una busqueda del paciente: "+id);
        Paciente respuesta = null;

        Connection connection = null;
        try{
            connection=BD.getConnection();
            PreparedStatement psBuscar =connection.prepareStatement(SQL_BUSCAR);
            psBuscar.setInt(1,id);
            ResultSet rs = psBuscar.executeQuery();
            DomicilioDAOH2 daoAux = new DomicilioDAOH2();
            while (rs.next()){
                Domicilio domicilio = daoAux.buscar(rs.getInt(6));
                respuesta = new Paciente(rs.getInt(1)+ rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5).toLocalDate(),domicilio);
            }
            LOGGER.info("Se realizo la busqueda del paciente: "+id+" correctamente");
        }
        catch (Exception e){
            LOGGER.error("Se produjo un error al buscar el paciente: "+id +": "+ e.getMessage());
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
    public void actualizar(Paciente paciente) {
        LOGGER.info("Se esta actualizando un paciente");
        Connection connection = null;
        try{
            connection=BD.getConnection();
            PreparedStatement psActualizar =connection.prepareStatement(SQL_ACTUALIZAR);
            psActualizar.setString(1, paciente.getNombre());
            psActualizar.setString(2,paciente.getApellido());
            psActualizar.setString(3, paciente.getDni());
            psActualizar.setObject(4, paciente.getFechaIngreso());
            psActualizar.setObject(5,paciente.getDomicilio());
            psActualizar.execute();

            LOGGER.info("Se actualizo el paciente correctamente");
        }
        catch (Exception e){
            LOGGER.error("Se produjo un error al actualizar el paciente: "+e.getMessage());
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
        LOGGER.info("Se esta eliminando un paciente");
        Connection connection = null;
        try{
            connection=BD.getConnection();
            PreparedStatement psDelete =connection.prepareStatement(SQL_ELIMINAR);
            psDelete.setInt(1,id);
            psDelete.execute();

            LOGGER.info("Se elimino el paciente correctamente");
        }
        catch (Exception e){
            LOGGER.error("Se produjo un error al eliminar el paciente: "+e.getMessage());
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
    public List<Paciente> buscarTodo() {
        List<Paciente> listaPacientes= new ArrayList<>();
        LOGGER.info("Se esta realizando una busqueda de todos los pacientes");
        Connection connection = null;
        try{
            connection=BD.getConnection();
            PreparedStatement psBuscarTodos =connection.prepareStatement(SQL_BUSCAR_TODOS);
            ResultSet rs = psBuscarTodos.executeQuery();
            DomicilioDAOH2 daoAux = new DomicilioDAOH2();
            while (rs.next()){
                Domicilio domicilio = daoAux.buscar(rs.getInt(6));
                Paciente paciente = new Paciente(rs.getInt(1)+ rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5).toLocalDate(),domicilio);
                listaPacientes.add(paciente);
            }

            LOGGER.info("Se realizo la busqueda de todos los pacientes correctamente");
        }
        catch (Exception e){
            LOGGER.error("Se produjo un error al buscar todos los pacientes: "+e.getMessage());
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
        return listaPacientes;
    }
}
