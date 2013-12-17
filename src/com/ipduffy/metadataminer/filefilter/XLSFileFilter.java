/*
 * CSVFileFilter.java
 *
 * Created on March 3, 2004, 9:37 PM
 */

package com.ipduffy.metadataminer.filefilter;

/**
 *
 * @author  duffian
 */
import java.io.File;
import java.util.StringTokenizer;

public final class XLSFileFilter extends javax.swing.filechooser.FileFilter {
    
    private static final String mExtension = "xls";
    
    /** Creates a new instance of XLSFileFilter */
    public XLSFileFilter() {
    }
    
    public boolean accept(java.io.File f) {
        if (f.isDirectory()) {
            return(true);
        }
        String extension = getExtension(f);
        if (extension != null) {
            if (extension.equalsIgnoreCase(mExtension)) {
                return(true);
            }
        }
        return(false);
    }
    
    public String getDescription() {
        return("Excel Workbooks");
    }
    
    public String getExtension(File f) {
        StringTokenizer st = new StringTokenizer(f.getName(), ".");
        String extension = null;
        while (st.hasMoreTokens()) {
            extension = st.nextToken();
        }
        return(extension);
    }
    
    public String getExtension() {
        return(mExtension);
    }
}
