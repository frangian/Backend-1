package presencial;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TrianguloTest {
    @Test
    public void trianguloRojo2(){
        //dado
        Triangulo triangulo1=TrianguloFactory.getTriangulo("rojo",1);
        Triangulo triangulo2=TrianguloFactory.getTriangulo("rojo",2);
        Integer respEsperada=1;
        //cuando
        //entonces
        Assertions.assertEquals(respEsperada,TrianguloFactory.getTrianguloMap().size());
    }
}
