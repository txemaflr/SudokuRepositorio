package vista;

import javax.swing.*;

import controlador.Controlador;
import modelo.Sudoku;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class VentanaJuego extends JFrame implements Observer {

    private JPanel contentPane;
    private PanelTablero tablero;
    private JTextField campoValor;
    private JButton botonModificar;


    public VentanaJuego() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initialize();
        Sudoku.getMiSudoku().addObserver(this);
    }

    /**
     * Inicializa la ventana de juego
     */
    private void initialize() {
        setSize(750, 400);
        setResizable(false);
        setLayout(new GridLayout(1, 2));
        setContentPane(getContentPane());
        setTitle("Sudoku");
        setLocationRelativeTo(null);
        tablero = new PanelTablero();
        add(tablero.getTablero());
        add(getPanelDer());
        setVisible(true);
    }

    /**
     * Obtiene el panel situado a la derecha
     *
     * @return el panel del lado derecho de la ventana de juego
     */
    private JPanel getPanelDer() {

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(13, 1));
        for (int i = 0; i < 5; i++) {
            panel.add(new JLabel());
        }
        panel.add(getPanelValor());
        panel.add(getPanelModificar());
        for (int i = 0; i < 5; i++) {
            panel.add(new JLabel());
        }
        return panel;
    }

    /**
     * Obtiene el campo donde el usuario introducira los valores
     *
     * @return el campo de texto para introducir valores
     */
    private JTextField getCampoValor() {

        if (campoValor == null) {
            campoValor = new JTextField("", 10);
        }
        return campoValor;
    }

    /**
     * Obtiene el panel que indica al usuario que a su derecha puede introducir valores
     *
     * @return el panel de nombre Valor
     */
    private JPanel getPanelValor() {

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(new JLabel("Valor "));
        panel.add(getCampoValor());

        return panel;
    }

    /**
     * Obtiene el panel con el boton con el que el usuario anade un valor que ha introducido
     *
     * @return el panel que tiene el boton Modificar
     */
    private JPanel getPanelModificar() {

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(new JLabel());
        panel.add(getBotonModificar());
        panel.add(new JLabel());
        return panel;
    }

    /**
     * Obtiene el boton con el que el usuario anade el valor introducido en la celda que ha sido seleccionada
     *
     * @return el boton Modificar
     */
    private JButton getBotonModificar() {
        if (botonModificar == null) {
            botonModificar = new JButton("Modificar");
            botonModificar.addActionListener(Controlador.getMiControlador());
        }
        return botonModificar;
    }

    /**
     * Gestiona que se hara en base a la interaccion que el usuario ha tenido con la celda
     *
     *@param pAr array donde se recoge la informacion de la interaccion del usuario con la celda
     */
    private void interaccionCelda(int[] pAr) {

        String[] estado = tablero.refrescarCelda(pAr[1]);

        if (estado[0].equals("0")) { //Se ha deseleccionado la Celda

            campoValor.setText("");

        } else { //Se ha seleccionado

            campoValor.setText(estado[1]);
        }
    }

    /**
     * Gestiona que se hara en base a la situacion en la que el usuario ha pulsado el boton Modificar
     */
    private void interaccionBoton() {

        if (tablero.haySeleccionado()) {
            if (campoValor.getText().equals("")) { //Caso aviso 1
                crearAlerta(1);
            } else {
                try {
                    int valor = Integer.parseInt(campoValor.getText());
                    if ((valor < 1) || (valor > 9)) throw new NumberFormatException();
                    tablero.actualizarCelda(valor);

                } catch (NumberFormatException e) {
                    crearAlerta(2);
                }
                campoValor.setText("");
            }
        } else { //Caso aviso 0
            crearAlerta(0);
        }
    }

    /**
     * Muestra un mensaje de alerta concreto al usuario, dependiendo de la situacion
     *
     *@param pCaso numero que identifica al mensaje de alerta que debe aparecer
     */
    private void crearAlerta(int pCaso) {
        switch (pCaso) {
            case 0:
                JOptionPane.showMessageDialog(this, "No has seleccionado celda", "Aviso", JOptionPane.WARNING_MESSAGE);
                break;
            case 1:
                JOptionPane.showMessageDialog(this, "No hay ningún valor", "Aviso", JOptionPane.WARNING_MESSAGE);
                break;
            case 2:
                JOptionPane.showMessageDialog(this, "Valor introducido no válido (1-9)", "Aviso", JOptionPane.WARNING_MESSAGE);
                break;
        }
    }

    /**
     * Interaccion del patron Observer, lado del Observador
     *
     * @param o el Observado que ha provocado el update
     * @param arg argumentos recibidos
     */
    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Sudoku) {
            if (arg instanceof int[]) {
                int[] ar = (int[]) arg;
                if (ar[0] == 0) {
                    interaccionCelda(ar);
                } else if (ar[0] == 1) {
                    interaccionBoton();
                }
            }
        }
    }
}


