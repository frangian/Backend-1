package presencial;

public class Triangulo {
    private String color;
    private Integer tam;

    public Triangulo(String color, Integer tam) {
        this.color = color;
        this.tam = tam;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getTam() {
        return tam;
    }

    public void setTam(Integer tam) {
        this.tam = tam;
    }
}
