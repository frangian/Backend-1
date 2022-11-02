package dao;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class BD {
    private static String jdbc = "jdbc:h2:~/EjercicioRepasoParcial";
    private static String driver = "org.h2.Driver";
    private static String usuario = "sa";
    private static String pass = "sa";
    private static final Logger LOGGER = Logger.getLogger(BD.class);
    private static final String SQL_DROP_CREATE = "" +
            "DROP TABLE IF EXISTS IMPRESORA;" +
            "CREATE TABLE IMPRESORA" +
            "(" +
            "ID INT AUTO_INCREMENT PRIMARY KEY," +
            "NOMBRE VARCHAR (100) NOT NULL," +
            "MARCA VARCHAR (50) NOT NULL," +
            "COLOR VARCHAR (50) NOT NULL" +
            ");";
    public static Connection getConnection() throws Exception {
        Class.forName(driver);
        return DriverManager.getConnection(jdbc,usuario,pass);
    };

    public static void crearTablas(){
        Connection connection=null;
        try{
            //drop create
            LOGGER.info("Se esta creando la tabla");
            connection=getConnection();
            Statement statement= connection.createStatement();
            statement.execute(SQL_DROP_CREATE);
            LOGGER.info("Se creo la tabla correctamente");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try{
                connection.close();
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }


}
