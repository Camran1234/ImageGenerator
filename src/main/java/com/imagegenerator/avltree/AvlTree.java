/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imagegenerator.avltree;

import java.util.ArrayList;

/**
 *
 * @author camran1234
 */
public class AvlTree {
    private NodeAvl nodeAvl;
    
    public void addNode(NodeAvl newNode){
        if(nodeAvl==null){
            nodeAvl=newNode;
        }else{
            //Inserting the new node and resize the balanceFactor
            nodeAvl.insertNode(newNode);
            //We make rotates if it's neccesary and the root will be the one who returns the method
            nodeAvl = nodeAvl.checkBalanceFact();
            System.out.println("Hola Mundo");
        }
    }
    
    public NodeAvl findNode(String id){
        NodeAvl nodeFound=null;
        if(nodeAvl==null){
            return nodeFound;
        }else{
           nodeFound =  nodeAvl.findNode(id);
        }
        return nodeFound;
    }
    
    public void pullNode(String nodeId){
        NodeAvl aux = nodeAvl;
        NodeAvl father = aux;
        int selec=0;
        while(aux!=null){
            if(aux.getId().compareToIgnoreCase(nodeId)<0){
            //rightNode.deleteNode(nodeId);
            father = aux;
            aux = aux.getRightNode(); 
            aux.setLeftNode(new BalanceFactorHandler().checkRotate(aux.getLeftNode()));
            aux.setRightNode(new BalanceFactorHandler().checkRotate(aux.getRightNode()));
            selec=1;
            }else if(aux.getId().compareToIgnoreCase(nodeId)>0){
              //  answer = leftNode.deleteNode(nodeId);
            father = aux;             
            aux = aux.getLeftNode(); 
            aux.setLeftNode(new BalanceFactorHandler().checkRotate(aux.getLeftNode()));
            aux.setRightNode(new BalanceFactorHandler().checkRotate(aux.getRightNode()));
            selec=2;
            }else{                   
                if(aux.getLeftNode()!=null && aux.getRightNode()!=null){                
                    //Retiramos                    
                    father = father.getLeftNode().getLastElementRight(father);
                    father = new BalanceFactorHandler().checkRotate(father);
                }else if(aux.getLeftNode()==null && aux.getRightNode()!=null){                
                    if(selec==1){
                        father.setRightNode(aux.getRightNode());
                    }else if(selec==2){
                        father.setLeftNode(aux.getRightNode());
                    }
                    father.setBalanceFactor(father.getBalanceFactor()-1);
                }else if(aux.getLeftNode()!=null && aux.getRightNode()==null){                
                    father = father.getLeftNode().getLastElementRight(father);
                    father = new BalanceFactorHandler().checkRotate(father);                    
                }else if(aux.getLeftNode()==null && aux.getRightNode()==null){                
                    if(selec==1){
                        father.setRightNode(null);
                    }else if(selec==2){
                        father.setLeftNode(null);
                    }
                    father.setBalanceFactor(father.getBalanceFactor()-1);
                }
            }
            aux = null;
            break;
        }
    }
    
    public String getGraphvizCode(){
        StringBuffer string = new StringBuffer();
        string.append("digraph g {\n");
        //Generate the graphviz code
        if(nodeAvl!=null){
            string.append(nodeAvl.generateGraphvizCode());
        }
        
        string.append("}\n");
        return string.toString();
    }
    
    public ArrayList<String> getUsers(){
        ArrayList<String> users = new ArrayList<>();
        nodeAvl.getUsers(users);
        return users;
    }
    
    
    
}
