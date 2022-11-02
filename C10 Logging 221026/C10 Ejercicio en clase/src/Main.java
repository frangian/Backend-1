import org.apache.log4j.Logger;

public class Main {
    public static void main(String[] args) {

        Tigre tigre = new Tigre("a",10);
        tigre.correr();
        tigre.esMayorA10();
        Tigre tigre2 = new Tigre("b",-5);
        tigre2.correr();
        tigre2.esMayorA10();
        Leon leon = new Leon("aaa",-3,false);
        leon.esMayorA10();
    }
}
