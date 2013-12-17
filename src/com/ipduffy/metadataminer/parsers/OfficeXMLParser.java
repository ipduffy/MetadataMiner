/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ipduffy.metadataminer.parsers;


import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Date;
import java.util.zip.*;
import com.ipduffy.metadataminer.dao.*;

/**
 *
 * @author duffian
 */
public class OfficeXMLParser {

    private File mWorkingFile = null;

    private QueryFactory mQueryFactory = null;

    // Fields for the document
    private String mFilename = null;
    private String mFullPath = null;
    private String mTitle = null;
    private String mComments = null;
    private String mCompany = null;
    private String mManager = null;
    private String mCategory = null;
    private String mType = null;
    private String mExtension = null;
    private String mMD5 = null;
    private int mRevision = 0;
    private long mEditingTime;
    private String mApplication = null;
    private Date mCreationDate = null;
    private Date mLastSaveDate = null;
    private Date mLastPrintDate = null;
    private int mWordCount = 0;
    private int mPageCount = 0;
    private int mHiddenCount = 0;

    private String mAuthor = null;
    private String mEditor = null;

    public OfficeXMLParser(File aFile) {
        if (aFile == null) {
            throw new NullPointerException("Null File passed to OfficeXMLParser");
        }

        try {
            mQueryFactory = DBConnectionManager.getQueryFactory();
        } catch (Exception e) {
            System.out.println("Error getting QueryFactory in OLEParser: " + e.toString());
            e.printStackTrace();
        }

        mWorkingFile = aFile;
        mFullPath = aFile.getAbsolutePath();
    }

    public void processOfficeXMLFile() {
        try {
            // Get the file name
            mFilename = mWorkingFile.getName();

            // Get the file extension
            mExtension = getFileExtension(mFilename);

            // Calculate the MD5 hash
            mMD5 = calcMD5(mWorkingFile);

            // Parse the XML entries in the file
            ZipFile theZipFile = new ZipFile(mWorkingFile);
            ZipEntry appEntry = theZipFile.getEntry("docProps/app.xml");
            ZipEntry coreEntry = theZipFile.getEntry("docProps/core.xml");

            // Parse the app.xml file
            if (appEntry != null) {
                InputStream theAppXMLInputStream = theZipFile.getInputStream(appEntry);
                if (theAppXMLInputStream != null) {
                    AppXMLParser theAppParser = new AppXMLParser(this);
                    theAppParser.parseInput(theAppXMLInputStream);
                }
            }

            // Parse the core.xml file
            if (coreEntry != null) {
                InputStream theCoreXMLInputStream = theZipFile.getInputStream(coreEntry);
                if (theCoreXMLInputStream != null) {
                    CoreXMLParser theCoreParser = new CoreXMLParser(this);
                    theCoreParser.parseInput(theZipFile.getInputStream(coreEntry));
                }
            }

            long docID = mQueryFactory.documentExists(mMD5);

            if (docID < 0) {
                docID = mQueryFactory.insertDocument(mFilename, mFullPath, mTitle, mComments, mCompany, mManager, mCategory,
                        mType, mExtension, mMD5, mRevision, mEditingTime, mApplication, mCreationDate, mLastSaveDate, mLastPrintDate,
                        mWordCount, mPageCount, mHiddenCount);
                if (docID > 0) {
                    if (mAuthor != null) {
                        mQueryFactory.insertAuthor(docID, mAuthor);
                    }
                    if (mEditor != null) {
                        mQueryFactory.insertEditor(docID, mEditor);
                    }
                } else {
                    System.out.println("Error inserting document " + mFilename);
                }
            } else {
                mQueryFactory.insertDuplicate(mFilename, mFullPath, docID);
            }

            theZipFile.close();
        } catch (IOException e) {
            System.out.println("Error processing Office 2007 file " + mWorkingFile.getName() + "; invalid/corrupt file?");
            e.printStackTrace();
        }
    }

    public void setComments(String mComments) {
        this.mComments = mComments;
    }

    public void setCreationDate(Date mCreationDate) {
        this.mCreationDate = mCreationDate;
    }

    public void setAuthor(String author) {
        this.mAuthor = author;
    }

    public void setEditor(String mEditor) {
        this.mEditor = mEditor;
    }

    public void setLastPrintDate(Date mLastPrintDate) {
        this.mLastPrintDate = mLastPrintDate;
    }

    public void setLastSaveDate(Date mLastSaveDate) {
        this.mLastSaveDate = mLastSaveDate;
    }

    public void setRevision(int mRevision) {
        this.mRevision = mRevision;
    }

    public void setEditingTime(long mEditingTime) {
        this.mEditingTime = getEditingTimeInMinutes(mEditingTime);
    }

    public void setApplication(String mApplication) {
        this.mApplication = mApplication;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public void setPageCount(int mPageCount) {
        this.mPageCount = mPageCount;
    }

    public void setWordCount(int mWordCount) {
        this.mWordCount = mWordCount;
    }

    public void setManager(String manager) {
        this.mManager = manager;
    }

    public void setCompany(String company) {
        this.mCompany = company;
    }

    public void setCategory(String category) {
        this.mCategory = category;
    }

    public void setHiddenCount(int hiddenCount) {
        this.mHiddenCount = hiddenCount;
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
        } catch(Exception e) {
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
        long minutes = theOriginalTime / (60*1000);
        return minutes;
    }
}


