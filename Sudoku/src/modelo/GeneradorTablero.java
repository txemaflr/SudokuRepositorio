package modelo;

import java.io.File;
import java.util.Scanner;

public class GeneradorTablero {

    private static GeneradorTablero miGeneradorTablero = null;
    private String ruta = "src\\sudoku.txt"; //Ruta al archivo .txt que contiene los sudokus
    private Scanner lectorIniciales;
    private Scanner lectorCorrectas;
    private File fichero;

    private GeneradorTablero() {
    }

    /**
     * Sirve para obtener la instancia de GeneradorTablero
     *
     * @return unica instancia de GeneradorTablero
     */
    public static GeneradorTablero getMiGeneradorTablero() {
        if (miGeneradorTablero == null) miGeneradorTablero = new GeneradorTablero();
        return miGeneradorTablero;
    }

    /**
     * Abre el fichero cuya ruta se encuentra en "ruta"
     */
    private void abrirFichero() {
        fichero = new File(ruta);
    }

    /**
     * Obtiene un lector para el fichero
     *
     * @return lector
     */
    private Scanner obtenerLector() {
        Scanner sc = null;
        try {
            sc = new Scanner(fichero);
        } catch (Exception e) {
            System.out.println("Error al abrir archivo");
            System.exit(0);
        }
        return sc;
    }

    /**
     * Inicia ambos atributos de lectores, uno para iniciales y otro para correctas
     */
    private void iniciarLectores() {

        abrirFichero();

        lectorIniciales = obtenerLector();
        lectorIniciales.next(); //Saltarse la línea S00X

        lectorCorrectas = obtenerLector();
        for (int i = 0; i < 11; i++) { //Llegar hasta soluciones
            lectorCorrectas.next();
        }
    }

    /**
     * Cierra ambos lectores
     */
    private void cerrarLectores() {
        lectorIniciales.close();
        lectorCorrectas.close();
    }

    /**
     * Genera el tablero a partir del fichero .txt
     *
     * @return el tablero generado
     */
    public Tablero generarTablero() {

        iniciarLectores();
        String inicial, correcta;
        Tablero t = new Tablero(Integer.valueOf(lectorIniciales.next()));

        for (int i = 0; i < 9; i++) {

            inicial = lectorIniciales.next();
            correcta = lectorCorrectas.next();

            for (int j = 0; j < 9; j++) {

                if (Character.getNumericValue(inicial.charAt(j)) != 0) { //Si es un numero dado en el sudoku

                    t.anadirCelda(i, j, new Celda(Character.getNumericValue(inicial.charAt(j)), -1));

                } else {

                    t.anadirCelda(i, j, new Celda(Character.getNumericValue(inicial.charAt(j)), Character.getNumericValue(correcta.charAt(j))));
                    //t.anadirCelda(i, j, new Celda(Character.getNumericValue(correcta.charAt(j)), Character.getNumericValue(correcta.charAt(j)))); //Carga el tablero solucionado
                }

            }
        }
        cerrarLectores();
        return t;
    }
}
