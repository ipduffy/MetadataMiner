/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipduffy.metadataminer.networkGraph;

/**
 *
 * @author iduffy
 */
public class AuthorNode {
    private String name;
    private int documentCount;

    public AuthorNode(String name) {
        this.name = name;
        this.documentCount = 0;
    }
    
    public void incrementDocumentCount() {
        documentCount++;
    }
    
    public int getDocumentCount() {
        return documentCount;
    }

    @Override
    public String toString() {
        return "AuthorNode{name=" + name + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + (this.name != null ? this.name.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AuthorNode other = (AuthorNode) obj;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        return true;
    }
    
}
