package utiles;

import estructuras.ArbolAVL;
import estructuras.GrafoEtiquetado;
import model.Equipo;

import java.util.HashMap;

/**
 * @author Mateo Valdesolo
 */
public class Texto {

    public static void menuPrincipal() {
        encabezado("Menu Principal");
        System.out.println("0. Carga inicial del sistema\n" +
                "1. ABM de Habitaciones, Desafios y Equipos\n" +
                "2. Consulta sobre Habitaciones\n" +
                "3. Consulta sobre Desafios\n" +
                "4. Consulta sobre Equipos\n" +
                "5. Consultas Generales\n" +
                "6. Salir");
        separador();
    }

    public static void consultasGenerales(GrafoEtiquetado grafoCasa, ArbolAVL desafios, HashMap<String, Equipo> equipos) {
        encabezado("Consultas Generales");
        mostrarHabitaciones(grafoCasa);
        mostrarDesafios(desafios);
        mostrarEquipos(equipos);
        separador();
    }

    // ********** ABM **********

    public static void abm() {
        encabezado("ABM");
        System.out.println("0. Alta de Habitacion\n" +
                "1. Baja de Habitacion\n" +
                "2. Modificacion de Habitacion\n" +
                "3. Alta de Desafio\n" +
                "4. Baja de Desafio\n" +
                "5. Modificacion de Desafio\n" +
                "6. Alta de Equipo\n" +
                "7. Baja de Equipo\n" +
                "8. Modificacion de Equipo\n" +
                "9. Volver al menu principal");
        separador();
    }


    // ********** HABITACIONES **********

    public static void consultaHabitaciones() {
        encabezado("Consulta de Habitaciones");
        System.out.println("0. Mostrar Habitacion" +
                "1. Habitaciones Contiguas\n" +
                "2. Es Posible Llegar\n" +
                "3. Maximo Puntaje\n" +
                "4. Sin Pasar Por\n" +
                "5. Volver al menu principal");
        separador();
    }

    public static void habitacionInexistente() {
        System.out.println("La habitacion ingresada no existe");
    }

    private static void mostrarHabitaciones(GrafoEtiquetado grafoCasa) {
        System.out.println(grafoCasa.toString());
    }

    // ********** DESAFIOS **********

    public static void consultaDesafios() {
        encabezado("Consulta de Desafios");
        System.out.println("0. Mostrar Desafio\n" +
                "1. Mostrar Desafios Resueltos\n" +
                "2. Verificar Desafio Resuelto\n" +
                "3. Mostrar Desafios Tipo\n" +
                "4. Volver al menu principal");
        separador();
    }

    public static void desafioInexistente() {
        System.out.println("El desafio ingresado no existe");
    }

    private static void mostrarDesafios(ArbolAVL desafios) {
        System.out.println(desafios.toString());
    }

    public static void tipoDesafios() {
        System.out.println("B. BUSQUEDA\n" +
                "D. DESTREZA\n" +
                "L. LOGICO\n" +
                "M. MATEMATICO\n" +
                "I. INGENIO\n" +
                "E. MEMORIA\n");
    }

    public static void desafioResuelto() {
        System.out.println("El desafio ya fue resuelto");
    }

    public static void desafioNoResuelto() {
        System.out.println("El desafio no fue resuelto");
    }

    // ********** EQUIPOS **********

    public static void consultaEquipos() {
        encabezado("Consulta de Equipos");
        System.out.println("0. Mostrar Info Equipo\n" +
                "1. Posibles Desafios\n" +
                "2. Jugar Desafio\n" +
                "3. Pasar a Habitacion\n" +
                "4. Puede Salir\n" +
                "5. Volver al menu principal");
        separador();
    }

    public static void equipoInexistente() {
        System.out.println("El equipo ingresado no existe");
    }

    private static void mostrarEquipos(HashMap<String, Equipo> equipos) {
        // No muestro la clave ya que es el nombre del grupo, y este esta incluido en el objeto.
        System.out.println(equipos.toString());
        equipos.forEach((clave, equipo) ->
                System.out.println(equipo.toString()));
    }

    // ********** VARIOS **********

    private static void encabezado(String titulo) {
        System.out.println("====================================\n" +
                "|| " + titulo.toUpperCase() + " ||\n" +
                "====================================");
    }

    private static void separador() {
        System.out.println("====================================\n");
    }

    public static void opcionInvalida() {
        System.out.println("Opcion invalida, intente nuevamente");
    }

}
