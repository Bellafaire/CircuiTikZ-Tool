/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitikztool;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Polygon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author James
 */
public class CircuitMaker extends JPanel {

    static int GRID_SIZE = 25;

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

        int snapOffsetX = (x_offset / GRID_SIZE) * GRID_SIZE;
        int snapOffsetY = (y_offset / GRID_SIZE) * GRID_SIZE;

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 10000, 10000);

        g.setColor(Color.white);
        for (int x = 5; x < this.getWidth(); x += GRID_SIZE) {
            for (int y = 5; y < this.getHeight(); y += GRID_SIZE) {
                g.drawLine(x, y, x, y);
            }
        }

        //draw origin
        g.setColor(Color.white);
        g.drawOval(252 + snapOffsetX, 252 + snapOffsetY, 6, 6);

        //draw tool bar 
//        g.setColor(Color.BLACK);
//        g.fillRect(this.getWidth() - 80, 0, 80, this.getHeight());
//        g.setColor(Color.white);
//        g.drawRect(this.getWidth() - 80, 0, 80, this.getHeight());
    }

    public static BufferedImage getImage(String filename) {
        try {
            ClassLoader cldr = CircuitikzTool.ct.getClass().getClassLoader();;
            URL imageURL = cldr.getResource("images/" + filename);
            return ImageIO.read(imageURL);
        } catch (IOException ex) {
            System.out.println("Failed to load image: " + filename);
        }
        return new BufferedImage(1, 1, 1);
    }
}
