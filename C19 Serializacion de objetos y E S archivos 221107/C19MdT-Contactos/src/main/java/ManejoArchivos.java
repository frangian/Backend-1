import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ManejoArchivos implements Serializable {

    public static void agregarListaContactos (List<Contactos> lista, String archivo){

        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(archivo);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(lista);
            oos.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void recuperarListaContactos ( String archivo){
        FileInputStream fis = null;
        List<Contactos> listaContactos = null;

        try {
            fis = new FileInputStream(archivo);
            ObjectInputStream ois = new ObjectInputStream(fis);

            listaContactos = (ArrayList)ois.readObject();
            ois.close();


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        for (Contactos listaContacto : listaContactos) {
            System.out.println(listaContacto);
        }

    }

}
