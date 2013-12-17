/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ipduffy.metadataminer.parsers;


import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Date;
import org.apache.poi.hpsf.*;
import org.apache.poi.poifs.eventfilesystem.*;
import com.ipduffy.metadataminer.dao.*;

/**
 *
 * @author duffian
 */
public class OLEParser implements POIFSReaderListener {

    private QueryFactory mQueryFactory = null;
    private File mWorkingFile = null;

    private long mDocumentID = -1;
    private String mFullPath = null;
    private String mTitle = null;
    private String mDocumentName = null;
    private String mApplication = null;
    private String mComments = null;
    private String mCompany = null;
    private String mManager = null;
    private String mCategory = null;
    private String mType = null;
    private String mExtension = null;
    private String mMD5 = null;
    private int mRevision = 0;
    private long mEditingTime = 0;
    private Date mCreateDate = null;
    private String mAuthor = null;
    private String mEditor = null;
    private Date mLastSaveDate = null;
    private Date mLastPrintDate = null;
    private int mWordCount = 0;
    private int mPageCount = 0;
    private int mHiddenCount = 0;

    public OLEParser(File aFile) {
        if (aFile == null) {
            throw new NullPointerException("Null file passed to OLEParser.");
        }

        mWorkingFile = aFile;
        mDocumentName = mWorkingFile.getName();
        mFullPath = (aFile.getAbsolutePath());
        mExtension = getFileExtension(mDocumentName);
        mMD5 = calcMD5(mWorkingFile);
        
        try {
            mQueryFactory = DBConnectionManager.getQueryFactory();

            mDocumentID = mQueryFactory.documentExists(mMD5);

            // If the document ID is greater than zero, the document is already in the database. Otherwise, we need to insert it.
            if (mDocumentID < 0) {
                mDocumentID = mQueryFactory.insertDocument(mDocumentName, mFullPath, mTitle, mComments, mCompany, mManager, mCategory,
                    mType, mExtension, mMD5, mRevision, mEditingTime, mApplication, mCreateDate, mLastSaveDate,
                    mLastPrintDate, mWordCount, mPageCount, mHiddenCount);
                // If the document ID is still less than zero after attempting to insert it, then we have a problem...
                if (mDocumentID < 0) {
                    System.out.println("Error inserting document " + mDocumentName);
                }
            } else {
                // If the document ID was greater than zero, we have a duplicate document. Make an entry in the duplicate table.
                mQueryFactory.insertDuplicate(mDocumentName, mFullPath, mDocumentID);
            }
        } catch (Exception e) {
            System.out.println("Error creating initial document in OLEParser: " + e.toString());
            e.printStackTrace();
        }
    }

    public void processPOIFSReaderEvent(POIFSReaderEvent event) {
        if (event == null) return;

        String eventName = event.getName();

        if (eventName != null) {
            eventName = eventName.trim();
        } else {
            return;
        }

        if (eventName.equalsIgnoreCase("SummaryInformation")) {
            SummaryInformation si = null;
            try {
                si = (SummaryInformation)PropertySetFactory.create(event.getStream());
            } catch (Exception ex) {
                throw new RuntimeException
                    ("Property set stream \"" +
                        event.getPath() + event.getName() + "\": " + ex);
            }

            mTitle = si.getTitle();
            mApplication = si.getApplicationName();
            mComments = si.getComments();
            String revNumber = si.getRevNumber();
            if (revNumber != null) {
                mRevision = Integer.parseInt(revNumber);
            }
            mEditingTime = getEditingTimeInMinutes(si.getEditTime());
            mCreateDate = si.getCreateDateTime();
            mAuthor = si.getAuthor();
            mEditor = si.getLastAuthor();
            mLastSaveDate = si.getLastSaveDateTime();
            mLastPrintDate = si.getLastPrinted();
            mWordCount = si.getWordCount();
            mPageCount = si.getPageCount();

            mQueryFactory.updateSummaryInformation(mDocumentID, mTitle, mComments, mRevision, mEditingTime, mApplication, mCreateDate, mLastSaveDate, mLastPrintDate, mWordCount, mPageCount);
            if (mAuthor != null)
                mQueryFactory.insertAuthor(mDocumentID, mAuthor);
            if (mEditor != null)
                mQueryFactory.insertEditor(mDocumentID, mEditor);
        } else if (eventName.equalsIgnoreCase("DocumentSummaryInformation")) {
            DocumentSummaryInformation dsi = null;
            try {
                dsi = (DocumentSummaryInformation)PropertySetFactory.create(event.getStream());
            } catch (Exception ex) {
                throw new RuntimeException
                    ("Property set stream \"" +
                        event.getPath() + event.getName() + "\": " + ex);
            }

            mCompany = dsi.getCompany();
            mManager = dsi.getManager();
            mCategory = dsi.getCategory();
            mHiddenCount = dsi.getHiddenCount();

            mQueryFactory.updateDocumentSummaryInformation(mDocumentID, mCompany, mManager, mCategory, mHiddenCount);
        }
    }

    private String calcMD5(File aFile) {
        if (aFile == null) return null;
        byte[] buffer = new byte[8192];
        int read = 0;
        try {
            FileInputStream fin = new FileInputStream(aFile);
            MessageDigest digest = MessageDigest.getInstance("MD5");
            while( (read = fin.read(buffer)) > 0) {
                digest.update(buffer, 0, read);
            }

            byte[] md5sum = digest.digest();
            BigInteger bigInt = new BigInteger(1, md5sum);

            fin.close();

            return(bigInt.toString(16));
        }
        catch(Exception e) {
            System.out.println("Error calculating file MD5 hash: " + e.toString());
            e.printStackTrace();
        }
        return(null);
    }

    private String getFileExtension(String aFileName) {
        int index = aFileName.lastIndexOf(".");
        if (index >= 0) {
            String theExtension = aFileName.substring(index);
            return theExtension;
        }
        return null;
    }

    private long getEditingTimeInMinutes(long theOriginalTime) {
        long minutes = theOriginalTime / (60*10000000);
        return minutes;
    }
}

