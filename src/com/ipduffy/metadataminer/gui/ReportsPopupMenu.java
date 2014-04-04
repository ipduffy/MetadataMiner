/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ipduffy.metadataminer.gui;

import com.ipduffy.metadataminer.core.Author;
import com.ipduffy.metadataminer.core.AuthorEditorPair;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import com.ipduffy.metadataminer.dao.*;
import com.ipduffy.metadataminer.networkGraph.AuthorNode;
import com.ipduffy.metadataminer.networkGraph.AuthorNodeLabeller;
import com.ipduffy.metadataminer.networkGraph.EditorEdge;
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import java.awt.Color;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.*;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.style.*;
import net.sf.dynamicreports.report.constant.*;
import static net.sf.dynamicreports.report.builder.DynamicReports.*;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author duffian
 */

public class ReportsPopupMenu extends javax.swing.JPopupMenu {

    private JMenuItem mTopAuthorsMenuItem = new JMenuItem("Top Authors");
    private JMenuItem mTopEditorsMenuItem = new JMenuItem("Top Editors");
    private JMenuItem mAllDocumentsByAuthorMenuItem = new JMenuItem("All Documents by Author");
    private JMenuItem mAllDocumentsByApplicationMenuItem = new JMenuItem("All Documents by Application");
    private JMenuItem mEditingTimeMenuItem = new JMenuItem("Most Heavily Edited Documents (by Editing Time)");
    private JMenuItem mRevisionMenuItem = new JMenuItem("Most Heavily Edited Documents (by Revision Number)");
    private JMenuItem mPrintedDocsMenuItem = new JMenuItem("Recently Printed Documents");
    private JMenuItem mEditedDocsMenuItem = new JMenuItem("Recently Edited Documents");
    private JMenuItem mNetworkGraphMenuItem = new JMenuItem("Network Graph");

