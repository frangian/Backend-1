import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RopaFactoryTest {

    @Test
    public void testRopa1(){
        Ropa r1 = RopaFactory.getRopa("S", "Remera", true, true);
        Ropa r2 = RopaFactory.getRopa("S", "Remera", true, true);
        Ropa r3 = RopaFactory.getRopa("S", "Remera", true, true);
        Ropa r4 = RopaFactory.getRopa("S", "Remera", true, true);
        Ropa r5 = RopaFactory.getRopa("S", "Remera", true, true);

        Ropa r6 = RopaFactory.getRopa("M", "Pantalon", true, false);
        Assertions.assertEquals(2,RopaFactory.getRopaMap().size());
    }


    @Test
    public void testRopa2(){

        String expectedRes = "T:STi:RemeraN:trueI:true";

        Ropa r1 = RopaFactory.getRopa("S", "Remera", true, true);


        Assertions.assertEquals(expectedRes,r1.toString());
    }





}