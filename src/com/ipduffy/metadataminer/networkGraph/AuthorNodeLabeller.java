/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipduffy.metadataminer.networkGraph;

/**
 *
 * @author iduffy
 */
public class AuthorNodeLabeller implements org.apache.commons.collections15.Transformer<AuthorNode,String> {

    public AuthorNodeLabeller() {
    }

    public String transform(AuthorNode i) {
        return i.getName();
    }
    
}
