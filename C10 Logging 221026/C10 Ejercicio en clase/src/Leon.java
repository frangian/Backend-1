import org.apache.log4j.Logger;

public class Leon {
    private static final Logger logger = Logger.getLogger(Leon.class);

    private String nombre;
    private int edad;
    private boolean esAlfa;

    public Leon(String nombre, int edad, boolean esAlfa) {
        this.nombre = nombre;
        this.edad = edad;
        this.esAlfa = esAlfa;
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
    }
}
