package java.model;

/**
 * @author Mateo Valdesolo
 */
public class Desafio {

    private Comparable puntaje;
    private String nombre;
    private String tipo;

    public Desafio(Comparable puntaje, String nombre, String tipo) {
        this.puntaje = puntaje;
        this.nombre = nombre;
        this.tipo = tipo;
    }

    public Comparable getPuntaje() {
        return puntaje;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Desafio{" +
                "puntaje=" + puntaje +
                ", nombre='" + nombre + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
