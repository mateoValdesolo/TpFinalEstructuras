import estructuras.ArbolAVL;
import estructuras.GrafoEtiquetado;
import estructuras.Lista;
import model.Desafio;
import model.Equipo;
import model.Habitacion;
import model.TipoDesafio;
import utiles.Texto;

import java.util.HashMap;
import java.util.HashSet;
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
                    verificarDesafioResuelto(sc, desafiosPorEquipo, equipos, desafios);
                    break;
                case '3':
                    // Mostrar Desafios Tipo
                    mostrarDesafiosTipo(sc, desafios);
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
    public static void verificarDesafioResuelto(Scanner sc, HashMap<String, Lista> desafiosPorEquipo, HashMap<String, Equipo> equipos, ArbolAVL desafios) {
        System.out.println("Ingrese el nombre del equipo: ");
        String nombreEquipo = sc.nextLine();
        System.out.println("Ingrese el nombre del desafio: ");
        String nombreDesafio = sc.nextLine();

        if (equipos.containsKey(nombreEquipo)) {
            Lista desafiosHechos = desafiosPorEquipo.get(nombreEquipo);
            if (desafiosHechos != null) {
                if (desafiosHechos.desafioResuelto(nombreDesafio)) {
                    Texto.desafioResuelto();
                } else {
                    Texto.desafioNoResuelto();
                }
            }
        } else {
            Texto.equipoInexistente();
        }
    }


    /**
     * Dados dos puntajes a y b y un tipo X, muestra todos los desafios de tipo X con puntaje en el rango [a, b].
     *
     * @param sc
     * @param desafios
     */
    public static void mostrarDesafiosTipo(Scanner sc, ArbolAVL desafios) {
        TipoDesafio tipoDesafio = null;

        System.out.println("Ingrese puntaje minimo: ");
        double puntajeMin = sc.nextDouble();
        System.out.println("Ingrese puntaje maximo: ");
        double puntajeMax = sc.nextDouble();
        Texto.tipoDesafios();
        char tipo = sc.nextLine().charAt(0);

        switch (tipo) {
            case 'B':
                tipoDesafio = TipoDesafio.BUSQUEDA;
                break;
            case 'D':
                tipoDesafio = TipoDesafio.DESTREZA;
                break;
            case 'L':
                tipoDesafio = TipoDesafio.LOGICO;
                break;
            case 'M':
                tipoDesafio = TipoDesafio.MATEMATICO;
                break;
            case 'I':
                tipoDesafio = TipoDesafio.INGENIO;
                break;
            case 'E':
                tipoDesafio = TipoDesafio.MEMORIA;
                break;
            default:
                Texto.desafioInexistente();
                break;
        }

        Lista lista = desafios.mostrarDesafiosTipo(puntajeMin, puntajeMax, tipoDesafio);

        for (int i = 1; i <= lista.longitud(); i++) {
            System.out.println(lista.recuperar(i));
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
                    mostrarInfoEquipo(sc, equipos);
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
    public static void mostrarInfoEquipo(Scanner sc, HashMap<String, Equipo> equipos) {
        String nombreEquipo = sc.nextLine();
        Equipo equipo = equipos.get(nombreEquipo);
        if (equipo != null) {
            equipo.toString();
        } else {
            Texto.equipoInexistente();
        }
    }


}