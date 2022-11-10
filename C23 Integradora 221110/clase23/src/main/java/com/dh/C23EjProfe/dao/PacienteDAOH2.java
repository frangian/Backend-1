package com.dh.C23EjProfe.dao;

import com.dh.C23EjProfe.model.Domicilio;
import com.dh.C23EjProfe.model.Paciente;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.List;


public class PacienteDAOH2 implements IDao<Paciente>{
    private static final Logger LOGGER= Logger.getLogger(PacienteDAOH2.class);
    private static final String SQL_SELECT="SELECT * FROM PACIENTES WHERE ID=?";
    private static final String SQL_INSERT = "INSERT INTO PACIENTES (NOMBRE, APELLIDO, DNI, FECHA_INGRESO, DOMICILIO_ID, EMAIL) VALUES (?,?,?,?,?,?)";
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
                Domicilio domicilio= daoAux.buscar(rs.getInt(6));
                paciente=new Paciente(rs.getInt(1),rs.getString(2),
                        rs.getString(3),rs.getString(4),
                        rs.getDate(5).toLocalDate(),domicilio, rs.getString(7));
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

    }

    @Override
    public void eliminar(Integer id) {

    }

    @Override
    public List<Paciente> buscarTodo() {
        return null;
    }

    @Override
    public Paciente buscarXString(String valor) {
        Connection connection=null;
        LOGGER.info("Iniciando la busqueda del paciente con id="+valor);
        Paciente paciente=null;
        try{
            connection=BD.getConnection();
            PreparedStatement psSelect=connection.prepareStatement(SQL_SELECT_BY_EMAIL);
            psSelect.setString(1,valor);
            ResultSet rs= psSelect.executeQuery();
            //Recordar que || Tabla Paciente -> 1,Rodolfo,Baspineiro, 584, 02-11-2022,15(fk)
            DomicilioDAOH2 daoAux= new DomicilioDAOH2();
            while (rs.next()){
                Domicilio domicilio= daoAux.buscar(rs.getInt(6));
                paciente=new Paciente(rs.getInt(1),rs.getString(2),
                        rs.getString(3),rs.getString(4),
                        rs.getDate(5).toLocalDate(),domicilio, rs.getString(7));
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
}
