import java.util.ArrayList;
import java.util.List;

public class Cliente {

    public static void main(String[] args) {


        Contactos con1 = new Contactos("Juan","Perez","juan@gmail.com","387546456");
        Contactos con2 = new Contactos("Jose","Garcia","jose@gmail.com","387546456");
        Contactos con3 = new Contactos("Sofia","Perez","sofia@gmail.com","9999999");
        Contactos con4 = new Contactos("Emanuel","Perez","ema@gmail.com","123456789");
        Contactos con5 = new Contactos("Laura","Perez","lau@gmail.com","54123456");

        List<Contactos> listaContactos = new ArrayList<>();

        listaContactos.add(con1);
        listaContactos.add(con2);
        listaContactos.add(con3);
        listaContactos.add(con4);
        listaContactos.add(con5);

        ManejoArchivos.agregarListaContactos(listaContactos, "manejoArchivo.txt");

        ManejoArchivos.recuperarListaContactos("manejoArchivo.txt");

    }
}
