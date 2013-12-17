/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ipduffy.metadataminer.dao;

import java.io.File;
import java.sql.*;
import java.util.Vector;
import org.hsqldb.jdbc.*;

/**
 *
 * @author duffian
 */
public final class DBConnectionManager {

    private static jdbcDataSource mJDBCDataSource = new jdbcDataSource();
    private static String dbURL = null;
    private static Connection mConnection = null;
    private static QueryFactory mQueryFactory = null;
    private static boolean isConnected = false;
    private static Vector dbConnectionListeners = new Vector();

    /**
     * Get the value of dbURL
     *
     * @return the value of dbURL
     */
    public static String getDbURL() {
        return dbURL;
    }

    public static void createConnection(File dbFile, boolean isNew) {
        try {
            dbURL = "jdbc:hsqldb:file:" + getBaseFileName(dbFile.getAbsolutePath());
            mJDBCDataSource.setDatabase(dbURL);

            mConnection = mJDBCDataSource.getConnection("SA", "");
            isConnected = true;

            // If this is a new database file, create the tables
            if (isNew) {
                QueryFactory theQueryFactory = DBConnectionManager.getQueryFactory();
                theQueryFactory.createDatabaseTables();
            }

            // Now we can notify listeners that the database is set up and ready to go
            for (int i=dbConnectionListeners.size() - 1; i>=0; i--) {
                if (dbConnectionListeners.get(i) == null) {
                    dbConnectionListeners.remove(i);
                } else {
                    DBConnectionListener theListener = (DBConnectionListener)dbConnectionListeners.get(i);
                    theListener.connected(isConnected);
                }
            }
        } catch (Exception e) {
            System.out.println("Error creating connection: " + e.toString());
            isConnected = false;
            mConnection = null;
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return mConnection;
    }

    public static QueryFactory getQueryFactory() throws Exception {
        if (mConnection == null) {
            throw new IllegalStateException("Unable to instantiate QueryFactory without a database connection.");
        }

        if (mQueryFactory == null) {
            mQueryFactory = new QueryFactory(mConnection);
        }
        
        return mQueryFactory;
    }

    public static void closeDBConnection() {
        if (mConnection == null) return;
        try {
            QueryFactory theQueryFactory = DBConnectionManager.getQueryFactory();
            theQueryFactory.shutdownDB();
        } catch (Exception e) {
            System.out.println("Error shutting down DB: " + e.toString());
            e.printStackTrace();
        }
    }

    public static boolean isConnected() {
        return isConnected;
    }

    public static void addConnectionListener(DBConnectionListener aListener) {
        if (aListener != null) {
            dbConnectionListeners.add(aListener);
        }
    }

    private static String getBaseFileName(String aFileName) {
        if (aFileName != null) {
            if (aFileName.indexOf(".") < 0) {
                return(aFileName);
            } else {
                return (aFileName.substring(0, aFileName.lastIndexOf(".")));
            }
        }
        return(aFileName);
    }
}
