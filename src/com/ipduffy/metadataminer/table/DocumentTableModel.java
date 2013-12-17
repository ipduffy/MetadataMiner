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
import com.ipduffy.metadataminer.core.Document;
import java.util.ArrayList;

public final class DocumentTableModel extends javax.swing.table.AbstractTableModel implements AbstractTableModel {
    
    public ArrayList<Document> mData = null;
    private int mSortCol = 0;
    private boolean mSortAscending = false;
    private static final String mColumnNames [] = {"Filename", "Title", "Comments", "Company", "Author", "Application"};
    private static final boolean DEBUG = false;
    
    /** Creates a new instance of HostTableModel */
    public DocumentTableModel(ArrayList theList) {  // ArrayList of Document objects
        if (theList == null) {
            mData = new ArrayList<Document> ();
        } else {
            mData = theList;
        }
    }
    
    public DocumentTableModel() {
        mData = new ArrayList<Document> ();
    }
    
    public void addRows(ArrayList<Document> theDocuments) {
        mData.addAll(theDocuments);
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
                Document theDocument = (Document)mData.get(row);
                switch (col) {
                    case 0: if (theDocument.hasDuplicates()) {
                                return("*" + theDocument.getFilename());
                            } else {
                                return (theDocument.getFilename());
                            }
                    case 1: return(theDocument.getTitle());
                    case 2: return (theDocument.getComments());
                    case 3: return (theDocument.getCompany());
                    case 4: if (theDocument.getAuthor() != null) {
                                return (theDocument.getAuthor().getAuthorName());
                            } else {
                                return null;
                            }
                    case 5: return(theDocument.getApplication());
                }
            }
        } catch (Exception e) {
            System.out.println("Error geting value at " + row + ", " + col);
            e.printStackTrace();
        }
        return(null);
    }
    
    public long getDocumentID(int row) {
        try {
            if (row < mData.size()) {
                //Object [] theRow = (Object[])mData.get(row);
                Document theDocument = (Document)mData.get(row);
                if (theDocument != null) {
                    return(theDocument.getId());
                }
            }
        } catch (Exception e) {
            System.out.println("Error geting Document ID at " + row);
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
    
    public void removeRow(int row, Document aDocument) {
    }
    
    public void removeRows(int theRows[], Document aDocument) {
    }
    
    public void showRowDetails(int theRowNumber) {
        try {
            if (theRowNumber < mData.size()) {
                
            }
        } catch (Exception e) {
            System.out.println("Error displaying Document Info: " + e.toString());
        }
    }
    
    public void showRowDetails(int theRowNumbers[]) {
    }

    public Document getDocumentAtRow(int theRowNumber) {
        if (theRowNumber < 0 || theRowNumber > mData.size()) {
            return null;
        }

        return (Document)mData.get(theRowNumber);
    }
    
    public String getSelectedValue(int row, int col) {
        try {
            if (row < mData.size()) {
                Document theDocument = (Document)mData.get(row);
                switch (col) {
                    case 0: if (theDocument.hasDuplicates()) {
                                return("*" + theDocument.getFilename());
                            } else {
                                return (theDocument.getFilename());
                            }
                    case 1: return(theDocument.getTitle());
                    case 2: return (theDocument.getComments());
                    case 3: return (theDocument.getCompany());
                    case 4: if (theDocument.getAuthor() != null) {
                                return (theDocument.getAuthor().getAuthorName());
                            } else {
                                return null;
                            }
                    case 5: return(theDocument.getApplication());
                }
            }
        } catch (Exception e) {
            System.out.println("Error geting value at " + row + ", " + col);
            e.printStackTrace();
        }
        return(null);
    }
    
    public void sort(int sortCol) {
        if (sortCol == mSortCol) {
            mSortAscending = !mSortAscending;
        }
        mSortCol = sortCol;
        // Implement sort here
        fireTableDataChanged();
    }
}
