import java.util.ArrayList;
import java.util.List;

public class Cliente {

    public static void main(String[] args) {

        List<Empleado> listadoEmpleados = new ArrayList<>();

        listadoEmpleados.add(new Empleado("Juan","Rodriguez","1154","56000"));
        listadoEmpleados.add(new Empleado("Jorge","Sanchez","2322","74000"));
        listadoEmpleados.add(new Empleado("Mario","Martinez","5420","46000"));
        listadoEmpleados.add(new Empleado("Santiago","Pereyra","7293","75000"));

        ManejadorEntradaSalida.guardarListaEmpleadosFormatoEspecial(listadoEmpleados);

    }
}
