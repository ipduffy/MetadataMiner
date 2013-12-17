/*
 * DocumentTableModel.java
 *
 * Created on December 29, 2003, 10:02 AM
 */

package com.ipduffy.metadataminer.table;

/**
 *
 * @author  duffian
 */
import com.ipduffy.metadataminer.core.Duplicate;
import java.util.ArrayList;

public final class DuplicateTableModel extends javax.swing.table.AbstractTableModel {
    
    private ArrayList mData = new ArrayList();
    private static final String mColumnNames [] = {"Filename", "Path"};
    
    /** Creates a new instance of HostTableModel */
        public DuplicateTableModel(ArrayList theList) {  // ArrayList of Document objects
        if (theList == null) {
            throw new NullPointerException("Null list passed to DuplicateTableModel constructor.");
        } else {
            mData = theList;
        }
    }
    
    public int getColumnCount() {
        return(mColumnNames.length);
    }
    
    public int getRowCount() {
        return(mData.size());
    }
    
    public Object getValueAt(int row, int col) {
        try {
            if (row < mData.size()) {
                Duplicate theDuplicate = (Duplicate)mData.get(row);
                switch (col) {
                    case 0: return theDuplicate.getFilename();
                    case 1: return theDuplicate.getPath();
                }
            }
        } catch (Exception e) {
            System.out.println("Error geting value at " + row + ", " + col);
            e.printStackTrace();
        }
        return(null);
    }
    
    public long getDuplicateID(int row) {
        try {
            if (row < mData.size()) {
                Duplicate theDuplicate = (Duplicate)mData.get(row);
                return theDuplicate.getId();
            }
        } catch (Exception e) {
            System.out.println("Error geting Duplicate ID at " + row);
            e.printStackTrace();
        }
        return(-1l);
    }
    
    
    public boolean isCellEditable(int row, int col) {
        return(false);
    }
    
    public String getColumnName(int col) {
        if (col > mColumnNames.length) {
            return(null);
        }
        return(mColumnNames[col]);
    }
    
    public void showRowDetails(int theRowNumber) {
        try {
            if (theRowNumber < mData.size()) {
                
            }
        } catch (Exception e) {
            System.out.println("Error displaying Duplicate Info: " + e.toString());
        }
    }
    
    public void showRowDetails(int theRowNumbers[]) {
    }

    public Duplicate getDuplicateAtRow(int theRowNumber) {
        if (theRowNumber < 0 || theRowNumber > mData.size()) {
            return null;
        }

        return (Duplicate)mData.get(theRowNumber);
    }
}
