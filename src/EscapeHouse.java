import estructuras.ArbolAVL;
import estructuras.GrafoEtiquetado;
import estructuras.Lista;
import model.Desafio;
import model.Equipo;
import model.Habitacion;
import model.TipoDesafio;
import utiles.FileManager;
import utiles.Texto;

import java.io.File;
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
        HashMap<String, Lista> desafiosPorEquipo = new HashMap<>(); //TODO: Ver si esta bien el 1 a muchos.

        FileManager.inicioEjecucion();

        while (opcion != '6') {
            Texto.menuPrincipal();
            opcion = sc.next().charAt(0);
            switch (opcion) {
                case '0':
                    // Carga inicial del sistema
                    FileManager.estadoInicial();
                    FileManager.cargarDatos(desafios, equipos, grafoCasa, habitaciones); // TODO: que pasa con los desafios hechos hasta el momento? se pierden?
                    break;
                case '1':
                    // ABM de Habitaciones, Desafios y Equipos
                    Texto.abm();
                    abm(sc, grafoCasa, habitaciones, desafios, equipos, desafiosPorEquipo);
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
                    consultaEquipos(sc, equipos, desafiosPorEquipo, desafios,habitaciones, grafoCasa);
                    break;
                case '5':
                    // Consultas Generales
                    Texto.consultasGenerales(grafoCasa, desafios, equipos);
                    break;
                case '6':
                    // Salir
                    FileManager.finEjecucion();
                    FileManager.estadoFinal();
                    break;
                default:
                    Texto.opcionInvalida();
                    break;
            }
        }
        sc.close();
    }

    // ****************************** ABM ******************************

    /**
     * Menu de ABM
     *
     * @param sc                Scanner
     * @param grafoCasa         Grafo de Habitaciones
     * @param habitaciones      Arbol de Habitaciones
     * @param desafios          Arbol de Desafios
     * @param equipos           HashMap de Equipos
     * @param desafiosPorEquipo HashMap de Desafios por Equipo
     */
    public static void abm(Scanner sc, GrafoEtiquetado grafoCasa, ArbolAVL habitaciones, ArbolAVL desafios, HashMap<String, Equipo> equipos, HashMap<String, Lista> desafiosPorEquipo) {
        System.out.println("Ingrese una opcion: "); // TODO: Ver si la modificacion funciona, agregar Texto en las altas, bajas y modificaciones. Si ya existe, pedir hasta que no exista en la modificacion y alta.
        char opcion = sc.next().charAt(0);
        while(opcion != '9') {
            switch (opcion) {
                case '0':
                    // Alta de Habitacion
                    altaHabitacion(sc, habitaciones, grafoCasa);
                    break;
                case 'a':
                    // Alta de Puerta
                    altaPuerta(sc, grafoCasa);
                    break;
                case '1':
                    // Baja de Habitacion
                    bajaHabitacion(sc, habitaciones, grafoCasa);
                    break;
                case 'b':
                    // Baja de Puerta
                    bajaPuerta(sc, grafoCasa);
                    break;
                case '2':
                    // Modificacion de Habitacion
                    modificacionHabitacion(sc, habitaciones, grafoCasa);
                    break;
                case 'c':
                    // Modificacion de Puerta
                    modificacionPuerta(sc, grafoCasa);
                    break;
                case '3':
                    // Alta de Desafio
                    altaDesafio(sc, desafios);
                    break;
                case '4':
                    // Baja de Desafio
                    bajaDesafio(sc, desafios);
                    break;
                case '5':
                    // Modificacion de Desafio
                    modificacionDesafio(sc, desafios);
                    break;
                case '6':
                    // Alta de Equipo
                    altaEquipo(sc, equipos);
                    break;
                case '7':
                    // Baja de Equipo
                    bajaEquipo(sc, equipos);
                    break;
                case '8':
                    // Modificacion de Equipo
                    modificacionEquipo(sc, equipos);
                    break;
                case '9':
                    // Volver al menu principal
                    break;
            }
        }
    }

    // ****************************** ABM HABITACIONES ******************************

    /**
     * Alta de Habitacion
     *
     * @param sc           Scanner
     * @param habitaciones Arbol de Habitaciones
     * @param grafoCasa    Grafo de Habitaciones
     */
    public static void altaHabitacion(Scanner sc, ArbolAVL habitaciones, GrafoEtiquetado grafoCasa) {
        int nroHabitacion;
        boolean pertenece = false;
        do {
            System.out.println("Ingrese el numero de habitacion: ");
            nroHabitacion = sc.nextInt();
            pertenece = habitaciones.pertenece(nroHabitacion);
            if (pertenece) {
                System.out.println("El numero de habitacion ya existe, ingrese otro numero");
            }
        } while (pertenece);

        System.out.println("Ingrese el nombre de la habitacion: ");
        String nombreHabitacion = sc.nextLine();

        System.out.println("Ingrese la planta de la habitacion: ");
        int planta = sc.nextInt();

        System.out.println("Ingrese los metros cuadrados de la habitacion: ");
        double metrosCuadrados = sc.nextDouble();

        System.out.println("Ingrese si la habitacion tiene salida al exterior ('S' para SI, 'N' Para NO): ");
        boolean salidaAlExterior = sc.nextLine().charAt(0) == 'S';

        Habitacion habitacion = new Habitacion(nroHabitacion, nombreHabitacion, planta, metrosCuadrados, salidaAlExterior);

        habitaciones.insertar(nroHabitacion, habitacion);
        grafoCasa.insertarVertice(nroHabitacion);
        FileManager.altaHabitacion(habitacion);
        FileManager.logAltaHabitacion(nroHabitacion);
    }

    /**
     * Baja de Habitacion
     *
     * @param sc           Scanner
     * @param habitaciones Arbol de Habitaciones
     * @param grafoCasa    Grafo de Habitaciones
     */
    public static void bajaHabitacion(Scanner sc, ArbolAVL habitaciones, GrafoEtiquetado grafoCasa) {
        System.out.println("Ingrese el numero de habitacion: ");
        int nroHabitacion = sc.nextInt();

        Habitacion habitacion = (Habitacion) habitaciones.encontrarElemento(nroHabitacion);

        if (habitacion != null) {
            FileManager.bajaHabitacion(habitacion);
            habitaciones.eliminar(nroHabitacion);
            grafoCasa.eliminarVertice(nroHabitacion);
            System.out.println("La habitacion " + nroHabitacion + " se elimino correctamente");
            FileManager.logBajaHabitacion(nroHabitacion);
        } else {
            System.out.println("El numero de habitacion no existe");
        }
    }

    /**
     * Modificacion de Habitacion
     *
     * @param sc           Scanner
     * @param habitaciones Arbol de Habitaciones
     * @param grafoCasa    Grafo de Habitaciones
     */
    public static void modificacionHabitacion(Scanner sc, ArbolAVL habitaciones, GrafoEtiquetado grafoCasa) {
        System.out.println("Ingrese el numero de habitacion: ");
        int nroHabitacion = sc.nextInt();

        Habitacion habitacion = (Habitacion) habitaciones.encontrarElemento(nroHabitacion);

        if (habitacion != null) {
            System.out.println("Ingrese el nuevo nombre de la habitacion: ");
            String nombreHabitacion = sc.nextLine();

            System.out.println("Ingrese la nueva planta de la habitacion: ");
            int planta = sc.nextInt();

            System.out.println("Ingrese los nuevos metros cuadrados de la habitacion: ");
            double metrosCuadrados = sc.nextDouble();

            System.out.println("Ingrese si la habitacion tiene salida al exterior ('S' para SI, 'N' Para NO): ");
            boolean salidaAlExterior = sc.nextLine().charAt(0) == 'S';

            Habitacion habitacionMod = (Habitacion) habitaciones.encontrarElemento(nroHabitacion);
            habitacionMod.setNombre(nombreHabitacion);
            habitacionMod.setPlanta(planta);
            habitacionMod.setMetrosCuadrados(metrosCuadrados);
            habitacionMod.setSalidaExterior(salidaAlExterior);

            FileManager.modificacionHabitacion(habitacion,habitacionMod);

            System.out.println("La habitacion " + nroHabitacion + " se modifico correctamente");
            FileManager.logModificacionHabitacion(nroHabitacion);
        } else {
            System.out.println("La habitacion " + nroHabitacion + " no existe");
        }
    }

    // ****************************** ABM PUERTAS ******************************

    /**
     * Alta de Puerta
     *
     * @param sc        Scanner
     * @param grafoCasa Grafo de Habitaciones
     */
    public static void altaPuerta(Scanner sc, GrafoEtiquetado grafoCasa) { //TODO: No puede haber una puerta con el mismo puntaje que otra
        System.out.println("Ingrese el numero de habitacion 1: ");
        int nroHabitacion1 = sc.nextInt();

        System.out.println("Ingrese el numero de habitacion 2: ");
        int nroHabitacion2 = sc.nextInt();

        System.out.println("Ingrese los puntos necesarios para abrir la puerta: ");
        int puntaje = sc.nextInt();

        grafoCasa.insertarArco(nroHabitacion1, nroHabitacion2, puntaje);
        FileManager.altaPuerta(nroHabitacion1, nroHabitacion2, puntaje);
        FileManager.logAltaPuerta(puntaje);
    }

    /**
     * Baja de Puerta
     *
     * @param sc        Scanner
     * @param grafoCasa Grafo de Habitaciones
     */
    public static void bajaPuerta(Scanner sc, GrafoEtiquetado grafoCasa) {
        System.out.println("Ingrese el numero de habitacion 1: ");
        int nroHabitacion1 = sc.nextInt();

        System.out.println("Ingrese el numero de habitacion 2: ");
        int nroHabitacion2 = sc.nextInt();

        int puntaje = (int) grafoCasa.obtenerEtiqueta(nroHabitacion1, nroHabitacion2);

        grafoCasa.eliminarArco(nroHabitacion1, nroHabitacion2);
        FileManager.bajaPuerta(nroHabitacion1, nroHabitacion2,puntaje);
        FileManager.logBajaPuerta(puntaje);
    }

    /**
     * Modificacion de Puerta
     *
     * @param sc        Scanner
     * @param grafoCasa Grafo de Habitaciones
     */
    public static void modificacionPuerta(Scanner sc, GrafoEtiquetado grafoCasa) {
        // TODO: Ver si es necesario este metodo
        System.out.println("Ingrese el numero de habitacion 1: ");
        int nroHabitacion1 = sc.nextInt();

        System.out.println("Ingrese el numero de habitacion 2: ");
        int nroHabitacion2 = sc.nextInt();

        System.out.println("Ingrese los nuevos puntos necesarios para abrir la puerta: ");
        int puntajeMod = sc.nextInt();

        int puntaje = (int) grafoCasa.obtenerEtiqueta(nroHabitacion1, nroHabitacion2);

        grafoCasa.eliminarArco(nroHabitacion1, nroHabitacion2);
        grafoCasa.insertarArco(nroHabitacion1, nroHabitacion2, puntajeMod);
        FileManager.modificacionPuerta(nroHabitacion1, nroHabitacion2, puntaje, puntajeMod);
        FileManager.logModificacionPuerta(puntaje);
    }

    // ****************************** ABM DESAFIOS ******************************

    /**
     * Alta de Desafio
     *
     * @param sc       Scanner
     * @param desafios Arbol de Desafios
     */
    public static void altaDesafio(Scanner sc, ArbolAVL desafios) {
        boolean pertenece = false;
        int puntaje;
        do {
            System.out.println("Ingrese el puntaje del desafio: ");
            puntaje = sc.nextInt();
            pertenece = desafios.pertenece(puntaje);
            if (pertenece) {
                System.out.println("El desafio ya existe");
            }
        } while (pertenece);

        System.out.println("Ingrese el nombre del desafio: ");
        String nombreDesafio = sc.nextLine();

        System.out.println("Ingrese el tipo del desafio: ");
        Texto.tipoDesafios();
        char tipo = sc.nextLine().charAt(0);
        TipoDesafio tipoDesafio = tipoDesafio(tipo);

        Desafio desafio = new Desafio(puntaje, nombreDesafio, tipoDesafio);
        desafios.insertar(puntaje, desafio);
        FileManager.altaDesafio(desafio);
        FileManager.logAltaDesafio(puntaje);
    }

    /**
     * Baja de Desafio
     *
     * @param sc       Scanner
     * @param desafios Arbol de Desafios
     */
    public static void bajaDesafio(Scanner sc, ArbolAVL desafios) {
        System.out.println("Ingrese el puntaje del desafio: ");
        int puntaje = sc.nextInt();

        Desafio desafio = (Desafio) desafios.encontrarElemento(puntaje);

        if (desafio != null) {
            FileManager.bajaDesafio(desafio);
            desafios.eliminar(puntaje);
            FileManager.logBajaDesafio(puntaje);
        } else {
            Texto.desafioInexistente();
        }
    }

    /**
     * Modificacion de Desafio
     *
     * @param sc       Scanner
     * @param desafios Arbol de Desafios
     */
    public static void modificacionDesafio(Scanner sc, ArbolAVL desafios) {
        System.out.println("Ingrese el puntaje del desafio: ");
        int puntaje = sc.nextInt();

        Desafio desafio = (Desafio) desafios.encontrarElemento(puntaje);

        if (desafio != null) {
            System.out.println("Ingrese el nuevo nombre del desafio: ");
            String nombreDesafio = sc.nextLine();

            System.out.println("Ingrese el nuevo tipo del desafio: ");
            Texto.tipoDesafios();
            char tipo = sc.nextLine().charAt(0);
            TipoDesafio tipoDesafio = tipoDesafio(tipo);

            Desafio desafioMod = (Desafio) desafios.encontrarElemento(puntaje);
            desafioMod.setNombre(nombreDesafio);
            desafioMod.setTipo(tipoDesafio);
            FileManager.modificacionDesafio(desafio, desafioMod);
            FileManager.logModificacionDesafio(puntaje);
        } else {
            Texto.desafioInexistente();
        }
    }

    // ****************************** ABM EQUIPOS ******************************

    /**
     * Alta de Equipo
     *
     * @param sc      Scanner
     * @param equipos Arbol de Equipos
     */
    public static void altaEquipo(Scanner sc, HashMap<String, Equipo> equipos) {
        boolean pertenece = false;
        String nombreEquipo;
        do {
            System.out.println("Ingrese el nombre del equipo: ");
            nombreEquipo = sc.next();
            pertenece = equipos.containsKey(nombreEquipo);
            if (pertenece) {
                System.out.println("El equipo ya existe");
            }
        } while (pertenece);

        System.out.println("Ingrese el puntaje exigido del equipo para salir: ");
        int puntajeExigido = sc.nextInt();

        System.out.println("Ingrese el puntaje total del equipo: ");
        int puntajeTotal = sc.nextInt();

        System.out.println("Ingrese el puntaje actual del equipo: ");
        int puntajeActual = sc.nextInt();

        System.out.println("Ingrese el numero de habitacion actual del equipo: ");
        int habitacionActual = sc.nextInt();

        Equipo equipo = new Equipo(nombreEquipo, puntajeExigido, puntajeTotal, puntajeActual, habitacionActual);
        equipos.put(nombreEquipo, equipo);
        FileManager.altaEquipo(equipo);
        FileManager.logAltaEquipo(nombreEquipo);
    }

    /**
     * Baja de Equipo
     *
     * @param sc      Scanner
     * @param equipos Arbol de Equipos
     */
    public static void bajaEquipo(Scanner sc, HashMap<String, Equipo> equipos) {
        System.out.println("Ingrese el nombre del equipo: ");
        String nombreEquipo = sc.nextLine();

        Equipo equipo = equipos.get(nombreEquipo);

        if (equipo != null) {
            FileManager.bajaEquipo(equipo);
            equipos.remove(nombreEquipo);
            FileManager.logBajaEquipo(nombreEquipo);
        } else {
            Texto.equipoInexistente();
        }
    }

    /**
     * Modificacion de Equipo
     *
     * @param sc      Scanner
     * @param equipos Arbol de Equipos
     */
    public static void modificacionEquipo(Scanner sc, HashMap<String, Equipo> equipos) {
        System.out.println("Ingrese el nombre del equipo: ");
        String nombreEquipo = sc.nextLine();

        Equipo equipo = equipos.get(nombreEquipo); // TODO: Ver problema con referencia al objeto.

        if (equipo != null) {
            System.out.println("Ingrese el nuevo puntaje exigido del equipo para salir: ");
            int puntajeExigido = sc.nextInt();

            System.out.println("Ingrese el nuevo puntaje total del equipo: ");
            int puntajeTotal = sc.nextInt();

            System.out.println("Ingrese el nuevo puntaje actual del equipo: ");
            int puntajeActual = sc.nextInt();

            System.out.println("Ingrese el nuevo numero de habitacion actual del equipo: ");
            int habitacionActual = sc.nextInt();

            Equipo equipoMod = equipos.get(nombreEquipo);
            equipoMod.setPuntajeExigido(puntajeExigido);
            equipoMod.setPuntajeTotal(puntajeTotal);
            equipoMod.setPuntajeActual(puntajeActual);
            equipoMod.setHabitacionActual(habitacionActual);
            FileManager.modificacionEquipo(equipo, equipoMod);
            FileManager.logModificacionEquipo(nombreEquipo);
        } else {
            Texto.equipoInexistente();
        }
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
            opcion = sc.next().charAt(0);
            switch (opcion) {
                case '0':
                    // Mostrar Habitacion
                    mostrarHabitacion(sc, habitaciones);
                    break;
                case '1':
                    // Habitaciones Contiguas
                    habitacionesContiguas(sc, habitaciones, grafoCasa);
                    break;
                case '2':
                    // Es Posible Llegar
                    esPosibleLlegar(sc, habitaciones, grafoCasa);
                    break;
                case '3':
                    // Maximo Puntaje
                    maximoPuntaje(); // TODO: Implementar maximoPuntaje
                    break;
                case '4':
                    // Sin Pasar Por
                    sinPasarPor(sc,habitaciones,grafoCasa);
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
        System.out.println("Ingrese el codigo de la habitacion:");
        int nroHabitacion = sc.nextInt();
        Habitacion habitacion = (Habitacion) habitaciones.encontrarElemento(nroHabitacion);
        if (habitacion != null) {
            System.out.println(habitacion.toString());
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
        System.out.println("Ingrese el codigo de la habitacion:");
        int nroHabitacion = sc.nextInt();

        Habitacion habitacion = (Habitacion) habitaciones.encontrarElemento(nroHabitacion);
        if (habitacion != null) {
            System.out.println(grafoCasa.nodosContiguos(nroHabitacion));
        } else {
            Texto.habitacionInexistente();
        }
    }

    /**
     * Dados los codigos de hab1 y hab2, y un valor k, mostrar si es o no posible llegar de hab1 a hab2, acumulando k puntos.
     */
    public static void esPosibleLlegar(Scanner sc, ArbolAVL habitaciones, GrafoEtiquetado grafoCasa) {
        System.out.println("Ingrese el codigo de la primera habitacion:");
        int nroHabitacion1 = sc.nextInt();
        System.out.println("Ingrese el codigo de la segunda habitacion:");
        int nroHabitacion2 = sc.nextInt();
        System.out.println("Ingrese el puntaje maximo a obtener entre ambas habitaciones:");
        int puntajeMaximo = sc.nextInt();

        Habitacion habitacion1 = (Habitacion) habitaciones.encontrarElemento(nroHabitacion1);
        Habitacion habitacion2 = (Habitacion) habitaciones.encontrarElemento(nroHabitacion2);

        if(habitacion1 != null && habitacion2 != null) {
            if (grafoCasa.esPosibleLlegar(nroHabitacion1, nroHabitacion2, puntajeMaximo)) {
                System.out.println("Es posible llegar de la habitacion " + nroHabitacion1 + " a la habitacion " + nroHabitacion2 + " acumulando " + puntajeMaximo + " puntos.");
            } else {
                System.out.println("No es posible llegar de la habitacion " + nroHabitacion1 + " a la habitacion " + nroHabitacion2 + " acumulando " + puntajeMaximo + " puntos.");
            }
        } else {
            Texto.habitacionInexistente();
        }
    }

    /**
     * Dados dos codigos de habitacion, mostrar el maximo puntaje que deberian acumular para ir de hab1 a hab2.
     */
    public static void maximoPuntaje() {

    }

    /**
     * Dados tres codigos de habitacion y un valor numerico P, mostrar todas las formas de llegar desde hab1 a hab2 sin pasar por
     * la tercera habitacion (hab3) que no requieran ganar mas de P puntos.
     */
    public static void sinPasarPor(Scanner sc, ArbolAVL habitaciones, GrafoEtiquetado grafoCasa) {
        System.out.println("Ingrese el codigo de la habitacion origen:");
        int nroHabitacion1 = sc.nextInt();
        System.out.println("Ingrese el codigo de la habitacion destino:");
        int nroHabitacion2 = sc.nextInt();
        System.out.println("Ingrese el codigo de la habitacion a evitar:");
        int nroHabitacion3 = sc.nextInt();
        System.out.println("Ingrese el puntaje maximo:");
        int puntajeMaximo = sc.nextInt();

        Habitacion habitacion1 = (Habitacion) habitaciones.encontrarElemento(nroHabitacion1);
        Habitacion habitacion2 = (Habitacion) habitaciones.encontrarElemento(nroHabitacion2);
        Habitacion habitacion3 = (Habitacion) habitaciones.encontrarElemento(nroHabitacion3);

        if(habitacion1 != null && habitacion2 != null && habitacion3 != null) {
            System.out.println("Las formas de llegar desde la habitacion " + nroHabitacion1 + " a la habitacion " + nroHabitacion2 + " sin pasar por la habitacion " + nroHabitacion3 + " sin requerir "+puntajeMaximo+" puntos, son:");
            System.out.println(grafoCasa.sinPasarPor(nroHabitacion1,nroHabitacion2, nroHabitacion3, puntajeMaximo).toString());
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
            opcion = sc.next().charAt(0);
            switch (opcion) {
                case '0':
                    // Mostrar Desafio
                    mostrarDesafio(sc, desafios);
                    break;
                case '1':
                    // Mostrar Desafios Resueltos
                    mostrarDesafiosResueltos(sc, equipos,desafiosPorEquipo);
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
        System.out.println("Ingrese el codigo del desafio:");
        int nroDesafio = sc.nextInt();
        Desafio desafio = (Desafio) desafios.encontrarElemento(nroDesafio);
        if (desafio != null) {
            System.out.println(desafio.toString());
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
    public static void mostrarDesafiosResueltos(Scanner sc,HashMap<String,Equipo> equipos, HashMap<String, Lista> desafiosPorEquipo) {
        System.out.println("Ingrese el nombre del equipo: ");
        String nombreEquipo = sc.next();
        Equipo equipo = equipos.get(nombreEquipo);

        if (equipo != null) {
            Lista desafios = desafiosPorEquipo.get(nombreEquipo);
            if (desafios != null){
                System.out.println(desafios.toString());
            } else {
                System.out.println("El equipo "+nombreEquipo+" no ha resuelto ningun desafio.");
            }
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
        boolean resuelto = false; // TODO: Tener en cuenta que el desafio puede no existir
        int i = 1;
        System.out.println("Ingrese el nombre del equipo: ");
        String nombreEquipo = sc.next();
        System.out.println("Ingrese el nombre del desafio: ");
        String nombreDesafio = sc.next();

        if (equipos.containsKey(nombreEquipo)) {
            Lista desafiosHechos = desafiosPorEquipo.get(nombreEquipo);
            if (desafiosHechos != null) {
                int cantDesafios = desafiosHechos.longitud();
                while(i <= cantDesafios && !resuelto){
                    Desafio desafio = (Desafio) desafiosHechos.recuperar(i);
                    if(desafio.getNombre().equals(nombreDesafio)){
                        resuelto = true;
                    }
                }

                if (resuelto) {
                    Texto.desafioResuelto();
                } else {
                    Texto.desafioNoResuelto();
                }
            } else {
                Texto.desafioNoResuelto();
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
        int puntajeMin = sc.nextInt();
        System.out.println("Ingrese puntaje maximo: ");
        int puntajeMax = sc.nextInt();
        System.out.println("Ingrese tipo de desafio: ");
        Texto.tipoDesafios();
        char tipo = sc.next().charAt(0);

        tipoDesafio = tipoDesafio(tipo);

        Lista lista = desafios.listarRango(puntajeMin, puntajeMax);
        
        int cant = lista.longitud();

        for (int i = 1; i <= cant ; i++) {
            Desafio desafio = (Desafio) lista.recuperar(i);
            if (desafio.getTipo() == tipoDesafio) {
                System.out.println(desafio);
            }
        }
    }


    // ****************************** CONSULTAS EQUIPOS ******************************

    /**
     * Menu de consulta de equipos
     *
     * @param sc Scanner
     */
    public static void consultaEquipos(Scanner sc, HashMap<String, Equipo> equipos, HashMap<String, Lista> desafiosPorEquipo, ArbolAVL desafios,ArbolAVL habitaciones, GrafoEtiquetado grafoCasa) {
        char opcion = '0';
        while (opcion != '5') {
            Texto.consultaEquipos();
            opcion = sc.next().charAt(0);
            switch (opcion) {
                case '0':
                    // Mostrar Equipo
                    mostrarInfoEquipo(sc, equipos);
                    break;
                case '1':
                    // Posibles Desafios
                    posiblesDesafios();
                    break;
                case '2':
                    // Jugar Desafio
                    jugarDesafio(sc, equipos, desafiosPorEquipo, desafios);
                    break;
                case '3':
                    // Pasar a Habitacion
                    pasarAHabitacion( sc,  equipos,  habitaciones, grafoCasa);
                    break;
                case '4':
                    // Puede Salir
                    puedeSalir(sc,equipos,habitaciones);
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
     * Dado el nombre del equipo, mostrar todos sus datos.
     *
     * @param sc      Scanner
     * @param equipos HashMap de equipos
     */
    public static void mostrarInfoEquipo(Scanner sc, HashMap<String, Equipo> equipos) {
        System.out.println("Ingrese el nombre del equipo: ");
        String nombreEquipo = sc.next();
        Equipo equipo = equipos.get(nombreEquipo);
        if (equipo != null) {
            System.out.println(equipo.toString());
        } else {
            Texto.equipoInexistente();
        }
    }

    /**
     * Dado un equipo y una habitacion hab, en caso en que hab sea adyacente al lugar donde este ubicado
     * el equipo, mostrar todos los desafios que podría resolver el equipo para pasar a hab resolviendo un solo desafio.
     * En caso en que hab no sea adyacente, mostrar un mensaje aclaratorio.
     */
    public static void posiblesDesafios() {

    }


    /**
     * Dado el nombre del equipo, mostrar todos los desafios que puede resolver.
     *
     * @param sc                Scanner
     * @param equipos           HashMap de equipos
     * @param desafiosPorEquipo HashMap de desafios por equipo
     * @param desafios          Arbol AVL de desafios
     */
    public static void jugarDesafio(Scanner sc, HashMap<String, Equipo> equipos, HashMap<String, Lista> desafiosPorEquipo, ArbolAVL desafios) {
        boolean encontrado = false;
        boolean resuelto = false;
        int i = 1;
        Desafio desafio = null;
        System.out.println("Ingrese el nombre del equipo: ");
        String nombreEquipo = sc.next();
        System.out.println("Ingrese el nombre del desafio: ");
        String nombreDesafio = sc.next();

        Equipo equipo = equipos.get(nombreEquipo);

        if (equipo != null) {
            Lista todosDesafios = desafios.listar();

            while(i <= todosDesafios.longitud() && !encontrado){
                int nroDesafioActual = (int) todosDesafios.recuperar(i);
                Desafio desafioActual = (Desafio) desafios.encontrarElemento(nroDesafioActual);
                if(desafioActual.getNombre().equals(nombreDesafio)){
                    desafio = desafioActual;
                    encontrado = true;
                }
                i+=1;
            }


            if (encontrado){
                Lista desafiosHechos = desafiosPorEquipo.get(nombreEquipo);

                if(desafiosHechos == null){
                    desafiosPorEquipo.put(nombreEquipo, new Lista());
                    desafiosPorEquipo.get(nombreEquipo).insertar(desafio, desafiosPorEquipo.get(nombreEquipo).longitud() + 1);
                    equipo.setPuntajeActual(equipo.getPuntajeActual() + (int) desafio.getPuntaje());
                    equipo.setPuntajeTotal(equipo.getPuntajeTotal() + (int) desafio.getPuntaje());
                } else {
                    if (!resuelto) {
                        desafiosPorEquipo.get(nombreEquipo).insertar(desafio, desafiosPorEquipo.get(nombreEquipo).longitud() + 1);
                        equipo.setPuntajeActual(equipo.getPuntajeActual() + (int) desafio.getPuntaje());
                        equipo.setPuntajeTotal(equipo.getPuntajeTotal() + (int) desafio.getPuntaje());
                    } else {
                        Texto.desafioResuelto();
                    }
                }
            } else {
                Texto.desafioInexistente();
            }
        } else {
            Texto.equipoInexistente();
        }
    }

    /**
     * Dado un equipo eq y una habitacion hab, verificar si es posible que el equipo eq pase a la habitacion hab
     * (considerando si es contigua a la actual y el puntaje acumulado es suficiente) y en caso afirmativo actualizar
     * los datos del equipo apropiadamente.
     */
    public static void pasarAHabitacion(Scanner sc, HashMap<String,Equipo> equipos, ArbolAVL habitaciones, GrafoEtiquetado grafoCasa) {
        System.out.println("Ingrese el nombre del equipo: ");
        String nombreEquipo = sc.next();

        Equipo equipo = equipos.get(nombreEquipo);
        if (equipo != null) {
            System.out.println("Ingrese el numero de la habitacion: ");
            int codigoHabitacion = sc.nextInt();
            Habitacion habitacion = (Habitacion) habitaciones.encontrarElemento(codigoHabitacion);

            if (habitacion != null) {
                Habitacion habitacionActual = (Habitacion) habitaciones.encontrarElemento(equipo.getHabitacionActual());
                int codigoHabitacionActual = (int) habitacionActual.getCodigo();
                int puntajeNecesario = (int) grafoCasa.obtenerEtiqueta(codigoHabitacion,codigoHabitacionActual); // TODO: Ver esto, tengo que ver si las habitaciones son contiguas
                if (puntajeNecesario > 0) {
                    if(puntajeNecesario<= equipo.getPuntajeActual()){
                        equipo.setHabitacionActual(codigoHabitacion);
                        //equipo.setPuntajeActual(0);
                        Texto.siguienteHabitacion(nombreEquipo,codigoHabitacion);
                    } else {
                        Texto.puntajeInsuficiente(codigoHabitacion);
                    }
                } else {
                    Texto.habitacionNoContigua(codigoHabitacion);
                }
            } else {
                Texto.habitacionInexistente();
            }
        } else {
            Texto.equipoInexistente();
        }
    }

    /**
     * Dado el nombre del equipo participante, decir si puede o no salir del juego en base al puntaje acumulado,
     * al puntaje que debe obtener para ganar el juego y si la habitacion en la que se encuentra tiene o no salida al exterior.
     *
     * @param sc           Scanner
     * @param equipos      HashMap de equipos
     * @param habitaciones Arbol AVL de habitaciones
     */
    public static void puedeSalir(Scanner sc, HashMap<String, Equipo> equipos, ArbolAVL habitaciones) {
        System.out.println("Ingrese el nombre del equipo: ");
        String nombreEquipo = sc.next();
        Equipo equipo = equipos.get(nombreEquipo);
        if (equipo != null) {
            if (equipo.getPuntajeTotal() >= equipo.getPuntajeExigido()) {
                Habitacion habitacion = (Habitacion) habitaciones.encontrarElemento(equipo.getHabitacionActual());
                if (habitacion.isSalidaExterior()) {
                    Texto.puedeSalir((String) equipo.getNombre());
                } else {
                    Texto.noPuedeSalir((String) equipo.getNombre());
                }
            } else {
                Texto.noPuedeSalir((String) equipo.getNombre());
            }
        } else {
            Texto.equipoInexistente();
        }
    }


    // ****************************** OTROS ******************************

    private static TipoDesafio tipoDesafio(char tipo) {
        TipoDesafio tipoDesafio = null;
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
        return tipoDesafio;
    }

}