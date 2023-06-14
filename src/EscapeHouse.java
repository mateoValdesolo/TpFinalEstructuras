import estructuras.ArbolAVL;
import estructuras.GrafoEtiquetado;
import estructuras.Lista;
import model.Equipo;
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

        while(opcion != '6'){
            Texto.menuPrincipal();
            opcion = sc.nextLine().charAt(0);
            switch (opcion){
                case '0':
                    // Carga inicial del sistema
                    break;
                case '1':
                    // ABM de Habitaciones, Desafios y Equipos
                    break;
                case '2':
                    // Consulta sobre Habitaciones
                    break;
                case '3':
                    // Consulta sobre Desafios
                    break;
                case '4':
                    // Consulta sobre Equipos
                    break;
                case '5':
                    // Consultas Generales
                    break;
                case '6':
                    // Salir
                    break;
                default:
                    Texto.opcionInvalida();
            }
        }

        sc.close();
    }
}