package dao;

import model.Odontologo;
import org.apache.log4j.Logger;
import java.sql.*;

import java.util.List;

public class OdontologoDAOH2 implements iDao<Odontologo>{
    private static final String SQL_INSERT = "" +
            "INSERT INTO ODONTOLOGOS (APELLIDO,NOMBRE,MATRICULA) " +
            "VALUES (?,?,?);";
    private static final String SQL_BUSCAR = "SELECT * FROM ODONTOLOGOS WHERE ID = ?;";
    private static final String SQL_ACTUALIZAR = "UPDATE ODONTOLOGOS SET APELLIDO = ?, NOMBRE = ?, MATRICULA = ? WHERE ID = ?;";
    private static final String SQL_ELIMINAR = "DELETE FROM ODONTOLOGOS WHERE ID = ?;";
    private static final String SQL_BUSCAR_TODOS = "SELECT * FROM ODONTOLOGOS;";
    private static final Logger LOGGER = Logger.getLogger(OdontologoDAOH2.class);
    @Override
    public Odontologo guardar(Odontologo odontologo) {
        LOGGER.info("Se esta registrando un odontologo nuevo");
        Connection connection = null;
        try{
            connection=BD.getConnection();
            PreparedStatement psInsert =connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            psInsert.setString(1,odontologo.getApellido());
            psInsert.setString(2,odontologo.getNombre());
            psInsert.setString(3,odontologo.getMatricula());
            psInsert.execute();
            ResultSet rs = psInsert.getGeneratedKeys();
            while (rs.next()){
                odontologo.setID(rs.getInt(1));
            }

            LOGGER.info("Se registraron los datos del nuevo odontologo correctamente");
        }
        catch (Exception e){
            LOGGER.error("Se produjo un error al registrar el odontologo: "+e.getMessage());
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
        return odontologo;
    }

    @Override
    public Odontologo buscar(int id) {
        return null;
    }

    @Override
    public void actualizar(Odontologo odontologo) {

    }

    @Override
    public void eliminar(int id) {

    }

    @Override
    public List<Odontologo> buscarTodo() {
        return null;
    }
}
