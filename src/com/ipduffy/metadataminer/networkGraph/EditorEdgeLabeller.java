/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipduffy.metadataminer.networkGraph;

/**
 *
 * @author iduffy
 */
public class EditorEdgeLabeller implements org.apache.commons.collections15.Transformer<EditorEdge,String>{

    public EditorEdgeLabeller() {
    }

    public String transform(EditorEdge i) {
        return Integer.toString(i.getDocumentCount());
    }
    
}
