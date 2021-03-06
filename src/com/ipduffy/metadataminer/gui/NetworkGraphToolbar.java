/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipduffy.metadataminer.gui;

import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;

/**
 *
 * @author iduffy
 */
public class NetworkGraphToolbar extends javax.swing.JPanel {

    DefaultModalGraphMouse gm;
            
    /**
     * Creates new form NetworkGraphToolbar
     */
    public NetworkGraphToolbar(DefaultModalGraphMouse gm) {
        initComponents();
        this.gm = gm;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        btnTransform = new javax.swing.JButton();
        btnSelect = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        jToolBar1.setRollover(true);

        btnTransform.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ipduffy/metadataminer/images/hand.png"))); // NOI18N
        btnTransform.setText("Transform");
        btnTransform.setFocusable(false);
        btnTransform.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnTransform.setSelected(true);
        btnTransform.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnTransform.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTransformActionPerformed(evt);
            }
        });
        jToolBar1.add(btnTransform);

        btnSelect.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ipduffy/metadataminer/images/cursor.png"))); // NOI18N
        btnSelect.setText("Select");
        btnSelect.setToolTipText("Allows individual nodes to be selected / moved / edited");
        btnSelect.setFocusable(false);
        btnSelect.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSelect.setMaximumSize(new java.awt.Dimension(69, 38));
        btnSelect.setMinimumSize(new java.awt.Dimension(69, 38));
        btnSelect.setPreferredSize(new java.awt.Dimension(69, 38));
        btnSelect.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectActionPerformed(evt);
            }
        });
        jToolBar1.add(btnSelect);

        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ipduffy/metadataminer/images/find.png"))); // NOI18N
        btnSearch.setText("Search");
        btnSearch.setFocusable(false);
        btnSearch.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSearch.setMaximumSize(new java.awt.Dimension(69, 38));
        btnSearch.setMinimumSize(new java.awt.Dimension(69, 38));
        btnSearch.setPreferredSize(new java.awt.Dimension(69, 38));
        btnSearch.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });
        jToolBar1.add(btnSearch);

        add(jToolBar1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectActionPerformed
        gm.setMode(ModalGraphMouse.Mode.PICKING);
        btnSelect.setSelected(true);
        btnTransform.setSelected(false);
    }//GEN-LAST:event_btnSelectActionPerformed

    private void btnTransformActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransformActionPerformed
        gm.setMode(ModalGraphMouse.Mode.TRANSFORMING);
        btnTransform.setSelected(true);
        btnSelect.setSelected(false);
    }//GEN-LAST:event_btnTransformActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSearchActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSelect;
    private javax.swing.JButton btnTransform;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
}
