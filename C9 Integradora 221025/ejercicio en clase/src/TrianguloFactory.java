import java.util.HashMap;
import java.util.Map;

public class TrianguloFactory {

    private static Map<String,Triangulo> trianguloMap = new HashMap<>();

    public static Triangulo getTriangulo(String color, Integer tam){
        Triangulo triangulo = trianguloMap.get(color);
        if (triangulo == null){
            triangulo=new Triangulo(color, tam);
            trianguloMap.put(color,triangulo);
        }
        return triangulo;
    };

    public static Map<String, Triangulo> getTrianguloMap() {
        return trianguloMap;
    }
}
