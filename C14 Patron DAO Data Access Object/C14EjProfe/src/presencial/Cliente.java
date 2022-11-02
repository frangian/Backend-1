package presencial;

import presencial.DAO.BD;
import presencial.MODEL.Medicamento;
import presencial.SERVICE.MedicamentoService;

public class Cliente {
    public static void main(String[] args) {
        //necesitas el Service
        //necesitas crear las tablas
        //necesitas un medicamento para poder guardarlo con el service
        MedicamentoService medservice = new MedicamentoService();
        BD.crearTablas();
        Medicamento med = new Medicamento(1,"Paracetamol","Pepito SRL",10,200d,123);
        //pedirle al service que lo guarde
        medservice.guardar(med);
        medservice.buscar(1);
    }
}
