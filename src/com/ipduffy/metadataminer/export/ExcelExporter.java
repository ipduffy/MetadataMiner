/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ipduffy.metadataminer.export;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import com.ipduffy.metadataminer.core.Document;

/**
 *
 * @author duffian
 */
public class ExcelExporter {

    private File mOutputFile = null;
    private ArrayList mDocumentList = null;

    public ExcelExporter(ArrayList aDocumentList, File anOutputFile) {
        mOutputFile = anOutputFile;
        mDocumentList = aDocumentList;

        writeExcelSpreadsheet();
    }

    public void writeExcelSpreadsheet() {
        if (mOutputFile == null || mDocumentList == null) {
            return;
        }

        HSSFWorkbook theWorkBook = new HSSFWorkbook();
        HSSFSheet theSheet = theWorkBook.createSheet("Office Document Metadata");
        CreationHelper createHelper = theWorkBook.getCreationHelper();

        // Create a bold font for the header
        HSSFCellStyle cs = theWorkBook.createCellStyle();
        HSSFFont f = theWorkBook.createFont();
        f.setBoldweight(f.BOLDWEIGHT_BOLD);
        cs.setFont(f);

        // Create a date cell style
        HSSFCellStyle dateCellStyle = theWorkBook.createCellStyle();
        dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("m/d/yy h:mm"));
        
        // Create a text cell style
        HSSFCellStyle textCellStyle = theWorkBook.createCellStyle();
        textCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("text"));

        HSSFRow theTitleRow = theSheet.createRow(0);
        
        for (int column = 0; column < 21; column++) {
            
            HSSFCell theCell = theTitleRow.createCell(column);
            theCell.setCellStyle(cs);
            
            switch (column) {
                case 0: theCell.setCellValue("Filename");
                        break;
                case 1: theCell.setCellValue("Path");
                        break;
                case 2: theCell.setCellValue("Title");
                        break;
                case 3: theCell.setCellValue("Comments");
                        break;
                case 4: theCell.setCellValue("Company");
                        break;
                case 5: theCell.setCellValue("Manager");
                        break;
                case 6: theCell.setCellValue("Category");
                        break;
                case 7: theCell.setCellValue("Type");
                        break;
                case 8: theCell.setCellValue("Extension");
                        break;
                case 9: theCell.setCellValue("MD5");
                        break;
                case 10: theCell.setCellValue("Revision");
                        break;
                case 11: theCell.setCellValue("Application");
                        break;
                case 12: theCell.setCellValue("Editing Time");
                        break;
                case 13: theCell.setCellValue("Creation Date");
                        break;
                case 14: theCell.setCellValue("Last Save Date");
                        break;
                case 15: theCell.setCellValue("Last Print Date");
                        break;
                case 16: theCell.setCellValue("Word Count");
                        break;
                case 17: theCell.setCellValue("Page Count");
                        break;
                case 18: theCell.setCellValue("Hidden Count");
                        break;
                case 19: theCell.setCellValue("Author");
                        break;
                case 20: theCell.setCellValue("Last Edited By");
                        break;
            }
        }

        int rowCount = 0;
        while (rowCount < mDocumentList.size()) {
            HSSFRow theRow = theSheet.createRow(rowCount + 1);
            Document theDocument = (Document)mDocumentList.get(rowCount);

            theRow.createCell(0).setCellValue(theDocument.getFilename());
            theRow.createCell(1).setCellValue(theDocument.getFullPath());
            theRow.createCell(2).setCellValue(theDocument.getTitle());
            theRow.createCell(3).setCellValue(theDocument.getComments());
            theRow.createCell(4).setCellValue(theDocument.getCompany());
            theRow.createCell(5).setCellValue(theDocument.getManager());
            theRow.createCell(6).setCellValue(theDocument.getCategory());
            theRow.createCell(7).setCellValue(theDocument.getType());
            theRow.createCell(8).setCellValue(theDocument.getExtension());
            
            HSSFCell md5Cell = theRow.createCell(9);
            md5Cell.setCellType(Cell.CELL_TYPE_STRING);
            md5Cell.setCellStyle(textCellStyle);
            md5Cell.setCellValue(createHelper.createRichTextString(theDocument.getMd5()));
            
            theRow.createCell(10).setCellValue(theDocument.getRevision());
            theRow.createCell(11).setCellValue(theDocument.getApplication());
            
            theRow.createCell(12).setCellValue(theDocument.getEditingTime());
            if (theDocument.getCreationDate() != null) {
                Date theCreationDate = theDocument.getCreationDate();
                HSSFCell theCell = theRow.createCell(13);
                theCell.setCellValue(theCreationDate);
                theCell.setCellStyle(dateCellStyle);
            }
            if (theDocument.getLastSaveDate() != null) {
                Date theLastSaveDate = theDocument.getLastSaveDate();
                HSSFCell theCell = theRow.createCell(14);
                theCell.setCellValue(theLastSaveDate);
                theCell.setCellStyle(dateCellStyle);
            }
            if (theDocument.getLastPrintDate() != null) {
                Date theLastPrintDate = theDocument.getLastPrintDate();
                HSSFCell theCell = theRow.createCell(15);
                theCell.setCellValue(theLastPrintDate);
                theCell.setCellStyle(dateCellStyle);
            }

            theRow.createCell(16).setCellValue(theDocument.getWordCount());
            theRow.createCell(17).setCellValue(theDocument.getPageCount());
            theRow.createCell(18).setCellValue(theDocument.getHiddenCount());
            if (theDocument.getAuthor() != null) {
                theRow.createCell(19).setCellValue(theDocument.getAuthor().getAuthorName());
            }
            if (theDocument.getEditor() != null) {
                theRow.createCell(20).setCellValue(theDocument.getEditor().getAuthorName());
            }

            rowCount++;
        }

        try {
            FileOutputStream fout = new FileOutputStream(mOutputFile);
            theWorkBook.write(fout);
            fout.close();
        } catch (Exception e) {
            System.out.println("Error writing Excel Spreadsheet: " + e.toString());
            e.printStackTrace();
        }
    }
}
