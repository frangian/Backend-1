import org.apache.log4j.Logger;

public class Tigre {

    private static final Logger logger = Logger.getLogger(Tigre.class);
    private String nombre;
    private int edad;

    public Tigre(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public void correr(){
        logger.info("El tigre " + this.nombre + " esta corriendo");
    };

    public void esMayorA10(){
        if(this.edad > 10){
            logger.info("El tigre " + this.nombre + " es mayor a 10 años");
        } else if (this.edad > 0 && this.edad < 10) {
            logger.info("El tigre " + this.nombre + " es menor a 10 años");
        } else {
            logger.error("La edad no puede ser negativa");
            throw new RuntimeException();
        }
    };
}
