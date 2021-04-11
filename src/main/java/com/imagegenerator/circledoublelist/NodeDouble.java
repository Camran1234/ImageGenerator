/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imagegenerator.circledoublelist;

import com.imagegenerator.list.SimpleDoubleList;
import com.imagegenerator.treebb.NodeBB;
import java.util.ArrayList;

/**
 * Where we save our images
 * @author camran1234
 */
public class NodeDouble {
    private NodeDouble next;
    private NodeDouble previous;
    private int image;
    private SimpleDoubleList layers = new SimpleDoubleList();
    
    public NodeDouble(int image){
        this.image = image;
    }
    
    public void insert(NodeDouble newNode){
        newNode.setNext(next);
        newNode.setPrevious(this);
        next = newNode;
    }

    /**
     * Add a new Layer Node
     * @param node 
     */
    public void insertLayer(NodeBB node){
        layers.addNode(node.getIdUser(),node);
    }
    
    public void setNext(NodeDouble next) {
        this.next = next;
    }

    public void setPrevious(NodeDouble previous) {
        this.previous = previous;
    }

    public NodeDouble getNext() {
        return next;
    }

    public NodeDouble getPrevious() {
        return previous;
    }
    
    public int getId(){
        return image;   
    }
    
    public void setId(int id){
        this.image = id;
    }
    
    /**
     * Get just the nodes
     * @return 
     */
    public String getGraphvizCode(){
        StringBuffer string = new StringBuffer();
        //Select the image
        string.append(layers.getGraphvizCode(String.valueOf(image),'n'));
        //Get the nodes code
        return string.toString();
    }
    
    public String getGraphvizCodePointer(){
        StringBuffer string = new StringBuffer();
        //Select the image
        string.append(layers.getGraphvizCode(String.valueOf(image),'n'));
        string.append(layers.generatePointerCode(String.valueOf(image)));
        //Get the nodes code
        return string.toString();
    }
    
    /**
     * Get the nodes list as array
     * @return 
     */
    public ArrayList<NodeBB> getNodesAsArray(){
        ArrayList<NodeBB> nodes = new ArrayList<>();
        layers.getNodes(nodes);
        return nodes;
    }
    
}
