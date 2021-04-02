package Modelo;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Tablero {

    private Celda[][] mCelda;
    private int nivel;

    public Tablero(int pNivel) {
        setNivel(pNivel);
        mCelda = new Celda[9][9];
    }

    /**
     * Comprueba que todas las Celdas sean correctas
     *
     * @return true si es asi, false sino
     */
    public boolean esCorrecto() {

        IteradorMatriz iterador = new IteradorMatriz(mCelda);

        while (iterador.hasNext()) {
            if (!iterador.next().comprobarCorrecto())
                return false; //Si la celda actual no es correcta, se termina la comprobación
        }
        return true;
    }

    /**
     * Anade la celda a la lista donde corresponde
     *
     * @param pFil numero de la fila
     * @param pCol numero de la columna
     * @param pCelda objeto celda a introducir
     */
    public void anadirCelda(int pFil, int pCol, Celda pCelda) {
        mCelda[pFil][pCol] = pCelda;
    }

    /**
     * Obtiene la celda pedida
     *
     * @param pNumComp numero de componente de la celda (identificacion con respecto a su posicion en el tablero)
     * @return Celda con numero de componente pNumComp
     */
    private Celda obtenerCelda(int pNumComp) {
        IteradorMatriz iterador = new IteradorMatriz(mCelda);
        for (int i = 0; i < pNumComp; i++) iterador.next();
        return iterador.next();
    }

    /**
     * Actualiza el valor de la celda
     *
     * @param pComp numero de componente de la celda
     * @param pValor nuevo valor que tomara la celda
     */
    public void actualizarCelda(int pComp, int pValor) {
        obtenerCelda(pComp).setNum(pValor);
    }

    /**
     * Devuelve si la celda tiene un valor fijo desde el inicio de la partida
     *
     * @param pNum numero de componente de la celda
     * @return true si es celda inicial, false en caso contrario
     */
    public boolean esCeldaInicial(int pNum) {
        return obtenerCelda(pNum).esInicial();
    }

    /**
     * Devuelve si todas las celdas tienen valor
     *
     * @return true si todas las celdas tienen valor, false en caso contrario
     */
    public boolean todasRellenas() {
        IteradorMatriz iterador = new IteradorMatriz(mCelda);
        while (iterador.hasNext()) {
            if (!iterador.next().estaRellena())
                return false; //Si la celda actual no está rellena, se termina la comprobación
        }
        return true;
    }

    /**
     * Devuelve el valor actual de la celda dados su fila y su columna
     *
     * @param pFila numero de la fila
     * @param pColumna numero de la columna
     * @return valor de la celda
     */
    public int valorActualCelda(int pFila, int pColumna) {
        return mCelda[pFila][pColumna].getNumActual();
    }

    /**
     * Devuelve el valor actual de la celda dado su numero de componente
     *
     * @param pComponente numero de componente de la celda
     * @return valor de la celda
     */
    public int valorActualCelda(int pComponente) {
        return obtenerCelda(pComponente).getNumActual();
    }

    public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	private class IteradorMatriz implements Iterator<Object> {

        private Celda[][] matriz;
        private int indiceFila = 0;
        private int indiceColumna = 0;

        public IteradorMatriz(Celda[][] pMatriz) {
            if (pMatriz != null) {
                matriz = pMatriz;
            }
        }

        /**
         * Indica si hay otra Celda despues
         *
         * @return true si la hay, false sino
         */
        @Override
        public boolean hasNext() {
            if (indiceFila >= matriz.length) return false;
            if (indiceColumna >= matriz[indiceFila].length && (indiceFila >= matriz.length || indiceFila == matriz.length - 1))
                return false;
            return true;
        }

        /**
         * Devuelve la siguiente Celda
         *
         * @return siguiente Celda
         */
        @Override
        public Celda next() {
            if (!hasNext()) throw new NoSuchElementException();
            if (indiceColumna >= matriz[indiceFila].length) {
                indiceFila++;
                indiceColumna = 0;
            }
            return matriz[indiceFila][indiceColumna++];
        }
    }
}
