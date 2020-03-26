package circuitikztool;

import java.awt.Point;
import java.awt.image.BufferedImage;

public class Component implements java.io.Serializable {
    
    private int TERMINALS; 
    int x, y; 
    Point[] connectionPoints; 
    BufferedImage Icon; 
    String name; 
    
    
    final static int WIRE = 0; 
//    final static int RESISTOR =1; 
//    final static int CAPACITOR = 2; 
//    final static int INDUCTOR = 3; 
//    final static int VOLTAGE = 4; 
//    final static int CURRENT = 5;
//    final static int GROUND = 6; 
//    final static int VCC = 7;
//    final static int DIODE = 8;
//    final static int BATTERY = 9;
//    final static int TRANSISTOR = 10;

    final static int TWO_TERMINAL = 1; 
    final static int THREE_TERMINAL = 2;
    
    public Component(int x, int y, int componentSelected){
        
    }
    
    
    
}
