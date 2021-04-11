/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imagegenerator.matrix;

/**
 *
 * @author camran1234
 */
public class NodeMatrix <T> {
    private int x;
    private int y;
    private NodeMatrix antColumna, sigColumna, antFila, sigFila;
    private T object;
    
    /**
     * Constructor of a Node for a Sparse Matrix,
     * Getting the position of the matrix and the content
     * @param x
     * @param y
     * @param object 
     */
    public NodeMatrix(int x, int y, T object){
        this.x = x;
        this.y = y;
        this.object = object;
        antColumna=sigColumna=antFila=sigFila=null;
    }

    public NodeMatrix getAntColumna() {
        return antColumna;
    }

    public void setAntColumna(NodeMatrix antColumna) {
        this.antColumna = antColumna;
    }

    public NodeMatrix getSigColumna() {
        return sigColumna;
    }

    public void setSigColumna(NodeMatrix sigColumna) {
        this.sigColumna = sigColumna;
    }

    public NodeMatrix getAntFila() {
        return antFila;
    }

    public void setAntFila(NodeMatrix antFila) {
        this.antFila = antFila;
    }

    public NodeMatrix getSigFila() {
        return sigFila;
    }

    public void setSigFila(NodeMatrix sigFila) {
        this.sigFila = sigFila;
    }
    
    
    
    /**
     * Get the position in X
     * @return 
     */
    public int getX(){
        return x;
    }
    
    /**
     * Get the position in Y
     * @return 
     */
    public int getY(){
        return y;
    }
    
    /**
     * Get the node info
     * @return 
     */
    public T getObject(){
        return object;
    }
    
    
    
}
