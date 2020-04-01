package circuitikztool;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class Component {

    Point wireStart, wireEnd;
    Point position;
    BufferedImage Icon;
    String Text = "";
    String Label = "";

    int componentType;

    final static int WIRE = 0;
    final static int TWO_TERMINAL = 1;
    final static int THREE_TERMINAL = 2;

    public Component(int grid_x, int grid_y, int componentSelected) {
        if (componentSelected == WIRE) {
            throw new IllegalArgumentException("Component Selected == Wire but no start/end point is defined");
        }
        componentType = componentSelected;
    }

    public Component(int grid_x_start, int grid_y_start, int grid_x_end, int grid_y_end, int componentSelected) {
        if (componentSelected != WIRE) {
            throw new IllegalArgumentException("Component Selected != Wire");
        }
        componentType = componentSelected;
    }

    public Component(Point wireStart, Point wireEnd, int componentSelected) {
        if (componentSelected != WIRE) {
            throw new IllegalArgumentException("Component Selected != Wire");
        }
        this.wireStart = wireStart;
        this.wireEnd = wireEnd;
        Text = "to[short]";
        Label = "Wire";
        componentType = componentSelected;
    }

    public void paint(Graphics g, int gridSize, Point offset, boolean selected) {
        if (selected) {
            g.setColor(Color.blue);
        } else {
            g.setColor(Color.white);
        }

        switch (componentType) {
            case WIRE:
                g.drawLine(
                        gridSize * (wireStart.x + offset.x),
                        gridSize * (wireStart.y + offset.y),
                        gridSize * (wireEnd.x + offset.x),
                        gridSize * (wireEnd.y + offset.y)
                );
                break;
            case TWO_TERMINAL:
                break;
            case THREE_TERMINAL:
                break;
            default:
                break;
        }
    }

    public String getComponentLabel() {
        return Label;
    }

    public void setComponentLabel(String text) {
        Label = text;
    }

    public String getComponentString() {
        return Text;
    }

    public void setComponentString(String text) {
        Text = text;
    }

    public Point getStart() {
        return wireStart;
    }

    public Point getEnd() {
        return wireEnd;
    }

    public String getComponentLabelString() {
        String retString = "";
        retString += Label + " ";
        retString += "[" + wireStart.x + "," + wireStart.y + "] to [" + wireEnd.x + "," + wireEnd.y + "]";
        return retString;
    }

}
