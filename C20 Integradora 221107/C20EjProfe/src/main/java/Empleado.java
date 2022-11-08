import java.io.Serializable;

public class Empleado implements Serializable {

    private String nombre;
    private String apellido;
    private String legajo;
    private String razonSocial;

    public Empleado(String nombre, String apellido, String legajo, String razonSocial) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.legajo = legajo;
        this.razonSocial = razonSocial;
    }

    @Override
    public String toString() {
        return nombre+";"+apellido+";"+ legajo +";"+razonSocial+";";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getLegajo() {
        return legajo;
    }

    public void setLegajo(String legajo) {
        this.legajo = legajo;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }
}
