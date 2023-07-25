package utiles;

import estructuras.ArbolAVL;
import estructuras.GrafoEtiquetado;
import model.Desafio;
import model.Equipo;
import model.Habitacion;
import sun.util.logging.PlatformLogger;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
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

    private static void escribirEnLog(String texto) throws IOException {
            FileWriter file = new FileWriter(FileManager.getFile(), true);
            BufferedWriter buff = new BufferedWriter(file);

            buff.write(texto);
            buff.newLine();

            buff.flush();
            buff.close();
            file.close();
    }

    private static void escribirEnDatosIniciales(String texto) throws IOException {
        FileWriter file = new FileWriter(FileManager.getReadFile(), true);
        BufferedWriter buff = new BufferedWriter(file);

        buff.write(texto);
        buff.newLine();

        buff.flush();
        buff.close();
        file.close();
    }

    // ****************************** ESTADOS ******************************

    /**
     * Escribe el estado inicial del sistema en el log.
     */
    public static void estadoInicial() {
        try {
            FileWriter file = new FileWriter(FileManager.getFile(), true);
            BufferedWriter buff = new BufferedWriter(file);

            buff.write(espacio("Estado inicial") + " - " + getTime());
            buff.newLine();

            estado(buff);

            buff.write(espacio("Fin Estado inicial") + " - " + getTime());
            buff.newLine();

            buff.flush();
            buff.close();
            file.close();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error escribir el estado inicial en el log", e);
        }
    }

    /**
     * Escribe el estado final del sistema en el log.
     */
    public static void estadoFinal() {
        try {
            FileWriter file = new FileWriter(FileManager.getFile(), true);
            BufferedWriter buff = new BufferedWriter(file);

            buff.write(espacio("Estado final") + " - " + getTime());
            buff.newLine();

            estado(buff);

            buff.write(espacio("Fin Estado final") + " - " + getTime());
            buff.newLine();

            buff.flush();
            buff.close();
            file.close();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error escribir el estado final en el log", e);
        }
    }

    /**
     * Escribe el estado del sistema en el log.
     */
    public static void estado(BufferedWriter buff) throws IOException {
        try {
            FileReader file = new FileReader(FileManager.getReadFile());
            BufferedReader buffRead = new BufferedReader(file);

            String linea;
            while ((linea = buffRead.readLine()) != null) {
                buff.write(espacio(linea) + " - " + getTime());
                buff.newLine();
            }

        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error escribir el estado inicial en el log", e);
        }
    }

    public static void inicioEjecucion() {
        try {
            FileWriter file = new FileWriter(FileManager.getFile(), true);
            BufferedWriter buff = new BufferedWriter(file);

            String inicio = "Inicio de ejecucion";

            buff.write(espacio(inicio) + " - " + getTime());
            buff.newLine();

            buff.flush();
            buff.close();
            file.close();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error escribir el inicio de ejecucion en el log", e);
        }
    }

    public static void finEjecucion() {
        try {
            FileWriter file = new FileWriter(FileManager.getFile(), true);
            BufferedWriter buff = new BufferedWriter(file);

            String fin = "Fin de ejecucion";

            buff.write(espacio(fin) + " - " + getTime());
            buff.newLine();

            buff.flush();
            buff.close();
            file.close();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error escribir el fin de ejecucion en el log", e);
        }
    }

    // ****************************** ABM ******************************

    public static void altaDesafio(Desafio desafio){
        try {
            String alta = formatDesafio(desafio);
            escribirEnDatosIniciales(alta);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error escribir el alta de un desafio en el archivo de datos iniciales", e);
        }
    }

    public static void bajaDesafio(Desafio desafio){
        try {
            String baja = formatDesafio(desafio);
            eliminarLinea(baja);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error escribir la baja de un desafio en el archivo de datos iniciales", e);
        }
    }

    public static void modificacionDesafio(Desafio desafio, Desafio modificado) {
        try{
            String desafioFormateado = formatDesafio(desafio);
            String modificadoFormateado = formatDesafio(modificado);
            modificarLinea(desafioFormateado,modificadoFormateado);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error al escribir la modificacion de un desafio en el archivo de datos iniciales", e);
        }
    }

    public static void altaEquipo(Equipo equipo){
        try {
            String alta = formatEquipo(equipo);
            escribirEnDatosIniciales(alta);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error escribir el alta de un equipo en el archivo de datos iniciales", e);
        }
    }

    public static void bajaEquipo(Equipo equipo){
        try {
            String baja = formatEquipo(equipo);
            eliminarLinea(baja);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error escribir la baja de un equipo en el archivo de datos iniciales", e);
        }
    }

    public static void modificacionEquipo(Equipo equipo, Equipo modificado) {
        try{
            String equipoFormateado = formatEquipo(equipo);
            String modificadoFormateado = formatEquipo(modificado);
            modificarLinea(equipoFormateado,modificadoFormateado);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error al escribir la modificacion de un equipo en el archivo de datos iniciales", e);
        }
    }

    public static void altaHabitacion(Habitacion habitacion){
        try {
            String alta = formatHabitacion(habitacion);
            escribirEnDatosIniciales(alta);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error escribir el alta de una habitacion en el archivo de datos iniciales", e);
        }
    }

    public static void bajaHabitacion(Habitacion habitacion){
        try {
            String baja = formatHabitacion(habitacion);
            eliminarLinea(baja);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error escribir la baja de una habitacion en el archivo de datos iniciales", e);
        }
    }

    public static void modificacionHabitacion(Habitacion habitacion, Habitacion modificado) {
        try{
            String habitacionFormateado = formatHabitacion(habitacion);
            String modificadoFormateado = formatHabitacion(modificado);
            modificarLinea(habitacionFormateado,modificadoFormateado);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error al escribir la modificacion de una habitacion en el archivo de datos iniciales", e);
        }
    }

    public static void altaPuerta(int origen,int destino,int puntaje){
        try {
            String alta = formatPuerta(origen,destino,puntaje);
            escribirEnDatosIniciales(alta);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error escribir el alta de una puerta en el archivo de datos iniciales", e);
        }
    }

    public static void bajaPuerta(int origen,int destino,int puntaje){
        try {
            String baja = formatPuerta(origen,destino,puntaje);
            eliminarLinea(baja);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error escribir la baja de una puerta en el archivo de datos iniciales", e);
        }
    }

    public static void modificacionPuerta(int origen,int destino,int puntaje,int modificadoPuntaje) {
        try{
            String puertaFormateado = formatPuerta(origen,destino,puntaje);
            String modificadoFormateado = formatPuerta(origen,destino,modificadoPuntaje);
            modificarLinea(puertaFormateado,modificadoFormateado);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error al escribir la modificacion de una puerta en el archivo de datos iniciales", e);
        }
    }

    // ------------------ LOGS ------------------

    public static void logAltaDesafio(int desafio) {
        try {
            String alta = "ALTA de desafio: " + desafio;
            String str = espacio(alta) + " - " + getTime();
            escribirEnLog(str);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error escribir el alta de un desafio en el log", e);
        }
    }

    public static void logBajaDesafio(int desafio) {
        try {
            String baja = "BAJA de desafio: " + desafio;
            String str = espacio(baja) + " - " + getTime();
            escribirEnLog(str);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error escribir la baja de un desafio en el log", e);
        }
    }

    public static void logModificacionDesafio(int desafio) {
        try {
            String modificacion = "MODIFICACION de desafio: " + desafio;
            String str = espacio(modificacion) + " - " + getTime();
            escribirEnLog(str);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error escribir la modificacion de un desafio en el log", e);
        }
    }

    public static void logAltaEquipo(String equipo) {
        try {
            String alta = "ALTA de equipo: " + equipo;
            String str = espacio(alta) + " - " + getTime();
            escribirEnLog(str);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error escribir el alta de un equipo en el log", e);
        }
    }

    public static void logBajaEquipo(String equipo) {
        try {
            String baja = "BAJA de equipo: " + equipo;
            String str = espacio(baja) + " - " + getTime();
            escribirEnLog(str);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error escribir la baja de un equipo en el log", e);
        }
    }

    public static void logModificacionEquipo(String equipo) {
        try {
            String modificacion = "MODIFICACION de equipo: " + equipo;
            String str = espacio(modificacion) + " - " + getTime();
            escribirEnLog(str);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error escribir la modificacion de un equipo en el log", e);
        }
    }

    public static void logAltaHabitacion(int habitacion) {
        try {
            String alta = "ALTA de habitacion: " + habitacion;
            String str = espacio(alta) + " - " + getTime();
            escribirEnLog(str);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error escribir el alta de un habitacion en el log", e);
        }
    }

    public static void logBajaHabitacion(int habitacion) {
        try {
            String baja = "BAJA de habitacion: " + habitacion;
            String str = espacio(baja) + " - " + getTime();
            escribirEnLog(str);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error escribir la baja de una habitacion en el log", e);
        }
    }

    public static void logModificacionHabitacion(int habitacion) {
        try {
            String modificacion = "MODIFICACION de habitacion: " + habitacion;
            String str = espacio(modificacion) + " - " + getTime();
            escribirEnLog(str);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error escribir la modificacion de una habitacion en el log", e);
        }
    }

    public static void logAltaPuerta(int puerta) {
        try {
            String alta = "ALTA de puerta: " + puerta;
            String str = espacio(alta) + " - " + getTime();
            escribirEnLog(str);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error escribir el alta de una puerta en el log", e);
        }
    }

    public static void logBajaPuerta(int puerta) {
        try {
            String baja = "BAJA de puerta: " + puerta;
            String str = espacio(baja) + " - " + getTime();
            escribirEnLog(str);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error escribir la baja de una puerta en el log", e);
        }
    }

    public static void logModificacionPuerta(int puerta) {
        try {
            String modificacion = "MODIFICACION de puerta: " + puerta;
            String str = espacio(modificacion) + " - " + getTime();
            escribirEnLog(str);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error escribir la modificacion de una puerta en el log", e);
        }
    }


    // ****************************** LECTURA ******************************

    /**
     * Devuelve el archivo de datos iniciales.
     * @return Archivo de datos iniciales
     */
    private static File getReadFile() {
        File file = null;
        try {
            String filePath = "src/resources/datosIniciales.txt";
            file = new File(filePath);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al obtener el archivo de log", e);
        }
        return file;
    }

    public static void cargarDesafios(ArbolAVL desafios){ // TODO: Terminar de implementar las cargas.
        File file = FileManager.getReadFile();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            while(br.readLine() != null){

            }

            br.close();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error al cargar desafios", e);
        }
    }

    public static void cargarEquipos(HashMap<String, Equipo> equipos){

    }

    public static void cargarHabitaciones(GrafoEtiquetado grafoCasa,ArbolAVL habitaciones){

    }

    public static void cargarPuertas(GrafoEtiquetado grafoCasa){

    }

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

    /**
     * Ajusta el espacio entre el texto y la hora.
     *
     * @param cadena
     * @return
     */
    private static String espacio(String cadena) {
        int espacios = 60 - cadena.length();
        String espacio = cadena;
        for (int i = 0; i < espacios; i++) {
            espacio += " ";
        }
        return espacio;
    }

    /**
     * Modifica una linea del archivo.
     * @param linea
     * @param nuevaLinea
     */
    private static void modificarLinea(String linea, String nuevaLinea) throws IOException {

            File file = FileManager.getReadFile();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = "", oldText = "";
            while ((line = reader.readLine()) != null) {
                oldText += line + "\r\n";
            }
            reader.close();

            String newText = oldText.replaceAll(linea, nuevaLinea);

            FileWriter writer = new FileWriter(file);
            writer.write(newText);
            writer.close();

    }

    /**
     * Elimina una linea del archivo.
     * @param linea
     */
    private static void eliminarLinea(String linea) throws IOException {
            File file = FileManager.getReadFile();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = "", oldText = "";
            while ((line = reader.readLine()) != null) {
                if (!line.equals(linea)) {
                    oldText += line + "\r\n";
                }
            }
            reader.close();

            FileWriter writer = new FileWriter(file);
            writer.write(oldText);
            writer.close();
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

    /**
     * Formatea una puerta con el siguiente formato:
     * P;origen;destino;puntaje
     * @param origen
     * @param destino
     * @param puntaje
     * @return
     */
    private static String formatPuerta(int origen, int destino, int puntaje){
        String puertaFormateada = "P;";
        puertaFormateada += origen + ";";
        puertaFormateada += destino + ";";
        puertaFormateada += puntaje;

        return puertaFormateada;
    }


}