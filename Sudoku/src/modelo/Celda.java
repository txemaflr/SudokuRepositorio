package modelo;

public class Celda {
	private int numActual;
    private int numCorrecto; //En caso de que la Celda sea una inicial, numCorrecto se inicia a "-1"

    /**
     * Constructora
     *
     * @param pActual numero actual de la Celda
     * @param pCorrecto numero que deberia ser para ser correcto
     */
    public Celda(int pActual, int pCorrecto) {
        numActual = pActual;
        numCorrecto = pCorrecto;
    }

    /**
     * Comprueba si la Celda es correcta
     *
     * @return true si lo es, false sino
     */
    public boolean comprobarCorrecto() {
        if (numCorrecto == -1) return true;
        return numActual == numCorrecto;
    }

    /**
     * Indica si la celda actual esta rellena
     *
     * @return true si lo está, false sino
     */
    public boolean estaRellena() {
        return numActual != 0;
    }

    /**
     * Pone el atributo recibido como numero actual
     *
     * @param pNum numero a poner
     */
    public void setNum(int pNum) {
        numActual = pNum;
    }

    /**
     * Devuelve el numero actual
     *
     * @return numero actual
     */
    public int getNumActual() {
        return numActual;
    }

    /**
     * Indica si la celda es una inicial
     *
     * @return true si lo es, false sino
     */
    public boolean esInicial() {
        return numCorrecto == -1;
    }

}
