package pruebas;

import estructuras.GrafoEtiquetado;

/**
 * @author Mateo Valdesolo
 */
public class GrafoTest {
    public static void main(String[] args) {
        GrafoEtiquetado grafo = new GrafoEtiquetado();
        grafo.insertarVertice("B");
        grafo.insertarVertice("D");
        grafo.insertarVertice("C");
        grafo.insertarVertice("A");
        grafo.insertarVertice("O");

        grafo.insertarArco("B", "C", 8);
        grafo.insertarArco("B", "A", 12);
        grafo.insertarArco("D", "C", 3);
        grafo.insertarArco("D", "A", 10);
        grafo.insertarArco("C", "A", 5);
        grafo.insertarArco("A", "O", 1);

        System.out.println(grafo.esVacio());
        System.out.println(grafo.existeArco("A","O"));
        System.out.println(grafo.existeArco("B","D"));
        System.out.println(grafo.toString());
        System.out.println(grafo.eliminarArco("A","C"));
        System.out.println(grafo.toString());
        System.out.println(grafo.eliminarArco("B","D"));
        System.out.println(grafo.toString());

        System.out.println(grafo.toString());

        grafo.eliminarVertice("A");

        System.out.println(grafo.toString());
    }
}
