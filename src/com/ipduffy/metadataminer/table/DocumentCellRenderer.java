/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ipduffy.metadataminer.table;

/**
 *
 * @author duffian
 */

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class DocumentCellRenderer extends DefaultTableCellRenderer {

    public DocumentCellRenderer() {

    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {
        DocumentTableModel theTableModel = (DocumentTableModel)table.getModel();
        setToolTipText((String)theTableModel.getValueAt(row, column));

        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}
