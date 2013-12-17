/*
 * NmapParser.java
 *
 * Created on December 18, 2001, 6:35 PM
 */

/**
 * 
 * @author  duffian
 */
package com.ipduffy.metadataminer.parsers;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.parsers.*;
import org.xml.sax.*;

public class CoreXMLParser extends org.xml.sax.helpers.DefaultHandler {

    private XMLReader xmlReader;
    protected String lastElement = null;

    // The parent parser where we send all the variable data
    private OfficeXMLParser mParent = null;

    // Fields for the document
    private String mCreator = null;
    private String mEditor = null;
    private String mTitle = null;
    private String mCategory = null;
    private String mComments = null;
    private String mRevision = null;
    private String mCreationDate = null;
    private String mLastSaveDate = null;
    private String mLastPrintDate = null;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

    
    /** Creates new SAXAlertParser. */
    public CoreXMLParser(OfficeXMLParser theParent) {
        if (theParent == null) {
            throw new NullPointerException("Null OfficeXMLParser passed to CoreXMLParser.");
        }

        mParent = theParent;

        SAXParserFactory parserFactory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = parserFactory.newSAXParser();
            xmlReader = parser.getXMLReader();
        } catch (Exception e) {
            System.out.println("Error in constructor of CoreXMLParser: " + e.toString());
            e.printStackTrace();
        }
        
        xmlReader.setContentHandler(this);
        xmlReader.setErrorHandler(this);
    }
    
    public void parseInput(InputStream instream) {
        // set up the XML input source (created from the inputstream)
        InputSource in = new InputSource(instream);
        try {
            xmlReader.parse(in);
        } catch (Exception e) {
            System.out.println("Error in parseInput function in CoreXMLParser class: " + e.toString());
            e.printStackTrace();
        }
    }
    
    public void startElement(String filename, String localname, String qualifiedname, Attributes attributes) {
        lastElement = qualifiedname;
    }
    
    public void characters(char textChars[], int textStart, int textLength) {
        String textData = new String(textChars, textStart, textLength);

        textData = textData.trim();
        
        if (textData.length() > 0) {
            if (lastElement.equals("dc:creator")) {
                if (mCreator == null) {
                    mCreator = textData;
                } else {
                    mCreator += textData;
                }
            }
            if (lastElement.equals("dc:title")) {
                if (mTitle == null) {
                    mTitle = textData;
                } else {
                    mTitle += textData;
                }
            }
            if (lastElement.equals("dc:description")) {
                if (mComments == null) {
                    mComments = textData;
                } else {
                    mComments += textData;
                }
            }
            if (lastElement.equals("cp:lastModifiedBy")) {
                if (mEditor == null) {
                    mEditor = textData;
                } else {
                    mEditor += textData;
                }
            }
            if (lastElement.equals("cp:revision")) {
                if (mRevision == null) {
                    mRevision = textData;
                } else {
                    mRevision += textData;
                }
            }
            if (lastElement.equals("cp:lastPrinted")) {
                if (mLastPrintDate == null) {
                    mLastPrintDate = textData;
                } else {
                    mLastPrintDate += textData;
                }
            }
            if (lastElement.equals("dcterms:created")) {
                if (mCreationDate == null) {
                    mCreationDate = textData;
                } else {
                    mCreationDate += textData;
                }
            }
            if (lastElement.equals("dcterms:modified")) {
                if (mLastSaveDate == null) {
                    mLastSaveDate = textData;
                } else {
                    mLastSaveDate += textData;
                }
            }
            if (lastElement.equals("cp:category")) {
                if (mCategory == null) {
                    mCategory = textData;
                } else {
                    mCategory += textData;
                }
            }
        }
    }
    
    public void endElement(String filename, String localName, String qualifiedName) {
      
    }

    public void endDocument() {
        mParent.setAuthor(mCreator);
        mParent.setEditor(mEditor);
        mParent.setTitle(mTitle);
        mParent.setComments(mComments);
        mParent.setCategory(mCategory);

        try {
            if (mRevision != null) {
                int theRevision = Integer.parseInt(mRevision);
                mParent.setRevision(theRevision);
            }
            if (mCreationDate != null) {
                Date create = sdf.parse(mCreationDate);
                mParent.setCreationDate(create);
            }
            if (mLastSaveDate != null) {
                Date lastsave = sdf.parse(mLastSaveDate);
                mParent.setLastSaveDate(lastsave);
            }
            if (mLastPrintDate != null) {
                Date lastprint = sdf.parse(mLastPrintDate);
                mParent.setLastPrintDate(lastprint);
            }
        } catch (Exception e) {
            System.out.println("Error in CoreXML parser: " + e.toString());
            e.printStackTrace();
        }
    }
}
