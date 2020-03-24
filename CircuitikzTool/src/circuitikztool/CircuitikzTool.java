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

    /**
     * @param args the command line arguments
     */
    
  static public CircuitikzTool ct = new CircuitikzTool();
  static public CircuitMaker cm = new CircuitMaker();
  
    public static void main(String[] args) {
        GUI ui = new GUI();
        ui.setTitle("CircuiTikz Tool Ver 1.0.0");
        ui.setLocationRelativeTo(null);
        ui.jPanel1.add(cm);
        ui.setVisible(true);

        while(true){
            cm.repaint();
        }
    }
    
}
