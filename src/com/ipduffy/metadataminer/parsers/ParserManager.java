/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ipduffy.metadataminer.parsers;

import java.util.ArrayList;
/**
 *
 * @author duffian
 */

public class ParserManager {

    private static ArrayList mParserListeners = new ArrayList();

    public ParserManager() {
    }

    public static void addParserListener(ParserListener aListener) {
        if (aListener != null) {
            mParserListeners.add(aListener);
        }
    }

    public static void parsingBegan() {
        for (int i=mParserListeners.size() - 1; i>=0; i--) {
            if (mParserListeners.get(i) == null) {
                mParserListeners.remove(i);
            } else {
                ParserListener theListener = (ParserListener)mParserListeners.get(i);
                theListener.parsingBegan();
            }
        }
    }

    public static void parsingEnded() {
        for (int i=mParserListeners.size() - 1; i>=0; i--) {
            if (mParserListeners.get(i) == null) {
                mParserListeners.remove(i);
            } else {
                ParserListener theListener = (ParserListener)mParserListeners.get(i);
                theListener.parsingEnded();
            }
        }
    }
}
