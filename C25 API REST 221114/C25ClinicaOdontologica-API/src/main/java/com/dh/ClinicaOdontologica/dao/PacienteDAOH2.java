package com.dh.ClinicaOdontologica.dao;

import com.dh.ClinicaOdontologica.model.Domicilio;
import com.dh.ClinicaOdontologica.model.Paciente;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class PacienteDAOH2 implements IDao<Paciente>{
    private static final Logger LOGGER= Logger.getLogger(PacienteDAOH2.class);
    private static final String SQL_INSERT = "INSERT INTO PACIENTES (NOMBRE, APELLIDO, DNI, FECHA_INGRESO, DOMICILIO_ID, EMAIL) VALUES (?,?,?,?,?,?)";
    private static final String SQL_SELECT="SELECT * FROM PACIENTES WHERE ID=?";
    private static final String SQL_UPDATE="UPDATE PACIENTES SET NOMBRE=?, APELLIDO=?, DNI=?, FECHA_INGRESO=?, DOMICILIO_ID=?, EMAIL=? WHERE ID=?;";
    private static final String SQL_DELETE="DELETE FROM PACIENTES WHERE ID=?";
    private static final String SQL_SELECT_ALL="SELECT * FROM PACIENTES;";
    private static final String SQL_SELECT_BY_EMAIL = "SELECT * FROM PACIENTES WHERE EMAIL = ?;";

    @Override
    public Paciente guardar(Paciente paciente) {
        LOGGER.info("Se inicia el registro de un paciente nuevo");
        Connection connection = null;
        try{
            connection=BD.getConnection();
            DomicilioDAOH2 daoAux= new DomicilioDAOH2();
            daoAux.guardar(paciente.getDomicilio());

            PreparedStatement psInsert =connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            psInsert.setString(1,paciente.getNombre());
            psInsert.setString(2,paciente.getApellido());
            psInsert.setString(3,paciente.getDni());
            psInsert.setObject(4,paciente.getFechaIngreso());
            psInsert.setInt(5, paciente.getDomicilio().getId());
            psInsert.setString(6, paciente.getEmail());

            psInsert.execute();

            ResultSet rs1 = psInsert.getGeneratedKeys();
            while (rs1.next()){
                paciente.setId(rs1.getInt(1));
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
    public Paciente buscar(Integer id) {
        Connection connection=null;
        LOGGER.info("Iniciando la busqueda del paciente con id="+id);
        Paciente paciente=null;
        try{
            connection=BD.getConnection();
            PreparedStatement psSelect=connection.prepareStatement(SQL_SELECT);
            psSelect.setInt(1,id);
            ResultSet rs= psSelect.executeQuery();
            //Recordar que || Tabla Paciente -> 1,Rodolfo,Baspineiro, 584, 02-11-2022,15(fk)
            DomicilioDAOH2 daoAux= new DomicilioDAOH2();
            while (rs.next()){
                Domicilio domicilioID= daoAux.buscar(rs.getInt(6));
                paciente=new Paciente(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getDate(5).toLocalDate(),
                        domicilioID,
                        rs.getString(7));
                //LocalDate fecha= LocalDate.of(2022,11,1);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try{
                connection.close();
            }
            catch (SQLException ex){
                ex.printStackTrace();
            }
        }
        return  paciente;
    }

    @Override
    public void actualizar(Paciente paciente) {
        LOGGER.info("Se inicia la actualizacion del paciente: "+paciente.getId());
        Connection connection = null;
        try{
            connection=BD.getConnection();
            //En caso de q se actualice la info del paciente Y DEL DOMICILIO TAMBIEN
            DomicilioDAOH2 daoAux = new DomicilioDAOH2();
            daoAux.actualizar(paciente.getDomicilio());
            //En caso de solo actualizar la info del paciente, las 2 lineas anteriores no irian
            PreparedStatement psUpdate =connection.prepareStatement(SQL_UPDATE);
            psUpdate.setString(1,paciente.getNombre());
            psUpdate.setString(2,paciente.getApellido());
            psUpdate.setString(3,paciente.getDni());
            psUpdate.setDate(4,Date.valueOf(paciente.getFechaIngreso()));
            psUpdate.setInt(5, paciente.getDomicilio().getId());
            psUpdate.setString(6, paciente.getEmail());
            psUpdate.setInt(7,paciente.getId());
            psUpdate.execute();
            LOGGER.info("Se actualizaron los datos del paciente: "+paciente.getId()+" correctamente");
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
    }

    @Override
    public void eliminar(Integer id) {
        LOGGER.info("Se inicia la eliminacion del paciente: "+id);
        Connection connection=null;
        try{
            connection=BD.getConnection();
            PreparedStatement psDelete=connection.prepareStatement(SQL_DELETE);
            psDelete.setInt(1, id);
            psDelete.execute();
            LOGGER.info("Se eliminaron los datos del paciente: "+id+" correctamente");
        }
        catch (Exception e){
            LOGGER.error("Error al eliminar el paciente: "+id+" - "+e.getMessage());
            e.printStackTrace();
        }
        finally {
            try{
                connection.close();
            }
            catch (SQLException ex){
                LOGGER.error("Error al cerrar conexion de eliminacion del paciente: "+id+" - "+ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    @Override
    public List<Paciente> buscarTodo() {
        LOGGER.info("Se esta realizando una busqueda de todos los pacientes");
        List<Paciente> listaPacientes = new ArrayList<>();
        Connection connection = null;
        try{
            connection=BD.getConnection();
            PreparedStatement psBuscarTodos =connection.prepareStatement(SQL_SELECT_ALL);
            ResultSet rs = psBuscarTodos.executeQuery();
            DomicilioDAOH2 daoAux= new DomicilioDAOH2();
            while (rs.next()){
                Domicilio domicilioID= daoAux.buscar(rs.getInt(6));
                Paciente paciente=new Paciente(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getDate(5).toLocalDate(),
                        domicilioID,
                        rs.getString(7));
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
                LOGGER.error("Se produjo un error al cerrar la conexion de la busqueda de todos los pacientes: "+ex.getMessage());
                ex.printStackTrace();
            }
        }
        return listaPacientes;
    }

    @Override
    public Paciente buscarXString(String valor) {
        Connection connection=null;
        LOGGER.info("Iniciando la busqueda del paciente con email = "+valor);
        Paciente paciente=null;
        try{
            connection=BD.getConnection();
            PreparedStatement psSelect=connection.prepareStatement(SQL_SELECT_BY_EMAIL);
            psSelect.setString(1,valor);
            ResultSet rs= psSelect.executeQuery();
            DomicilioDAOH2 daoAux= new DomicilioDAOH2();
            while (rs.next()){
                Domicilio domicilio= daoAux.buscar(rs.getInt(6));
                paciente=new Paciente(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getDate(5).toLocalDate(),
                        domicilio,
                        rs.getString(7));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try{
                connection.close();
            }
            catch (SQLException ex){
                ex.printStackTrace();
            }
        }
        return paciente;
    }
}
