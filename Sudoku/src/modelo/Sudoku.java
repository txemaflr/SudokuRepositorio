package modelo;

import java.util.Observable;


public class Sudoku extends Observable {

    private Tablero tablero;
    private static Sudoku miSudoku = null;

    private Sudoku() {
    }

    /**
     * Sirve para obtener la instancia de Sudoku
     *
     * @return unica instancia de Sudoku
     */
    public static Sudoku getMiSudoku() {
        if (miSudoku == null) miSudoku = new Sudoku();
        return miSudoku;
    }

    public void ejecutar() {
        cargarTablero();
        new VentanaJuego();
    }

    /**
     * Carga el tablero
     */
    private void cargarTablero() {
        tablero = GeneradorTablero.getMiGeneradorTablero().generarTablero();
    }

    /**
     * Comprueba que la solucion de tablero es la correcta
     *
     * @return true si es correcta y false sino
     */
    private boolean comprobarSolucion() {
        return tablero.esCorrecto();
    }

    /**
     * Devuelve el valor actual de la celda pedida
     *
     * @param pFila	el numero de la fila
     * @param pColumna el numero de la columna
     * @return el valor de la celda
     */
    public int valorActualCelda(int pFila, int pColumna) {
        return tablero.valorActualCelda(pFila, pColumna);
    }

    /**
     * Devuelve el valor del componente que se recibe
     *
     * @param pNumComponente numero de componente del que se quiere conseguir el valor
     * @return el valor de la celda
     */
    public int valorActualCelda(int pNumComponente) {
        return tablero.valorActualCelda(pNumComponente);
    }

    /**
     * Indica si la celda que se envia es inicial
     *
     * @param pNumComponente numero de componente de la celda
     * @return true si es inicial, false sino
     */
    public boolean esCeldaInicial(int pNumComponente) {
        return tablero.esCeldaInicial(pNumComponente);
    }

    /**
     * Actualiza la celda con el valor recibido, además si la celda a rellenar es la ultima comprueba la solucion e imprime
     *
     * @param pNumComp numero de componente de la celda a modificar
     * @param pValor valor a ponerle a la celda
     */
    public void actualizarCelda(int pNumComp, int pValor) {
        tablero.actualizarCelda(pNumComp, pValor);
        if (tablero.todasRellenas()) {
            if (comprobarSolucion()) { System.out.println("Sudoku correcto"); }
            else { System.out.println("Sudoku incorrecto"); }
        }
    }

    /**
     * Llamado cuando se selecciona una celda, CODIGO 0 para ar
     *
     * @param pNumComponente numero de componente que hay que refrescar
     */
    public void refrescarCelda(int pNumComponente) {
        int[] ar = {0, pNumComponente};
        setChanged();
        notifyObservers(ar);
    }

    /**
     * Llamado cuando se pulsa el boton de modificar, CODIGO 1 para ar
     */
    public void modificar() {
        int[] ar = {1};
        setChanged();
        notifyObservers(ar);
    }


}


