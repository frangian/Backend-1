package model;

public class Impresora {

    private int id;
    private String nombre, marca, color;

    public Impresora(String nombre, String marca, String color) {
        this.nombre = nombre;
        this.marca = marca;
        this.color = color;
    }

    public Impresora(int id, String nombre, String marca, String color) {
        this.id = id;
        this.nombre = nombre;
        this.marca = marca;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Impresora{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", marca='" + marca + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
