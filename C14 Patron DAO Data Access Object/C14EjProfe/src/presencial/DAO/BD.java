package presencial.DAO;

import java.sql.*;

public class BD {
    public static Connection getConnection () throws Exception{
        Class.forName("org.h2.Driver").newInstance();
        return DriverManager.getConnection("jdbc:h2:./C14EjProfe","sa","sa");
    };
    public static void crearTablas(){
        Connection conn = null;
        try {
            conn=getConnection();
            Statement st = conn.createStatement();
            st.execute("DROP TABLE IF EXISTS MEDICAMENTO; CREATE TABLE MEDICAMENTO " +
                    "(" +
                    "ID INT PRIMARY KEY," +
                    "NOMBRE VARCHAR (100)," +
                    "LABORATORIO VARCHAR (100) NOT NULL," +
                    "CANTIDAD INT NOT NULL," +
                    "PRECIO NUMERIC (10,2) NOT NULL," +
                    "CODIGO INT NOT NULL);");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
}
