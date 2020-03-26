/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitikztool;

import javax.swing.JFrame;

/**
 *
 * @author James
 */
public class CircuitikzTool extends JFrame {

    static public CircuitikzTool ct = new CircuitikzTool();
    static public CircuitMaker cm = new CircuitMaker();
    static public GUI ui = new GUI(); 
    public CircuitikzTool() {

    }

    public static void main(String[] args) throws InterruptedException {

//         ui = new GUI();
        ui.setTitle("CircuiTikz Tool Ver 1.0.0");
        ui.setLocationRelativeTo(null);
        ui.setVisible(true);
        while (true) {
            ui.schematicWindow.repaint();
            CircuitMaker.setCurrentTool(ui.getCurrentToolSelected());
        }
    }


}
