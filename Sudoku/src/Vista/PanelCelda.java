package Vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelCelda extends JPanel {

    private int numComponente;
    private JLabel label;

    /**
     * Constructora
     *
     * @param pValorLabel valor que va a tener la Celda
     * @param pNumComponente numero de componente que es dentro del JPanel del Tablero
     */
    public PanelCelda(String pValorLabel, int pNumComponente) {
        numComponente=pNumComponente;
        label=new JLabel(pValorLabel);
        setBorder(BorderFactory.createLineBorder(Color.black));
        setPreferredSize(new Dimension(30, 30));
        if (pValorLabel.equals("")) label.setForeground(Color.BLACK);
        else label.setForeground(Color.RED);
        add(label);
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                Sudoku.getMiSudoku().refrescarCelda(numComponente);
            }
        });
    }

    /**
     * Devuelve que numero de componente es
     *
     * @return el numero de componente
     */

    public int getNumComponente() {
        return numComponente;
    }

    /**
     * Actualiza el valor actual del JLabel
     *
     * @param pValor valor al que actualizar el JLabel
     */
    public void actualizarTexto(int pValor) {
        label.setText(String.valueOf(pValor));
    }

}


