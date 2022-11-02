package test;

import dao.BD;
import model.Impresora;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.ImpresoraService;

public class ImpresoraServiceTest {

    @Test
    public void buscar(){
        ImpresoraService impresoraService = new ImpresoraService();
        BD.crearTablas();
        Impresora impresora = new Impresora(1,"Recepcion","EPSON","NEGRA");
        impresoraService.registrarImpresora(impresora);

        Impresora busqueda = impresoraService.buscarImpresora(1);
        Assertions.assertEquals(impresora.getNombre(),busqueda.getNombre());
    }
    @Test
    public void busquedaNotOk(){
        ImpresoraService impresoraService = new ImpresoraService();
        BD.crearTablas();
        Impresora impresora = new Impresora(1,"HP100","HP","blanco");
        Impresora impresora2 = new Impresora(2,"HP999","EPSON","negro");

        impresoraService.registrarImpresora(impresora);
        impresoraService.registrarImpresora(impresora2);

        Impresora busqueda = impresoraService.buscarImpresora(1);

        Assertions.assertNotEquals(impresora2.toString(), busqueda.toString());
    }
}