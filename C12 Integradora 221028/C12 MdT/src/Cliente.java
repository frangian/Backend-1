import org.apache.log4j.Logger;

import java.sql.*;

public class Cliente {
    private static final String SQL_CREATE_DROP_TABLE = "DROP TABLE IF EXISTS EMPLEADO; CREATE TABLE EMPLEADO (ID INT PRIMARY KEY, NOMBRE VARCHAR (100), APELLIDO VARCHAR (100), EDAD INT, EMPRESA VARCHAR (100));";
    private static final String SQL_INSERT_WRONG = "INSERT INTO EMPLEADO VALUES (1,'JORGE','GARCIA',25,'FACEBOOK'),(1,'ANDRES','PEREZ',26,'GOOGLE'),(3,'ERNESTO','FIORE',30,'APPLE');";
    private static final String SQL_INSERT = "INSERT INTO EMPLEADO VALUES (1,'JORGE','GARCIA',25,'FACEBOOK'),(2,'ANDRES','PEREZ',26,'GOOGLE'),(3,'ERNESTO','FIORE',30,'APPLE');";
    private static final String SQL_SELECT = "SELECT * FROM EMPLEADO;";
    private static final String SQL_UPDATE = "UPDATE EMPLEADO SET NOMBRE = 'RAUL' WHERE ID = 2;";
    private static final String SQL_DELETE = "DELETE FROM EMPLEADO WHERE ID = 3;";
    private static final String SQL_DELETE2 = "DELETE FROM EMPLEADO WHERE APELLIDO ='GARCIA';";
    private static final Logger LOGGER = Logger.getLogger(Cliente.class);
    public static void main(String[] args) {
        Connection connection = null;
            //Debemos insertar tres filas con distintos datos, y una de ellas debe tener el ID repetido.
        try {
            connection=getConnection();
            Statement statement = connection.createStatement();
            statement.execute(SQL_CREATE_DROP_TABLE);
            statement.execute(SQL_INSERT_WRONG);
            ResultSet rs = statement.executeQuery(SQL_SELECT);
            while (rs.next()){
                System.out.println("ID: "+rs.getInt(1)+
                        " - Primer Nombre: "+rs.getString(2)+
                        " - Apellido: "+rs.getString(3)+
                        " - Edad: "+rs.getInt(4) + " - Empresa: "+rs.getString(5));
                LOGGER.info("ID: "+rs.getInt(1)+
                        " - Primer Nombre: "+rs.getString(2)+
                        " - Apellido: "+rs.getString(3)+
                        " - Edad: "+rs.getInt(4) + " - Empresa: "+rs.getString(5));
            }
            System.out.println("**************************");
        }
        catch (Exception e){
            //Tenemos que verificar que nuestro log está mostrando este error —como los ID son primary keys, al intentar insertar el mismo id, nos va a dar un error, loguear el error—.
            LOGGER.error(e.toString());
            e.printStackTrace();
            System.out.println("***************************");
            LOGGER.info("*************************************");
        }
        finally {
            try {
                connection.close();
            }
            catch (Exception e2){
                e2.printStackTrace();
            }
        }
            //Luego, hay que actualizar a uno de los empleados con un nuevo valor en alguna de las columnas y loguear con un objeto debug toda la información del empleado.
        try {
            connection=getConnection();
            Statement statement = connection.createStatement();
            statement.execute(SQL_CREATE_DROP_TABLE);
            statement.execute(SQL_INSERT);
            ResultSet rs = statement.executeQuery(SQL_SELECT);
            while (rs.next()){
                System.out.println("ID: "+rs.getInt(1)+
                        " - Primer Nombre: "+rs.getString(2)+
                        " - Apellido: "+rs.getString(3)+
                        " - Edad: "+rs.getInt(4) + " - Empresa: "+rs.getString(5));
                LOGGER.info("ID: "+rs.getInt(1)+
                        " - Primer Nombre: "+rs.getString(2)+
                        " - Apellido: "+rs.getString(3)+
                        " - Edad: "+rs.getInt(4) + " - Empresa: "+rs.getString(5));
            }
            System.out.println("**************************");
            LOGGER.info("*************************************");
            statement.execute(SQL_UPDATE);
            rs = statement.executeQuery(SQL_SELECT);
            while (rs.next()){
                System.out.println("ID: "+rs.getInt(1)+
                        " - Primer Nombre: "+rs.getString(2)+
                        " - Apellido: "+rs.getString(3)+
                        " - Edad: "+rs.getInt(4) + " - Empresa: "+rs.getString(5));
                LOGGER.debug("ID: "+rs.getInt(1)+
                        " - Primer Nombre: "+rs.getString(2)+
                        " - Apellido: "+rs.getString(3)+
                        " - Edad: "+rs.getInt(4) + " - Empresa: "+rs.getString(5));
            }
            System.out.println("**************************");
        }
        catch (Exception e){
            LOGGER.error(e.toString());
            e.printStackTrace();
        }
        finally {
            try {
                connection.close();
            }
            catch (Exception e2){
                e2.printStackTrace();
            }
        }


    }

    public static Connection getConnection () throws Exception{
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:~/C12MdT","sa","sa");
    }
}