    public ReportsPopupMenu() {
        super();

        add(mTopAuthorsMenuItem);
        add(mTopEditorsMenuItem);
        add(mAllDocumentsByAuthorMenuItem);
        add(mAllDocumentsByApplicationMenuItem);
        add(mEditingTimeMenuItem);
        add(mRevisionMenuItem);
        add(mPrintedDocsMenuItem);
        add(mEditedDocsMenuItem);
        add(new JSeparator());
        add(mNetworkGraphMenuItem);

        mTopAuthorsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                topAuthorsMenuItemActionPerformed(evt);
            }
        });

        mTopEditorsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                topEditorsMenuItemActionPerformed(evt);
            }
        });

        mAllDocumentsByAuthorMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                allDocumentsByAuthorMenuItemActionPerformed(evt);
            }
        });

        mAllDocumentsByApplicationMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                allDocumentsByApplicationMenuItemActionPeformed(evt);
            }
        });

        mEditingTimeMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                editingTimeMenuItemActionPerformed(evt);
            }
        });

        mRevisionMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                revisionMenuItemActionPerformed(evt);
            }
        });

        mPrintedDocsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                printedDocsMenuItemActionPerformed(evt);
            }
        });

        mEditedDocsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                editedDocsMenuItemActionPerformed(evt);
            }
        });
        
        mNetworkGraphMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                networkGraphMenuItemActionPerformed(evt);
            }
        });
    }

    private void topAuthorsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        JasperReportBuilder rb = DynamicReports.report();

        TextColumnBuilder authorColumn = col.column("Author", "author", type.stringType());
        TextColumnBuilder countColumn = col.column("# Documents", "number", type.integerType());

        StyleBuilder boldStyle = stl.style().bold();
        StyleBuilder boldCenteredStyle = stl.style(boldStyle).setHorizontalAlignment(HorizontalAlignment.CENTER);
        StyleBuilder columnTitleStyle = stl.style(boldCenteredStyle).setBorder(stl.pen1Point()).setBackgroundColor(Color.LIGHT_GRAY);

        rb.columns(authorColumn, countColumn);
        rb.setColumnTitleStyle(columnTitleStyle);
        rb.highlightDetailEvenRows();
        rb.title(cmp.text("Top Document Authors").setStyle(boldCenteredStyle));

        ResultSet rs = null;
        
        try {
            QueryFactory theQueryFactory = DBConnectionManager.getQueryFactory();
            rs = theQueryFactory.getTopAuthors();
            rb.setDataSource(rs);
            rb.show(false);
        } catch (Exception e) {
            System.out.println("Error creating top authors report: " + e.toString());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
            }
        }
    }

    private void topEditorsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        JasperReportBuilder rb = DynamicReports.report();

        TextColumnBuilder authorColumn = col.column("Editor", "author", type.stringType());
        TextColumnBuilder countColumn = col.column("# Documents", "number", type.integerType());

        StyleBuilder boldStyle = stl.style().bold();
        StyleBuilder boldCenteredStyle = stl.style(boldStyle).setHorizontalAlignment(HorizontalAlignment.CENTER);
        StyleBuilder columnTitleStyle = stl.style(boldCenteredStyle).setBorder(stl.pen1Point()).setBackgroundColor(Color.LIGHT_GRAY);

        rb.columns(authorColumn, countColumn);
        rb.setColumnTitleStyle(columnTitleStyle);
        rb.highlightDetailEvenRows();
        rb.title(cmp.text("Top Document Editors").setStyle(boldCenteredStyle));

        ResultSet rs = null;

        try {
            QueryFactory theQueryFactory = DBConnectionManager.getQueryFactory();
            rs = theQueryFactory.getTopEditors();
            rb.setDataSource(rs);
            rb.show(false);
        } catch (Exception e) {
            System.out.println("Error creating top editors report: " + e.toString());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
            }
        }
    }

    private void allDocumentsByAuthorMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        JasperReportBuilder rb = DynamicReports.report();

        TextColumnBuilder fileNameColumn = col.column("File name", "filename", type.stringType());
        TextColumnBuilder md5Column = col.column("MD5 Hash", "md5", type.stringType());
        TextColumnBuilder titleColumn = col.column("Title", "title", type.stringType());
        TextColumnBuilder companyColumn = col.column("Company", "company", type.stringType());
        TextColumnBuilder authorColumn = col.column("Author", "author", type.stringType());

        StyleBuilder boldStyle = stl.style().bold();
        StyleBuilder boldCenteredStyle = stl.style(boldStyle).setHorizontalAlignment(HorizontalAlignment.CENTER);
        StyleBuilder columnTitleStyle = stl.style(boldCenteredStyle).setBorder(stl.pen1Point()).setBackgroundColor(Color.LIGHT_GRAY);

        authorColumn.setStyle(boldStyle);

        rb.columns(fileNameColumn, md5Column, titleColumn, companyColumn);
        rb.setColumnTitleStyle(columnTitleStyle);
        rb.highlightDetailEvenRows();
        rb.groupBy(authorColumn);
        
        rb.title(cmp.text("All Documents by Author").setStyle(boldCenteredStyle));

        ResultSet rs = null;

        try {
            QueryFactory theQueryFactory = DBConnectionManager.getQueryFactory();
            rs = theQueryFactory.getAllDocumentsByAuthor();
            rb.setDataSource(rs);
            rb.show(false);
        } catch (Exception e) {
            System.out.println("Error creating all documents by author report: " + e.toString());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
            }
        }
    }

    private void allDocumentsByApplicationMenuItemActionPeformed(java.awt.event.ActionEvent evt) {
        JasperReportBuilder rb = DynamicReports.report();

        TextColumnBuilder fileNameColumn = col.column("File name", "filename", type.stringType());
        TextColumnBuilder md5Column = col.column("MD5 Hash", "md5", type.stringType());
        TextColumnBuilder titleColumn = col.column("Title", "title", type.stringType());
        TextColumnBuilder companyColumn = col.column("Company", "company", type.stringType());
        TextColumnBuilder applicationColumn = col.column("Application", "application", type.stringType());

        StyleBuilder boldStyle = stl.style().bold();
        StyleBuilder boldCenteredStyle = stl.style(boldStyle).setHorizontalAlignment(HorizontalAlignment.CENTER);
        StyleBuilder columnTitleStyle = stl.style(boldCenteredStyle).setBorder(stl.pen1Point()).setBackgroundColor(Color.LIGHT_GRAY);

        applicationColumn.setStyle(boldStyle);

        rb.columns(fileNameColumn, md5Column, titleColumn, companyColumn);
        rb.setColumnTitleStyle(columnTitleStyle);
        rb.highlightDetailEvenRows();
        rb.groupBy(applicationColumn);

        rb.title(cmp.text("All Documents by Application").setStyle(boldCenteredStyle));

        ResultSet rs = null;

        try {
            QueryFactory theQueryFactory = DBConnectionManager.getQueryFactory();
            rs = theQueryFactory.getAllDocumentsByApplication();
            rb.setDataSource(rs);
            rb.show(false);
        } catch (Exception e) {
            System.out.println("Error creating all documents by application report: " + e.toString());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
            }
        }
    }

    private void editingTimeMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        JasperReportBuilder rb = DynamicReports.report();

        TextColumnBuilder fileNameColumn = col.column("File name", "filename", type.stringType());
        TextColumnBuilder md5Column = col.column("MD5 Hash", "md5", type.stringType());
        TextColumnBuilder revisionColumn = col.column("Revision", "revision", type.integerType());
        TextColumnBuilder editingTimeColumn = col.column("Editing Time", "editingtime", type.integerType());

        StyleBuilder boldStyle = stl.style().bold();
        StyleBuilder boldCenteredStyle = stl.style(boldStyle).setHorizontalAlignment(HorizontalAlignment.CENTER);
        StyleBuilder columnTitleStyle = stl.style(boldCenteredStyle).setBorder(stl.pen1Point()).setBackgroundColor(Color.LIGHT_GRAY);

        rb.columns(fileNameColumn, md5Column, revisionColumn, editingTimeColumn);
        rb.setColumnTitleStyle(columnTitleStyle);
        rb.highlightDetailEvenRows();
        rb.title(cmp.text("Most Heavily Edited Documents (By Editing Time)").setStyle(boldCenteredStyle));

        ResultSet rs = null;

        try {
            QueryFactory theQueryFactory = DBConnectionManager.getQueryFactory();
            rs = theQueryFactory.getTopDocumentsByEditingTime();
            rb.setDataSource(rs);
            rb.show(false);
        } catch (Exception e) {
            System.out.println("Error creating top edited documents report: " + e.toString());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
            }
        }
    }

    private void revisionMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        JasperReportBuilder rb = DynamicReports.report();

        TextColumnBuilder fileNameColumn = col.column("File name", "filename", type.stringType());
        TextColumnBuilder md5Column = col.column("MD5 Hash", "md5", type.stringType());
        TextColumnBuilder editingTimeColumn = col.column("Editing Time", "editingtime", type.integerType());
        TextColumnBuilder revisionColumn = col.column("Revision", "revision", type.integerType());

        StyleBuilder boldStyle = stl.style().bold();
        StyleBuilder boldCenteredStyle = stl.style(boldStyle).setHorizontalAlignment(HorizontalAlignment.CENTER);
        StyleBuilder columnTitleStyle = stl.style(boldCenteredStyle).setBorder(stl.pen1Point()).setBackgroundColor(Color.LIGHT_GRAY);

        rb.columns(fileNameColumn, md5Column, editingTimeColumn, revisionColumn);
        rb.setColumnTitleStyle(columnTitleStyle);
        rb.highlightDetailEvenRows();
        rb.title(cmp.text("Most Heavily Edited Documents (By Revision Number)").setStyle(boldCenteredStyle));

        ResultSet rs = null;

        try {
            QueryFactory theQueryFactory = DBConnectionManager.getQueryFactory();
            rs = theQueryFactory.getTopDocumentsByRevision();
            rb.setDataSource(rs);
            rb.show(false);
        } catch (Exception e) {
            System.out.println("Error creating top edited documents report: " + e.toString());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
            }
        }
    }

    private void printedDocsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        JasperReportBuilder rb = DynamicReports.report();

        TextColumnBuilder fileNameColumn = col.column("File name", "filename", type.stringType());
        TextColumnBuilder md5Column = col.column("MD5 Hash", "md5", type.stringType());
        TextColumnBuilder titleColumn = col.column("Title", "title", type.stringType());
        TextColumnBuilder companyColumn = col.column("Company", "company", type.stringType());
        TextColumnBuilder datePrintedColumn = col.column("Date Printed", "lastprintdate", type.dateType());

        StyleBuilder boldStyle = stl.style().bold();
        StyleBuilder boldCenteredStyle = stl.style(boldStyle).setHorizontalAlignment(HorizontalAlignment.CENTER);
        StyleBuilder columnTitleStyle = stl.style(boldCenteredStyle).setBorder(stl.pen1Point()).setBackgroundColor(Color.LIGHT_GRAY);

        rb.columns(fileNameColumn, md5Column, titleColumn, companyColumn, datePrintedColumn);
        rb.setColumnTitleStyle(columnTitleStyle);
        rb.highlightDetailEvenRows();

        rb.title(cmp.text("Recently Printed Documents").setStyle(boldCenteredStyle));

        ResultSet rs = null;

        try {
            QueryFactory theQueryFactory = DBConnectionManager.getQueryFactory();
            rs = theQueryFactory.getPrintedDocuments();
            rb.setDataSource(rs);
            rb.show(false);
        } catch (Exception e) {
            System.out.println("Error creating printed documents report: " + e.toString());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
            }
        }
    }

    private void editedDocsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        JasperReportBuilder rb = DynamicReports.report();

        TextColumnBuilder fileNameColumn = col.column("File name", "filename", type.stringType());
        TextColumnBuilder md5Column = col.column("MD5 Hash", "md5", type.stringType());
        TextColumnBuilder titleColumn = col.column("Title", "title", type.stringType());
        TextColumnBuilder companyColumn = col.column("Company", "company", type.stringType());
        TextColumnBuilder datePrintedColumn = col.column("Date Saved", "lastsavedate", type.dateType());

        StyleBuilder boldStyle = stl.style().bold();
        StyleBuilder boldCenteredStyle = stl.style(boldStyle).setHorizontalAlignment(HorizontalAlignment.CENTER);
        StyleBuilder columnTitleStyle = stl.style(boldCenteredStyle).setBorder(stl.pen1Point()).setBackgroundColor(Color.LIGHT_GRAY);

        rb.columns(fileNameColumn, md5Column, titleColumn, companyColumn, datePrintedColumn);
        rb.setColumnTitleStyle(columnTitleStyle);
        rb.highlightDetailEvenRows();

        rb.title(cmp.text("Recently Edited/Saved Documents").setStyle(boldCenteredStyle));

        ResultSet rs = null;

        try {
            QueryFactory theQueryFactory = DBConnectionManager.getQueryFactory();
            rs = theQueryFactory.getEditedDocuments();
            rb.setDataSource(rs);
            rb.show(false);
        } catch (Exception e) {
            System.out.println("Error creating recently edited documents report: " + e.toString());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
            }
        }
    }
    
    private void networkGraphMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            QueryFactory theQueryFactory = DBConnectionManager.getQueryFactory();
            ArrayList <AuthorEditorPair>thePairs = theQueryFactory.getAuthorEditorPairs();
            
            if(thePairs.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No Data", "No data in database.", JOptionPane.INFORMATION_MESSAGE);
            }
            
            NetworkGraphFrame ngf = new NetworkGraphFrame();
            
            for(int i=0; i<thePairs.size(); i++) {
                AuthorEditorPair thePair = thePairs.get(i);
                Author theAuthor = thePair.getAuthor();
                Author theEditor = thePair.getEditor();
                
                if (theAuthor != null) {
                    ngf.addOrIncrementVertex(theAuthor.getAuthorName());
                    
                    if (theEditor != null) {
                        ngf.addVertex(theEditor.getAuthorName());
                        ngf.addOrIncrementEdge(theAuthor.getAuthorName(), theEditor.getAuthorName());
                    }
                }
            }
            
            ngf.setVisible(true); 
            //System.out.println(g.toString());
        } catch (Exception e) {
            System.out.println("Error building network graph: " + e.toString());
            e.printStackTrace();
        }
    }
}
