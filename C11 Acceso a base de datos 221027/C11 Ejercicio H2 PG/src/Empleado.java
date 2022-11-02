import java.time.LocalDate;

public class Empleado {

    private int ID, edad;
    private String nombre, empresa, fechaInicio;

    public Empleado(int ID, int edad, String nombre, String empresa, String fechaInicio) {
        this.ID = ID;
        this.edad = edad;
        this.nombre = nombre;
        this.empresa = empresa;
        this.fechaInicio = fechaInicio;
    }
}
