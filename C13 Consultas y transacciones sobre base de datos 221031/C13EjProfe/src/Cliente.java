import java.sql.*;

public class Cliente {

        private static final String SQL_DROP_CREATE = "DROP TABLE IF EXISTS CUENTAS; CREATE TABLE CUENTAS "
                +"("
                +"ID INT PRIMARY KEY,"
                +"NUMERO_CUENTA INT NOT NULL,"
                +"NOMBRE VARCHAR (100) NOT NULL,"
                +"SALDO NUMERIC (100,2) NOT NULL"
                +");";
        private static final String SQL_INSERT= "INSERT INTO CUENTAS VALUES (?,?,?,?);";
        private static final String SQL_UPDATE = "UPDATE CUENTAS SET SALDO = ? WHERE ID = ?;";
        private static final String SQL_SELECT = "SELECT * FROM CUENTAS;";

    public static void main(String[] args) throws Exception {
        Connection connection = null;

        try {
            connection=getConnection();
            Statement st = connection.createStatement();
            st.execute(SQL_DROP_CREATE);

            PreparedStatement psInsert = connection.prepareStatement(SQL_INSERT);
            psInsert.setInt(1,1);
            psInsert.setInt(2,222);
            psInsert.setString(3,"Rodolfo");
            psInsert.setDouble(4,100d);
            psInsert.execute();

            connection.setAutoCommit(false);

            PreparedStatement psUpdate = connection.prepareStatement(SQL_UPDATE);
            psUpdate.setDouble(1,200d);
            psUpdate.setInt(2,1);
            psUpdate.execute();
            int a = 4/0;

            connection.commit();
            connection.setAutoCommit(true);

            Statement statement2 = connection.createStatement();
            ResultSet rs = statement2.executeQuery(SQL_SELECT);
            while (rs.next()){
                System.out.println("ID: "+rs.getInt(1) + " - NUMERO_CUENTA: "+rs.getInt(2)+ " - NOMBRE: "+rs.getString(3)+ " - SALDO: "+rs.getDouble(4));
            }
            System.out.println("*****************************");

        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
        } finally {
            try{
            connection.close();
            } catch (Exception ex){
                ex.printStackTrace();
            }
        }
        Connection conn1 = getConnection();
        String sql = "SELECT * FROM CUENTAS;";
        Statement st3 = conn1.createStatement();
        ResultSet rs = st3.executeQuery(sql);
        while (rs.next()){
            System.out.println("ID: "+rs.getInt(1) + " - NUMERO_CUENTA: "+rs.getInt(2)+ " - NOMBRE: "+rs.getString(3)+ " - SALDO: "+rs.getDouble(4));
        }
        System.out.println("*****************************");

    }

    public static final Connection getConnection() throws Exception{
        Class.forName("org.h2.Driver").newInstance();
        return DriverManager.getConnection("jdbc:h2:./C13EjProfe","sa","sa");
    }
}
