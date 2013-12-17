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

public class AppXMLParser extends org.xml.sax.helpers.DefaultHandler {

    private XMLReader xmlReader;
    protected String lastElement = null;

    // The parent parser where we send all the variable data
    private OfficeXMLParser mParent = null;

    // Fields for the document
    private String mApplication = null;
    private String mAppVersion = null;
    private String mWordCount = null;
    private String mPageCount = null;
    private String mEditingTime = null;
    private String mManager = null;
    private String mCompany = null;

    
    /** Creates new SAXAlertParser. */
    public AppXMLParser(OfficeXMLParser theParent) {
        if (theParent == null) {
            throw new NullPointerException("Null OfficeXMLParser passed to AppXMLParser.");
        }

        mParent = theParent;

        SAXParserFactory parserFactory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = parserFactory.newSAXParser();
            xmlReader = parser.getXMLReader();
        } catch (Exception e) {
            System.out.println("Error in constructor of AppXMLParser: " + e.toString());
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
            if (lastElement.equals("Pages")) {
                if (mPageCount == null) {
                    mPageCount = textData;
                } else {
                    mPageCount += textData;
                }
            }
            if (lastElement.equals("Words")) {
                if (mWordCount == null) {
                    mWordCount = textData;
                } else {
                    mWordCount += textData;
                }
            }
            if (lastElement.equals("Application")) {
                if (mApplication == null) {
                    mApplication = textData;
                } else {
                    mApplication += textData;
                }
            }
            if (lastElement.equals("TotalTime")) {
                if (mEditingTime == null) {
                    mEditingTime = textData;
                } else {
                    mEditingTime += textData;
                }
            }
            if (lastElement.equals("AppVersion")) {
                if (mAppVersion == null) {
                    mAppVersion = textData;
                } else {
                    mAppVersion += textData;
                }
            }
            if (lastElement.equals("Manager")) {
                if (mManager == null) {
                    mManager = textData;
                } else {
                    mManager += textData;
                }
            }
            if (lastElement.equals("Company")) {
                if (mCompany == null) {
                    mCompany = textData;
                } else {
                    mCompany += textData;
                }
            }
        }
    }
    
    public void endElement(String filename, String localName, String qualifiedName) {
    }

    public void endDocument() {
        try {
            if (mWordCount != null) {
                int theWordCount = Integer.parseInt(mWordCount);
                mParent.setWordCount(theWordCount);
            }
            if (mPageCount != null) {
                int thePageCount = Integer.parseInt(mPageCount);
                mParent.setPageCount(thePageCount);
            }
            if (mEditingTime != null) {
                long theEditingTimeInMinutes = Long.parseLong(mEditingTime);
                mParent.setEditingTime(theEditingTimeInMinutes * 60000);
            }
            mParent.setApplication(mApplication + " " + mAppVersion);
            mParent.setManager(mManager);
            mParent.setCompany(mCompany);
        } catch (Exception e) {
            System.out.println("Error parsing application values in AppXMLParser: " + e.toString());
            e.printStackTrace();
        }
    }
}
