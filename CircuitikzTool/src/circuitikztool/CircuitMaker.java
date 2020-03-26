/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitikztool;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author James
 */
public class CircuitMaker extends JPanel {

    static int GRID_SIZE = 25;

    int x_offset = GRID_SIZE * 5;
    int y_offset = GRID_SIZE * 5;
    boolean dragging = false;
    boolean clicking = false;
    int lastMouseX, lastMouseY;

    int xGridPosition;
    int yGridPosition;

    Point originOffset;

    ArrayList<Component> components;

    private static String[] availableTools = {"Wire", "Resistor", "Capacaitor"};

    static int currentTool = Component.WIRE;
    private Point wireStart;

    public static void setCurrentTool(int tool) {
        currentTool = tool;
    }

    public CircuitMaker() {
        components = new ArrayList(0);

        addMouseMotionListener(new MouseAdapter() {

            @Override
            public void mouseMoved(MouseEvent e) {
                xGridPosition = (e.getX() / GRID_SIZE);
                yGridPosition = (e.getY() / GRID_SIZE);

            }

            @Override
            public void mouseDragged(MouseEvent e) {
                xGridPosition = (e.getX() / GRID_SIZE);
                yGridPosition = (e.getY() / GRID_SIZE);
            }
        }
        );

        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {

                switch (e.getButton()) {
                    case MouseEvent.BUTTON1:
                        //left click
                        clicking = true;

                        //wire's are a special case, since they require two points to be placed
                        if (currentTool == Component.WIRE) {
                            wireStart = new Point(xGridPosition - originOffset.x, yGridPosition - originOffset.y);
                        }
                        break;
                    case MouseEvent.BUTTON2:
                        //center click
                        dragging = true;
                        lastMouseX = MouseInfo.getPointerInfo().getLocation().x;
                        lastMouseY = MouseInfo.getPointerInfo().getLocation().y;
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    //left click
                    clicking = false;
                    if (currentTool == Component.WIRE) {
                        placeWire();
                    }
                } else if (e.getButton() == MouseEvent.BUTTON2) {
                    //center click
                    dragging = false;
                }
            }
        });
    }

    public static String[] getAvailableTools() {
        return availableTools;
    }

    @Override
    public void paint(Graphics g) {
        if (dragging) {
            x_offset += (MouseInfo.getPointerInfo().getLocation().x - lastMouseX);
            y_offset += (MouseInfo.getPointerInfo().getLocation().y - lastMouseY);
            lastMouseX = MouseInfo.getPointerInfo().getLocation().x;
            lastMouseY = MouseInfo.getPointerInfo().getLocation().y;
        }

        int originOffsetX = (x_offset / GRID_SIZE) * GRID_SIZE;
        int originOffsetY = (y_offset / GRID_SIZE) * GRID_SIZE;

        originOffset = new Point(x_offset / GRID_SIZE, y_offset / GRID_SIZE);

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 10000, 10000);

        //if someone hovers over the origin lets make sure they know that its the origin
        if (originOffsetX == xGridPosition * GRID_SIZE && originOffsetY == yGridPosition * GRID_SIZE) {
            g.setColor(Color.white);
            g.drawString("Origin", originOffsetX - 10, originOffsetY - 5);
        }

        //draw the grid
        g.setColor(Color.GRAY);
        for (int x = 0; x < this.getWidth(); x += GRID_SIZE) {
            for (int y = 0; y < this.getHeight(); y += GRID_SIZE) {
                g.drawLine(x, y, x, y);
            }
        }

        //draw origin
        g.setColor(Color.BLUE);
        g.fillOval(originOffsetX - 3, originOffsetY - 3, 5, 5);

        //draw the current mouse position snapped to the grid
        g.setColor(Color.white);
        g.fillOval(GRID_SIZE * xGridPosition - 3, GRID_SIZE * yGridPosition - 3, 5, 5);

        if (clicking) {
            switch (currentTool) {
                case Component.WIRE:
                    g.setColor(Color.white);
                    g.drawLine(
                            GRID_SIZE * (wireStart.x + originOffset.x),
                            GRID_SIZE * (wireStart.y + originOffset.y),
                            GRID_SIZE * xGridPosition,
                            GRID_SIZE * yGridPosition);
                    break;
                case Component.TWO_TERMINAL:
                    break;
                case Component.THREE_TERMINAL:
                    break;
                default:
                    throw new IllegalStateException("Current Tool Selected does not exist");
            }
        }

        for (int a = 0; a < components.size(); a++) {
            components.get(a).paint(g, GRID_SIZE, originOffset);
        }

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

    public void placeWire() {
        components.add(new Component(wireStart, new Point(xGridPosition - originOffset.x, yGridPosition - originOffset.y), Component.WIRE));
    }
}
