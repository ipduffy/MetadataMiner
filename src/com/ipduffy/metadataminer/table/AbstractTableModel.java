/*
 * AbstractTableModel.java
 *
 * Created on January 22, 2004, 10:54 AM
 */

package com.ipduffy.metadataminer.table;

/**
 *
 * @author  duffian
 */
import com.ipduffy.metadataminer.core.Document;

public interface AbstractTableModel {
    
    public void removeRow(int aRow, Document aDocument);
    public void removeRows(int theRows[], Document aDocument);
    public void showRowDetails(int aRow);
    public void showRowDetails(int theRows[]);
    
}
