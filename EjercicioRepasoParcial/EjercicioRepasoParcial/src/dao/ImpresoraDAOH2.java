package dao;

import model.Impresora;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ImpresoraDAOH2 implements iDAO<Impresora> {
    private static final String SQL_INSERT = "" +
            "INSERT INTO IMPRESORA VALUES (?,?,?,?);";
    private static final String SQL_BUSCAR = "SELECT * FROM IMPRESORA WHERE ID = ?;";
    private static final String SQL_ELIMINAR = "DELETE FROM IMPRESORA WHERE ID = ?;";
    private static final Logger LOGGER = Logger.getLogger(ImpresoraDAOH2.class);

    @Override
    public Impresora registrar(Impresora impresora) {
        LOGGER.info("Se esta registrando una impresora nueva");
        Connection connection = null;
        try{
            connection=BD.getConnection();
            PreparedStatement psInsert =connection.prepareStatement(SQL_INSERT);
            psInsert.setInt(1,impresora.getId());
            psInsert.setString(2,impresora.getNombre());
            psInsert.setString(3,impresora.getMarca());
            psInsert.setString(4,impresora.getColor());
            psInsert.execute();

            LOGGER.info("Se registraron los datos de la nueva impresora correctamente");
        }
        catch (Exception e){
            LOGGER.error("Se produjo un error al registrar la impresora: "+e.getMessage());
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
        return impresora;
    }

    @Override
    public void eliminar(int id) {
        LOGGER.info("Se esta eliminando una impresora");
        Connection connection = null;
        try{
            connection=BD.getConnection();
            PreparedStatement psDelete =connection.prepareStatement(SQL_ELIMINAR);
            psDelete.setInt(1,id);
            psDelete.execute();

            LOGGER.info("Se elimino la impresora correctamente");
        }
        catch (Exception e){
            LOGGER.error("Se produjo un error al eliminar la impresora: "+e.getMessage());
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
    public Impresora buscar(int id) {
        LOGGER.info("Se esta realizando una busqueda de una impresora");
        Impresora respuesta = null;

        Connection connection = null;
        try{
            connection=BD.getConnection();
            PreparedStatement psBuscar =connection.prepareStatement(SQL_BUSCAR);
            psBuscar.setInt(1,id);
            ResultSet rs = psBuscar.executeQuery();
            while (rs.next()){
                respuesta = new Impresora(rs.getString(1),rs.getString(2),rs.getString(3));
            }

            LOGGER.info("Se realizo la busqueda de la impresora correctamente");
        }
        catch (Exception e){
            LOGGER.error("Se produjo un error al buscar la impresora: "+e.getMessage());
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
}
