/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitikztool;

import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author James
 */
public class CircuitikzTool extends JFrame {

    /**
     *
     */
    static public CircuitikzTool ct = new CircuitikzTool();

    /**
     *
     */
    static public GUI ui = new GUI();

    /**
     *
     */
    public CircuitikzTool() {

    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        ui.setTitle("CircuiTikz Tool");
        ui.setLocationRelativeTo(null);
        ui.setVisible(true);
        ui.setFocusable(true);
        ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Preferences.importPreferences();
        Preferences.ConfigPrefrences();

        while (true) {
            ui.repaintCircuitMaker();
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(CircuitikzTool.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
