import org.junit.Test;
import org.junit.jupiter.api.Assertions;


public class TrianguloTest {

    @Test
    public void trianguloRojo2(){
        Triangulo triangulo1=TrianguloFactory.getTriangulo("rojo",1);
        Triangulo triangulo2=TrianguloFactory.getTriangulo("rojo",2);
        Integer respEsperada = 1;

        Assertions.assertEquals(respEsperada,TrianguloFactory.getTrianguloMap().size() );
        ;
    }
}
