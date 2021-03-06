/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DocumentInfoPanel.java
 *
 * Created on Jun 27, 2011, 8:39:01 PM
 */

package com.ipduffy.metadataminer.gui;


import com.ipduffy.metadataminer.core.*;
import com.ipduffy.metadataminer.dao.*;
import com.ipduffy.metadataminer.table.DuplicateTableModel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.table.TableColumn;

/**
 *
 * @author duffian
 */
public class DocumentInfoPanel extends javax.swing.JPanel {

    private DuplicateTableModel tblModel = null;

    /** Creates new form DocumentInfoPanel */
    public DocumentInfoPanel(Document aDocument) {

        if (aDocument == null) {
            throw new NullPointerException("Null Document passed to DocumentInfoPanel.");
        }

        try {
            QueryFactory theQueryFactory = DBConnectionManager.getQueryFactory();
            ArrayList theDuplicates = theQueryFactory.getDuplicates(aDocument.getId());
            tblModel = new DuplicateTableModel(theDuplicates);
        } catch (Exception e) {
            System.out.println("Error getting duplicate file information: " + e.toString());
            e.printStackTrace();
        }

        initComponents();

        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");

        if (aDocument != null) {
            txtFilename.setText(aDocument.getFilename());
            txtFilename.setToolTipText(aDocument.getFilename());

            txtPath.setText(aDocument.getFullPath());
            txtPath.setToolTipText(aDocument.getFullPath());

            txtTitle.setText(aDocument.getTitle());
            txtTitle.setToolTipText(aDocument.getTitle());

            txtComments.setText(aDocument.getComments());
            txtComments.setToolTipText(aDocument.getComments());

            txtCompany.setText(aDocument.getCompany());
            txtCompany.setToolTipText(aDocument.getCompany());

            txtManager.setText(aDocument.getManager());
            txtManager.setToolTipText(aDocument.getManager());

            txtCategory.setText(aDocument.getCategory());
            txtCategory.setToolTipText(aDocument.getCategory());

            txtMD5.setText(aDocument.getMd5());
            txtMD5.setToolTipText(aDocument.getMd5());

            txtRevision.setText(aDocument.getRevisionString());
            txtRevision.setToolTipText(aDocument.getRevisionString());

            txtEditingTime.setText(getEditTimeString(aDocument));
            txtEditingTime.setToolTipText(getEditTimeString(aDocument));

            txtApplication.setText(aDocument.getApplication());
            txtApplication.setToolTipText(aDocument.getApplication());

            txtWordCount.setText(Long.toString(aDocument.getWordCount()));
            txtWordCount.setToolTipText(Long.toString(aDocument.getWordCount()));

            txtPageCount.setText(Long.toString(aDocument.getPageCount()));
            txtPageCount.setToolTipText(Long.toString(aDocument.getPageCount()));

            txtHidden.setText(Long.toString(aDocument.getHiddenCount()));
            txtHidden.setToolTipText(Long.toString(aDocument.getHiddenCount()));

            if (aDocument.getCreationDate() != null) {
                lblDateCreatedValue.setText(sdf.format(aDocument.getCreationDate()));
            }

            if (aDocument.getLastPrintDate() != null) {
                lblLastPrintedValue.setText(sdf.format(aDocument.getLastPrintDate()));
            }

            if (aDocument.getLastSaveDate() != null) {
                lblLastSavedValue.setText(sdf.format(aDocument.getLastSaveDate()));
            }

            if (aDocument.getAuthor() != null) {
                Author theAuthor = aDocument.getAuthor();
                txtAuthor.setText(theAuthor.getAuthorName());
                txtAuthor.setToolTipText(theAuthor.getAuthorName());
            }

            if (aDocument.getEditor() != null) {
                Author theEditor = aDocument.getEditor();
                txtEditor.setText(theEditor.getAuthorName());
                txtEditor.setToolTipText(theEditor.getAuthorName());
            }
        }

        // Set initial table column widths for the Duplicates Table
        tblDuplicates.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        TableColumn column = tblDuplicates.getColumnModel().getColumn(0);
        column.setMinWidth(25);
        column.setMaxWidth(200);
        column.setPreferredWidth(150);
    }

