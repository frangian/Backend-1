import org.apache.log4j.Logger;

import java.sql.*;

public class Cliente {
    //Preparar los SQL mediante constantes
    private static final String SQL_DROP_CREATE="DROP TABLE IF EXISTS USUARIOS; CREATE TABLE USUARIOS (ID INT PRIMARY KEY, PRIMER_NOMBRE VARCHAR(100) NOT NULL, APELLIDO VARCHAR (100) NOT NULL, EDAD INT NOT NULL);";
    private static final String SQL_INSERT = "INSERT INTO USUARIOS VALUES (1, 'Rodolfo', 'Baspineiro',29),(2, 'Ezequiel', 'Baspineiro',29),(3, 'Kevin', 'Baspineiro',29);";
    private static final String SQL_DELETE = "DELETE FROM USUARIOS WHERE ID = 3;";
    private static final String SQL_SELECT = "SELECT * FROM USUARIOS;";
    //pasarle el nombre de la clase + .class
    private static final Logger LOGGER = Logger.getLogger(Cliente.class);

    public static void main(String[] args) {
        //preparar la conexion
        Connection connection = null;
        //como el metodo connection arroja excepciones lo debemos meter en un try catch
        try {
            connection=getConnection();
            Statement statement = connection.createStatement();
            //crear la tabla
            statement.execute(SQL_DROP_CREATE);
            //insertar 3 usuarios
            statement.execute(SQL_INSERT);
            //mostrar
            ResultSet rs = statement.executeQuery(SQL_SELECT);
            while (rs.next()){
                System.out.println("ID: "+ rs.getInt(1)+" - Primer nombre: "+rs.getString(2)+" - Apellido: "+rs.getString(3)+ " - Edad: "+rs.getInt(4));
                LOGGER.info("ID: "+ rs.getInt(1)+" - Primer nombre: "+rs.getString(2)+" - Apellido: "+rs.getString(3)+ " - Edad: "+rs.getInt(4));
            }
            System.out.println("******************************");
            //borrar un usuario
            statement.execute(SQL_DELETE);
            LOGGER.warn("Aviso: se borro el usuario con id=3");
            //mostrar
            rs = statement.executeQuery(SQL_SELECT);
            while (rs.next()){
                System.out.println("ID: "+ rs.getInt(1)+" - Primer nombre: "+rs.getString(2)+" - Apellido: "+rs.getString(3)+ " - Edad: "+rs.getInt(4));
                LOGGER.info("ID: "+ rs.getInt(1)+" - Primer nombre: "+rs.getString(2)+" - Apellido: "+rs.getString(3)+ " - Edad: "+rs.getInt(4));
            }
            System.out.println("******************************");

        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            //como al cerra la connexion tambien tira una excepcion, debemos meterla dentro de otro try catch
            try {
            connection.close();
                }
            catch (Exception ex){
                ex.printStackTrace();
            }
            //cierre de la conexion por nuestra cuenta
        }
    }
    public static Connection getConnection () throws Exception{
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:~/c3clase12","sa","sa");
    }

}
