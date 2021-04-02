package vista;

import javax.swing.*;
import java.awt.*;

public class PanelTablero {

    private JPanel tablero;
    private boolean seleccionado;
    private PanelCelda celdaSeleccionada;

    public PanelTablero() {
        initialize();
    }

    /**
     * Inicializa el tablero 9x9 en la vista
     */
    private void initialize() {
        tablero = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5); //espacio entre las labels
        int numComponente=0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                String valorLabel = "";
                int valorTablero = Sudoku.getMiSudoku().valorActualCelda(i, j);
                if (valorTablero != 0) {
                    valorLabel = valorLabel + valorTablero;
                }
                c.gridx = j;
                c.gridy = i;
                tablero.add(new PanelCelda(valorLabel,numComponente++), c);
                //Posibilidad de pasar coordenadas a la celda, para un futuro
            }
        }
    }

    /**
     * Indica si hay una Celda seleccionada
     *
     * @return true si la hay, false sino
     */
    public boolean haySeleccionado() {
        return seleccionado;
    }

    /**
     * Refresca la celda cuya posicion se recibe como parametro
     *
     * @param pNum numero de la celda que hay que refrescar dentro de tablero
     * @return array de Strings, en [0] si se ha seleccionado o no, en [1] el numero que tiene la celda en caso de haber seleccionado una
     */
    public String[] refrescarCelda(int pNum) {

        String num = "";
        String sel = "0";

        if (!Sudoku.getMiSudoku().esCeldaInicial(pNum)) {
            PanelCelda celdaObj = (PanelCelda) tablero.getComponent(pNum);
            String estadoBorde = celdaObj.getBorder().getBorderInsets(celdaObj).toString().substring(20, 21);
            if (estadoBorde.equals("1")) { //La celda no esta seleccionada
                if (!seleccionado) {
                    num = seleccionar(celdaObj);
                    sel = "1";
                }
            } else { //Si esta seleccionada
                deseleccionar();
            }
        }
        return new String[]{sel, num};
    }

    /**
     * Selecciona la celda que recibe
     *
     * @param pCelda celda a seleccionar
     * @return
     */
    private String seleccionar(PanelCelda pCelda) {
        celdaSeleccionada = pCelda;
        seleccionado = true;
        celdaSeleccionada.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        JLabel texto = (JLabel) celdaSeleccionada.getComponent(0);
        return texto.getText();
    }

    /**
     * Deselecciona la celda y resetea el borde
     */
    private void deseleccionar() {
        seleccionado = false;
        celdaSeleccionada.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    }

    /**
     * Actualiza la celdaSeleccionada con el valor recibido
     *
     * @param pValor valor a ponerle a la celda
     */
    public void actualizarCelda(int pValor) {
        int num = celdaSeleccionada.getNumComponente();
        Sudoku.getMiSudoku().actualizarCelda(num, pValor);
        celdaSeleccionada.actualizarTexto(Sudoku.getMiSudoku().valorActualCelda(num));
        deseleccionar();
    }

    /**
     * Devuelve el tablero
     *
     * @return JPanel del tablero
     */
    public JPanel getTablero() {
        return tablero;
    }
}


