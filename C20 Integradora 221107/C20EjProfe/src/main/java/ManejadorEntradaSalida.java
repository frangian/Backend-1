import java.io.*;
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
}



