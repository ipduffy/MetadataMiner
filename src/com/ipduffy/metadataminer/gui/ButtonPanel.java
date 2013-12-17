/*
 * ButtonPanel.java
 *
 * Created on December 10, 2003, 1:21 PM
 */

package com.ipduffy.metadataminer.gui;

/**
 *
 * @author  duffian
 */
import javax.swing.JButton;

public final class ButtonPanel extends javax.swing.JPanel {
    
    /** Creates a new instance of ButtonPanel */
    public ButtonPanel(JButton buttons[]) {
        setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));
        for (int i=0; i<buttons.length; i++) {
            add(buttons[i]);
        }
    }
    
}
