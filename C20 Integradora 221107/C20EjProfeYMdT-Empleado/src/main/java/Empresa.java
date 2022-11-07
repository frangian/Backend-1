import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Empresa implements Serializable {
    private String cuit;
    private String razonSocial;
    private List<Empleado> empleadoList;

    public Empresa(String cuit, String razonSocial) {
        this.cuit = cuit;
        this.razonSocial = razonSocial;
        this.empleadoList = new ArrayList<>();
    }

    public Empresa(String cuit, String razonSocial, List<Empleado> empleadoList) {
        this.cuit = cuit;
        this.razonSocial = razonSocial;
        this.empleadoList = empleadoList;
    }

    @Override
    public String toString() {
        return cuit+" - "+razonSocial+"\n"+ empleadoList;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public List<Empleado> getEmpleadoList() {
        return empleadoList;
    }

    public void setEmpleadoList(List<Empleado> empleadoList) {
        this.empleadoList = empleadoList;
    }

    public void agregarEmpleado(Empleado empleado){
        empleadoList.add(empleado);
    }
}
