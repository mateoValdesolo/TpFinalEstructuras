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
        ArbolAVL desafios = new ArbolAVL();

        desafios.insertar(10,10);
        desafios.insertar(20,20);
        desafios.insertar(30,30);
        desafios.insertar(40,40);
        desafios.insertar(50,50);
        desafios.insertar(60,60);
        desafios.insertar(70,70);
        desafios.insertar(80,80);
        desafios.insertar(90,90);
        desafios.insertar(7,7);
        desafios.insertar(5,5);
        desafios.insertar(15,15);
        desafios.insertar(35,35);
        desafios.insertar(45,45);
        desafios.insertar(25,25);
        desafios.insertar(55,55);
        desafios.insertar(65,65);
        desafios.insertar(75,75);
        desafios.insertar(85,85);
        desafios.insertar(95,95);
        desafios.insertar(3,3);
        desafios.insertar(6,6);
        desafios.insertar(8,8);
        desafios.insertar(9,9);
        desafios.insertar(1,1);
        desafios.insertar(2,2);
        desafios.insertar(13,13);

        System.out.println(desafios);

    }
}
