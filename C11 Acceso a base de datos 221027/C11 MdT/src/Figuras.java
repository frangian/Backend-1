import java.sql.*;


public class Figuras {

    public static void main(String[] args) throws Exception{

        Class.forName("org.h2.Driver").newInstance();
        Connection connection = DriverManager.getConnection("jdbc:h2:~/clase11MdT", "sa","");
        Statement statement = connection.createStatement();

        String creamosTabla = "DROP TABLE IF EXISTS FIGURAS; \n" + "CREATE TABLE FIGURAS (ID INT PRIMARY KEY, NOMBRE VARCHAR (100), COLOR VARCHAR (50)); \n";
        statement.execute(creamosTabla);

        String datos = "INSERT INTO FIGURAS VALUES (1,'CIRCULO','ROJO'),(2,'CIRCULO','AZUL'),(3,'CUADRADO','ROJO'),(4,'CUADRADO','AMARILLO'),(5,'CUADRADO','NEGRO'),(6,'CIRCULO','ROJO');\n";
        statement.execute(datos);

        String sql = "SELECT * FROM FIGURAS WHERE COLOR = 'ROJO' AND NOMBRE = 'CIRCULO';";
        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()){
            System.out.println(rs.getString(2) + " - "+ rs.getString(3));
        }

    }

}
