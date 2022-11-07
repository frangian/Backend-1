import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ManejadorEntradaSalida {

    public static void guardarListaEmpleadosFormatoEspecial(List<Empleado> lista){

        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream("empleados.txt");
            BufferedOutputStream bos = new BufferedOutputStream(fos);

            for (Empleado empleado: lista) {
                String linea = empleado.toString()+"\n";
                bos.write(linea.getBytes());
            }

            bos.close();


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void guardarEmpresa(Empresa empresa){
        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream("empresa.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(empresa);
            oos.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void recuperarEmpresa ( String archivo){
        FileInputStream fis = null;
        Empresa empresa = null;
        try {
            fis = new FileInputStream(archivo);
            ObjectInputStream ois = new ObjectInputStream(fis);

            empresa = (Empresa) ois.readObject();
            ois.close();


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println(empresa);
    }

}



