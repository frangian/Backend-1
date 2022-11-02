package presencial;

import presencial.dao.BD;
import presencial.model.Medicamento;
import presencial.service.MedicamentoService;

public class Cliente {
    public static void main(String[] args) {
        //necesitas el Service
        MedicamentoService medicamentoService= new MedicamentoService();
        //necesitas crear las tablas
        BD.crearTablas();
        //necesitas un medicamento para poder guardarlo con el service
        Medicamento medicamento= new Medicamento(1,"Paracetamol",
                "Pepito SRL",1500,200.0,987481);
        //pedirle al service que lo guarde
        medicamentoService.guardar(medicamento);
    }
}
