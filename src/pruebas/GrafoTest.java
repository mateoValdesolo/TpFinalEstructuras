package pruebas;

import estructuras.ArbolAVL;
import estructuras.GrafoEtiquetado;
import estructuras.Lista;
import model.Desafio;
import model.Equipo;
import model.TipoDesafio;
import utiles.FileManager;
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

//        ArbolAVL desafios = new ArbolAVL();
//        ArbolAVL habitaciones = new ArbolAVL();
//        GrafoEtiquetado grafoCasa = new GrafoEtiquetado();
//        HashMap<String, Equipo> equipos = new HashMap<>();
//        HashMap<String, Lista> desafiosPorEquipo = new HashMap<>();
//        Texto.menuPrincipal();
//        Texto.abm();
//        Texto.consultaHabitaciones();
//        Texto.consultaDesafios();
//        Texto.consultaEquipos();
//        Texto.consultasGenerales(grafoCasa,desafios,equipos);

/*        ArbolAVL desafios = new ArbolAVL();
        Desafio d1 = new Desafio(1, "Desafio 1", TipoDesafio.BUSQUEDA);
        Desafio d2 = new Desafio(3, "Desafio 2", TipoDesafio.DESTREZA);
        Desafio d3 = new Desafio(15, "Desafio 3", TipoDesafio.INGENIO);
        Desafio d4 = new Desafio(2, "Desafio 4", TipoDesafio.MEMORIA);
        Desafio d5 = new Desafio(9, "Desafio 5", TipoDesafio.BUSQUEDA);
        Desafio d6 = new Desafio(30, "Desafio 6", TipoDesafio.BUSQUEDA);

        desafios.insertar(d5.getPuntaje(), d5);
        desafios.insertar(d1.getPuntaje(), d1);
        desafios.insertar(d3.getPuntaje(), d3);
        desafios.insertar(d2.getPuntaje(), d2);
        desafios.insertar(d4.getPuntaje(), d4);
        desafios.insertar(d6.getPuntaje(), d6);

        System.out.println(desafios.toString());

        Lista lista = desafios.mostrarDesafiosTipo(1,10, TipoDesafio.INGENIO);
        System.out.println(lista.longitud());
        for (int i = 1; i <= lista.longitud(); i++) {
            System.out.println(lista.recuperar(i));
        }*/

        FileManager.logAltaDesafio(1);
        FileManager.logModificacionDesafio(1);
        FileManager.logBajaDesafio(1);
        FileManager.logAltaEquipo("Equipo 1");
        FileManager.logModificacionEquipo("Equipo 1");
        FileManager.logBajaEquipo("Equipo 1");
        FileManager.logAltaHabitacion(3);
        FileManager.logModificacionHabitacion(3);
        FileManager.logBajaHabitacion(3);

    }
}
