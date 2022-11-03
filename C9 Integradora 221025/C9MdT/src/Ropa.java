public class Ropa {

    private String talle;
    private String tipo;
    private Boolean esNuevo;
    private Boolean importada;

    public Ropa(String talle, String tipo, Boolean esNuevo, Boolean importada) {
        this.talle = talle;
        this.tipo = tipo;
        this.esNuevo = esNuevo;
        this.importada = importada;
    }

    public String getTalle() {
        return talle;
    }

    public void setTalle(String talle) {
        this.talle = talle;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Boolean getEsNuevo() {
        return esNuevo;
    }

    public void setEsNuevo(Boolean esNuevo) {
        this.esNuevo = esNuevo;
    }

    public Boolean getImportada() {
        return importada;
    }

    public void setImportada(Boolean importada) {
        this.importada = importada;
    }

    @Override
    public String toString() {
        return "T:"+talle+"Ti:"+tipo+"N:"+esNuevo+"I:"+importada;
    }
}
