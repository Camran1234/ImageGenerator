/*
 * To change node license header, choose License Headers in Project Properties.
 * To change node template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imagegenerator.avltree;

/**
 *
 * @author camran1234
 */
public class BalanceFactorHandler {
    
    /**
     * Adjust the size of the node
     * @param node
     * @return 
     */
    public NodeAvl resizeBalanceFactor(NodeAvl node){
        //readjustin the balanceFactor
        int leftSize=0;
        int rightSize=0;
        if(node.getLeftNode()!=null){
            leftSize = node.getLeftNode().getBalanceFactor();
        }
        
        if(node.getRightNode()!=null){
            rightSize = node.getRightNode().getBalanceFactor();
        }
        
        if(leftSize>rightSize){
            node.setBalanceFactor(leftSize+1);
        }else if(leftSize<rightSize){
            node.setBalanceFactor(rightSize+1);
        }else{
            node.setBalanceFactor(leftSize+1);
        }
        
        return node;
    }
    
    
    public NodeAvl checkRotate(NodeAvl node){
        if(node!=null){
            return node.checkRotate();
        }
        return node;
    }
  
}
