/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ipduffy.metadataminer.core;

/**
 *
 * @author duffian
 */
public class Duplicate {

    private long mId = -1;
    private long mDocumentID = -1;
    private String mFilename = null;
    private String mPath = null;


    public Duplicate(long theID, long theDocumentID, String theFilename, String thePath) {
        mId = theID;
        mDocumentID = theDocumentID;
        mFilename = theFilename;
        mPath = thePath;
    }

    /**
     * Get the value of Path
     * @return String value of Path
     */
    public String getPath() {
        return mPath;
    }

    /**
     * Get the value of mFilename
     *
     * @return the value of mFilename
     */
    public String getFilename() {
        return mFilename;
    }

    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public long getId() {
        return mId;
    }


    /**
     * Get the value of mDocumentID
     *
     * @return the value of mDocumentID
     */
    public long getDocumentID() {
        return mDocumentID;
    }

}
