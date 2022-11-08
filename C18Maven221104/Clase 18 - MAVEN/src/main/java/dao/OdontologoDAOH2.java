package dao;

import model.Odontologo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.List;

public class OdontologoDAOH2 implements IDao <Odontologo>{

    private static final String SQL_INSERT="INSERT INTO ODONTOLOGOS (APELLIDO," +
            "NOMBRE,MATRICULA) VALUES (?,?,?)";
    private static final String SQL_SELECT="SELECT * FROM ODONTOLOGOS WHERE ID=?";

    private static final String SQL_UPDATE="UPDATE ODONTOLOGOS SET APELLIDO=?, NOMBRE=?, MATRICULA=? WHERE ID=?";
    private static final String SQL_DELETE="DELETE FROM ODONTOLOGOS WHERE ID=?";
    private static final String SQL_SELECT_ALL="SELECT * FROM ODONTOLOGOS";

    private static final Logger LOGGER= Logger.getLogger(OdontologoDAOH2.class);
    @Override
    public Odontologo guardar(Odontologo odontologo) {
        Connection connection=null;
        try{
            LOGGER.info("Inicio de una operación de guardar un odontologo");
            connection=BD.getConnection();
            PreparedStatement psInsert=connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            psInsert.setString(1,odontologo.getApellido());
            psInsert.setString(2,odontologo.getNombre());
            psInsert.setString(3, odontologo.getMatricula());
            psInsert.execute();
            ResultSet rs=psInsert.getGeneratedKeys();
            while(rs.next()){
                odontologo.setId(rs.getInt(1));
            }

            //psInsert.close();
            //el código para guardar
        }
        catch (Exception e){
            LOGGER.error("Error: "+e.getMessage());
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
        return odontologo;
    }



    @Override
    public Odontologo buscar(Integer id) {
        return null;
    }

    @Override
    public void actualizar(Odontologo odontologo) {

    }

    @Override
    public void eliminar(Integer id) {

    }

    @Override
    public List<Odontologo> buscarTodo() {
        return null;
    }
}
