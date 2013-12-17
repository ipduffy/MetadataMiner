/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * mainPanel.java
 *
 * Created on May 14, 2011, 1:39:06 PM
 */

package com.ipduffy.metadataminer.gui;


import java.awt.Cursor;
import java.io.*;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import com.ipduffy.metadataminer.dao.*;

/**
 *
 * @author duffian
 */
public class mainPanel extends javax.swing.JPanel implements DBConnectionListener {

    private boolean isConnected = false;
    private File mWorkingDirectory = null;
    private Cursor mWaitCursor = new Cursor(Cursor.WAIT_CURSOR);
    private Cursor mDefaultCursor = new Cursor(Cursor.DEFAULT_CURSOR);

    /** Creates new form mainPanel */
    public mainPanel() {
        initComponents();
        DBConnectionManager.addConnectionListener(this);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        lblWorkingDir = new javax.swing.JLabel();
        txtWorkingDir = new javax.swing.JTextField();
        btnGo = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        lblWorkingDir.setText("Working Directory:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(lblWorkingDir, gridBagConstraints);

        txtWorkingDir.setEditable(false);
        txtWorkingDir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtWorkingDirMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(txtWorkingDir, gridBagConstraints);

        btnGo.setText("Go");
        btnGo.setEnabled(false);
        btnGo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(btnGo, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void txtWorkingDirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtWorkingDirMouseClicked
        if (isConnected) {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int returnVal = chooser.showOpenDialog(this);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                if (chooser.getSelectedFile() != null) {
                    mWorkingDirectory = chooser.getSelectedFile();
                    txtWorkingDir.setText(mWorkingDirectory.getAbsolutePath());
                }
            }
        }
    }//GEN-LAST:event_txtWorkingDirMouseClicked

    private void btnGoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGoActionPerformed
        // Set the glasspane active and waitcursor active
        this.getRootPane().getGlassPane().setVisible(true);
        this.setCursor(mWaitCursor);

        if (mWorkingDirectory != null) {
            ParserThread theThread = new ParserThread(mWorkingDirectory);
            theThread.start();
        } else {
            JOptionPane.showMessageDialog(this, "Please choose a valid directory", "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Set the cursors back to their original state
        this.getRootPane().getGlassPane().setVisible(false);
        this.setCursor(mDefaultCursor);
    }//GEN-LAST:event_btnGoActionPerformed

    public void connected(boolean connected) {
        isConnected = connected;
        txtWorkingDir.setEditable(connected);
        btnGo.setEnabled(connected);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGo;
    private javax.swing.JLabel lblWorkingDir;
    private javax.swing.JTextField txtWorkingDir;
    // End of variables declaration//GEN-END:variables

}
