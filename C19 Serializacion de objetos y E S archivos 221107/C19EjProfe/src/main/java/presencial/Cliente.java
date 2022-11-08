package presencial;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Cliente {

    public static void main(String[] args) {

        List<Perro> canes = new ArrayList<>();

        canes.add(new Perro("Tevez",1));
        canes.add(new Perro("Riquelme",2));
        canes.add(new Perro("Lollo",3));
        canes.add(new Perro("Rojo",4));
        canes.add(new Perro("Benedetto",5));
        canes.add(new Perro("Rossi",6));
        canes.add(new Perro("Varela",7));

        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream("perros.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(canes);
            oos.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<Perro> canes2 = null;
        FileInputStream fis = null;

        try {
            fis = new FileInputStream("perros.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            //casteamos el objeto a una array list, la cual es su origen
            canes2 = (ArrayList)ois.readObject();
            ois.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        for (Perro perro: canes2) {
            System.out.println(perro);
        }

    }

}
