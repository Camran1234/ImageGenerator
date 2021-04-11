/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imagegenerator.circledoublelist;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author camran1234
 */
public class CircleDoubleList {
    private NodeDouble initNode;
    private boolean empty;
    
    public CircleDoubleList(){
        this.empty = false;
    }
    
    public boolean isEmpty(){
        if(initNode==null){
            return false;
        }else{
            return true;
        }
    }
    
    /**
     * Add a new Node
     * @param idImage 
     */
    public void insert(int idImage){
        NodeDouble newNode = new NodeDouble(idImage);
        if(this.initNode==null){
            this.initNode = newNode;
            this.initNode.setNext(initNode);
            this.initNode.setPrevious(initNode);
        }else{
            NodeDouble aux = initNode;
            if(findNode(idImage)==null){
                while(true){
                    if(aux.getNext().equals(initNode)){
                        newNode.setNext(initNode);
                        newNode.setPrevious(aux);
                        aux.setNext(newNode);
                        initNode.setPrevious(newNode);
                        //added node
                        break;
                    }
                    aux = aux.getNext();
                }
            }else{
                JOptionPane.showMessageDialog(null, "No se puede Agregar la imagen "+idImage+" Ya existe");
            }
        }
    }
    
    
    public void insert(NodeDouble node){
        NodeDouble newNode = node;
        if(this.initNode==null){
            this.initNode = newNode;
            this.initNode.setNext(initNode);
            this.initNode.setPrevious(initNode);
        }else{
            NodeDouble aux = initNode;
            while(true){
                if(findNode(node.getId())==null)
                if(aux.getNext().equals(initNode)){
                    newNode.setNext(initNode);
                    newNode.setPrevious(aux);
                    aux.setNext(newNode);
                    initNode.setPrevious(newNode);
                    //added node
                    break;
                }
                aux = aux.getNext();
            }
        }
    }
    
    /**
     * Returns a node
     * @param idImage
     * @return 
     */
    public NodeDouble findNode(int idImage){
        if(this.initNode ==null){
            return null;
        }else{
            boolean flag=false;
            NodeDouble aux = initNode;
            while(true){
                if(flag){
                    break;
                }
                if(aux.getId()==idImage){
                    return aux;
                }
                aux = aux.getNext();
                if(aux.equals(initNode)){
                    flag=true;
                }
            }
        }
        return null;
    }
    
    public ArrayList<String> getImages(){
        ArrayList<String> string = new ArrayList<>();
        NodeDouble aux = initNode;
        while(true){
                string.add(Integer.toString(aux.getId()));
                if(aux.getNext().equals(initNode)){   
                    break;
                }
                aux = aux.getNext();
            }
        return string;
    }
    
    public String generateClusterCode(NodeDouble node){
        StringBuffer string = new StringBuffer();
        string.append("subgraph cluster_0{\n");
        string.append("node[shape=box style=filled fillcolor=blue]\n" +
"style=filled fillcolor=grey\n" +
"label=imagen"+node.getId()+"\n");
        if(node!=null){
            string.append(node.getGraphvizCodePointer());
        }
        string.append("}\n");
        return string.toString();
    }
    
    public String getGraphvizCode(){
        StringBuffer string = new StringBuffer();
        string.append("digraph g{\n");
        if(this.initNode != null){
            NodeDouble aux = initNode;
            while(true){
                string.append("n"+aux.getId()+" [label=\""+aux.getId()+"\"]; \n");
                if(aux.getNext().equals(initNode)){   
                    break;
                }
                aux = aux.getNext();
            }
            //Setting same rank
            aux=initNode;
            string.append("rank=same{ ");
            boolean added=false;
            while(true){
                
                if(aux.getNext().equals(initNode)){   
                    if(added){
                        string.append(", ");
                    }
                    string.append("n"+aux.getId());
                    break;
                }else{
                    if(added){
                        string.append(", ");
                    }
                    string.append("n"+aux.getId());
                    added=true;
                }
                aux = aux.getNext();
            }
            string.append("}\n");
            //Setting next Nodes
            aux = initNode;
            while(true){
                if(aux.getNext().equals(initNode)){
                    string.append("n"+aux.getId()+" -> n"+aux.getNext().getId()+" ;\n");
                    break;
                }else{
                    string.append("n"+aux.getId()+" -> ");
                }
                aux = aux.getNext();
            }
            //Setting previous Nodes
            aux = initNode;
            while(true){

                if(aux.getPrevious().equals(initNode)){
                    string.append("n"+aux.getId()+" -> n"+aux.getPrevious().getId()+" ; \n");
                    break;
                }else{
                    string.append("n"+aux.getId()+" -> ");
                }
                aux = aux.getPrevious();
            }
            
            //Setting List of Nodes 
            aux = initNode;
            while(true){
                string.append(aux.getGraphvizCode());
                if(aux.getNext().equals(initNode)){
                    break;
                }
                aux = aux.getNext();
            }
        }
        string.append("}");
        return string.toString();
    }
}
