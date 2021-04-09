/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitikztool;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

/**
 *
 * @author James
 */
public class LatexStringBuilder extends javax.swing.JDialog {

    public static final Component RET_CANCEL = new Component(Component.CANCEL);
    public static final Component RET_DELETE = new Component(Component.DELETE);

    PathComponentBuilder pathMenu;
    DiodeBuilder diodeMenu;
    VoltageSourceBuilder voltageMenu;
    CurrentSourceBuilder currentMenu;

    Component inputComponent;

    Executor fieldUpdater = Executors.newSingleThreadExecutor();

    /**
     * Creates new form LatexStringBuilder
     */
    public LatexStringBuilder(java.awt.Frame parent, boolean modal, Component com) {
        super(parent, modal);
        initComponents();

        getContentPane().setBackground(Preferences.themeBackgroundColor);

        Done.setForeground(Preferences.themeText);
        cancel.setForeground(Preferences.themeText);
        delete.setForeground(Preferences.themeText);
//        info.setForeground(Preferences.themeText);
        jFormattedTextField1.setForeground(Preferences.themeText);
        jLabel1.setForeground(Preferences.themeText);
        jLabel2.setForeground(Preferences.themeText);
//        jScrollPane1.setForeground(Preferences.themeText);
        label.setForeground(Preferences.themeText);
        latexString.setForeground(Preferences.themeText);

        inputComponent = com;
        latexString.setText(inputComponent.getLatexString());
        label.setText(inputComponent.getComponentLabel());

        customizationPanel.setBackground(Preferences.themeBackgroundColor);

//        if (inputComponent.isPathComponent() && inputComponent.componentType != Component.PATH) {
        //if it's a path component then we need to generate all the customization options and create an interface to allow the user to access them.
        switch (inputComponent.componentType) {
            case Component.PATH:
            case Component.RESISTOR:
            case Component.CAPACITOR:
            case Component.INDUCTOR:
                pathMenu = new PathComponentBuilder(inputComponent.latexParameters);
                customizationPanel.setLayout(new BorderLayout());
                customizationPanel.add(pathMenu);
                customizationPanel.setSize(pathMenu.getWidth(), pathMenu.getHeight());
                this.setSize(400, 350);
                pathMenu.setVisible(true);
                break;
            case Component.DIODE:
                diodeMenu = new DiodeBuilder(inputComponent.latexParameters);
                customizationPanel.setLayout(new BorderLayout());
                customizationPanel.add(diodeMenu);
                customizationPanel.setSize(diodeMenu.getWidth(), diodeMenu.getHeight());
                this.setSize(400, 275);
                diodeMenu.setVisible(true);
                break;
            case Component.VOLTAGE_SOURCE:
                voltageMenu = new VoltageSourceBuilder(inputComponent.latexParameters);
                customizationPanel.setLayout(new BorderLayout());
                customizationPanel.add(voltageMenu);
                customizationPanel.setSize(voltageMenu.getWidth(), voltageMenu.getHeight());
                this.setSize(400, 250);
                break;
            case Component.CURRENT_SOURCE:
                currentMenu = new CurrentSourceBuilder(inputComponent.latexParameters);
                customizationPanel.setLayout(new BorderLayout());
                customizationPanel.add(currentMenu);
                customizationPanel.setSize(currentMenu.getWidth(), currentMenu.getHeight());
                this.setSize(400, 250);
                break;
            default:
                latexString.setEditable(true);
                this.setSize(400,180);
                break;
        }

//        }
//        if (inputComponent.isPathComponent()) {
//            info.setText("Path Component CircuiTikz Reference\n"
//                    + "l= - label assigned to component\n"
//                    + "v= - labels voltage across component\n"
//                    + "i= - labels current through component\n"
//                    + "f= - labels arrow parallel to component\n"
//                    + "*- - adds \"connection\" dots at start of path\n"
//                    + "-* - adds \"connection\" dots at end of path\n"
//                    + "*-* - adds \"connection\" dots to both ends of path");
//        } else if (inputComponent.componentType == Component.TRANSFORMER || inputComponent.componentType == Component.TRANSFORMER_WITH_CORE) {
//            info.setText("Transformer Information\n"
//                    + "Adding Dot Convention Markers: \n"
//                    + "add (T" + inputComponent.getDeviceID() + ".inner dot A1) node[circ]{} for upper left dot\n"
//                    + "add (T" + inputComponent.getDeviceID() + ".inner dot A2) node[circ]{} for lower left dot\n"
//                    + "add (T" + inputComponent.getDeviceID() + ".inner dot B1) node[circ]{} for upper right dot\n"
//                    + "add (T" + inputComponent.getDeviceID() + ".inner dot B2) node[circ]{} for lower right dot\n"
//                    + "\n"
//                    + "Adding K Turns ratio: \n"
//                    + "add (T" + inputComponent.getDeviceID() + ".base) node{*turns ratio here*}");
//        } else {
//            info.setText("");
//        }
//        int lineCount = 1;
//        for (int a = 0; a < info.getText().length(); a++) {
//            if (info.getText().charAt(a) == '\n') {
//                lineCount++;
//            }
//        }
//        this.setSize(this.getWidth(), this.getHeight() + 15 * lineCount);
        // Close the dialog when Esc is pressed
        String cancelName = "cancel";
        InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), cancelName);
        ActionMap actionMap = getRootPane().getActionMap();
        actionMap.put(cancelName, new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                doClose(RET_CANCEL);
            }
        });
    }

    /**
     * @return the return status of this dialog - one of RET_OK or RET_CANCEL
     */
    public Component getReturnStatus() {
        return returnStatus;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        label = new javax.swing.JTextField();
        delete = new javax.swing.JButton();
        cancel = new javax.swing.JButton();
        Done = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        latexString = new javax.swing.JEditorPane();
        customizationPanel = new javax.swing.JPanel();

        jFormattedTextField1.setText("jFormattedTextField1");

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                formKeyTyped(evt);
            }
        });

        jLabel1.setText("LaTeX String");

        jLabel2.setText("Label ");

        label.setBackground(Preferences.themeAccent);
        label.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                labelCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        label.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                labelKeyTyped(evt);
            }
        });

        delete.setBackground(Preferences.themeAccent);
        delete.setText("Delete");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });

        cancel.setBackground(Preferences.themeAccent);
        cancel.setText("Cancel");
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });

        Done.setBackground(Preferences.themeAccent);
        Done.setText("Done");
        Done.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DoneActionPerformed(evt);
            }
        });

        latexString.setEditable(false);
        latexString.setBackground(Preferences.themeAccent );
        latexString.setAutoscrolls(false);
        latexString.setCaretColor(Preferences.themeText);
        latexString.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                latexStringFocusGained(evt);
            }
        });
        latexString.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                latexStringCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        jScrollPane2.setViewportView(latexString);

        customizationPanel.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                customizationPanelCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        customizationPanel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                customizationPanelKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout customizationPanelLayout = new javax.swing.GroupLayout(customizationPanel);
        customizationPanel.setLayout(customizationPanelLayout);
        customizationPanelLayout.setHorizontalGroup(
            customizationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        customizationPanelLayout.setVerticalGroup(
            customizationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 107, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(customizationPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Done, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label)
                            .addComponent(jScrollPane2))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(customizationPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(delete)
                    .addComponent(cancel)
                    .addComponent(Done))
                .addGap(10, 10, 10))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Closes the dialog
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        doClose(RET_CANCEL);
    }//GEN-LAST:event_closeDialog

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        doClose(RET_DELETE);
    }//GEN-LAST:event_deleteActionPerformed

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
        doClose(RET_CANCEL);
    }//GEN-LAST:event_cancelActionPerformed

    private void DoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DoneActionPerformed
        doClose(inputComponent);
    }//GEN-LAST:event_DoneActionPerformed

    private void labelCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_labelCaretPositionChanged
        System.out.println("teest");
    }//GEN-LAST:event_labelCaretPositionChanged

    private void labelKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_labelKeyTyped
        updateLabelAndString();
    }//GEN-LAST:event_labelKeyTyped

    private void formKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyTyped

    }//GEN-LAST:event_formKeyTyped

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed

    }//GEN-LAST:event_formKeyPressed

    private void customizationPanelKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_customizationPanelKeyTyped

    }//GEN-LAST:event_customizationPanelKeyTyped

    private void customizationPanelCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_customizationPanelCaretPositionChanged

    }//GEN-LAST:event_customizationPanelCaretPositionChanged

    private void latexStringFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_latexStringFocusGained

    }//GEN-LAST:event_latexStringFocusGained

    private void latexStringCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_latexStringCaretPositionChanged

    }//GEN-LAST:event_latexStringCaretPositionChanged

    private void updateLabelAndString() {
        inputComponent.setLatexString(latexString.getText());
        inputComponent.setComponentLabel(label.getText());
    }

    private void doClose(Component retObj) {
        getCurrentComponentLatex();
        updateLabelAndString();
        returnStatus = retObj;
        setVisible(false);
        dispose();
    }

    public void getCurrentComponentLatex() {
        if (inputComponent.componentType == Component.PATH
                || inputComponent.componentType == Component.RESISTOR
                || inputComponent.componentType == Component.CAPACITOR
                || inputComponent.componentType == Component.INDUCTOR) {
            latexString.setText(pathMenu.getLatexParameters());
        } else if (inputComponent.componentType == Component.DIODE) {
            latexString.setText(diodeMenu.getLatexParameters());
        } else if (inputComponent.componentType == Component.VOLTAGE_SOURCE) {
            latexString.setText(voltageMenu.getLatexParameters());
        } else if (inputComponent.componentType == Component.CURRENT_SOURCE) {
            latexString.setText(currentMenu.getLatexParameters());
        }
    }

    /**
     * @param args the command line arguments
     */
    public void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LatexStringBuilder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LatexStringBuilder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LatexStringBuilder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LatexStringBuilder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                LatexStringBuilder dialog = new LatexStringBuilder(new javax.swing.JFrame(), true, inputComponent);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Done;
    private javax.swing.JButton cancel;
    private javax.swing.JPanel customizationPanel;
    private javax.swing.JButton delete;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField label;
    private javax.swing.JEditorPane latexString;
    // End of variables declaration//GEN-END:variables

    private Component returnStatus = RET_CANCEL;
}
