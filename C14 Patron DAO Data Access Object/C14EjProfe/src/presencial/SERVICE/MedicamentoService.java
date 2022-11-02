package presencial.SERVICE;

import presencial.DAO.IDao;
import presencial.DAO.MedicamentoDaoH2;
import presencial.MODEL.Medicamento;

public class MedicamentoService {

    private IDao<Medicamento> medicamentoIDao;

    public MedicamentoService() {
        this.medicamentoIDao = new MedicamentoDaoH2();
    }

    public Medicamento guardar(Medicamento medicamento){
        return medicamentoIDao.guardar(medicamento);
    }
    public void buscar (Integer id){ medicamentoIDao.buscar(id); }

}
