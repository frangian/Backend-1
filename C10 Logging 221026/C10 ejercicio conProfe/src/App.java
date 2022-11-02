import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class App {
    private static final Logger logger=Logger.getLogger(App.class);
    private List<Integer> listaEnteros;

    public App (){
        listaEnteros = new ArrayList<>();
    }

    // metodo para incorporar un elemento a la lista
    public void agregarEntero(Integer numero){
        listaEnteros.add(numero);if(listaEnteros.size()>5){
            //loguear que tiene mas de 5 elementos
            logger.info("La lista tiene mas de 5 elementos, en total tiene: "+listaEnteros.size());
        }
    }
}
