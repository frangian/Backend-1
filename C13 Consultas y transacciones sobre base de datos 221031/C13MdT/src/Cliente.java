import java.sql.*;

public class Cliente {
    private static final String SQL_CREATE_DROP = "DROP TABLE IF EXISTS ODONTOLOGO; CREATE TABLE ODONTOLOGO "
            +"("
            +"ID INT PRIMARY KEY,"
            +"APELLIDO VARCHAR (100) NOT NULL,"
            +"NOMBRE VARCHAR (100) NOT NULL,"
            +"MATRICULA VARCHAR (100) NOT NULL"
            +");";
    private static final String SQL_INSERT = "INSERT INTO ODONTOLOGO VALUES (?,?,?,?);";
    private static final String SQL_UPDATE = "UPDATE ODONTOLOGO SET MATRICULA = ? WHERE ID = ?;";
    private static final String SQL_SELECT = "SELECT * FROM ODONTOLOGO;";

    public static void main(String[] args) throws Exception{
        Connection conn = null;
        try {

            conn = getConnection();
            Statement st = conn.createStatement();
            st.execute(SQL_CREATE_DROP);

            PreparedStatement psInsert = conn.prepareStatement(SQL_INSERT);
            psInsert.setInt(1,1);
            psInsert.setString(2,"GARCIA");
            psInsert.setString(3,"JUAN");
            psInsert.setString(4,"456789");
            psInsert.execute();

            ResultSet rs = st.executeQuery(SQL_SELECT);
            while (rs.next()){
                System.out.println("ID: "+rs.getInt(1) + " - APELLIDO: "+rs.getString(2)+ " - NOMBRE: "+rs.getString(3)+ " - MATRICULA: "+rs.getString(4));
            }
            System.out.println("*****************************");

            conn.setAutoCommit(false);

            PreparedStatement psUpdate = conn.prepareStatement(SQL_UPDATE);
            psUpdate.setString(1,"555555");
            psUpdate.setInt(2,1);
            psUpdate.execute();
//            int a = 4/0;

            conn.commit();
            conn.setAutoCommit(true);


        } catch (Exception e) {
            conn.rollback();
            e.printStackTrace();
        } finally {
            try {
            conn.close();

            } catch (Exception ex){
                ex.printStackTrace();
            }
        }

        Connection conn2 = getConnection();
        Statement st2 = conn2.createStatement();
        ResultSet rs = st2.executeQuery(SQL_SELECT);
        while (rs.next()){
            System.out.println("ID: "+rs.getInt(1) + " - APELLIDO: "+rs.getString(2)+ " - NOMBRE: "+rs.getString(3)+ " - MATRICULA: "+rs.getString(4));
        }
        System.out.println("*****************************");

    }

    public static Connection getConnection () throws Exception{
        Class.forName("org.h2.Driver").newInstance();
        return DriverManager.getConnection("jdbc:h2:./C13MdT", "sa","sa");
    }

}
