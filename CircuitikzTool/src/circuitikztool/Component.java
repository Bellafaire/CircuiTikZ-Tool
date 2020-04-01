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
    final static int TWO_TERMINAL = 1;
    final static int THREE_TERMINAL = 2;

    public Component(int grid_x, int grid_y, int componentSelected) {
        if (componentSelected == PATH) {
            throw new IllegalArgumentException("Component Selected == Wire but no start/end point is defined");
        }
        componentType = componentSelected;
    }

    public Component(int grid_x_start, int grid_y_start, int grid_x_end, int grid_y_end, int componentSelected) {
        if (componentSelected != PATH) {
            throw new IllegalArgumentException("Component Selected != Wire");
        }
        componentType = componentSelected;
    }

    public Component(Point wireStart, Point wireEnd, int componentSelected) {
        if (componentSelected != PATH) {
            throw new IllegalArgumentException("Component Selected != Wire");
        }
        this.wireStart = wireStart;
        this.wireEnd = wireEnd;
        Text = "to[short]";
        Label = "Wire";
        componentType = componentSelected;
    }

    public void paint(Graphics g, int gridSize, Point offset, boolean selected, int gridX, int gridY) {
        if (selected) {
            g.setColor(Color.blue);
        } else {
            g.setColor(Color.white);
        }

        switch (componentType) {
            case PATH:
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
