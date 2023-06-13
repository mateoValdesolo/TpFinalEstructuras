package model;

/**
 *
 * @author Mateo Valdesolo
 */
public class Habitacion {

    private Comparable codigo;
    private String nombre;
    private int planta;
    private double metrosCuadrados;
    private boolean salidaExterior;

    public Habitacion(Comparable codigo, String nombre, int planta, double metrosCuadrados, boolean salidaExterior) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.planta = planta;
        this.metrosCuadrados = metrosCuadrados;
        this.salidaExterior = salidaExterior;
    }

    public Comparable getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPlanta() {
        return planta;
    }

    public void setPlanta(int planta) {
        this.planta = planta;
    }

    public double getMetrosCuadrados() {
        return metrosCuadrados;
    }

    public void setMetrosCuadrados(double metrosCuadrados) {
        this.metrosCuadrados = metrosCuadrados;
    }

    public boolean isSalidaExterior() {
        return salidaExterior;
    }

    public void setSalidaExterior(boolean salidaExterior) {
        this.salidaExterior = salidaExterior;
    }

    @Override
    public String toString() {
        return "Habitacion{" +
                "codigo=" + codigo +
                ", nombre='" + nombre + '\'' +
                ", planta=" + planta +
                ", metrosCuadrados=" + metrosCuadrados +
                ", salidaExterior=" + salidaExterior +
                '}';
    }
}
