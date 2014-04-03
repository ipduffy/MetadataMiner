/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipduffy.metadataminer.newGUI;

/**
 *
 * @author duffian
 */
import com.ipduffy.metadataminer.core.Document;
import java.util.ArrayList;

public class pnlDocumentTable extends javax.swing.JPanel {

    /**
     * Creates new form pnlDocumentTable
     */
    public pnlDocumentTable() {
        initComponents();
    }
    
    public pnlDocumentTable(ArrayList<Document> theDocuments) {
        initComponents();
        //documents.addAll(theDocuments);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        spDocumentTable = new javax.swing.JScrollPane();
        tblDocument = new javax.swing.JTable();
        lblFilename = new javax.swing.JLabel();
        txtFilename = new javax.swing.JTextField();
        lblPath = new javax.swing.JLabel();
        txtPath = new javax.swing.JTextField();
        lblMD5 = new javax.swing.JLabel();
        txtMD5 = new javax.swing.JTextField();

        setLayout(new java.awt.GridBagLayout());

        tblDocument.setColumnSelectionAllowed(true);

        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, new java.util.ArrayList(), tblDocument);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("filename"));
        columnBinding.setColumnName("Filename");
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("title"));
        columnBinding.setColumnName("Title");
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("comments"));
        columnBinding.setColumnName("Comments");
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("company"));
        columnBinding.setColumnName("Company");
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("fullPath"));
        columnBinding.setColumnName("Path");
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("md5"));
        columnBinding.setColumnName("MD5");
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        tblDocument.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDocumentMouseClicked(evt);
            }
        });
        spDocumentTable.setViewportView(tblDocument);
        tblDocument.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(spDocumentTable, gridBagConstraints);

        lblFilename.setText("File Name:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(lblFilename, gridBagConstraints);

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, tblDocument, org.jdesktop.beansbinding.ELProperty.create("${rowCount}"), txtFilename, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(txtFilename, gridBagConstraints);

        lblPath.setText("Path:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(lblPath, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(txtPath, gridBagConstraints);

        lblMD5.setText("MD5 Hash:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(lblMD5, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(txtMD5, gridBagConstraints);

        bindingGroup.bind();
    }// </editor-fold>//GEN-END:initComponents

    private void tblDocumentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDocumentMouseClicked

    }//GEN-LAST:event_tblDocumentMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblFilename;
    private javax.swing.JLabel lblMD5;
    private javax.swing.JLabel lblPath;
    private javax.swing.JScrollPane spDocumentTable;
    private javax.swing.JTable tblDocument;
    private javax.swing.JTextField txtFilename;
    private javax.swing.JTextField txtMD5;
    private javax.swing.JTextField txtPath;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
