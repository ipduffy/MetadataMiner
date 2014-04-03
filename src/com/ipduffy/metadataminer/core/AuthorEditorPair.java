/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipduffy.metadataminer.core;

/**
 *
 * @author iduffy
 */
public class AuthorEditorPair {
    private Author theAuthor;
    private Author theEditor;

    public AuthorEditorPair(Author theAuthor, Author theEditor) {
        this.theAuthor = theAuthor;
        this.theEditor = theEditor;
    }

    public AuthorEditorPair() {
    }

    public Author getAuthor() {
        return theAuthor;
    }

    public void setAuthor(Author theAuthor) {
        this.theAuthor = theAuthor;
    }

    public Author getEditor() {
        return theEditor;
    }

    public void setEditor(Author theEditor) {
        this.theEditor = theEditor;
    }
}
