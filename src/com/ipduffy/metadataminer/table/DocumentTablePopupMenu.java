/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ipduffy.metadataminer.table;

import com.ipduffy.metadataminer.core.*;
import com.ipduffy.metadataminer.gui.*;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 *
 * @author duffian
 */

public class DocumentTablePopupMenu extends javax.swing.JPopupMenu {

    private JMenuItem mOpenMenuItem = new JMenuItem("Open Document");
    private JMenuItem mViewDetailsMenuItem = new JMenuItem("View Document Details");

    private Document mDocument = null;

    public DocumentTablePopupMenu(Document theDocument) {
        super();

        mDocument = theDocument;

        add(mViewDetailsMenuItem);
        add(mOpenMenuItem);

        mOpenMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                openMenuItemActionPerformed(evt);
            }
        });

        mViewDetailsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                viewDetailsMenuItemActionPerformed(evt);
            }
        });


    }

    private void openMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        if (Desktop.isDesktopSupported()) {
            Desktop theDesktop = Desktop.getDesktop();
            if (mDocument != null) {
                try {
                    File theFile = new File(mDocument.getFullPath());
                    theDesktop.open(theFile);
                } catch (Exception e) {
                    System.out.println("Error opening document " + mDocument.getFilename() + "at path " + mDocument.getFullPath());
                } finally {
                    return;
                }
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Document selected.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void viewDetailsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        if (mDocument != null) {
            DocumentInfoFrame theDocumentInfoFrame = new DocumentInfoFrame(mDocument);
            theDocumentInfoFrame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid Document selected.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
