/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imagegenerator.list;

import com.imagegenerator.treebb.NodeBB;
import com.imagegenerator.matrix.SparseMatrix;
import java.util.ArrayList;

/**
 * Where we save our layers in a image Node
 * @author camran1234
 */
public class SimpleDoubleList <T> {
    private NodeList startNode;
    private boolean empty;
    
    public SimpleDoubleList(){
        this.empty = true;
    }
    
    /**
     * Add a node in the double list
     * @param id
     * @param layer 
     */
    public void addNode(int id,T node){
        if(startNode == null){
            this.startNode = new NodeList(id,node);
        }else{
            NodeList newNode = new NodeList(id,node);
            NodeList aux = startNode;
            if(getNode(id)==null)
            while(aux != null){
                if(aux.getNext() == null){
                    aux.setNext(newNode);
                    newNode.setPrevious(aux);
                    break;
                }
                aux = aux.getNext();
            }
        }
    }
    
    
    
    /**
     * Pull a node from the list
     * @param id
     * @return 
     */
    public NodeList pullNode(int id){
        if(startNode == null){
            return null;
        }else{
            NodeList aux = startNode;
            NodeList extracted=null;
            boolean pull=false;
            while(aux != null){
                if(aux.getId()== id){
                    extracted = aux;
                    aux.getPrevious().setNext(aux.getNext());
                    aux.getNext().setPrevious(aux.getPrevious());
                    break;
                }
                aux = aux.getNext();
            }
            return extracted;
        }
    }
    
    public NodeList getNode(int id){
        if(startNode == null){
            return null;
        }else{
            NodeList aux = startNode;
            NodeList founded=null;
            while(aux != null){
                if(aux.getId()== id){
                    founded = aux;
                    break;
                }
                aux = aux.getNext();
            }
            return founded;
        }
    }

    /**
     * Get the code of the input image in graphviz with the relation with the layers
     * @param image
     * @return 
     */
    public String getGraphvizCode(String image,char nameNode) {
        //Work the image as n and the layers as c
        StringBuffer string = new StringBuffer();
         if(startNode != null){
            NodeList aux = startNode;
            //Declare the layers
            while(aux != null){
                String num = String.valueOf(aux.getId()) + String.valueOf(image);
                string.append("c"+num+" [label=\""+aux.getId()+"\" ];\n");
                aux = aux.getNext();
            }
            
            aux = startNode;
            String text = String.valueOf(nameNode) + String.valueOf(image);
            string.append(text);
            //Connect the list
            while(aux != null){
                String num = String.valueOf(aux.getId()) + String.valueOf(image);
                string.append("-> c"+num);
                aux = aux.getNext();
            }
            string.append(";\n");
        }
        return string.toString();
    }
    
    public String generatePointerCode(String image){
        //Work the image as n and the layers as c
        StringBuffer string = new StringBuffer();
         if(startNode != null){
            NodeList aux = startNode;
            aux = startNode;
            //Connect the list
            
            while(aux != null){
                String num = String.valueOf(aux.getId()) + String.valueOf(image);
                NodeBB nodeReference = (NodeBB)aux.getObject();
                string.append("c"+num+" -> b"+nodeReference.getMatrix().getLayer()+";\n");
                aux = aux.getNext();
            }
        }
        return string.toString();
    }

    public void getImages(ArrayList<String> list) {
        if(startNode!=null){
            NodeList aux = startNode;
            while(aux!=null){
                list.add(String.valueOf(aux.getId()));
                aux = aux.getNext();
            }
        }
    }

    public void getNodes(ArrayList<NodeBB> nodes) {
        if(startNode!=null){
            NodeList aux = startNode;
            while(aux!=null){
                NodeBB node = (NodeBB) aux.getObject();
                nodes.add(node);
                aux = aux.getNext();
            }
        }
    }
    
    
    
}
