/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SearchFrame.java
 *
 * Created on May 31, 2011, 8:40:19 PM
 */

package com.ipduffy.metadataminer.gui;

import com.ipduffy.metadataminer.dao.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JOptionPane;
/**
 *
 * @author duffian
 */

public class SearchFrame extends javax.swing.JFrame {

    JButton btnOK;
    JButton btnCancel;
    JButton btnBrowse;

    /** Creates new form SearchFrame */
    public SearchFrame() {
        initComponents();

        btnOK = new JButton("OK");
        btnCancel = new JButton("Cancel");
        btnBrowse = new JButton("Browse All");

        JButton buttons[] = {btnBrowse, btnCancel, btnOK};
        ButtonPanel bp = new ButtonPanel(buttons);
        getContentPane().add(bp, java.awt.BorderLayout.SOUTH);
        getRootPane().setDefaultButton(btnOK);

        btnBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                btnBrowseActionPerformed(arg0);
            }
        });

        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                btnCloseActionPerformed(arg0);
            }
        });

        btnOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                btnOKActionPerformed(arg0);
            }
        });
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlSearch = new com.ipduffy.metadataminer.gui.SearchPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Search");
        getContentPane().add(pnlSearch, java.awt.BorderLayout.CENTER);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-509)/2, (screenSize.height-167)/2, 509, 167);
    }// </editor-fold>//GEN-END:initComponents


    private void btnCloseActionPerformed(ActionEvent evt) {
        this.dispose();
    }

    private void btnOKActionPerformed(ActionEvent evt) {
        try {
            QueryFactory theQueryFactory = DBConnectionManager.getQueryFactory();
            ArrayList theList = null;
            String theColumn = pnlSearch.getSelectedSearchColumn();

            if (theColumn != null) {
                if (theColumn.equalsIgnoreCase("author")) {
                    theList = theQueryFactory.searchByAuthor(pnlSearch.getSearchComparator(), pnlSearch.getSearchValue(), "Author");
                } else if (theColumn.equalsIgnoreCase("editor")) {
                    theList = theQueryFactory.searchByAuthor(pnlSearch.getSearchComparator(), pnlSearch.getSearchValue(), "Editor");
                } else {
                    theList = theQueryFactory.search(theColumn, pnlSearch.getSearchComparator(), pnlSearch.getSearchValue());
                }
            }

            if (theList != null && theList.size() > 0) {
                new SearchResultsFrame(theList).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "No matching Documents found.", null, JOptionPane.INFORMATION_MESSAGE);
            }
            this.setVisible(false);
        } catch (Exception e) {
            System.out.println("Error querying database in SearchFrame: " + e.toString());
            e.printStackTrace();
        }
    }

    private void btnBrowseActionPerformed(ActionEvent evt) {
        try {
            QueryFactory theQueryFactory = DBConnectionManager.getQueryFactory();
            ArrayList theList = theQueryFactory.search("id", ">=", "0");
            if (theList != null) {
                new SearchResultsFrame(theList).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "No matching Documents found.", null, JOptionPane.INFORMATION_MESSAGE);
            }
            this.setVisible(false);
        } catch (Exception e) {
            System.out.println("Error querying database in SearchFrame: " + e.toString());
            e.printStackTrace();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.ipduffy.metadataminer.gui.SearchPanel pnlSearch;
    // End of variables declaration//GEN-END:variables

}
