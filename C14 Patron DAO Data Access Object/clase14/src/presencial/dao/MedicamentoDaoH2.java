package presencial.dao;

import org.apache.log4j.Logger;
import presencial.model.Medicamento;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class MedicamentoDaoH2 implements IDao<Medicamento>{
    private static final String SQL_INSERT="INSERT INTO MEDICAMENTOS " +
            "VALUES (?,?,?,?,?,?);";
    private static final Logger LOGGER= Logger.getLogger(MedicamentoDaoH2.class);
    @Override
    public Medicamento guardar(Medicamento medicamento) {
        LOGGER.info("Se inició un pedido de incorporación de medicamento");
        //va el código que realizabamos con anteriodad
        //ahora la información está en medicamento como parametro
        Connection connection=null;
        try{
            //conectarme a la base
            connection=BD.getConnection();
            //insertar
            PreparedStatement psInsert= connection.prepareStatement(
                    SQL_INSERT);
            psInsert.setInt(1,medicamento.getId());
            psInsert.setString(2,medicamento.getNombre());
            psInsert.setString(3, medicamento.getLaboratorio());
            psInsert.setInt(4,medicamento.getCantidad());
            psInsert.setDouble(5,medicamento.getPrecio());
            psInsert.setInt(6,medicamento.getCodigo());
            psInsert.execute();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try{
                connection.close();
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return medicamento;
    }
}
