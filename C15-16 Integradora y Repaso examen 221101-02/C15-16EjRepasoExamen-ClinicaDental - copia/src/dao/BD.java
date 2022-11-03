package dao;

import org.apache.log4j.Logger;

import java.sql.*;

public class BD {
    private static final String SQL_DROP_CREATE_PACIENTES = "" +
            "DROP TABLE IF EXISTS PACIENTES; " +
            "CREATE TABLE PACIENTES" +
            "(" +
            "ID INT AUTO_INCREMENT PRIMARY KEY," +
            "NOMBRE VARCHAR (100)," +
            "APELLIDO VARCHAR (100)," +
            "DNI VARCHAR (12)," +
            "FECHAINGRESO DATE," +
            "DOMICILIO_ID INT" +
            ");";
    private static final String SQL_DROP_CREATE_DOMICILIOS = "" +
            "DROP TABLE IF EXISTS DOMICILIOS;" +
            "CREATE TABLE DOMICILIOS" +
            "(" +
            "ID INT AUTO_INCREMENT PRIMARY KEY," +
            "CALLE VARCHAR (100)," +
            "NUMERO INT," +
            "LOCALIDAD VARCHAR (100)," +
            "PROVINCIA VARCHAR (100)" +
            ");";
    private static final String SQL_DROP_CREATE_ODONTOLOGOS = "" +
            "DROP TABLE IF EXISTS ODONTOLOGOS;" +
            "CREATE TABLE ODONTOLOGOS" +
            "(" +
            "ID INT AUTO_INCREMENT PRIMARY KEY," +
            "APELLIDO VARCHAR (100)," +
            "NOMBRE VARCHAR (100)," +
            "MATRICULA VARCHAR (100)" +
            ");";

    private static final Logger LOGGER = Logger.getLogger(BD.class);

    public static Connection getConnection () throws Exception{
        LOGGER.info("Se inicia la conexion al servidor...");
        Class.forName("org.h2.Driver");
//        LOGGER.info("Conexion al servidor exitosa");
        return DriverManager.getConnection("jdbc:h2:~/C15","sa","sa");
    }
    public static void crearTablas (){
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            st.execute(SQL_DROP_CREATE_PACIENTES);
            st.execute(SQL_DROP_CREATE_DOMICILIOS);
            st.execute(SQL_DROP_CREATE_ODONTOLOGOS);
        LOGGER.info("Se crean las tablas en la base de datos con exito");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try {
                conn.close();
                LOGGER.info("Se cierra la conexion con el servidor");
            } catch ( SQLException ex){
                ex.printStackTrace();
            }
        }
    }

}
