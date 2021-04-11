/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imagegenerator.treebb;

import com.imagegenerator.list.SimpleDoubleList;
import com.imagegenerator.matrix.SparseMatrix;
import java.util.ArrayList;

/**
 * Here goes the Layers
 * @author camran1234
 */
public class BBTree {
    private NodeBB node=null;
    
    /**
     * Insert Node
     * @param idUser
     * @param list 
     */
    public void addNode(int idLayer, SparseMatrix matrix){
        NodeBB newNode = new NodeBB(matrix);
        if(this.node == null){
            this.node = newNode;
        }else{
            node.insert(newNode);
        }
    }
    
    public String generateGraphvizCodeSubGraphs(String cluster){
        StringBuffer string = new StringBuffer();
        string.append("digraph a{\n");
        string.append("node[ style=filled fillcolor=white]\n");
        if(node!=null){
            string.append(node.generateGraphvizCode());
        }
        string.append(cluster);
        string.append("}\n");
        return string.toString();
    }
    
    public String generateGraphvizCode(){
        StringBuffer string = new StringBuffer();
        string.append("digraph b{");
        if(node != null){
            string.append(node.generateGraphvizCode());
        }
        string.append("}\n");
        return string.toString();
    }
    
    public NodeBB findNode(int idLayer){
        NodeBB aux = null;
        if(this.node !=null){
            aux = node.getNode(idLayer);
        }
        return aux;
    }
    /*
    public void deleteNode(NodeBB previous, NodeBB delete){
        if(previous.getRightNode()!=null){
            
        }else if(previous.getLeftNode()!=null){
            
        }
    }*/
    
    /**
     * Get an array of the layers of the BBTree
     * @return 
     */
    public ArrayList<String> getImages(){
        ArrayList<String> list = new ArrayList<>();
        if(node==null){
            return null;
        }else{
            NodeBB aux = node;
            node.getImages(list);
        }
        return list;
    }
    
    public void getInorderLayer(ArrayList<NodeBB> nodes){
        if(node!=null){
            node.getInorder(nodes);
        }
    }
    
    public void getPreOrderLayer(ArrayList<NodeBB> nodes){
        if(node!=null){
            node.getPreOrder(nodes);
        }
    }
    
    public void getPostOrderLayer(ArrayList<NodeBB> nodes){
        if(node!=null){
            node.getPostOrder(nodes);
        }
    }
    
    public NodeBB pullNode(int idUser){
        NodeBB pullNode = null;
        if(node==null){
            return null;
        }else{
            NodeBB aux = node;
            NodeBB previousNode = aux;
            while(aux!=null){
                if(aux.getIdUser() >= idUser){
                    //Go to the Left Node 
                    if(aux.getLeftNode()!=null){
                        previousNode = aux;
                        aux = aux.getLeftNode();
                    }else{
                        return null;
                    }
                    
                }else if(aux.getIdUser() < idUser){
                    //Go to the right Node
                    if(aux.getRightNode()!=null){
                        previousNode = aux;
                        aux = aux.getRightNode();
                    }else{
                        return null;
                    }
                }else{
                    pullNode=aux;
                    if(aux.getRightNode()!=null && aux.getLeftNode()==null){
                        //Replace with the RightNode
                        
                    }else if(aux.getRightNode()==null && aux.getLeftNode()!=null){
                        //Replace with the leftNode
                    }else if(aux.getRightNode()==null && aux.getLeftNode()==null){
                        //Return nothing just delete
                    }else if(aux.getRightNode()!=null && aux.getLeftNode()==null){

                    }
                    break;
                }
            }
        }
        return pullNode;
    }
    
    /**
     * Returns the Node that is the most in the right of the request node
     * @param node
     * @return 
     */
    public NodeBB getInorderNode(NodeBB node){
        NodeBB aux = node;
        NodeBB previousNode = aux;
        while(aux!=null){
            if(aux.getRightNode() == null){
                previousNode.setRightNode(null);
                break;
            }
            previousNode = aux;
            aux = aux.getRightNode();
        }
        return aux;
    }
    
}
