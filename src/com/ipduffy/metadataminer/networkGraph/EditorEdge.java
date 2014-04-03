/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipduffy.metadataminer.networkGraph;

/**
 *
 * @author iduffy
 */
public class EditorEdge {
    private int documentCount;
    private double weight;
    
    public EditorEdge(double weight) {
        documentCount = 0;
        this.weight = weight;
    }

    public int getDocumentCount() {
        return documentCount;
    }
    
    public void incrementDocumentCount() {
        documentCount++;
    }
    
    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
    
    @Override
    public String toString() {
        return "EditorEdge{" + "documentCount=" + documentCount + '}';
    }
    
}
