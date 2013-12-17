/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ipduffy.metadataminer.gui;

import java.io.File;
import java.io.FileInputStream;
import javax.swing.JOptionPane;
import org.apache.poi.poifs.eventfilesystem.*;
import com.ipduffy.metadataminer.parsers.*;

/**
 *
 * @author duffian
 */
public class ParserThread extends java.lang.Thread {

    private File mWorkingDirectory = null;

    public ParserThread(File theDirectory) {
        mWorkingDirectory = theDirectory;
    }

    public void run() {
        if (mWorkingDirectory != null) {
            // Notify the parser listeners
            ParserManager.parsingBegan();

            // Show a progress dialog
            ProgressDialog theProgressDialog = new ProgressDialog(null, false);
            theProgressDialog.setVisible(true);

            // Do the work
            processDirectory(mWorkingDirectory);

            // Hide and dispose of the progress dialog
            theProgressDialog.setVisible(false);
            theProgressDialog.dispose();

            // Notify the listeners that the parsing ended
            ParserManager.parsingEnded();

            JOptionPane.showMessageDialog(null, "Processing completed!", "All Done", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Error: Please specify an input directory containing MS Office files.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void processDirectory(File aFile) {
        if (aFile.isDirectory()) {
            String[] children = aFile.list();
            for (int i=0; i<children.length; i++) {
                processDirectory(new File(aFile, children[i]));
            }
        } else {
            String theFilename = aFile.getName();
            String fileExtension = getFileExtension(aFile);
            if (fileExtension != null && (fileExtension.equalsIgnoreCase(".doc") || fileExtension.equalsIgnoreCase(".xls") ||
                    fileExtension.equalsIgnoreCase(".ppt")) && !theFilename.startsWith("._")) {
                POIFSReader r = new POIFSReader();
                OLEParser theOLEParser = new OLEParser(aFile);
                r.registerListener(theOLEParser,
                       "\005SummaryInformation");
                r.registerListener(theOLEParser, "\005DocumentSummaryInformation");
                try {
                    r.read(new FileInputStream(aFile));
                } catch (Exception e) {
                    System.out.println("Invalid file detected: " + aFile.getName());
                } finally {
                    return;
                }
            }
            if (fileExtension != null && (fileExtension.equalsIgnoreCase(".docx") || fileExtension.equalsIgnoreCase(".xlsx") ||
                    fileExtension.equalsIgnoreCase(".pptx")) && !theFilename.startsWith("._")) {
                    OfficeXMLParser theParser = new OfficeXMLParser(aFile);
                    theParser.processOfficeXMLFile();
            }
        }
    }

    private String getFileExtension(File aFile) {
        String filename = aFile.getName();
        int index = filename.lastIndexOf(".");
        String theExtension = null;
        if (index > 0) {
            theExtension = filename.substring(index);
        }
        return theExtension;
    }
}
