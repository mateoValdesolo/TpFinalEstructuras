package pruebas;

import estructuras.ArbolAVL;
import estructuras.GrafoEtiquetado;
import estructuras.Lista;
import model.Equipo;
import utiles.Texto;

import java.util.HashMap;

/**
 * @author Mateo Valdesolo
 */
public class GrafoTest {
    public static void main(String[] args) {
/*
        GrafoEtiquetado grafo = new GrafoEtiquetado();
        grafo.insertarVertice("B");
        grafo.insertarVertice("D");
        grafo.insertarVertice("C");
        grafo.insertarVertice("A");
        grafo.insertarVertice("E");
        System.out.println(grafo.toString());

        grafo.insertarArco("A", "B", 100);
        grafo.insertarArco("A", "C", 23);
        grafo.insertarArco("B", "E", 56);
        grafo.insertarArco("C", "E", 99);
        grafo.insertarArco("D", "E", 30);
        System.out.println(grafo.toString());

        System.out.println("Elimino Arco 30");
        grafo.eliminarArco("E","D");
        System.out.println(grafo.toString());*/

        ArbolAVL desafios = new ArbolAVL();
        ArbolAVL habitaciones = new ArbolAVL();
        GrafoEtiquetado grafoCasa = new GrafoEtiquetado();
        HashMap<String, Equipo> equipos = new HashMap<>();
        HashMap<String, Lista> desafiosPorEquipo = new HashMap<>();
        Texto.menuPrincipal();
        Texto.abm();
        Texto.consultaHabitaciones();
        Texto.consultaDesafios();
        Texto.consultaEquipos();
        Texto.consultasGenerales(grafoCasa,desafios,equipos);

    }
}
