package model;

/**
 * @author Mateo Valdesolo
 */
public class Desafio {

    private Comparable puntaje;
    private String nombre;
    private TipoDesafio tipo;

    public Desafio(Comparable puntaje, String nombre, TipoDesafio tipo) {
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

    public TipoDesafio getTipo() {
        return tipo;
    }

    public void setTipo(TipoDesafio tipo) {
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
