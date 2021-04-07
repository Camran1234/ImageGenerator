/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imagegenerator.avltree;

/**
 *
 * @author camran1234
 */
public class NodeAvl <T>{
    private int balanceFactor=0;
    private int id;
    private NodeAvl rightNode=null;
    private NodeAvl leftNode=null;
    private T object=null;
    
    public NodeAvl (int id,T object){
        this.object = object;
        this.id = id;
        this.balanceFactor = 1;
    }
    public NodeAvl(int id){
        this.id = id;
        this.balanceFactor = 1;
    }
    
    public NodeAvl(NodeAvl auxNode){
        this.object = (T) auxNode.getObject();
        this.id = auxNode.getId();
    }
    
    boolean deleteNode(int nodeId){
        boolean answer=false;
        if(nodeId>this.id){
            answer = rightNode.deleteNode(nodeId);
        }else if(nodeId<this.id){
            answer = leftNode.deleteNode(nodeId);
        }else{
            BalanceFactorHandler handler = new BalanceFactorHandler();
            NodeAvl node = handler.findNode(this);
            this.id = node.getId();
            this.object = (T) node.getObject();
            node=null;
            handler.resizeBalanceFactor(this);
        }
        return answer;
    }
    
    public NodeAvl getLastElementRight(){
        NodeAvl aux=this;
        if(this.rightNode!=null){
            aux = this.rightNode.getLastElementRight();
        }
        
        return aux;
    }
    
    /**
     * Function to insert new nodes by id and reasign balanceFactor
     * If an id already exists the object inside the node will be change
     * @param node 
     */
    int insertNode(NodeAvl node){
        int auxfact=0;
        //We make the comprobation of were we put the node
        if(node.getId() >= this.id){
            //If its null will be added just once
            if(rightNode!=null){
                auxfact = rightNode.insertNode(node);
            }else{
                auxfact = 1;
                rightNode = node;
            }
        }else if(node.getId() <this.id){
            if(leftNode!=null){
                auxfact = leftNode.insertNode(node);
            }else{
                auxfact = 1;
                leftNode = node;
            }
        }
        
        //We adjust the balanceFactor if the recovery ones are the same
        if(auxfact==balanceFactor){
            this.balanceFactor++;
            auxfact = balanceFactor;
        }
        
        leftNode = new BalanceFactorHandler().checkRotate(leftNode);
        rightNode = new BalanceFactorHandler().checkRotate(rightNode);
        return auxfact;
    }
    
    public NodeAvl checkRotate(){
        NodeAvl node=this;
        int rightFact=0;
        int leftFact=0;
        int result = 0;
        if(rightNode!=null){
            rightFact = rightNode.getBalanceFactor();
        }
        if(leftNode!=null){
            leftFact = leftNode.getBalanceFactor();
        }
        result = rightFact - leftFact;
        
        //Is bigger in the left side
        if(result<-1){
            if(result==-3){
                this.leftNode.checkRotate();
            }else if(result==-2){
                node = simpleRightRotate();
            }
            //Is bigger in the right side
        }else if(result>1){
            if(result==3){
                this.rightNode.checkRotate();
            }else if(result==2){
                node = simpleLeftRotate();
            }
        }
        
        return node;
    }
    
    //Do a simple rotate to the right
    //It requies a left node otherwise it can't do anything
    private NodeAvl simpleRightRotate(){
        NodeAvl rootNode = this.leftNode;
        NodeAvl leftNode = null;
        //The actual root
        NodeAvl rightNode = null;
        //i'
        leftNode = this.leftNode.getLeftNode();
        //d'
        rightNode = this.leftNode.getRightNode();
        
        rootNode.setLeftNode(leftNode);
        rootNode.setRightNode(this);
        this.setLeftNode(rightNode);
        
        //We adjust the new balance factor
        BalanceFactorHandler handlerBalance = new BalanceFactorHandler();
        handlerBalance.resizeBalanceFactor(rootNode.getRightNode());
        handlerBalance.resizeBalanceFactor(rootNode);
        return rootNode;
    }
    
    
    
    private NodeAvl simpleLeftRotate(){
        NodeAvl rootNode = this.rightNode;
        //beforeRoot
        NodeAvl leftNode = this;
        //The actual root
        //'d
        NodeAvl rightNode = this.rightNode.getRightNode();
        
        
        rootNode.setRightNode(rightNode);
        rootNode.setLeftNode(leftNode);
        this.setRightNode(this.rightNode.getLeftNode());
        
        //We adjust the new balance factor
        BalanceFactorHandler handlerBalance = new BalanceFactorHandler();
        handlerBalance.resizeBalanceFactor(rootNode.getLeftNode());
        handlerBalance.resizeBalanceFactor(rootNode);
        
        return rootNode;
    }
    
    public int getId(){
        return this.id;
    }
    
    //Getter and Setters
    public int getBalanceFactor() {
        return balanceFactor;
    }
    
    

    public void setBalanceFactor(int height) {
        this.balanceFactor = height;
    }

    public NodeAvl getRightNode() {
        return rightNode;
    }

    public void setRightNode(NodeAvl rightNode) {
        this.rightNode = rightNode;
    }

    public NodeAvl getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(NodeAvl leftNode) {
        this.leftNode = leftNode;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }
    
    
    
}
