/*
 * HostTableRowComparator.java
 *
 * Created on November 20, 2006, 6:29 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.ipduffy.metadataminer.table;

/**
 *
 * @author User
 */
public class HostTableRowComparator implements java.util.Comparator {
    
    private int sortCol = 0;
    private boolean sortAscending = true;
    
    /** Creates a new instance of VulnComparator */
    public HostTableRowComparator(int col, boolean ascending) {
        sortCol = col;
        sortAscending = ascending;
    }
    
    public int compare(Object obj, Object obj1) {
        Object[] row1 = (Object[])obj;
        Object[] row2 = (Object[])obj1;
        
        int result = 0;
        
        switch (sortCol) {
            case 0:
                result = row1[1].toString().compareTo(row2[1].toString());
                break;
            case 1:
                result = row1[2].toString().compareTo(row2[2].toString());
                break;
        }
        
        if (sortAscending) {
            return(result);
        }
        
        return(-result);
    }
}
