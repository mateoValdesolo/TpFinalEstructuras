package utiles;

import model.Desafio;
import model.Equipo;
import model.Habitacion;
import sun.util.logging.PlatformLogger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Mateo Valdesolo
 */
public class FileManager {

    private static Logger logger = Logger.getLogger(FileManager.class.getName());

    // ****************************** ESCRITURA ******************************

    private static File getFile() {
        File file = null;
        try {
            String filePath = "src/resources/log.txt";
            file = new File(filePath);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al obtener el archivo de log", e);
        }
        return file;
    }

    // ****************************** ESTADOS ******************************
    public static void estadoInicial() {

    }

    public static void estadoFinal() {

    }

    // ****************************** ABM ******************************

    public static void logAltaDesafio(int desafio) {
        try {
            FileWriter file = new FileWriter(FileManager.getFile(), true);
            BufferedWriter buff = new BufferedWriter(file);

            String alta = "ALTA de desafio: " + desafio;

            buff.write(espacio(alta) + " - " + getTime());
            buff.newLine();

            buff.flush();
            buff.close();
            file.close();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error escribir el alta de un desafio en el log", e);
        }
    }

    public static void logBajaDesafio(int desafio) {
        try {
            FileWriter file = new FileWriter(FileManager.getFile(), true);
            BufferedWriter buff = new BufferedWriter(file);

            String baja = "BAJA de desafio: " + desafio;

            buff.write(espacio(baja) + " - " + getTime());
            buff.newLine();

            buff.flush();
            buff.close();
            file.close();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error escribir la baja de un desafio en el log", e);
        }
    }

    public static void logModificacionDesafio(int desafio) {
        try {
            FileWriter file = new FileWriter(FileManager.getFile(), true);
            BufferedWriter buff = new BufferedWriter(file);

            String modificacion = "MODIFICACION de desafio: " + desafio;

            buff.write(espacio(modificacion) + " - " + getTime());
            buff.newLine();

            buff.flush();
            buff.close();
            file.close();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error escribir la modificacion de un desafio en el log", e);
        }
    }

    public static void logAltaEquipo(String equipo) {
        try {
            FileWriter file = new FileWriter(FileManager.getFile(), true);
            BufferedWriter buff = new BufferedWriter(file);

            String alta = "ALTA de equipo: " + equipo;

            buff.write(espacio(alta) + " - " + getTime());
            buff.newLine();

            buff.flush();
            buff.close();
            file.close();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error escribir el alta de un equipo en el log", e);
        }
    }

    public static void logBajaEquipo(String equipo) {
        try {
            BufferedWriter buff = new BufferedWriter(new FileWriter(FileManager.getFile(), true));

            String baja = "BAJA de equipo: " + equipo;

            buff.write(espacio(baja) + " - " + getTime());
            buff.newLine();
            buff.close();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error escribir la baja de un equipo en el log", e);
        }
    }

    public static void logModificacionEquipo(String equipo) {
        try {
            FileWriter file = new FileWriter(FileManager.getFile(), true);
            BufferedWriter buff = new BufferedWriter(file);

            String modificacion = "MODIFICACION de equipo: " + equipo;

            buff.write(espacio(modificacion) + " - " + getTime());
            buff.newLine();

            buff.flush();
            buff.close();
            file.close();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error escribir la modificacion de un equipo en el log", e);
        }
    }

    public static void logAltaHabitacion(int habitacion) {
        try {
            FileWriter file = new FileWriter(FileManager.getFile(), true);
            BufferedWriter buff = new BufferedWriter(file);

            String alta = "ALTA de habitacion: " + habitacion;

            buff.write(espacio(alta) + " - " + getTime());
            buff.newLine();

            buff.flush();
            buff.close();
            file.close();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error escribir el alta de un habitacion en el log", e);
        }
    }

    public static void logBajaHabitacion(int habitacion) {
        try {
            FileWriter file = new FileWriter(FileManager.getFile(), true);
            BufferedWriter buff = new BufferedWriter(file);

            String baja = "BAJA de habitacion: " + habitacion;

            buff.write(espacio(baja) + " - " + getTime());
            buff.newLine();

            buff.flush();
            buff.close();
            file.close();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error escribir la baja de una habitacion en el log", e);
        }
    }

    public static void logModificacionHabitacion(int habitacion) {
        try {
            FileWriter file = new FileWriter(FileManager.getFile(), true);
            BufferedWriter buff = new BufferedWriter(file);

            String modificacion = "MODIFICACION de habitacion: " + habitacion;

            buff.write(espacio(modificacion) + " - " + getTime());
            buff.newLine();

            buff.flush();
            buff.close();
            file.close();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error escribir la modificacion de una habitacion en el log", e);
        }
    }


    // ****************************** FORMAT ******************************

    /**
     * Formatea un desafio con el siguiente formato:
     * D;puntaje;nombre;tipo
     *
     * @param desafio
     * @return Desafio formateado
     */
    private static String formatDesafio(Desafio desafio) {
        String desafioFormateado = "D;";
        desafioFormateado += desafio.getPuntaje() + ";";
        desafioFormateado += desafio.getNombre() + ";";
        desafioFormateado += desafio.getTipo().toString();

        return desafioFormateado;
    }

    /**
     * Formatea un equipo con el siguiente formato:
     * E;nombre;puntajeExigido;puntajeTotal;habitacionActual;puntajeActual
     *
     * @param equipo
     * @return Equipo formateado
     */
    private static String formatEquipo(Equipo equipo) {
        String equipoFormateado = "E;";
        equipoFormateado += equipo.getNombre() + ";";
        equipoFormateado += equipo.getPuntajeExigido() + ";";
        equipoFormateado += equipo.getPuntajeTotal() + ";";
        equipoFormateado += equipo.getHabitacionActual() + ";";
        equipoFormateado += equipo.getPuntajeActual();

        return equipoFormateado;
    }

    /**
     * Formatea una habitacion con el siguiente formato:
     * H;codigo;nombre;planta;metrosCuadrados;salidaExterior
     *
     * @param habitacion
     * @return Habitacion formateada
     */
    private static String formatHabitacion(Habitacion habitacion) {
        String habitacionFormateada = "H;";
        habitacionFormateada += habitacion.getCodigo() + ";";
        habitacionFormateada += habitacion.getNombre() + ";";
        habitacionFormateada += habitacion.getPlanta() + ";";
        habitacionFormateada += habitacion.getMetrosCuadrados() + ";";
        habitacionFormateada += habitacion.isSalidaExterior();

        return habitacionFormateada;
    }

    // ****************************** LECTURA ******************************


    // ****************************** OTROS ******************************

    /**
     * Devuelve la fecha y hora actual
     *
     * @return Fecha y hora actual
     */
    private static String getTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
        return formatter.format(System.currentTimeMillis());
    }

    private static String espacio(String cadena){
        int espacios = 60 - cadena.length();
        String espacio = cadena;
        for (int i = 0; i < espacios; i++) {
            espacio += " ";
        }
        return espacio;
    }

}