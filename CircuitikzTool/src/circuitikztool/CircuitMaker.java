/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitikztool;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author James
 */
public class CircuitMaker extends JPanel {
    
  public CircuitMaker(){
      this.setBackground(Color.black);
  }
    
  
  @Override
   public void paint(Graphics g) {
      g.setColor(Color.RED);
      g.drawOval(50, 50, 50, 50);
   }
    
}
