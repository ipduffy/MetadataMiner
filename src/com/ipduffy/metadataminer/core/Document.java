/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ipduffy.metadataminer.core;


import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Date;
/**
 *
 * @author duffian
 */
public class Document {

    private long id;
    private String filename;
    private String fullPath;
    private String title;
    private String comments;
    private String company;
    private String manager;
    private String category;
    private String type;
    private String extension;
    private String md5;
    private int revision;
    private long editingTime;
    private String application;
    private Date creationDate;
    private Date lastSaveDate;
    private Date lastPrintDate;
    private int wordCount;
    private int pageCount;
    private int hiddenCount;
    private Author author;
    private Author editor;

    private boolean hasDuplicates = false;
    
    private final PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    public Document(long id, String filename, String fullPath, String title, String comments, String company, String manager, String category,
            String type, String extension, String md5, int revision, long editingTime, String application, Date creationDate,
            Date lastSaveDate, Date lastPrintDate, int wordCount, int pageCount, int hiddenCount, Author theAuthor, Author theEditor) {
        this.id = id;
        this.filename = filename;
        this.fullPath = fullPath;
        this.title = title;
        this.comments = comments;
        this.company = company;
        this.manager = manager;
        this.category = category;
        this.type = type;
        this.extension = extension;
        this.md5 = md5;
        this.revision = revision;
        this.editingTime = editingTime;
        this.application = application;
        this.creationDate = creationDate;
        this.lastSaveDate = lastSaveDate;
        this.lastPrintDate = lastPrintDate;
        this.wordCount = wordCount;
        this.pageCount = pageCount;
        this.hiddenCount = hiddenCount;

        this.author = theAuthor;
        this.editor = theEditor;
    }
    
    public Document() {
    }

    public void setHasDuplicates(boolean hasDupes) {
        this.hasDuplicates = hasDupes;
    }

    public boolean hasDuplicates() {
        return hasDuplicates;
    }

    public String getFullPath() {
        return fullPath;
    }

    public String getManager() {
        return manager;
    }

    public String getCategory() {
        return category;
    }

    public String getCompany() {
        return company;
    }

    public int getHiddenCount() {
        return hiddenCount;
    }

    public int getPageCount() {
        return pageCount;
    }

    public int getWordCount() {
        return wordCount;
    }

    public Author getAuthor() {
        return author;
    }

    public Author getEditor() {
        return editor;
    }

    /**
     * Get the value of lastPrintDate
     *
     * @return the value of lastPrintDate
     */
    public java.util.Date getLastPrintDate() {
        return lastPrintDate;
    }



    /**
     * Get the value of lastSaveDate
     *
     * @return the value of lastSaveDate
     */
    public java.util.Date getLastSaveDate() {
        return lastSaveDate;
    }


    /**
     * Get the value of creationDate
     *
     * @return the value of creationDate
     */
    public java.util.Date getCreationDate() {
        return creationDate;
    }


    /**
     * Get the value of application
     *
     * @return the value of application
     */
    public String getApplication() {
        return application;
    }


    /**
     * Get the value of editingTime
     *
     * @return the value of editingTime
     */
    public long getEditingTime() {
        return editingTime;
    }


    /**
     * Get the value of revision
     *
     * @return the value of revision
     */
    public int getRevision() {
        return revision;
    }

    /*
     * Get the string value of revision
     *
     * @return value of revision as a String
     */
     public String getRevisionString() {
         return Integer.toString(revision);
     }

    /**
     * Get the value of md5
     *
     * @return the value of md5
     */
    public String getMd5() {
        return md5;
    }


    /**
     * Get the value of extension
     *
     * @return the value of extension
     */
    public String getExtension() {
        return extension;
    }


    /**
     * Get the value of type
     *
     * @return the value of type
     */
    public String getType() {
        return type;
    }


    /**
     * Get the value of comments
     *
     * @return the value of comments
     */
    public String getComments() {
        return comments;
    }



    /**
     * Get the value of title
     *
     * @return the value of title
     */
    public String getTitle() {
        return title;
    }


    /**
     * Get the value of filename
     *
     * @return the value of filename
     */
    public String getFilename() {
        return filename;
    }

    @Override
    public String toString() {
        return filename;
    }

    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public long getId() {
        return id;
    }
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }    
}
