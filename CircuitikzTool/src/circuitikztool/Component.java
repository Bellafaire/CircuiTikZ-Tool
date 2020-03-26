package circuitikztool;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class Component {

    int x, y;
    Point[] connectionPoints;
    BufferedImage Icon;
    String name;

    final static int WIRE = 0;
    final static int TWO_TERMINAL = 1;
    final static int THREE_TERMINAL = 2;

    public Component(int grid_x, int grid_y, int componentSelected) {
                if(componentSelected == WIRE){
            throw new IllegalArgumentException("Component Selected == Wire but no start/end point is defined");
        }
    }

    public Component(int grid_x_start, int grid_y_start, int grid_x_end, int grid_y_end, int componentSelected) {
        if(componentSelected != WIRE){
            throw new IllegalArgumentException("Component Selected != Wire");
        }
    }
    
       public void paint(Graphics g, int gridSize) {
       
       }

}
