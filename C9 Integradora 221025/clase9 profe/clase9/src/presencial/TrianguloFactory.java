package presencial;

import java.util.HashMap;
import java.util.Map;
/*
    Claves        Valores
            key1    |    valor 1
            key2    |    valor 2
            key3    |    valor 3
            azul    |    valor 4
            key5    |    valor 5
*/

public class TrianguloFactory {
    private static Map<String, Triangulo> trianguloMap=new HashMap<>();
    public static Triangulo getTriangulo(String color, Integer tam){
        /*
        mediante el método get, estamos buscado un valor en el mapa
        utiliznado la clave color.
         */

        Triangulo triangulo= trianguloMap.get(color);
        System.out.println("valor de triangulo"+triangulo);
        //triangulo=null
        if (triangulo==null){
            //creamos un triangulo
            //lo agregamos al mapa
            triangulo=new Triangulo(color,tam);
            System.out.println("color:"+triangulo.getColor());
            trianguloMap.put(color,triangulo);
        }
        //triangulo=valornuevo
        return triangulo;


    }

    public static Map<String, Triangulo> getTrianguloMap() {
        return trianguloMap;
    }
}
