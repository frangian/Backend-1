package service;

import dao.ImpresoraDAOH2;
import dao.iDAO;
import model.Impresora;

public class ImpresoraService {

    private iDAO<Impresora> impresoraiDAO;

    //constructor del servicio
    public ImpresoraService(){ this.impresoraiDAO = new ImpresoraDAOH2(); }

    //metodos del servicio
    public Impresora registrarImpresora (Impresora impresora){ return impresoraiDAO.registrar(impresora); }

    public void eliminarImpresora(int id){ impresoraiDAO.eliminar(id); }

    public Impresora buscarImpresora(int id){ return impresoraiDAO.buscar(id); }
}
