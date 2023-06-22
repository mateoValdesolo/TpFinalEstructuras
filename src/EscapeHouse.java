import estructuras.ArbolAVL;
import estructuras.GrafoEtiquetado;
import estructuras.Lista;
import model.Desafio;
import model.Equipo;
import model.Habitacion;
import utiles.Texto;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @author Mateo Valdesolo
 */
public class EscapeHouse {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char opcion = '0';

        // Estructuras
        ArbolAVL desafios = new ArbolAVL();
        ArbolAVL habitaciones = new ArbolAVL();
        GrafoEtiquetado grafoCasa = new GrafoEtiquetado();
        HashMap<String, Equipo> equipos = new HashMap<>();
        HashMap<String, Lista> desafiosPorEquipo = new HashMap<>();

        while (opcion != '6') {
            Texto.menuPrincipal();
            opcion = sc.nextLine().charAt(0);
            switch (opcion) {
                case '0':
                    // Carga inicial del sistema
                    break;
                case '1':
                    // ABM de Habitaciones, Desafios y Equipos
                    Texto.abm();
                    break;
                case '2':
                    // Consulta sobre Habitaciones
                    consultaHabitaciones(sc, grafoCasa, habitaciones);
                    break;
                case '3':
                    // Consulta sobre Desafios
                    consultaDesafios(sc, desafios, equipos, desafiosPorEquipo);
                    break;
                case '4':
                    // Consulta sobre Equipos
                    consultaEquipos(sc, equipos);
                    break;
                case '5':
                    // Consultas Generales
                    Texto.consultasGenerales(grafoCasa, desafios, equipos);
                    break;
                case '6':
                    // Salir
                    break;
                default:
                    Texto.opcionInvalida();
                    break;
            }
        }

