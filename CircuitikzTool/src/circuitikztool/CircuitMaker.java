/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitikztool;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

/**
 *
 * @author James
 */
public class CircuitMaker extends JPanel {

    int x_offset, y_offset = 0;
    boolean dragging = false;
    int lastMouseX, lastMouseY;

    public CircuitMaker() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    //left click
                } else if (e.getButton() == MouseEvent.BUTTON2) {
                    //center click
                    dragging = true;
                    lastMouseX = MouseInfo.getPointerInfo().getLocation().x;
                    lastMouseY = MouseInfo.getPointerInfo().getLocation().y;
                }

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    //left click
                } else if (e.getButton() == MouseEvent.BUTTON2) {
                    //center click
                    dragging = false;
                }
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        if (dragging) {
            x_offset += (MouseInfo.getPointerInfo().getLocation().x - lastMouseX);
            y_offset += (MouseInfo.getPointerInfo().getLocation().y - lastMouseY);
            lastMouseX = MouseInfo.getPointerInfo().getLocation().x;
            lastMouseY = MouseInfo.getPointerInfo().getLocation().y;
        }
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 10000, 10000);

        g.setColor(Color.white);
        g.drawOval(250 + x_offset, 250 + y_offset, 5, 5);
        
        
    }

}