    private String getEditTimeString(Document aDocument) {
        if (aDocument == null) return null;
        long theEditingTime = aDocument.getEditingTime();
        String theString = Long.toString(theEditingTime);
        return theString + " min";
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

        lblFilename = new javax.swing.JLabel();
        txtFilename = new javax.swing.JTextField();
        lblPath = new javax.swing.JLabel();
        txtPath = new javax.swing.JTextField();
        lblTitle = new javax.swing.JLabel();
        txtTitle = new javax.swing.JTextField();
        lblComments = new javax.swing.JLabel();
        txtComments = new javax.swing.JTextField();
        lblCompany = new javax.swing.JLabel();
        txtCompany = new javax.swing.JTextField();
        lblManager = new javax.swing.JLabel();
        txtManager = new javax.swing.JTextField();
        lblApplication = new javax.swing.JLabel();
        txtApplication = new javax.swing.JTextField();
        lblCategory = new javax.swing.JLabel();
        txtCategory = new javax.swing.JTextField();
        lblMD5 = new javax.swing.JLabel();
        txtMD5 = new javax.swing.JTextField();
        lblRevision = new javax.swing.JLabel();
        txtRevision = new javax.swing.JTextField();
        lblEditingTime = new javax.swing.JLabel();
        txtEditingTime = new javax.swing.JTextField();
        lblWordCount = new javax.swing.JLabel();
        txtWordCount = new javax.swing.JTextField();
        lblPageCount = new javax.swing.JLabel();
        txtPageCount = new javax.swing.JTextField();
        lblHidden = new javax.swing.JLabel();
        txtHidden = new javax.swing.JTextField();
        lblDateCreated = new javax.swing.JLabel();
        lblDateCreatedValue = new javax.swing.JLabel();
        lblLastSaved = new javax.swing.JLabel();
        lblLastSavedValue = new javax.swing.JLabel();
        lblLastPrinted = new javax.swing.JLabel();
        lblLastPrintedValue = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        lblAuthor = new javax.swing.JLabel();
        txtAuthor = new javax.swing.JTextField();
        lblEditor = new javax.swing.JLabel();
        txtEditor = new javax.swing.JTextField();
        scrlPaneDuplicates = new javax.swing.JScrollPane();
        tblDuplicates = new javax.swing.JTable();
        lblDuplicates = new javax.swing.JLabel();

        setLayout(new java.awt.GridBagLayout());

        lblFilename.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblFilename.setText("Filename:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(lblFilename, gridBagConstraints);

        txtFilename.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(txtFilename, gridBagConstraints);

        lblPath.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPath.setText("Path:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(lblPath, gridBagConstraints);

        txtPath.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(txtPath, gridBagConstraints);

        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTitle.setText("Title:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(lblTitle, gridBagConstraints);

        txtTitle.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(txtTitle, gridBagConstraints);

        lblComments.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblComments.setText("Comments:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(lblComments, gridBagConstraints);

        txtComments.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(txtComments, gridBagConstraints);

        lblCompany.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCompany.setText("Company:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(lblCompany, gridBagConstraints);

        txtCompany.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(txtCompany, gridBagConstraints);

        lblManager.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblManager.setText("Manager:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(lblManager, gridBagConstraints);

        txtManager.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(txtManager, gridBagConstraints);

        lblApplication.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblApplication.setText("Application:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(lblApplication, gridBagConstraints);

        txtApplication.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(txtApplication, gridBagConstraints);

        lblCategory.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCategory.setText("Category:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(lblCategory, gridBagConstraints);

        txtCategory.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(txtCategory, gridBagConstraints);

        lblMD5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblMD5.setText("MD5:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(lblMD5, gridBagConstraints);

        txtMD5.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(txtMD5, gridBagConstraints);

        lblRevision.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblRevision.setText("Revision:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(lblRevision, gridBagConstraints);

        txtRevision.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(txtRevision, gridBagConstraints);

        lblEditingTime.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblEditingTime.setText("Editing Time:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(lblEditingTime, gridBagConstraints);

        txtEditingTime.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(txtEditingTime, gridBagConstraints);

        lblWordCount.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblWordCount.setText("Word Count:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(lblWordCount, gridBagConstraints);

        txtWordCount.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(txtWordCount, gridBagConstraints);

        lblPageCount.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPageCount.setText("Page Count:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(lblPageCount, gridBagConstraints);

        txtPageCount.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(txtPageCount, gridBagConstraints);

        lblHidden.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblHidden.setText("Hidden Chars:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(lblHidden, gridBagConstraints);

        txtHidden.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(txtHidden, gridBagConstraints);

        lblDateCreated.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDateCreated.setText("Date Created:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(lblDateCreated, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(lblDateCreatedValue, gridBagConstraints);

        lblLastSaved.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblLastSaved.setText("Last Saved:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(lblLastSaved, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(lblLastSavedValue, gridBagConstraints);

        lblLastPrinted.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblLastPrinted.setText("Last Printed:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(lblLastPrinted, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(lblLastPrintedValue, gridBagConstraints);

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator1.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(jSeparator1, gridBagConstraints);

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator2.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(jSeparator2, gridBagConstraints);

        lblAuthor.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblAuthor.setText("Authored By:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(lblAuthor, gridBagConstraints);

        txtAuthor.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(txtAuthor, gridBagConstraints);

        lblEditor.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblEditor.setText("Last Edited By:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(lblEditor, gridBagConstraints);

        txtEditor.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(txtEditor, gridBagConstraints);

        tblDuplicates.setModel(tblModel);
        scrlPaneDuplicates.setViewportView(tblDuplicates);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(scrlPaneDuplicates, gridBagConstraints);

        lblDuplicates.setText("Duplicate Files:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(lblDuplicates, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblApplication;
    private javax.swing.JLabel lblAuthor;
    private javax.swing.JLabel lblCategory;
    private javax.swing.JLabel lblComments;
    private javax.swing.JLabel lblCompany;
    private javax.swing.JLabel lblDateCreated;
    private javax.swing.JLabel lblDateCreatedValue;
    private javax.swing.JLabel lblDuplicates;
    private javax.swing.JLabel lblEditingTime;
    private javax.swing.JLabel lblEditor;
    private javax.swing.JLabel lblFilename;
    private javax.swing.JLabel lblHidden;
    private javax.swing.JLabel lblLastPrinted;
    private javax.swing.JLabel lblLastPrintedValue;
    private javax.swing.JLabel lblLastSaved;
    private javax.swing.JLabel lblLastSavedValue;
    private javax.swing.JLabel lblMD5;
    private javax.swing.JLabel lblManager;
    private javax.swing.JLabel lblPageCount;
    private javax.swing.JLabel lblPath;
    private javax.swing.JLabel lblRevision;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblWordCount;
    private javax.swing.JScrollPane scrlPaneDuplicates;
    private javax.swing.JTable tblDuplicates;
    private javax.swing.JTextField txtApplication;
    private javax.swing.JTextField txtAuthor;
    private javax.swing.JTextField txtCategory;
    private javax.swing.JTextField txtComments;
    private javax.swing.JTextField txtCompany;
    private javax.swing.JTextField txtEditingTime;
    private javax.swing.JTextField txtEditor;
    private javax.swing.JTextField txtFilename;
    private javax.swing.JTextField txtHidden;
    private javax.swing.JTextField txtMD5;
    private javax.swing.JTextField txtManager;
    private javax.swing.JTextField txtPageCount;
    private javax.swing.JTextField txtPath;
    private javax.swing.JTextField txtRevision;
    private javax.swing.JTextField txtTitle;
    private javax.swing.JTextField txtWordCount;
    // End of variables declaration//GEN-END:variables

}
