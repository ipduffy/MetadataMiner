/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ipduffy.metadataminer.core;

/**
 *
 * @author duffian
 */
public class Author {

    private long id;
    private long documentID;
    private String authorName;
    private String authorType;

    public Author(long id, long documentID, String authorName, String authorType) {
        this.id = id;
        this.documentID = documentID;
        this.authorName = authorName;
        this.authorType = authorType;
    }

    
    /**
     * Get the value of documentID
     *
     * @return the value of documentID
     */
    public long getDocumentID() {
        return documentID;
    }


    /**
     * Get the value of authorType
     *
     * @return the value of authorType
     */
    public String getAuthorType() {
        return authorType;
    }


    /**
     * Get the value of authorName
     *
     * @return the value of authorName
     */
    public String getAuthorName() {
        return authorName;
    }


    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public long getId() {
        return id;
    }

}
