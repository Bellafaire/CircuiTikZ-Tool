package circuitikztool;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class Component {

    Point wireStart, wireEnd;
    Point position;
    BufferedImage Icon;
    String Text = "";
    String Label = "";

    int componentType;

    final static int PATH = 0;
    final static int RESISTOR = 1;
    final static int CAPACITOR = 2;
    final static int INDUCTOR = 3;
    final static int DIODE = 4;
    final static int VOLTAGE_SOURCE = 5;
    final static int CURRENT_SOURCE = 6;
    final static int GROUND_NODE = 7;
    final static int VCC_NODE = 8;

    public Component(Point wireStart, Point wireEnd, int componentSelected) {
        this.wireStart = wireStart;
        this.wireEnd = wireEnd;
        switch (componentSelected) {
            case PATH:
                Text = "to[short]";
                Label = "Wire";
                break;
            case RESISTOR:
                Text = "to[R,l=$R$]";
                Label = "R";
                break;
            case CAPACITOR:
                Text = "to[C,l=$C$]";
                Label = "C";
                break;
            case INDUCTOR:
                Text = "to[L,l=$L$]";
                Label = "L";
                break;
            case DIODE:
                Text = "to[D,l=$D$]";
                Label = "D";
                break;
            case VOLTAGE_SOURCE:
                Text = "to[V,l=$V$]";
                Label = "V";
                break;
            case CURRENT_SOURCE:
                Text = "to[isource,l=$I$]";
                Label = "I";
                break;
            case GROUND_NODE:
                Text = "node[ground]{}";
                Label = "GND";
                break;
            case VCC_NODE:
                Text = "node[vcc]{}";
                Label = "VCC";
                break;
        }
        componentType = componentSelected;
    }

    public void paint(Graphics g, int gridSize, Point offset, boolean selected, int gridX, int gridY) {
        if (selected) {
            g.setColor(Color.blue);
        } else {
            g.setColor(Color.white);
        }
        g.drawLine(
                gridSize * (wireStart.x + offset.x),
                gridSize * (wireStart.y + offset.y),
                gridSize * (wireEnd.x + offset.x),
                gridSize * (wireEnd.y + offset.y)
        );

        /*
                    this section of code implements the "Draw label to wire" functionality of the circuitmaker, 
                this way the user can always see what the label of any component is while they're working on the 
                schematic. 
                we first calculate the midpoint which is where we place the label. we then fill the background of the string black for visibility 
                then we draw the string
         */
        int fontSize = 10;
        g.setFont(new Font("Dialog", Font.PLAIN, fontSize));
        //find midpoint of the line
        Point mid = new Point(
                ((int) wireStart.getX() * CircuitMaker.GRID_SIZE + (int) wireEnd.getX() * CircuitMaker.GRID_SIZE) / 2,
                ((int) wireStart.getY() * CircuitMaker.GRID_SIZE + (int) wireEnd.getY() * CircuitMaker.GRID_SIZE) / 2
        );
        //calculate width of the string itself
        int stringWidth = g.getFontMetrics().stringWidth(Label);

        int boxPadding = 3;

        //create bounding box for the string
        g.setColor(Color.BLACK);
        g.fillRect((int) mid.getX() + gridX - stringWidth / 2 - boxPadding, (int) mid.getY() + gridY + 2 - fontSize - boxPadding, stringWidth + boxPadding * 2, fontSize + boxPadding * 2);

        if (selected) {
            g.setColor(Color.blue);
        } else {
            g.setColor(Color.white);
        }

        g.setColor(Color.WHITE);
        g.drawRect((int) mid.getX() + gridX - stringWidth / 2 - boxPadding, (int) mid.getY() + gridY + 2 - fontSize - boxPadding, stringWidth + boxPadding * 2, fontSize + boxPadding * 2);
        //draw label string
        g.drawString(Label, (int) mid.getX() + gridX - stringWidth / 2, (int) mid.getY() + gridY + 2);
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
