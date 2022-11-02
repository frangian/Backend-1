package presencial.DAO;

import org.apache.log4j.Logger;
import presencial.MODEL.Medicamento;

import java.sql.*;


public class MedicamentoDaoH2 implements IDao<Medicamento>{
    private static final String SQL_INSERT = "INSERT INTO MEDICAMENTO VALUES (?,?,?,?,?,?);";
    private static final String SQL_SELECT = "SELECT * FROM MEDICAMENTO WHERE ID = ?;";
    private static final Logger LOGGER = Logger.getLogger(MedicamentoDaoH2.class);

    @Override
    public Medicamento guardar(Medicamento medicamento) {
        LOGGER.info("Se inicio un pedido de incorporacion de medicamento");
        //aqui va el codigo q realizamabamos con anteoridad pero ahora la info esta en medicamenteo como parametro
        Connection connection = null;
        try{
        connection = BD.getConnection();
        PreparedStatement psInsert = connection.prepareStatement(SQL_INSERT);

        psInsert.setInt(1,medicamento.getId());
        psInsert.setString(2,medicamento.getNombre());
        psInsert.setString(3,medicamento.getLaboratorio());
        psInsert.setInt(4,medicamento.getCantidad());
        psInsert.setDouble(5,medicamento.getPrecio());
        psInsert.setInt(6,medicamento.getCodigo());
        psInsert.execute();


        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return medicamento;
    }

    @Override
    public void buscar(Integer id) {
        LOGGER.info("Se inicio un pedido de busqueda de medicamento");
        //aqui va el codigo q realizamabamos con anteoridad pero ahora la info esta en medicamenteo como parametro
        Connection connection = null;
        try{
        connection = BD.getConnection();
        PreparedStatement psSelect = connection.prepareStatement(SQL_SELECT);

        psSelect.setInt(1,id);

        ResultSet rs = psSelect.executeQuery();
        while (rs.next()){
            System.out.println("ID: "+rs.getInt(1) + " - NOMBRE: "+rs.getString(2)+ " - LABORATORIO: "+rs.getString(3)+ " - CANTIDAD: "+rs.getInt(4)+ " - PRECIO: "+rs.getDouble(5)+" - CODIGO: "+rs.getInt(6));
        }
        System.out.println("*****************************");


        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
}
