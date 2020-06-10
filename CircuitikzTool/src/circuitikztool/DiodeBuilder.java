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
public class DiodeBuilder extends javax.swing.JPanel {

    public final static int EMPTY = 0;
    public final static int SCHOTTKY = 1;
    public final static int ZENER = 2;
    public final static int ZZENER = 3;
    public final static int TUNNEL = 4;
    public final static int PHOTODIODE = 5;
    public final static int LED = 6;
    public final static int VARCAP = 7;
    public final static int BIDIRECTIONAL = 8;

    int diodeType = -1;

    String componentType;

    private void parseLatexParameters(String input) {
        try {
            try {
                componentType = input.substring(input.indexOf("to[") + 3, input.indexOf(","));
            } catch (IndexOutOfBoundsException e) {
                componentType = input.substring(input.indexOf("to[") + 3, input.indexOf("]"));
            }

            if (componentType.toLowerCase().contains("s")) {
                diodeType = SCHOTTKY;
            } else if (componentType.toLowerCase().contains("zz")) {
                diodeType = ZZENER;
            } else if (componentType.toLowerCase().contains("z")) {
                diodeType = ZENER;
            } else if (componentType.toLowerCase().contains("t")) {
                diodeType = TUNNEL;
            } else if (componentType.toLowerCase().contains("p")) {
                diodeType = PHOTODIODE;
            } else if (componentType.toLowerCase().contains("le")) {
                diodeType = LED; 
            } else if (componentType.toLowerCase().contains("vc")) {
                diodeType = LED;
            } else if (componentType.toLowerCase().contains("bi")) {
                diodeType = BIDIRECTIONAL;
            } else {
                diodeType = EMPTY;
                System.out.println("could not determine diode type from " + componentType);
            }

            if (componentType.contains("o")) {
                empty.setSelected(true);
                full.setSelected(false);
                stroke.setSelected(false);
            } else if (componentType.contains("*")) {
                empty.setSelected(false);
                full.setSelected(true);
                stroke.setSelected(false);
            } else if (componentType.contains("-")) {
                empty.setSelected(false);
                full.setSelected(false);
                stroke.setSelected(true);
            } else {
                empty.setSelected(true);
                full.setSelected(false);
                stroke.setSelected(false);
            }

            diodeTypeSelector.setSelectedIndex(diodeType);

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

        switch (diodeTypeSelector.getSelectedIndex()) {
            case EMPTY:
                ret += "D";
                break;
            case SCHOTTKY:
                ret += "sD";
                break;
            case ZENER:
                ret += "zD";
                break;
            case ZZENER:
                ret += "zzD";
                break;
            case TUNNEL:
                ret += "tD";
                break;
            case PHOTODIODE:
                ret += "pD";
                break;
            case LED:
                ret += "leD";
                break;
            case VARCAP:
                ret += "VC";
                break;
            case BIDIRECTIONAL:
                ret += "biD";
                break;
        }

        if (stroke.isSelected()) {
            ret += "-";
        } else if (full.isSelected()) {
            ret += "*";
        } else {
            ret += "o";
        }

        if (!componentLabel.getText().isEmpty()) {
            ret += ",l=" + componentLabel.getText();
        }
        ret += "]";
        return ret;
    }

    /**
     * Creates new form DiodeBuilder
     */
    public DiodeBuilder(String latexParams) {
        initComponents();

        parseLatexParameters(latexParams);

        setBackground(Preferences.themeBackgroundColor);

        jLabel1.setBackground(Preferences.themeBackgroundColor);
        empty.setBackground(Preferences.themeBackgroundColor);
        full.setBackground(Preferences.themeBackgroundColor);
        jLabel2.setBackground(Preferences.themeBackgroundColor);
        jLabel3.setBackground(Preferences.themeBackgroundColor);
        componentLabel.setBackground(Preferences.themeBackgroundColor);
        diodeTypeSelector.setBackground(Preferences.themeBackgroundColor);
        stroke.setBackground(Preferences.themeBackgroundColor);

        jLabel1.setForeground(Preferences.themeText);
        empty.setForeground(Preferences.themeText);
        full.setForeground(Preferences.themeText);
        jLabel2.setForeground(Preferences.themeText);
        jLabel3.setForeground(Preferences.themeText);
        componentLabel.setForeground(Preferences.themeText);
        diodeTypeSelector.setForeground(Preferences.themeText);
        stroke.setForeground(Preferences.themeText);

        componentLabel.setCaretColor(Preferences.themeText);

        setSize(320, 72);

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
        empty = new javax.swing.JRadioButton();
        full = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        componentLabel = new javax.swing.JTextField();
        diodeTypeSelector = new javax.swing.JComboBox<>();
        stroke = new javax.swing.JRadioButton();

        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(320, 75));

        jLabel1.setText("Fill");

        empty.setText("empty");
        empty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emptyActionPerformed(evt);
            }
        });

        full.setText("full");
        full.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fullActionPerformed(evt);
            }
        });

        jLabel2.setText("Diode Type");

        jLabel3.setText("Label");

        diodeTypeSelector.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Silicon", "Schottky", "Zener", "ZZener", "Tunnel", "Photo", "LED", "Varcap", "Bi-Directional" }));

        stroke.setText("stroke");
        stroke.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                strokeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(componentLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(empty)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(full)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(stroke)
                                .addGap(0, 119, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(diodeTypeSelector, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(componentLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(empty)
                    .addComponent(full)
                    .addComponent(stroke))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(diodeTypeSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void emptyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emptyActionPerformed
        full.setSelected(false);
        stroke.setSelected(false);
    }//GEN-LAST:event_emptyActionPerformed

    private void fullActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fullActionPerformed
        empty.setSelected(false);
        stroke.setSelected(false);
    }//GEN-LAST:event_fullActionPerformed

    private void strokeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_strokeActionPerformed
        empty.setSelected(false);
        full.setSelected(false);
    }//GEN-LAST:event_strokeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField componentLabel;
    private javax.swing.JComboBox<String> diodeTypeSelector;
    private javax.swing.JRadioButton empty;
    private javax.swing.JRadioButton full;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JRadioButton stroke;
    // End of variables declaration//GEN-END:variables
}
