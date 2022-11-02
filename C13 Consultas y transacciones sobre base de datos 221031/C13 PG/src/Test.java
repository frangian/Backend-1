import javax.swing.plaf.nimbus.State;
import java.sql.*;

public class Test {

    private static final String SQL_DROP_CREATE = "DROP TABLE IF EXISTS PACIENTE; CREATE TABLE PACIENTE "
        +"("
        +"ID INT PRIMARY KEY,"
        +"NOMBRE VARCHAR (50) NOT NULL,"
        +"APELLIDO VARCHAR (50) NOT NULL,"
        +"DOMICILIO VARCHAR (50) NOT NULL,"
        +"USUARIO VARCHAR (50) NOT NULL,"
        +"PASSWORD VARCHAR (50) NOT NULL,"
        +"DNI INT,"
        +"FECHAALTA VARCHAR (50) NOT NULL"
        +");";

    private static final String SQL_INSERT = "INSERT INTO PACIENTE (ID,NOMBRE,APELLIDO,DOMICILIO,USUARIO, PASSWORD, DNI, FECHAALTA) VALUES (?,?,?,?,?,?,?,?);";

    private static final String SQL_UPDATE = "UPDATE PACIENTE SET PASSWORD = ? WHERE ID = ?;";


    public static void main(String[] args) throws Exception {
        Paciente paciente = new Paciente("pepe","garcia","los arces 100","pepe","123",123456,"2020/1/1");

        Connection conn = null;

        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            st.execute(SQL_DROP_CREATE);

            PreparedStatement psInsert = conn.prepareStatement(SQL_INSERT);
            psInsert.setInt(1,1);
            psInsert.setString(2,paciente.getNombre());
            psInsert.setString(3,paciente.getApellido());
            psInsert.setString(4,paciente.getDomicilio());
            psInsert.setString(5,paciente.getUsuario());
            psInsert.setString(6,paciente.getPassword());
            psInsert.setInt(7,paciente.getDNI());
            psInsert.setString(8,paciente.getFechaAlta());
            psInsert.execute();

            conn.setAutoCommit(false);

            PreparedStatement psUpdate = conn.prepareStatement(SQL_UPDATE);
            psUpdate.setString(1,"456");
            psUpdate.setInt(2,1);
            psUpdate.execute();
//            int a = 4 / 0;

            conn.commit();
            conn.setAutoCommit(true);

            String sql = "SELECT * FROM PACIENTE;";
            Statement st2 = conn.createStatement();
            ResultSet rs = st2.executeQuery(sql);
            while (rs.next()){
                System.out.println("ID: "+rs.getInt(1) + " - NOMBRE: "+rs.getString(2)+ " - APELLIDO: "+rs.getString(3)+ " - DOMICILIO: "+rs.getString(4)+ " - USUARIO: "+rs.getString(5)+ " - PASS: "+rs.getString(6)+ " - DNI: "+rs.getInt(7)+ " - FECHAALTA: "+rs.getString(8));
            }
            System.out.println("*****************************");

        } catch (Exception e) {
            e.printStackTrace();
            conn.rollback();
        } finally {
            conn.close();
        }

        Connection conn1 = getConnection();
        String sql = "SELECT * FROM PACIENTE;";
        Statement st3 = conn1.createStatement();
        ResultSet rs = st3.executeQuery(sql);
        while (rs.next()){
            System.out.println("ID: "+rs.getInt(1) + " - NOMBRE: "+rs.getString(2)+ " - APELLIDO: "+rs.getString(3)+ " - DOMICILIO: "+rs.getString(4)+ " - USUARIO: "+rs.getString(5)+ " - PASS: "+rs.getString(6)+ " - DNI: "+rs.getInt(7)+ " - FECHAALTA: "+rs.getString(8));
        }

    }

    private static Connection getConnection() throws Exception{
        Class.forName("org.h2.Driver").newInstance();
        return DriverManager.getConnection("jdbc:h2:./C13", "sa", "");
    }

}