        sc.close();
    }

    // ****************************** CONSULTAS HABITACIONES ******************************

    /**
     * Menu de consulta de habitaciones
     *
     * @param sc           Scanner
     * @param grafoCasa    Grafo de la casa
     * @param habitaciones Arbol AVL de habitaciones
     */
    public static void consultaHabitaciones(Scanner sc, GrafoEtiquetado grafoCasa, ArbolAVL habitaciones) {
        char opcion = '0';
        while (opcion != '5') {
            Texto.consultaHabitaciones();
            opcion = sc.nextLine().charAt(0);
            switch (opcion) {
                case '0':
                    // Mostrar Habitacion
                    mostrarHabitacion(sc, habitaciones);
                    break;
                case '1':
                    // Habitaciones Contiguas
                    habitacionesContiguas(sc, habitaciones, grafoCasa); // TODO: Terminar habitacionesContiguas
                    break;
                case '2':
                    // Es Posible Llegar
                    break;
                case '3':
                    // Maximo Puntaje
                    break;
                case '4':
                    // Sin Pasar Por
                    break;
                case '5':
                    // Volver al menu principal
                    break;
                default:
                    Texto.opcionInvalida();
                    break;
            }
        }
    }

    /**
     * Dado un codigo de habitacion, mostrar toda la informacion sobre la misma.
     *
     * @param sc           Scanner
     * @param habitaciones Arbol AVL de habitaciones
     */
    public static void mostrarHabitacion(Scanner sc, ArbolAVL habitaciones) {
        int nroHabitacion = sc.nextInt();
        Habitacion habitacion = (Habitacion) habitaciones.encontrarElemento(nroHabitacion);
        if (habitacion != null) {
            habitacion.toString();
        } else {
            Texto.habitacionInexistente();
        }
    }

    /**
     * Dado un codigo de habitacion, mostrar las habitaciones contiguas a las que se puede acceder, y que puntaje se necesitaria para pasar a cada una.
     *
     * @param sc           Scanner
     * @param habitaciones Arbol AVL de habitaciones
     * @param grafoCasa    Grafo de la casa
     */
    public static void habitacionesContiguas(Scanner sc, ArbolAVL habitaciones, GrafoEtiquetado grafoCasa) {
        int nroHabitacion = sc.nextInt();
        Habitacion habitacion = (Habitacion) habitaciones.encontrarElemento(nroHabitacion);
        if (habitacion != null) {

        } else {
            Texto.habitacionInexistente();
        }
    }


    // ****************************** CONSULTAS DESAFIOS ******************************

    /**
     * Menu de consulta de desafios
     *
     * @param sc       Scanner
     * @param desafios Arbol AVL de desafios
     * @param equipos  HashMap de equipos
     */
    public static void consultaDesafios(Scanner sc, ArbolAVL desafios, HashMap<String, Equipo> equipos, HashMap<String, Lista> desafiosPorEquipo) {
        char opcion = '0';
        while (opcion != '4') {
            Texto.consultaDesafios();
            opcion = sc.nextLine().charAt(0);
            switch (opcion) {
                case '0':
                    // Mostrar Desafio
                    mostrarDesafio(sc, desafios);
                    break;
                case '1':
                    // Mostrar Desafios Resueltos
                    mostrarDesafiosResueltos(sc, desafiosPorEquipo);
                    break;
                case '2':
                    // Verificar Desafio Resuelto
                    verificarDesafioResuelto(sc, desafiosPorEquipo); // TODO: Terminar verificarDesafioResuelto, ver como obtengo el desafio solamente con el nombre
                    break;
                case '3':
                    // Mostrar Desafios Tipo
                    break;
                case '4':
                    // Volver al menu principal
                    break;
                default:
                    Texto.opcionInvalida();
                    break;
            }
        }

    }

    /**
     * Dado un codigo de desafio, mostrar toda su informacion.
     *
     * @param sc       Scanner
     * @param desafios Arbol AVL de desafios
     */
    public static void mostrarDesafio(Scanner sc, ArbolAVL desafios) {
        int nroDesafio = sc.nextInt();
        Desafio desafio = (Desafio) desafios.encontrarElemento(nroDesafio);
        if (desafio != null) {
            desafio.toString();
        } else {
            Texto.desafioInexistente();
        }
    }

    /**
     * Dado un equipo eq, mostrar todos los desafíos que ya resolvieron.
     *
     * @param sc                Scanner
     * @param desafiosPorEquipo HashMap de desafios por equipo
     */
    public static void mostrarDesafiosResueltos(Scanner sc, HashMap<String, Lista> desafiosPorEquipo) {
        String nombreEquipo = sc.nextLine();
        Lista desafios = desafiosPorEquipo.get(nombreEquipo);
        if (desafios != null) {
            desafios.toString();
        } else {
            Texto.equipoInexistente();
        }
    }

    /**
     * Dado un equipo y un nombre de desafio, indicar si el equipo ya lo resolvio.
     *
     * @param sc                Scanner
     * @param desafiosPorEquipo HashMap de desafios por equipo
     */
    public static void verificarDesafioResuelto(Scanner sc, HashMap<String, Lista> desafiosPorEquipo) {
        String nombreEquipo = sc.nextLine();
        String nombreDesafio = sc.nextLine();
        Lista desafios = desafiosPorEquipo.get(nombreEquipo);
        if (desafios != null) {
            Desafio desafio;
        } else {
            Texto.equipoInexistente();
        }
    }


    // ****************************** CONSULTAS EQUIPOS ******************************

    /**
     * Menu de consulta de equipos
     *
     * @param sc Scanner
     */
    public static void consultaEquipos(Scanner sc, HashMap<String, Equipo> equipos) {
        char opcion = '0';
        while (opcion != '4') {
            Texto.consultaEquipos();
            opcion = sc.nextLine().charAt(0);
            switch (opcion) {
                case '0':
                    // Mostrar Equipo
                    mostrarEquipo(sc, equipos);
                    break;
                case '1':
                    // Mostrar Equipos Ordenados
                    break;
                case '2':
                    // Mostrar Equipos Resolvieron Desafio
                    break;
                case '3':
                    // Mostrar Equipos Resolvieron Desafio Tipo
                    break;
                case '4':
                    // Volver al menu principal
                    break;
                default:
                    Texto.opcionInvalida();
                    break;
            }
        }
    }

    /**
     * Dado el nombre del equipo, mostrar todos sus datos.
     *
     * @param sc      Scanner
     * @param equipos HashMap de equipos
     */
    public static void mostrarEquipo(Scanner sc, HashMap<String, Equipo> equipos) {
        String nombreEquipo = sc.nextLine();
        Equipo equipo = equipos.get(nombreEquipo);
        if (equipo != null) {
            equipo.toString();
        } else {
            Texto.equipoInexistente();
        }
    }


}