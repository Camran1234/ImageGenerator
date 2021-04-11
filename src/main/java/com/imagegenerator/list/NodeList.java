/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imagegenerator.list;

import com.imagegenerator.treebb.NodeBB;
import com.imagegenerator.matrix.SparseMatrix;

/**
 * For layers and image
 * @author camran1234
 */
public class NodeList <T>{
    private NodeList nextNode;
    private NodeList previousNode;
    private T node;
    private int id;
    
    public NodeList(int id,T node){
        this.node = node;
        this.id = id;
    }
    
    public void setPrevious(NodeList node){
        this.previousNode = node;
    }
    
    public NodeList getPrevious(){
        return previousNode;
    }
    
    public void setNext(NodeList node){
        this.nextNode = node;
    }
    
    public NodeList getNext(){
        return nextNode;
    }
    
    public T getObject(){
        return node;
    }
    
    public int getId(){
        return id;
    }
}
