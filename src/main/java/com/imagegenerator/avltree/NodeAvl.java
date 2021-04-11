/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imagegenerator.avltree;

import com.imagegenerator.circledoublelist.NodeDouble;
import com.imagegenerator.list.SimpleDoubleList;
import java.util.ArrayList;

/**
 * Node for save user and the images that contains
 * @author camran1234
 */
public class NodeAvl {
    private int balanceFactor=0;
    private String id;
    private NodeAvl rightNode=null;
    private NodeAvl leftNode=null;
    private SimpleDoubleList doubleList = new SimpleDoubleList();
   
    public NodeAvl(String id){
        this.id = id;
        this.balanceFactor = 1;
    }
    
    public NodeAvl(NodeAvl auxNode){
        this.id = auxNode.getId();
    }
    
    public void setNodeImage(NodeDouble node){
        doubleList.addNode(node.getId(),node);
    }
    
    
    public NodeAvl getLastElementRight(NodeAvl father){
        NodeAvl aux=this;
        if(this.rightNode!=null){
            aux = this.rightNode.getLastElementRight(aux);
        }else{
            father.setRightNode(null);
            father.setBalanceFactor(1);
        }
        if(father!=null){
            if(father.getRightNode()!=null){
                father.setBalanceFactor(father.getBalanceFactor()+1);
            }
        }
        return aux;
    }
    
    public NodeAvl findNode(String id){
        NodeAvl founded=null;
        if(this.id.compareToIgnoreCase(id)<0){
            //If its null will be added just once
            if(rightNode!=null){
                //Continue seraching
                founded = rightNode.findNode(id);
            }else{
                founded=null;
            }
        }else if(this.id.compareToIgnoreCase(id)>0){
            if(leftNode!=null){
                founded = leftNode.findNode(id);
            }else{
                founded = null;
            }
        }else{
            founded = this;
        }
        
        return founded;
    }
    
    
    
    /**
     * Function to insert new nodes by id and reasign balanceFactor
     * If an id already exists the object inside the node will be change
     * @param node 
     */
    int insertNode(NodeAvl node){
        int auxfact=0;
        
        //We make the comprobation of were we put the node
        if(this.id.compareToIgnoreCase(node.getId())<0){
            //If its null will be added just once
            if(rightNode!=null){
                auxfact = rightNode.insertNode(node);
            }else{
                auxfact = 1;
                rightNode = node;
            }
        }else if(this.id.compareToIgnoreCase(node.getId())>=0){
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
        if(rightNode!=null && leftNode==null){
            rightFact = rightNode.getBalanceFactor();
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
        }else if(leftNode!=null && rightNode==null){
            leftFact = leftNode.getBalanceFactor();
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
        }else if(rightNode!=null && leftNode!=null){
            leftFact = leftNode.getBalanceFactor();
            rightFact = rightNode.getBalanceFactor();
            result = rightFact - leftFact;
            if(result>0){
                node = simpleLeftRotate();
            }else if(result<0){
                node = simpleRightRotate();
            }
        }    
        return node;
    }
    
    public NodeAvl checkBalanceFact(){
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
        
        this.setLeftNode(rightNode);
        rootNode.setLeftNode(leftNode);
        rootNode.setRightNode(this);
        
        
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
        
        this.setRightNode(this.rightNode.getLeftNode());
        rootNode.setRightNode(rightNode);
        rootNode.setLeftNode(leftNode);
        
        
        //We adjust the new balance factor
        BalanceFactorHandler handlerBalance = new BalanceFactorHandler();
        handlerBalance.resizeBalanceFactor(rootNode.getLeftNode());
        handlerBalance.resizeBalanceFactor(rootNode);
        
        return rootNode;
    }
    
    public String getId(){
        return this.id;
    }
    
    public void setId(String id){
        this.id = id;
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
    
    /**
     * First declare the node then connect
     * @return 
     */
    public String generateGraphvizCode(){
        StringBuffer string = new StringBuffer();
        //Declare this node
        string.append(this.generateDeclareCode());
        //Generate the declare Codes;
        if(leftNode!=null){
            string.append(leftNode.generateGraphvizCode());
            string.append("t"+getId()+" -> "+"t"+leftNode.getId()+";\n");
        }
        if(rightNode!=null){
            string.append(rightNode.generateGraphvizCode());
            string.append("t"+getId()+" -> "+"t"+rightNode.getId()+";\n");
        }
        
        try {
            string.append(doubleList.getGraphvizCode(id,'t'));
        } catch (Exception e) {
            System.out.println("Error en generateGraphvizCode en id: "+id+", Error: "+e.getMessage());
            e.printStackTrace();
        }
        
        return string.toString();
    }
    
    private String generateDeclareCode(){
        String string = "t"+this.getId()+" [label=\""+this.getId()+"\" ];\n";
        return string;
    }
    
    
    public void getUsers(ArrayList<String> list){
        list.add(id);
        if(rightNode!=null){
            rightNode.getUsers(list);
        }
        if(leftNode!=null){
            leftNode.getUsers(list);
        }
    }
    
    public void getImages(ArrayList<String> list){
        doubleList.getImages(list);
    }
    
    
}
