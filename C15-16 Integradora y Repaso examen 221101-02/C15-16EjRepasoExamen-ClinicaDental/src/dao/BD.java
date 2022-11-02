package dao;

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
            "FECHA_INGRESO DATE," +
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
//    private static final String SQL_ = "";
//    private static final String SQL_ = "";
//    private static final String SQL_ = "";

    public static Connection getConnection () throws Exception{
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbd:h2:~/C15","sa","sa");
    }
    public static void crearTablas (){
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            st.execute(SQL_DROP_CREATE_PACIENTES);
            st.execute(SQL_DROP_CREATE_DOMICILIOS);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try {
                conn.close();
            } catch ( SQLException ex){
                ex.printStackTrace();
            }
        }
    }

}
