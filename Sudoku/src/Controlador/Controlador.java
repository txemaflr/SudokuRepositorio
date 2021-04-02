package Controlador;

import java.awt.event.*;
import Modelo.Sudoku;

public class Controlador implements ActionListener {

    private static Controlador miControlador = null;

    private Controlador() {
    }

    /**
     * Sirve para obtener la instancia de Controlador
     *
     * @return unica instancia de Controlador
     */
    public static Controlador getMiControlador() {
        if (miControlador == null) miControlador = new Controlador();
        return miControlador;
    }

    public void iniciarSudoku ()
    {
    	Sudoku.getMiSudoku().ejecutar();
    }
    
    /**
     * Metodo que se encarga de recoger la interaccion con el boton y llamar al modelo
     * 
     * @param e ActionEvent que ha surgido
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Sudoku.getMiSudoku().modificar();
    }
}
