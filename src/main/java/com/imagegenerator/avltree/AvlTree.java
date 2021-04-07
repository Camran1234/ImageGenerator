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
public class AvlTree <T>{
    private NodeAvl nodeAvl;
    
    public void addNode(NodeAvl newNode){
        if(nodeAvl==null){
            nodeAvl=newNode;
        }else{
            //Inserting the new node and resize the balanceFactor
            nodeAvl.insertNode(newNode);
            //We make rotates if it's neccesary and the root will be the one who returns the method
            new BalanceFactorHandler().checkRotate(nodeAvl);
        }
    }
    
    public boolean deleteNode(int nodeId){
        boolean answer=false;
        answer = nodeAvl.deleteNode(nodeId);
        return answer;
    }
    
    
}
