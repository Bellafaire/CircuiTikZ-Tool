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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Set;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author James
 */
public class CircuitMaker extends JPanel {

    boolean wrapInFigure = true;
    boolean americanStyleComponents = true;
    boolean useHMarker = true;

    static int GRID_SIZE = 50;
    int xGridPosition;
    int yGridPosition;

    int x_offset = GRID_SIZE * 5;
    int y_offset = GRID_SIZE * 5;
    boolean dragging = false;
    boolean clicking = false;
    int lastMouseX, lastMouseY;

    private int componentIndexSelected = 0;

    Point originOffset;

    private ArrayList<Component> components;

    static int currentTool = Component.PATH;
    private Point wireStart;

    public static void setCurrentTool(int tool) {
        currentTool = tool;
    }

    public void setSelectedComponentIndex(int index) {
        componentIndexSelected = index;
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
//                System.out.println(componentIndexSelected);
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

                        wireStart = new Point(xGridPosition - originOffset.x, yGridPosition - originOffset.y);

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

                    placeComponent();
                    componentIndexSelected = components.size();
                    CircuitikzTool.ui.updateComponentList(); //this is very bad and we shouldn't do it this way but eh whatever

                } else if (e.getButton() == MouseEvent.BUTTON2) {
                    //center click
                    dragging = false;
                }

            }
        });

        addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                //we don't cap the user from zooming in, they can make the grid as big as they want
                //however if they make the grid too small it becomes difficult to render anything properly so we cap 
                //the zoom in to a gridsize of 10. if we're at a grid size of 10 we make sure the user can also zoom out
                if (GRID_SIZE > 10 || e.getWheelRotation() < 0) {
                    GRID_SIZE -= e.getWheelRotation(); //very simple zoom method here, could be improved
                }
                //     System.out.println("Grid size is now " + GRID_SIZE);
            }
        });
    }

    public int getSelectedComponentIndex() {
        return componentIndexSelected;
    }

    public String[] getComponentList() {
        String[] listItems = new String[components.size()];
        for (int a = 0; a < listItems.length; a++) {
            listItems[a] = components.get(a).getComponentLabelString();
        }
        return listItems;
    }

//    public void updateUIString() {
//        //update output with the current LaTex string of the circuit
//        CircuitikzTool.ui.outputField.setText(generateLatexString());
//    }
    @Override
    public void paint(Graphics g) {

        //first figure out what placed component has already been selected (we need to highlight it so the user can interact with it)
        setSelectedComponentIndex(CircuitikzTool.ui.componentList.getSelectedIndex());

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
            g.setColor(Color.white);
            if (Component.isPathComponent(currentTool)) {
                g.drawLine(
                        GRID_SIZE * (wireStart.x + originOffset.x),
                        GRID_SIZE * (wireStart.y + originOffset.y),
                        GRID_SIZE * xGridPosition,
                        GRID_SIZE * yGridPosition);
            } else {
                g.drawLine(GRID_SIZE * xGridPosition, GRID_SIZE * yGridPosition, GRID_SIZE * xGridPosition, GRID_SIZE * yGridPosition - GRID_SIZE);
                g.drawLine(GRID_SIZE * xGridPosition, GRID_SIZE * yGridPosition, GRID_SIZE * xGridPosition, GRID_SIZE * yGridPosition + GRID_SIZE);
                g.drawLine(GRID_SIZE * xGridPosition, GRID_SIZE * yGridPosition, GRID_SIZE * xGridPosition - GRID_SIZE, GRID_SIZE * yGridPosition);
                g.setColor(Color.black);
                g.fillOval(GRID_SIZE * xGridPosition - GRID_SIZE / 3, GRID_SIZE * yGridPosition - GRID_SIZE / 3, GRID_SIZE * 2 / 3, GRID_SIZE * 2 / 3);
                g.setColor(Color.white);
                g.drawOval(GRID_SIZE * xGridPosition - GRID_SIZE / 3, GRID_SIZE * yGridPosition - GRID_SIZE / 3, GRID_SIZE * 2 / 3, GRID_SIZE * 2 / 3);
            }
        }

        for (int a = 0; a < components.size(); a++) {
            if (a == componentIndexSelected) {
                components.get(a).paint(g, GRID_SIZE, originOffset, true, originOffsetX, originOffsetY);
            } else {
                components.get(a).paint(g, GRID_SIZE, originOffset, false, originOffsetX, originOffsetY);
            }
        }
    }

    public void setSelectedComponentLabel(String text) {
        if (componentIndexSelected >= 0) {
            components.get(componentIndexSelected).setComponentLabel(text);
        } else {
        }
    }

    public String getSelectedComponentLabel() {
        if (componentIndexSelected >= 0) {
            return components.get(componentIndexSelected).getComponentLabel();
        } else {
            return "";
        }
    }

    public void setSelectedComponentString(String text) {
        if (componentIndexSelected >= 0) {
            components.get(componentIndexSelected).setComponentString(text);
        } else {
        }
    }

    public String getSelectedComponentString() {
        if (componentIndexSelected >= 0) {
            return components.get(componentIndexSelected).getComponentString();
        } else {
            return "";
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

    public void placeComponent() {
        Component c;
        try {
            c = new Component(wireStart, new Point(xGridPosition - originOffset.x, yGridPosition - originOffset.y), currentTool);
        } catch (IllegalArgumentException e) {
            c = new Component(new Point(xGridPosition - originOffset.x, yGridPosition - originOffset.y), currentTool);
        }
        components.add(c);
        setSelectedComponentIndex(components.size() - 1);
        System.out.println("added component to index " + (components.size() - 1));
    }

    public void deleteSelectedComponent() {
        try {
            components.remove(componentIndexSelected);
            componentIndexSelected = (componentIndexSelected > 0) ? componentIndexSelected-- : 0;
        } catch (ArrayIndexOutOfBoundsException e) {
        }
    }

    public String generateLatexString() {
        String output = "";

        if (wrapInFigure) {
            output += "\\begin{figure}";
            if (useHMarker) {
                output += "[h]\n";
            } else {
                output += "\n";
            }
            output += "\\centering\n";
            output += "\\begin{circuitikz}";
            if (americanStyleComponents) {
                output += "[american]";
            }
            output += "\n";
        } else {
            output += "\\begin{circuitikz}";
            if (useHMarker && americanStyleComponents) {
                output += "[h, american]\n";
            } else if (useHMarker && !americanStyleComponents) {
                output += "[h]\n";
            } else if (!useHMarker && americanStyleComponents) {
                output += "[american]\n";
            } else {
                output += "\n";
            }
        }

        //determine whether we have any mosfets in the placed components, if we do then we need to add some extra formatting 
        boolean containsFet = false;
        for (int a = 0; a < components.size(); a++) {
            if (components.get(a).isFet()) {
                containsFet = true;
                break;
            }
        }
        if (containsFet) {
            output += "\\ctikzset{tripoles/mos style/arrows}\n";
            output += "\\ctikzset{tripoles/pmos style/nocircle}\n";
        }
        
        //generate latex string for each component placed in the circuitmaker window
        for (int a = 0; a < components.size(); a++) {
            output += components.get(a).getLatexLine();
        }

        output += "\\end{circuitikz}";
        if (wrapInFigure) {
            output += "\n\\end{figure}";
        }
        return output;
    }
}
