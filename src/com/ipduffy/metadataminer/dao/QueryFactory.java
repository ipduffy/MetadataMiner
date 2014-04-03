/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ipduffy.metadataminer.dao;

import com.ipduffy.metadataminer.core.*;
import java.util.ArrayList;
import java.util.Date;
import java.sql.*;

/**
 *
 * @author duffian
 */
public class QueryFactory {

    private static Connection mConnection = null;
    private static PreparedStatement mCreateDocumentTableStatement = null;
    private static PreparedStatement mCreateAuthorTableStatement = null;
    private static PreparedStatement mCreateDuplicateTableStatement = null;
    private static PreparedStatement mInsertDocumentStatement = null;
    private static PreparedStatement mInsertAuthorStatement = null;
    private static PreparedStatement mInsertDuplicateStatement = null;
    private static PreparedStatement mGetDuplicatesStatement = null;
    private static PreparedStatement mDocumentExistsStatement = null;
    private static PreparedStatement mAuthorExistsStatement = null;
    private static PreparedStatement mGetDocumentStatement = null;
    private static PreparedStatement mGetAuthorStatement = null;
    private static PreparedStatement mUpdateSummaryInfoStatement = null;
    private static PreparedStatement mUpdateDocumentSummaryInfoStatement = null;
    private static PreparedStatement mDocumentHasDuplicatesStatement = null;
    private static PreparedStatement mTopAuthorsStatement = null;
    private static PreparedStatement mTopEditorsStatement = null;
    private static PreparedStatement mTopEditedDocumentsStatement = null;
    private static PreparedStatement mTopRevisedDocumentsStatement = null;
    private static PreparedStatement mAllDocumentsByAuthorStatement = null;
    private static PreparedStatement mAllDocumentsStatement = null;
    private static PreparedStatement mPrintedDocumentsStatement = null;
    private static PreparedStatement mEditedDocumentsStatement = null;
    private static PreparedStatement mAllDocumentIDsStatement = null;
    private static PreparedStatement mAuthorEditorPairsStatement = null;
           
            
    // Constructor
    public QueryFactory(Connection aConnection) {
        try {
            setConnection(aConnection);
        } catch (Exception e) {
            System.out.println("Error instatiating QueryFactory: " + e.toString());
            e.printStackTrace();
        }
    }

    public static void setConnection(Connection aConnection) throws Exception {
        if (aConnection != null && !aConnection.isClosed()) {
            mConnection = aConnection;
        } else {
            throw new IllegalStateException("Invalid Connection in QueryFactory.setConnection.");
        }
    }

    public long getParsedDocumentCount() {
        long theCount = 0;
        String theSQLString = "SELECT count(*) FROM document";
        ResultSet theResultSet = null;

        try {
            Statement theStatement = mConnection.createStatement();
            theResultSet = theStatement.executeQuery(theSQLString);
            if (theResultSet != null && theResultSet.next()) {
                theCount = theResultSet.getLong(1);
            }
            theResultSet.close();
            theStatement.close();
        } catch (Exception e) {
            System.out.println("Error getting parsed document count: " + e.toString());
            e.printStackTrace();
        } finally {
            try {
                theResultSet.close();
            } catch (Exception e) {
            }
            return theCount;
        }
    }

