package utiles;

/**
 * @author Mateo Valdesolo
 */
public class Texto {

    public static void menuPrincipal() {
        encabezado("Menu Principal");
        System.out.println("0. Carga inicial del sistema\n" +
                "1. ABM de Habitaciones, Desafios y Equipos\n"+
                "2. Consulta sobre Habitaciones\n"+
                "3. Consulta sobre Desafios\n"+
                "4. Consulta sobre Equipos\n"+
                "5. Consultas Generales\n"+
                "6. Salir\n");
    }

    public static void opcionInvalida(){
        System.out.println("Opcion invalida, intente nuevamente");
    }

    private static void encabezado(String titulo) {
        System.out.println("====================================\n" +
                "       || " + titulo.toUpperCase() + " ||\n" +
                "====================================");
    }

}
