/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imagegenerator.treebb;

import com.imagegenerator.list.NodeList;
import com.imagegenerator.list.SimpleDoubleList;
import com.imagegenerator.matrix.SparseMatrix;
import java.util.ArrayList;

/**
 * Where we gonna save the layers
 * Each user have a simple List with images
 * @author camran1234
 */
public class NodeBB {
    private NodeBB rightNode;
    private NodeBB leftNode;
    private SparseMatrix matrix;
    
    public NodeBB(SparseMatrix matrix){
        this.matrix = matrix;
    }
    
    public int getIdUser() {
        return matrix.getLayer();
    }

    public void getInorder(ArrayList<NodeBB> node){
        //left
        if(leftNode!=null){
            leftNode.getInorder(node);
            node.add(this);
        }
        //Center
        if(leftNode==null && rightNode!=null){
            node.add(this);
        }
        //Right
        if(rightNode!=null){
            rightNode.getInorder(node);
        }
        //Last Node
        if(leftNode==null && rightNode==null){
            node.add(this);
        }
    }
    
    public void getPreOrder(ArrayList<NodeBB> node){
        node.add(this);
        if(leftNode!=null){
            leftNode.getPreOrder(node);
        }
        if(rightNode!=null){
            rightNode.getPreOrder(node);
        }
    }
    
    public void getPostOrder(ArrayList<NodeBB> node){
        //left
        if(leftNode!=null){
            leftNode.getPostOrder(node);
        }
        //Right
        if(rightNode!=null){
            rightNode.getPostOrder(node);
            node.add(this);
        }
        //Center
        if(leftNode!=null && rightNode==null){
            node.add(this);
        }
        //Last Node
        if(leftNode==null && rightNode==null){
            node.add(this);
        }
    }
    
    public void getImages(ArrayList<String> list){
        list.add(String.valueOf(matrix.getLayer()));
        if(rightNode!=null){
            rightNode.getImages(list);
        }
        if(leftNode!=null){
            leftNode.getImages(list);
        }
    }
   
    
    public void insert(NodeBB node){
        if(matrix.getLayer()>=node.getIdUser()){
            //Go to left Node
            if(this.leftNode == null){
                this.leftNode = node;
            }else{
                this.leftNode.insert(node);
            }
        }else if(matrix.getLayer()<node.getIdUser()){
            //Go to right Node
            if(this.rightNode ==null){
                this.rightNode = node;
            }else{
                this.rightNode.insert(node);
            }
        }
    }
    
    
    /**
     * Find Node
     * @param id
     * @return 
     */
    public NodeBB getNode(int id){
        NodeBB aux = this;
        if(matrix.getLayer()>id){
            //Go to left Node
            if(leftNode!=null){
                aux = leftNode.getNode(id);
            }else{
                return null;
            }
            
        }else if(matrix.getLayer()<id){
            //Go to right Node
            if(rightNode!=null){
                aux = rightNode.getNode(id);
            }else{
                return null;
            }
        }    
        return aux;
    }

    public NodeBB getRightNode() {
        return rightNode;
    }

    public void setRightNode(NodeBB rightNode) {
        this.rightNode = rightNode;
    }

    public NodeBB getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(NodeBB leftNode) {
        this.leftNode = leftNode;
    }
    
    public String generateGraphvizCode(){
        StringBuffer string = new StringBuffer();
        //Declare this node
        string.append(this.generateDeclareCode());
        //Generate the declare Codes;
        if(leftNode!=null){
            string.append(leftNode.generateGraphvizCode());
            string.append("b"+matrix.getLayer()+" -> "+"b"+leftNode.getMatrix().getLayer()+";\n");
        }
        if(rightNode!=null){
            string.append(rightNode.generateGraphvizCode());
            string.append("b"+matrix.getLayer()+" -> "+"b"+rightNode.getMatrix().getLayer()+";\n");
        }
        return string.toString();
    }
    
     private String generateDeclareCode(){
        String string = "\nb"+matrix.getLayer()+" [label=\""+matrix.getLayer()+"\" ];\n";
        return string;
    }
     
    public SparseMatrix getMatrix(){
        return this.matrix;
    }
    
}