    public long insertDocument(String filename, String fullPath, String title, String comments, String company, String manager, String category,
            String type, String extension, String md5, int revision, long editingtime, String applicationName,
            java.util.Date creationdate, java.util.Date lastsavedate, java.util.Date lastprintdate, int wordcount,
            int pagecount, int hiddencount) {
        if (mInsertDocumentStatement == null) {
            try {
                mInsertDocumentStatement = mConnection.prepareStatement("INSERT INTO document(filename, path, title, comments, company, manager, category, " +
                        "type, extension, md5, revision, editingtime, application, creationdate, lastsavedate, lastprintdate, wordcount, pagecount, " +
                        "hiddencount) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            } catch (Exception e) {
                System.out.println("Error creating InsertDocumentStatement: " + e.toString());
                e.printStackTrace();
            }
        }

        long theDocID = -1;

        try {
            if (mInsertDocumentStatement != null) {
                String theSQLString = "CALL IDENTITY();";
                mInsertDocumentStatement.setString(1, filename);
                mInsertDocumentStatement.setString(2, fullPath);
                mInsertDocumentStatement.setString(3, title);
                mInsertDocumentStatement.setString(4, comments);
                mInsertDocumentStatement.setString(5, company);
                mInsertDocumentStatement.setString(6, manager);
                mInsertDocumentStatement.setString(7, category);
                mInsertDocumentStatement.setString(8, type);
                mInsertDocumentStatement.setString(9, extension);
                mInsertDocumentStatement.setString(10, md5);
                mInsertDocumentStatement.setInt(11, revision);
                mInsertDocumentStatement.setLong(12, editingtime);
                mInsertDocumentStatement.setString(13, applicationName);
                if (creationdate != null) {
                    mInsertDocumentStatement.setTimestamp(14, new java.sql.Timestamp(creationdate.getTime()));
                } else {
                    mInsertDocumentStatement.setTimestamp(14, null);
                }
                if (lastsavedate != null) {
                    mInsertDocumentStatement.setTimestamp(15, new java.sql.Timestamp(lastsavedate.getTime()));
                } else {
                    mInsertDocumentStatement.setTimestamp(15, null);
                }
                if (lastprintdate != null) {
                    mInsertDocumentStatement.setTimestamp(16, new java.sql.Timestamp(lastprintdate.getTime()));
                } else {
                    mInsertDocumentStatement.setTimestamp(16, null);
                }
                mInsertDocumentStatement.setInt(17, wordcount);
                mInsertDocumentStatement.setInt(18, pagecount);
                mInsertDocumentStatement.setInt(19, hiddencount);
                mInsertDocumentStatement.execute();

                Statement theStatement = mConnection.createStatement();
                ResultSet theResultSet = theStatement.executeQuery(theSQLString);
                if (theResultSet != null && theResultSet.next()) {
                    theDocID = theResultSet.getLong(1);
                }
                theResultSet.close();
                theStatement.close();
            }
        } catch (Exception e) {
            System.out.println("Error inserting Document: " + e.toString());
            e.printStackTrace();
        } finally {
            return theDocID;
        }
    }

    public void deleteDocument(long docID) {

    }

    public long insertDuplicate(String filename, String fullpath, long docID) {
        long theDuplicateID = -1;
        try {
            if (mInsertDuplicateStatement == null) {
                mInsertDuplicateStatement = mConnection.prepareStatement("INSERT INTO duplicate(document_id, filename, path) VALUES (?, ?, ?);");
            }
        } catch (Exception e) {
            System.out.println("Error creating insert duplicate statement: " + e.toString());
            e.printStackTrace();
        }

        try {
            if (mInsertDuplicateStatement != null) {
                String theSQLString = "CALL IDENTITY();";
                mInsertDuplicateStatement.setLong(1, docID);
                mInsertDuplicateStatement.setString(2, filename);
                mInsertDuplicateStatement.setString(3, fullpath);
                mInsertDuplicateStatement.execute();

                Statement theStatement = mConnection.createStatement();
                ResultSet theResultSet = theStatement.executeQuery(theSQLString);
                if (theResultSet != null && theResultSet.next()) {
                    theDuplicateID = theResultSet.getLong(1);
                }
                theResultSet.close();
                theStatement.close();
            }
        } catch (Exception e) {
            System.out.println("Error inserting duplicate document: " + e.toString());
            e.printStackTrace();
        } finally {
            return theDuplicateID;
        }
    }

    public ArrayList getDuplicates(long aDocumentID) {
        ArrayList theDuplicates = new ArrayList();
        try {
            if (mGetDuplicatesStatement == null) {
                mGetDuplicatesStatement = mConnection.prepareStatement("SELECT * FROM duplicate WHERE document_id = ?");
            }
        } catch (Exception e) {
            System.out.println("Error creating getDuplcatesStatement: " + e.toString());
            e.printStackTrace();
        }

        try {
            if (mGetDuplicatesStatement != null) {
                mGetDuplicatesStatement.setLong(1, aDocumentID);
                ResultSet theResultSet = mGetDuplicatesStatement.executeQuery();
                while (theResultSet != null && theResultSet.next()) {
                    Duplicate theDuplicate = new Duplicate(theResultSet.getLong("id"), theResultSet.getLong("document_id"),
                            theResultSet.getString("filename"), theResultSet.getString("path"));
                    theDuplicates.add(theDuplicate);
                }
            }
        } catch (Exception e) {
            System.out.println("Error getting Duplicates: " + e.toString());
            e.printStackTrace();
        } finally {
            return theDuplicates;
        }
    }

    public long documentExists(String docMD5) {
        long documentID = -1;
        if (mDocumentExistsStatement == null) {
            try {
                mDocumentExistsStatement = mConnection.prepareStatement("SELECT id FROM document WHERE md5 = ?");
            } catch (Exception e) {
                System.out.println("Error preparing documentexists statement: " + e.toString());
                e.printStackTrace();
            }
        }

        ResultSet theResultSet = null;

        try {
            if (mDocumentExistsStatement != null) {
                mDocumentExistsStatement.setString(1, docMD5);
                theResultSet = mDocumentExistsStatement.executeQuery();
                if (theResultSet != null && theResultSet.next()) {
                    documentID = theResultSet.getLong(1);
                }
                theResultSet.close();
            }
        } catch (Exception e) {
            System.out.println("Error checking if document exits: " + e.toString());
                e.printStackTrace();
        } finally {
            try {
                theResultSet.close();
            } catch (Exception e) {
            }
            return documentID;
        }
    }

    public long authorExists(long docID, String authorName) {
        long authorID = -1;
        if (mAuthorExistsStatement == null) {
            try {
                mAuthorExistsStatement = mConnection.prepareStatement("SELECT id FROM author WHERE document_id = ? AND author = ? AND author_type = ?");
            } catch (Exception e) {
                System.out.println("Error preparing authorexists statement: " + e.toString());
                e.printStackTrace();
            }
        }

        ResultSet theResultSet = null;

        try {
            if (mAuthorExistsStatement != null) {
                mAuthorExistsStatement.setLong(1, docID);
                mAuthorExistsStatement.setString(2, authorName);
                mAuthorExistsStatement.setString(3, "Author");
                theResultSet = mAuthorExistsStatement.executeQuery();
                if (theResultSet != null && theResultSet.next()) {
                    authorID = theResultSet.getLong(1);
                }
                theResultSet.close();
            }
        } catch (Exception e) {
            System.out.println("Error checking if author exits: " + e.toString());
                e.printStackTrace();
        } finally {
            try {
                theResultSet.close();
            } catch (Exception e) {
            }
            return authorID;
        }
    }

    public long editorExists(long docID, String authorName) {
        long editorID = -1;
        if (mAuthorExistsStatement == null) {
            try {
                mAuthorExistsStatement = mConnection.prepareStatement("SELECT id FROM author WHERE document_id = ? AND author = ? AND author_type = ?");
            } catch (Exception e) {
                System.out.println("Error preparing editorexists statement: " + e.toString());
                e.printStackTrace();
            }
        }

        ResultSet theResultSet = null;

        try {
            if (mAuthorExistsStatement != null) {
                mAuthorExistsStatement.setLong(1, docID);
                mAuthorExistsStatement.setString(2, authorName);
                mAuthorExistsStatement.setString(3, "Editor");
                theResultSet = mAuthorExistsStatement.executeQuery();
                if (theResultSet != null && theResultSet.next()) {
                    editorID = theResultSet.getLong(1);
                }
                theResultSet.close();
            }
        } catch (Exception e) {
            System.out.println("Error checking if editor exits: " + e.toString());
                e.printStackTrace();
        } finally {
            try {
                theResultSet.close();
            } catch (Exception e) {
            }
            return editorID;
        }
    }

    public long insertAuthor(long docID, String authorName) {
        if (mInsertAuthorStatement == null) {
            try {
                mInsertAuthorStatement = mConnection.prepareStatement("INSERT INTO author(document_id, author, author_type) " +
                    "VALUES (?, ?, ?);");
            } catch (Exception e) {
                System.out.println("Error creating InsertAuthorStatement: " + e.toString());
                e.printStackTrace();
            }
        }

        long theAuthorID = authorExists(docID, authorName);

        try {
            if (theAuthorID < 0 && mInsertAuthorStatement != null) {
                String theSQLString = "CALL IDENTITY();";
                mInsertAuthorStatement.setLong(1, docID);
                mInsertAuthorStatement.setString(2, authorName);
                mInsertAuthorStatement.setString(3, "Author");
                mInsertAuthorStatement.execute();

                Statement theStatement = mConnection.createStatement();
                ResultSet theResultSet = theStatement.executeQuery(theSQLString);
                if (theResultSet != null && theResultSet.next()) {
                    theAuthorID = theResultSet.getLong(1);
                }
                theResultSet.close();
                theStatement.close();
            }
        } catch (Exception e) {
            System.out.println("Error inserting author: " + e.toString());
            e.printStackTrace();
        } finally {
            return theAuthorID;
        }
    }

    public long insertEditor(long docID, String editorName) {
        if (mInsertAuthorStatement == null) {
            try {
                mInsertAuthorStatement = mConnection.prepareStatement("INSERT INTO author(document_id, author, author_type) " +
                    "VALUES (?, ?, ?);");
            } catch (Exception e) {
                System.out.println("Error creating InsertAuthorStatement: " + e.toString());
                e.printStackTrace();
            }
        }

        long theEditorID = editorExists(docID, editorName);
        
        try {
            if (theEditorID < 0 && mInsertAuthorStatement != null) {
                String theSQLString = "CALL IDENTITY();";
                mInsertAuthorStatement.setLong(1, docID);
                mInsertAuthorStatement.setString(2, editorName);
                mInsertAuthorStatement.setString(3, "Editor");
                mInsertAuthorStatement.execute();

                Statement theStatement = mConnection.createStatement();
                ResultSet theResultSet = theStatement.executeQuery(theSQLString);
                if (theResultSet != null && theResultSet.next()) {
                    theEditorID = theResultSet.getLong(1);
                }
                theResultSet.close();
                theStatement.close();
            }
        } catch (Exception e) {
            System.out.println("Error inserting editor: " + e.toString());
            e.printStackTrace();
        } finally {
            return theEditorID;
        }
    }

    public ArrayList searchByAuthor(String comparator, String value, String authortype) {
        ArrayList theList = new ArrayList();

        try {
            String finalValue;
            String finalComparator;

            if (comparator.equalsIgnoreCase("contains")) {
                finalValue = "%" + value + "%";
                finalComparator = "LIKE";
            } else if (comparator.equalsIgnoreCase("starts with")) {
                finalValue = value + "%";
                finalComparator = "LIKE";
            } else if (comparator.equalsIgnoreCase("ends with")) {
                finalValue = "%" + value;
                finalComparator = "LIKE";
            } else {
                finalValue = value;
                finalComparator = comparator;
            }

            String theSQLString = "SELECT * FROM document JOIN author ON document.id = author.document_id WHERE author " + finalComparator +
                    " '" + finalValue + "' AND author.author_type = '" + authortype + "';";
            //System.out.println(theSQLString);
            Statement theStatement = mConnection.createStatement();
            ResultSet theResultSet = theStatement.executeQuery(theSQLString);
            while (theResultSet.next()) {
                long theDocumentID = theResultSet.getLong(1);
                Document theDocument = new Document(theDocumentID,
                        theResultSet.getString("filename"),      // filename
                        theResultSet.getString("path"),      // full path
                        theResultSet.getString("title"),      // title
                        theResultSet.getString("comments"),      // comments
                        theResultSet.getString("company"),      // company
                        theResultSet.getString("manager"),      // manager
                        theResultSet.getString("category"),      // category
                        theResultSet.getString("type"),      // type
                        theResultSet.getString("extension"),      // extension
                        theResultSet.getString("md5"),     // MD5
                        theResultSet.getInt("revision"),     // revision
                        theResultSet.getLong("editingtime"),       // editingTime
                        theResultSet.getString("application"),     // application
                        theResultSet.getTimestamp("creationdate"),       // creationDate
                        theResultSet.getTimestamp("lastsavedate"),       // lastSaveDate
                        theResultSet.getTimestamp("lastprintdate"),       // lastPrintDate
                        theResultSet.getInt("wordcount"),        // wordCount
                        theResultSet.getInt("pagecount"),        // pageCount
                        theResultSet.getInt("hiddencount"),        // hiddenCount
                        getDocumentAuthor(theDocumentID),
                        getDocumentEditor(theDocumentID));
                theDocument.setHasDuplicates(documentHasDuplicates(theDocumentID));
                theList.add(theDocument);
            }
            theResultSet.close();
            theStatement.close();
        } catch (Exception e) {
            System.out.println("Error searching database: " + e.toString());
            e.printStackTrace();
        } finally {
            System.out.println("Search found " + theList.size() + " matching values.");
            return theList;
        }
    }

    public ArrayList search(String column, String comparator, String value) {
        ArrayList theList = new ArrayList();
        try {
            String finalValue;
            String finalComparator;

            if (comparator.equalsIgnoreCase("contains")) {
                finalValue = "%" + value + "%";
                finalComparator = "LIKE";
            } else if (comparator.equalsIgnoreCase("starts with")) {
                finalValue = value + "%";
                finalComparator = "LIKE";
            } else if (comparator.equalsIgnoreCase("ends with")) {
                finalValue = "%" + value;
                finalComparator = "LIKE";
            } else {
                finalValue = value;
                finalComparator = comparator;
            }
            String theSQLString = "SELECT * FROM document WHERE " + column + " " + finalComparator +
                    " '" + finalValue + "';";
            System.out.println(theSQLString);
            Statement theStatement = mConnection.createStatement();
            ResultSet theResultSet = theStatement.executeQuery(theSQLString);
            while (theResultSet != null && theResultSet.next()) {
                long theDocumentID = theResultSet.getLong(1);
                Document theDocument = new Document(theDocumentID,
                        theResultSet.getString("filename"),      // filename
                        theResultSet.getString("path"),      // full path
                        theResultSet.getString("title"),      // title
                        theResultSet.getString("comments"),      // comments
                        theResultSet.getString("company"),      // company
                        theResultSet.getString("manager"),      // manager
                        theResultSet.getString("category"),      // category
                        theResultSet.getString("type"),      // type
                        theResultSet.getString("extension"),      // extension
                        theResultSet.getString("md5"),     // MD5
                        theResultSet.getInt("revision"),     // revision
                        theResultSet.getLong("editingtime"),       // editingTime
                        theResultSet.getString("application"),     // application
                        theResultSet.getTimestamp("creationdate"),       // creationDate
                        theResultSet.getTimestamp("lastsavedate"),       // lastSaveDate
                        theResultSet.getTimestamp("lastprintdate"),       // lastPrintDate
                        theResultSet.getInt("wordcount"),        // wordCount
                        theResultSet.getInt("pagecount"),        // pageCount
                        theResultSet.getInt("hiddencount"),        // hiddenCount
                        getDocumentAuthor(theDocumentID),
                        getDocumentEditor(theDocumentID));
                theDocument.setHasDuplicates(documentHasDuplicates(theDocumentID));
                theList.add(theDocument);
            }
            theResultSet.close();
            theStatement.close();
        } catch (Exception e) {
            System.out.println("Error searching database: " + e.toString());
            e.printStackTrace();
        } finally {
            System.out.println("Search found " + theList.size() + " matching values.");
            return theList;
        }
    }

    public boolean documentHasDuplicates(long theDocumentID) {
        try {
            if (mDocumentHasDuplicatesStatement == null) {
                mDocumentHasDuplicatesStatement = mConnection.prepareStatement("SELECT * FROM duplicate WHERE document_id = ?");
            }
        } catch (Exception e) {
            System.out.println("Error creating documenthasduplicatesstatement: " + e.toString());
            e.printStackTrace();
        }

        ResultSet theResultSet = null;

        boolean hasDupes = false;
        try {
            if (mDocumentHasDuplicatesStatement != null) {
                mDocumentHasDuplicatesStatement.setLong(1, theDocumentID);
                theResultSet = mDocumentHasDuplicatesStatement.executeQuery();
                if (theResultSet != null && theResultSet.next()) {
                    hasDupes = true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error determining if document has duplicates: " + e.toString());
            e.printStackTrace();
        } finally {
            try {
                theResultSet.close();
            } catch (Exception e) {
            }
            return hasDupes;
        }
    }

    public Document getDocumentByID(long theDocumentID) {
        Document theDocument = null;
        ResultSet theResultSet = null;

        try {
            if (mGetDocumentStatement == null) {
                mGetDocumentStatement = mConnection.prepareStatement("SELECT * FROM document WHERE id = ?;");
            }
            mGetDocumentStatement.setLong(1, theDocumentID);
            theResultSet = mGetDocumentStatement.executeQuery();
            if (theResultSet.next()) {
                theDocumentID = theResultSet.getLong(1);
                theDocument = new Document(theDocumentID,
                        theResultSet.getString("filename"),      // filename
                        theResultSet.getString("path"),      // full path
                        theResultSet.getString("title"),      // title
                        theResultSet.getString("comments"),      // comments
                        theResultSet.getString("company"),      // company
                        theResultSet.getString("manager"),      // manager
                        theResultSet.getString("category"),      // category
                        theResultSet.getString("type"),      // type
                        theResultSet.getString("extension"),      // extension
                        theResultSet.getString("md5"),     // MD5
                        theResultSet.getInt("revision"),     // revision
                        theResultSet.getLong("editingtime"),       // editingTime
                        theResultSet.getString("application"),     // application
                        theResultSet.getTimestamp("creationdate"),       // creationDate
                        theResultSet.getTimestamp("lastsavedate"),       // lastSaveDate
                        theResultSet.getTimestamp("lastprintdate"),       // lastPrintDate
                        theResultSet.getInt("wordcount"),        // wordCount
                        theResultSet.getInt("pagecount"),        // pageCount
                        theResultSet.getInt("hiddencount"),        // hiddenCount
                        getDocumentAuthor(theDocumentID),
                        getDocumentEditor(theDocumentID));
                theDocument.setHasDuplicates(documentHasDuplicates(theDocumentID));
            }
            theResultSet.close();
        } catch (Exception e) {
            System.out.println("Error searching database: " + e.toString());
            e.printStackTrace();
        } finally {
            try {
                theResultSet.close();
            } catch (Exception e) {
            }
            return theDocument;
        }
    }

    public Author getDocumentAuthor(long theDocumentID) {
        Author theAuthor = null;

        ResultSet theResultSet = null;

        try {
            if (mGetAuthorStatement == null) {
                mGetAuthorStatement = mConnection.prepareStatement("SELECT id, document_id, author, author_type FROM author WHERE document_id = ? AND author_type = ?;");
            }
            mGetAuthorStatement.setLong(1, theDocumentID);
            mGetAuthorStatement.setString(2, "Author");
            theResultSet = mGetAuthorStatement.executeQuery();
            if (theResultSet.next()) {
                theAuthor = new Author(theResultSet.getLong("id"), theResultSet.getLong("document_id"), theResultSet.getString("author"), theResultSet.getString("author_type"));
            }
            theResultSet.close();
        } catch (Exception e) {
            System.out.println("Error getting Document Author: " + e.toString());
            e.printStackTrace();
        } finally {
            try {
                theResultSet.close();
            } catch (Exception e) {
            }
            return theAuthor;
        }
    }

    public Author getDocumentEditor(long theDocumentID) {
        Author theAuthor = null;
        ResultSet theResultSet = null;

        try {
            if (mGetAuthorStatement == null) {
                mGetAuthorStatement = mConnection.prepareStatement("SELECT id, document_id, author, author_type FROM author WHERE document_id = ? AND author_type = ?;");
            }
            mGetAuthorStatement.setLong(1, theDocumentID);
            mGetAuthorStatement.setString(2, "Editor");
            theResultSet = mGetAuthorStatement.executeQuery();
            if (theResultSet.next()) {
                theAuthor = new Author(theResultSet.getLong("id"), theResultSet.getLong("document_id"), theResultSet.getString("author"), theResultSet.getString("author_type"));
            }
            theResultSet.close();
        } catch (Exception e) {
            System.out.println("Error getting Document Editor: " + e.toString());
            e.printStackTrace();
        } finally {
            try {
                theResultSet.close();
            } catch (Exception e) {
            }
            return theAuthor;
        }
    }

    public void updateSummaryInformation(long theDocID, String title, String comments, int revision, long editingTime, String application, Date creationDate, Date lastSaveDate,
            Date lastPrintDate, int wordCount, int pageCount) {
        try {
            if (mUpdateSummaryInfoStatement == null) {
                mUpdateSummaryInfoStatement = mConnection.prepareStatement("UPDATE document SET title = ?, comments = ?, revision = ?, editingtime = ?, " +
                        "application = ?, creationdate = ?, lastsavedate = ?, lastprintdate = ?, wordcount = ?, pagecount = ? WHERE id = ?;");
            }
            mUpdateSummaryInfoStatement.setString(1, title);
            mUpdateSummaryInfoStatement.setString(2, comments);
            mUpdateSummaryInfoStatement.setInt(3, revision);
            mUpdateSummaryInfoStatement.setLong(4, editingTime);
            mUpdateSummaryInfoStatement.setString(5, application);
            if (creationDate != null) {
                mUpdateSummaryInfoStatement.setTimestamp(6, new java.sql.Timestamp(creationDate.getTime()));
            } else {
                mUpdateSummaryInfoStatement.setTimestamp(6, null);
            }
            if (lastSaveDate != null) {
                mUpdateSummaryInfoStatement.setTimestamp(7, new java.sql.Timestamp(lastSaveDate.getTime()));
            } else {
                mUpdateSummaryInfoStatement.setTimestamp(7, null);
            }
            if (lastPrintDate != null) {
                mUpdateSummaryInfoStatement.setTimestamp(8, new java.sql.Timestamp(lastPrintDate.getTime()));
            } else {
                mUpdateSummaryInfoStatement.setTimestamp(8, null);
            }
            mUpdateSummaryInfoStatement.setInt(9, wordCount);
            mUpdateSummaryInfoStatement.setInt(10, pageCount);
            mUpdateSummaryInfoStatement.setLong(11, theDocID);
            mUpdateSummaryInfoStatement.execute();
        } catch (Exception e) {
            System.out.println("Error updating Summary Information: " + e.toString());
            e.printStackTrace();
        }
    }

    public void updateDocumentSummaryInformation(long theDocID, String company, String manager, String category, int hiddenCount) {
        try {
            if (mUpdateDocumentSummaryInfoStatement == null) {
                mUpdateDocumentSummaryInfoStatement = mConnection.prepareStatement("UPDATE document SET company = ?, manager = ?, category = ?, hiddencount = ? WHERE id = ?;");
            }

            mUpdateDocumentSummaryInfoStatement.setString(1, company);
            mUpdateDocumentSummaryInfoStatement.setString(2, manager);
            mUpdateDocumentSummaryInfoStatement.setString(3, category);
            mUpdateDocumentSummaryInfoStatement.setInt(4, hiddenCount);
            mUpdateDocumentSummaryInfoStatement.setLong(5, theDocID);
            mUpdateDocumentSummaryInfoStatement.execute();
        } catch (Exception e) {
            System.out.println("Error updating Document Summary Information: " + e.toString());
            e.printStackTrace();
        }
    }

    public void createDatabaseTables() {
        try {
            if (mCreateDocumentTableStatement == null) {
                mCreateDocumentTableStatement = mConnection.prepareStatement("CREATE CACHED TABLE document (id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY, " +
                    " filename VARCHAR NOT NULL, path VARCHAR NOT NULL, title VARCHAR, comments VARCHAR, company VARCHAR, manager VARCHAR, category VARCHAR," +
                    " type VARCHAR, extension VARCHAR, md5 VARCHAR NOT NULL, revision BIGINT, editingtime BIGINT, application VARCHAR, creationdate DATETIME, " +
                    " lastsavedate DATETIME, lastprintdate DATETIME, wordcount INTEGER, pagecount INTEGER, hiddencount INTEGER);");
            }
            if (mCreateAuthorTableStatement == null) {
                mCreateAuthorTableStatement = mConnection.prepareStatement("CREATE CACHED TABLE author (id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY, " +
                    "document_id INTEGER NOT NULL, author VARCHAR, author_type VARCHAR NOT NULL, " +
                    "CONSTRAINT FK FOREIGN KEY (document_id) REFERENCES document(id) ON DELETE CASCADE);");
            }
            if (mCreateDuplicateTableStatement == null) {
                mCreateDuplicateTableStatement = mConnection.prepareStatement("CREATE CACHED TABLE duplicate (id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY, " +
                        "document_id INTEGER NOT NULL, filename VARCHAR NOT NULL, path VARCHAR NOT NULL, CONSTRAINT FK2 FOREIGN KEY (document_id) REFERENCES document(id) ON DELETE CASCADE);");
            }
            mCreateDocumentTableStatement.execute();
            mCreateAuthorTableStatement.execute();
            mCreateDuplicateTableStatement.execute();
        } catch (Exception e) {
            System.out.println("Error creating database tables: " + e.toString());
            e.printStackTrace();
        }
    }

    public ResultSet getTopAuthors() {
        ResultSet theTopAuthors = null;

        try {
            if (mTopAuthorsStatement == null) {
                mTopAuthorsStatement = mConnection.prepareStatement("SELECT author, count(*) AS number FROM author WHERE author_type = 'Author' GROUP BY author ORDER BY number DESC;");
            }

            theTopAuthors = mTopAuthorsStatement.executeQuery();
        } catch (Exception e) {
            System.out.println("Error getting Top Authors: " + e.toString());
            e.printStackTrace();
        } finally {
            return theTopAuthors;
        }
    }

    public ResultSet getTopEditors() {
        ResultSet theTopEditors = null;

        try {
            if (mTopEditorsStatement == null) {
                mTopEditorsStatement = mConnection.prepareStatement("SELECT author, count(*) AS number FROM author WHERE author_type = 'Editor' GROUP BY author ORDER BY number DESC;");
            }

            theTopEditors = mTopEditorsStatement.executeQuery();
        } catch (Exception e) {
            System.out.println("Error getting Top Editors: " + e.toString());
            e.printStackTrace();
        } finally {
            return theTopEditors;
        }
    }

    public ResultSet getAllDocumentsByAuthor() {
        ResultSet allDocsByAuthor = null;

        try {
            if (mAllDocumentsByAuthorStatement == null) {
                mAllDocumentsByAuthorStatement = mConnection.prepareStatement("SELECT * FROM document JOIN author ON author.document_id = document.id WHERE author.author_type = 'Author' ORDER BY author.author;");
            }

            allDocsByAuthor = mAllDocumentsByAuthorStatement.executeQuery();
        } catch (Exception e) {
            System.out.println("Error getting all documents by author: " + e.toString());
            e.printStackTrace();
        }

        return allDocsByAuthor;
    }

    public ResultSet getAllDocumentsByApplication() {
        ResultSet allDocs = null;

        try {
            if (mAllDocumentsStatement == null) {
                mAllDocumentsStatement = mConnection.prepareStatement("SELECT * FROM document WHERE application IS NOT NULL ORDER BY application;");
            }

            allDocs = mAllDocumentsStatement.executeQuery();
        } catch (Exception e) {
            System.out.println("Error getting all documents by application: " + e.toString());
            e.printStackTrace();
        }

        return allDocs;
    }

    public ResultSet getTopDocumentsByEditingTime() {
        ResultSet theTopDocuments = null;

        try {
            if (mTopEditedDocumentsStatement == null) {
                mTopEditedDocumentsStatement = mConnection.prepareStatement("SELECT filename, md5, revision, editingtime FROM document WHERE editingtime IS NOT NULL ORDER BY editingtime DESC;");
            }

            theTopDocuments = mTopEditedDocumentsStatement.executeQuery();
        } catch (Exception e) {
            System.out.println("Error getting top edited documents: " + e.toString());
            e.printStackTrace();
        }

        return theTopDocuments;
    }

    public ResultSet getTopDocumentsByRevision() {
        ResultSet theTopDocuments = null;

        try {
            if (mTopRevisedDocumentsStatement == null) {
                mTopRevisedDocumentsStatement = mConnection.prepareStatement("SELECT filename, md5, editingtime, revision FROM document WHERE revision IS NOT NULL ORDER BY revision DESC;");
            }

            theTopDocuments = mTopRevisedDocumentsStatement.executeQuery();
        } catch (Exception e) {
            System.out.println("Error getting top revised documents: " + e.toString());
            e.printStackTrace();
        }

        return theTopDocuments;
    }

    public ResultSet getPrintedDocuments() {
        ResultSet thePrintedDocuments = null;

        try {
            if (mPrintedDocumentsStatement == null) {
                mPrintedDocumentsStatement = mConnection.prepareStatement("SELECT * from document WHERE lastprintdate IS NOT NULL ORDER BY lastprintdate DESC;");
            }

            thePrintedDocuments = mPrintedDocumentsStatement.executeQuery();
        } catch (Exception e) {
            System.out.println("Error getting printed documents: " + e.toString());
            e.printStackTrace();
        }

        return thePrintedDocuments;
    }

    public ResultSet getEditedDocuments() {
        ResultSet theEditedDocuments = null;

        try {
            if (mEditedDocumentsStatement == null) {
                mEditedDocumentsStatement = mConnection.prepareStatement("SELECT * from document WHERE lastsavedate IS NOT NULL ORDER BY lastsavedate DESC;");
            }

            theEditedDocuments = mEditedDocumentsStatement.executeQuery();
        } catch (Exception e) {
            System.out.println("Error getting recently edited documents: " + e.toString());
            e.printStackTrace();
        }

        return theEditedDocuments;
    }
    
    public ResultSet getAllDocumentIDs() {
        ResultSet allDocumentIDs = null;

        try {
            if (mAllDocumentIDsStatement == null) {
                mAllDocumentIDsStatement = mConnection.prepareStatement("SELECT id FROM document");
            }

            allDocumentIDs = mAllDocumentIDsStatement.executeQuery();
        } catch (Exception e) {
            System.out.println("Error getting all document IDs: " + e.toString());
            e.printStackTrace();
        }

        return allDocumentIDs;
    }
    
    public ArrayList getAuthorEditorPairs() {
        ResultSet theDocumentIDs = getAllDocumentIDs();
        ArrayList<AuthorEditorPair> thePairs = new ArrayList();

        try {
            while(theDocumentIDs.next()) {
                long theId = theDocumentIDs.getLong(1);
                AuthorEditorPair thePair = new AuthorEditorPair(getDocumentAuthor(theId), getDocumentEditor(theId));
                thePairs.add(thePair);
            }
            theDocumentIDs.close();
        } catch (SQLException e) {
            System.out.println("Error getting Author/Editor Pairs: " + e.toString());
            e.printStackTrace();
        }
        
        return thePairs;
    }

    /**
     * Get the value of mConnection
     *
     * @return the value of mConnection
     */
    public static Connection getMConnection() {
        return mConnection;
    }

    public void saveDB() {
        String theSQL = "COMMIT;";
        Statement theStatement = null;

        try {
            theStatement = mConnection.createStatement();
            theStatement.execute(theSQL);
        } catch (Exception e) {
            System.out.println("Error committing HSQLDB: " + e.toString());
            e.printStackTrace();
        } finally {
            try {
                theStatement.close();
            } catch (Exception e) {
            }
        }
    }

    public void shutdownDB() {
        // statements to shut down the database go here.
        String theSQL = "SHUTDOWN;";
        Statement theStatement = null;

        try {
            theStatement = mConnection.createStatement();
            theStatement.execute(theSQL);
            theStatement.close();
        } catch (Exception e) {
            System.out.println("Error shutting down HSQLDB: " + e.toString());
            e.printStackTrace();
        } finally {
            try {
                theStatement.close();
            } catch (Exception e) {
            }
        }
    }
}
