import static org.junit.jupiter.api.Assertions.*;
import java.sql.*;

class EmpleadoTest {

    public static void main(String[] args) throws Exception {

        Class.forName("org.h2.Driver").newInstance();
        Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
        Statement stmt = connection.createStatement();

        String createTable = "DROP TABLE IF EXISTS TEST;" + "CREATE TABLE TEST (ID INT PRIMARY KEY, NOMBRE VARCHAR (255), EDAD INT, EMPRESA VARCHAR (255), FECHAINICIO VARCHAR (10));";
        stmt.execute(createTable);

        String insertRows = "INSERT INTO TEST VALUES (1,'PEPE',25,'DIGITAL', '22/10/2020');"+"INSERT INTO TEST VALUES (2,'JUAN',26,'FACEBOOK', '15/07/2021');"+"INSERT INTO TEST VALUES (3,'ANA',30,'GOOGLE', '01/01/2020');";
        stmt.execute(insertRows);

        String sql = "SELECT * FROM TEST";
        ResultSet rd = stmt.executeQuery(sql);

        while (rd.next()){
            System.out.println(rd.getInt(1) + " " +rd.getString(2) + " " +rd.getInt(3) + " " +rd.getString(4)+ " " +rd.getString(5));
        };

    }

}