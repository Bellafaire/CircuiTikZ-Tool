/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitikztool;

/**
 *
 * @author James
 */
public class VoltageSourceBuilder extends javax.swing.JPanel {

    public final static int VOLTAGE = 0;
    public final static int CONTROLLED_VOLTAGE = 1;
    public final static int SIN_VOLTAGE = 2;
    public final static int CONTROLLED_SIN_VOLTAGE = 3;
    public final static int SQUARE_VOLTAGE = 4;
    public final static int TRIANGLE_VOLTAGE = 5;
    public final static int PHOTOVOLTAIC = 6;
    public final static int BATTERY = 7;
    public final static int SINGLE_CELL_1 = 8;
    public final static int SINGLE_CELL_2 = 9;

    int sourceType = -1;

    String componentType;

    private void parseLatexParameters(String input) {
        try {
            try {
                componentType = input.substring(input.indexOf("to[") + 3, input.indexOf(","));
            } catch (IndexOutOfBoundsException e) {
                componentType = input.substring(input.indexOf("to[") + 3, input.indexOf("]"));
            }
            //for the standard sources we need to handle both the american and the european versions of the sources. we'll
            //we'll decide which to output depending on the user's selection of american or european style components;
            if (componentType.toLowerCase().contains("battery2")) {
                sourceType = SINGLE_CELL_2;
            } else if (componentType.toLowerCase().contains("cv")) {
                sourceType = CONTROLLED_VOLTAGE;
            } else if (componentType.toLowerCase().contains("csv")) {
                sourceType = CONTROLLED_SIN_VOLTAGE;
            } else if (componentType.toLowerCase().contains("sv")) {
                sourceType = SIN_VOLTAGE;
            } else if (componentType.toLowerCase().contains("sqv")) {
                sourceType = SQUARE_VOLTAGE;
            } else if (componentType.toLowerCase().contains("tv")) {
                sourceType = TRIANGLE_VOLTAGE;
            } else if (componentType.toLowerCase().contains("pvsource")) {
                sourceType = PHOTOVOLTAIC;
            } else if (componentType.toLowerCase().contains("battery1")) {
                sourceType = SINGLE_CELL_1;
            } else if (componentType.toLowerCase().contains("battery")) {
                sourceType = BATTERY;
            } else if (componentType.toLowerCase().contains("v")) {
                sourceType = VOLTAGE;
            } else {
                System.out.println("Could not determine source type from: " + input);
            }

            sourceSelector.setSelectedIndex(sourceType);
            componentLabel.setText(getParameter(input, "l"));
        } catch (Exception e) {
            System.out.println("Failed to parse diode component in parseLatexParameters string: " + input + " could not be parsed" + e.getMessage());
        }
    }

    private String getParameter(String text, String parameter) {
        String cleaned = text.replace("to[", "").replace("]", "");
        String[] params = cleaned.split(",");
        for (int a = 0; a < params.length; a++) {
            if (params[a].contains(parameter + "=")) {
                return params[a].replace(parameter + "=", "");
            }
        }
        return "";
    }

    public String getLatexParameters() {
        String ret = "to[";
        switch (sourceSelector.getSelectedIndex()) {
            case VOLTAGE:
                ret += "V";
                break;
            case CONTROLLED_VOLTAGE:
                ret += "cV";
                break;
            //           public final static int SIN_VOLTAGE = 2;
            case SIN_VOLTAGE:
                ret += "sV";
                break;
            //    public final static int CONTROLLED_SIN_VOLTAGE = 3;
            case CONTROLLED_SIN_VOLTAGE:
                ret += "csV";
                break;
            //    public final static int SQUARE_VOLTAGE = 4;
            case SQUARE_VOLTAGE:
                ret += "sqV";
                break;
            //    public final static int TRIANGLE_VOLTAGE = 5;
            case TRIANGLE_VOLTAGE:
                ret += "tV";
                break;
            //    public final static int PHOTOVOLTAIC = 6;
            case PHOTOVOLTAIC:
                ret += "pvsource";
                break;
            //    public final static int BATTERY = 7;
            case BATTERY:
                ret += "battery";
                break;
            //    public final static int SINGLE_CELL_1 = 8;
            case SINGLE_CELL_1:
                ret += "battery1";
                break;
            //    public final static int SINGLE_CELL_2 = 9;
            case SINGLE_CELL_2:
                ret += "battery2";
                break;
            default:
                ret += "V";
                System.out.println("Defaulting voltage source to standard");
                break;
        }
        if (!componentLabel.getText().isEmpty()) {
            ret += ",l=" + componentLabel.getText();
        }
        ret += "]";
        return ret;
    }

    /**
     * Creates new form VoltageSourceBuilder
     */
    public VoltageSourceBuilder(String latexParams) {
        initComponents();

        parseLatexParameters(latexParams);

        setBackground(Preferences.backgroundColor);
        componentLabel.setBackground(Preferences.themeAccent);
        jLabel1.setBackground(Preferences.backgroundColor);
        jLabel2.setBackground(Preferences.backgroundColor);
        sourceSelector.setBackground(Preferences.themeAccent);

        componentLabel.setForeground(Preferences.themeText);
        jLabel1.setForeground(Preferences.themeText);
        jLabel2.setForeground(Preferences.themeText);
        sourceSelector.setForeground(Preferences.themeText);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        componentLabel = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        sourceSelector = new javax.swing.JComboBox<>();

        setPreferredSize(new java.awt.Dimension(320, 50));

        jLabel1.setText("Label");

        jLabel2.setText("Source Type");

        sourceSelector.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Voltage Source", "Controlled Voltage Source", "Sinusoidal Voltage Source", "Controlled Sinusoidal Voltage Source", "Square Voltage Source", "Triangle Voltage Source", "Photovoltaic Source", "Battery", "Single Cell Battery", "Single Cell Battery 2 " }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(componentLabel))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sourceSelector, 0, 256, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(componentLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(sourceSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField componentLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JComboBox<String> sourceSelector;
    // End of variables declaration//GEN-END:variables
}
