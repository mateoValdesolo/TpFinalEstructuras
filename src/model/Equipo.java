package model;


/**
 *
 * @author Mateo Valdesolo
 */
public class Equipo {

    private Comparable nombre;
    private int puntajeExigido;
    private int puntajeTotal;
    private int puntajeActual;
    private int habitacionActual;

    public Equipo(String nombre, int puntajeExigido, int puntajeTotal, int puntajeActual, int habitacionActual) {
        this.nombre = nombre;
        this.puntajeExigido = puntajeExigido;
        this.puntajeTotal = puntajeTotal;
        this.puntajeActual = puntajeActual;
        this.habitacionActual = habitacionActual;
    }

    public Comparable getNombre() {
        return nombre;
    }

    public int getPuntajeExigido() {
        return puntajeExigido;
    }

    public void setPuntajeExigido(int puntajeExigido) {
        this.puntajeExigido = puntajeExigido;
    }

    public int getPuntajeTotal() {
        return puntajeTotal;
    }

    public void setPuntajeTotal(int puntajeTotal) {
        this.puntajeTotal = puntajeTotal;
    }

    public int getPuntajeActual() {
        return puntajeActual;
    }

    public void setPuntajeActual(int puntajeActual) {
        this.puntajeActual = puntajeActual;
    }

    public int getHabitacionActual() {
        return habitacionActual;
    }

    public void setHabitacionActual(int habitacionActual) {
        this.habitacionActual = habitacionActual;
    }

    @Override
    public String toString() {
        return "Equipo{" +
                "nombre=" + nombre +
                ", puntajeExigido=" + puntajeExigido +
                ", puntajeTotal=" + puntajeTotal +
                ", puntajeActual=" + puntajeActual +
                ", habitacionActual=" + habitacionActual +
                '}';
    }
}
