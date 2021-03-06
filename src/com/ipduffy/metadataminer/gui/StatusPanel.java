/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * StatusPanel.java
 *
 * Created on Jun 1, 2011, 8:09:38 PM
 */

package com.ipduffy.metadataminer.gui;

import com.ipduffy.metadataminer.dao.*;
import com.ipduffy.metadataminer.parsers.*;
/**
 *
 * @author duffian
 */
public class StatusPanel extends javax.swing.JPanel implements DBConnectionListener, ParserListener {

    /** Creates new form StatusPanel */
    public StatusPanel() {
        initComponents();

        DBConnectionManager.addConnectionListener(this);
        ParserManager.addParserListener(this);
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

        lblParsedFilesTitle = new javax.swing.JLabel();
        lblParsedFileCount = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createTitledBorder("Current Working Set"));
        setLayout(new java.awt.GridBagLayout());

        lblParsedFilesTitle.setText("Parsed Files:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(lblParsedFilesTitle, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(lblParsedFileCount, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents


    public void connected(boolean connected) {
        if (connected) {
            updateLabel();
        }
    }

    public void parsingBegan() {
        //System.out.println("Status Panel notified that parsing began.");
    }

    public void parsingEnded() {
        //System.out.println("Status panel notified that parsing ended.");
        updateLabel();
    }

    private void updateLabel() {
        try {
            QueryFactory theQueryFactory = DBConnectionManager.getQueryFactory();
            if (theQueryFactory != null) {
                long theDocCount = theQueryFactory.getParsedDocumentCount();
                lblParsedFileCount.setText(Long.toString(theDocCount));
            }
        } catch (Exception e) {
            System.out.println("Error getting QueryFactory: " + e.toString());
            e.printStackTrace();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblParsedFileCount;
    private javax.swing.JLabel lblParsedFilesTitle;
    // End of variables declaration//GEN-END:variables

}
