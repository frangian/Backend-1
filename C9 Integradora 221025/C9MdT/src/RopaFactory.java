import java.util.HashMap;
import java.util.Map;

public class RopaFactory {

    private static Map<String, Ropa> ropaMap = new HashMap<>();


    public static Ropa getRopa(String talle, String tipo, Boolean esNuevo, Boolean importada){
        Ropa ropa = ropaMap.get(tipo);
        if (ropa==null){
            ropa = new Ropa(talle, tipo, esNuevo, importada);
            ropaMap.put(tipo, ropa);
        } else {
            ropa.setTalle(talle);
            ropa.setEsNuevo(esNuevo);
            ropa.setImportada(importada);
        }
        return ropa;
    }

    public static Map<String, Ropa> getRopaMap() {
        return ropaMap;
    }
}
