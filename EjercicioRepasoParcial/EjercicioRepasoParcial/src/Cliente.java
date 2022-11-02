import dao.BD;
import model.Impresora;
import service.ImpresoraService;

public class Cliente {

    public static void main(String[] args) {
        ImpresoraService impresoraService = new ImpresoraService();
        BD.crearTablas();
        Impresora impresora = new Impresora(1,"Recepcion","EPSON","NEGRA");
        Impresora impresora2 = new Impresora(2,"Sala","HP","BLANCA");
        Impresora impresora3 = new Impresora(3,"Sala2","HP","BLANCA");
        Impresora impresora4 = new Impresora(4,"Comedor","SAMSUNG","BLANCA");
        impresoraService.registrarImpresora(impresora);
        impresoraService.registrarImpresora(impresora2);
        impresoraService.registrarImpresora(impresora3);
        impresoraService.registrarImpresora(impresora4);

        impresoraService.eliminarImpresora(2);

        System.out.println(impresoraService.buscarImpresora(2));

        System.out.println(impresoraService.buscarImpresora(3));

    }

}
