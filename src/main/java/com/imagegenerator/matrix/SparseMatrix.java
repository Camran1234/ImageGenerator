/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imagegenerator.matrix;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author camran1234
 */
public class SparseMatrix <T>{
    private int idLayer;
    private int totalNodes;
    private int totalRows;
    private int totalCols;
    private NodeMatrix inicio;
    
    public int getTotalRows(){
        return totalRows;
    }
    
    public int getTotalCols(){
        return totalCols;
    }
    
    /**
     * Initialize the sparse matrix
     * @param object 
     */
    public SparseMatrix(int idLayer){
        this.idLayer = idLayer;
        inicio = new NodeMatrix(0,0,null);
        totalNodes = totalRows = totalCols =0;
    }
    
    public void setLayer(int idLayer){
        this.idLayer = idLayer;
    }
    
    public int getLayer(){
        return idLayer;
    }
    
    public NodeMatrix createNode(int x, int y, T object){
        return new NodeMatrix(x,y, object);
    }
    
    //This is the headers Rows
    public NodeMatrix insertRow(int row){
        NodeMatrix cabeza = inicio.getSigFila();
        NodeMatrix nuevo = new NodeMatrix(0, row, null);
        if(cabeza==null){
            inicio.setSigFila(nuevo);
            nuevo.setAntFila(inicio);
        }else{
            if(cabeza.getY() > row){
                nuevo.setSigFila(cabeza);
                cabeza.setAntFila(nuevo);
                inicio.setSigFila(nuevo);
                nuevo.setAntFila(inicio);
            }else{
                NodeMatrix aux = cabeza;
                while(aux.getSigFila() != null){
                    if(aux.getSigFila().getY() > row && aux.getY()<row ){
                        nuevo.setSigFila(aux.getSigFila());
                        aux.getSigFila().setAntFila(nuevo);
                        nuevo.setAntFila(aux);
                        aux.setSigFila(nuevo);
                        totalRows++;
                        return nuevo;
                    }
                    aux = aux.getSigFila();
                }
                aux.setSigFila(nuevo);
                nuevo.setAntColumna(aux);
            }
        }
        if(row > totalRows){
            totalRows = row;
        }
        return nuevo;
    }
    
    //This is the hadders Cols
    public NodeMatrix insertCol(int column){
        NodeMatrix cabeza = inicio.getSigColumna();
        NodeMatrix nuevo = new NodeMatrix(column, 0, null);
        if(cabeza==null){
            inicio.setSigColumna(nuevo);
            nuevo.setAntColumna(inicio);
        }else{
            if(cabeza.getX() > column){
                nuevo.setSigColumna(cabeza);
                cabeza.setAntColumna(nuevo);
                inicio.setSigColumna(nuevo);
                nuevo.setAntColumna(inicio);
            }else{
                NodeMatrix aux = cabeza;
                while(aux.getSigColumna() != null){
                    
                    if(aux.getSigColumna().getX() > column && aux.getX()<column){
                        nuevo.setSigColumna(aux.getSigColumna());
                        aux.getSigColumna().setAntColumna(nuevo);
                        nuevo.setAntColumna(aux);
                        aux.setSigColumna(nuevo);
                        totalCols++;
                        return nuevo;
                    }
                    aux = aux.getSigColumna();
                }
                aux.setSigColumna(nuevo);
                nuevo.setAntColumna(aux);
            }
        }
        if(column > totalCols){
            totalCols = column;
        }
        return nuevo;
    }
    
    //Insert new Col
    //We add 1 to not interfere with the headers
    /**
     * Insert a new node in the Matrix
     * @param column
     * @param row
     * @param object 
     */
    public void insert(int column, int row, T object){
        if(column==0){
            column++;
        }
        if(row==0){
            row++;
        }
        NodeMatrix newNode = new NodeMatrix(column, row, object);
        try {
            this.insert(newNode);
        } catch (Exception ex) {
            Logger.getLogger(SparseMatrix.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    
    //Insert new Node
    public void insert(NodeMatrix nodeMatrix) throws Exception{
        NodeMatrix nuevo = nodeMatrix;
        int fila = nodeMatrix.getY();
        int columna = nodeMatrix.getX();
        if(this.findNode(columna, fila)==null){
            NodeMatrix inicioFila = getRow(fila,true);
            NodeMatrix inicioColumna = getCol(columna,true);
            NodeMatrix cabeza = inicioColumna.getSigFila();
            if(cabeza==null){
                inicioColumna.setSigFila(nuevo);
                nuevo.setAntFila(inicioColumna);
            }else{
                if(cabeza.getY() > fila){
                    nuevo.setSigFila(cabeza);
                    cabeza.setAntFila(nuevo);
                    inicioColumna.setSigFila(nuevo);
                    nuevo.setAntFila(inicioColumna);
                }else{
                    NodeMatrix aux = cabeza;
                    boolean insertado = false;
                    while(aux.getSigFila() !=null){
                        if(aux.getSigFila().getY() >fila ){
                            nuevo.setSigFila(aux.getSigFila());
                            aux.getSigFila().setAntFila(nuevo);
                            nuevo.setAntFila(aux);
                            aux.setSigFila(nuevo);
                            insertado = true;
                            break;
                        }
                        aux = aux.getSigFila();
                    }

                    if(!insertado){
                        aux.setSigFila(nuevo);
                        nuevo.setAntFila(aux);
                    }

                }
            }

            cabeza = inicioFila.getSigColumna();
            if(cabeza==null){
                inicioFila.setSigColumna(nuevo);
                nuevo.setAntColumna(inicioFila);
            }else{
                if(cabeza.getX() > columna){
                    nuevo.setSigColumna(cabeza);
                    cabeza.setAntColumna(nuevo);
                    inicioFila.setSigColumna(nuevo);
                    nuevo.setAntColumna(inicioFila);
                }else{
                    NodeMatrix aux = cabeza;
                    boolean insertado = false;
                    while(aux.getSigColumna() !=null){
                        if(aux.getSigColumna().getX() >columna ){
                            nuevo.setSigColumna(aux.getSigColumna());
                            aux.getSigColumna().setAntColumna(nuevo);
                            nuevo.setAntColumna(aux);
                            aux.setSigColumna(nuevo);
                            insertado = true;
                            break;
                        }
                        aux = aux.getSigColumna();
                    }

                    if(!insertado){
                        aux.setSigColumna(nuevo);
                        nuevo.setAntColumna(aux);
                    }

                }
            }
            fillEmptySpaces();
            totalNodes++;
        }
    }
    
    public NodeMatrix getRow(int row, boolean flag){
       NodeMatrix aux = inicio;
       while(aux!=null){
           if(aux.getY() ==row){
               return aux;
           }
           aux = aux.getSigFila();
       }
       if(flag){
           return insertRow(row);
       }
       return null;
    }
    
    public NodeMatrix getCol(int col, boolean flag){
        NodeMatrix aux = inicio;
       while(aux!=null){
           if(aux.getX() ==col){
               return aux;
           }
           aux = aux.getSigColumna();
       }
       if(flag){
           return insertCol(col);
       }
       return null;
    }
    
    /**
     * Return a node with the parameters specifics
     * @param col
     * @param row
     * @return 
     */
    public NodeMatrix findNode(int col,int row){
        NodeMatrix nodeCol = getCol(col, false);
        if(nodeCol != null){
            NodeMatrix aux = nodeCol;
            while(aux != null){
                if(aux.getY() == row){
                    return aux;
                }
                aux = aux.getSigFila();
            }
            return null;
        }else{
            return null;
        }
    }
    
    /*digraph g{
	node [shape=square];
	A1->A2;
	A2->B2;
	B2->C2;
	C2->D2;
	rank=same{A1,A2}
}*/
   
    private void fillEmptySpaces(){
        try {
            //filling columns
        NodeMatrix aux = inicio;
        
        while(aux.getSigColumna()!=null){
            if(aux.getSigColumna().getX() != aux.getX()+1){
                NodeMatrix nuevo = new NodeMatrix(aux.getX()+1,0,null);
                nuevo.setAntColumna(aux);
                nuevo.setSigColumna(aux.getSigColumna());
                aux.getSigColumna().setAntColumna(nuevo);
                aux.setSigColumna(nuevo);
            }
            aux = aux.getSigColumna();
        }
        
        //Filling rows
        aux = inicio;
        while(aux.getSigFila()!=null){
            if(aux.getSigFila().getY() != aux.getY()+1){
                NodeMatrix nuevo = new NodeMatrix(0,aux.getY()+1,null);
                nuevo.setAntFila(aux);
                nuevo.setSigFila(aux.getSigFila());
                aux.getSigFila().setAntFila(nuevo);
                aux.setSigFila(nuevo);
            }
            aux = aux.getSigFila();
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    /**
     * Return the code to create a image representation of this layer
     * @return 
     */
    public String generateGraphvizImage(){
        try {
            StringBuffer string = new StringBuffer();
        string.append("digraph G {\n");
        string.append("\tnode [shape=plaintext fillcolor=white style=filled]\n" +
"		edge [style=invis]");
        string.append("		a0 [label=<\n" +
" <TABLE  cellspacing=\"0\" cellpadding=\"10\"  border=\"0\">");
        
        for(int rows=0; rows<=totalRows; rows++){
            string.append("//Row: "+rows);
            string.append("\n<TR>\n");
            for(int cols=0; cols<=totalCols; cols++){
                NodeMatrix matrix = this.findNode(cols, rows);
                if(matrix!=null){
                    String color = (String)matrix.getObject();
                    if(matrix.getObject()==null){
                        string.append("<TD  border=\"0\"  bgcolor=\"white\"></TD>\n");
                    }else{
                        string.append("<TD  border=\"0\"  bgcolor=\""+color+"\"></TD>\n");
                    }
                    
                }else{
                    string.append("<TD  border=\"0\"  bgcolor=\"white\"></TD>\n");
                }
            }
            string.append("\n</TR>\n");
        }
        string.append("</TABLE>>];\n" +
"\n" +
"\n" +
"\n" +
"} ");
        return string.toString();
        } catch (Exception e) {
            System.out.println("Erro en generateGraphvizImage: "+e.getMessage());
            e.printStackTrace();
            return "";
        }        
    }
    
    /**
     * Generate a graphviz code of the representation of this memory
     * @return 
     */
    public String getGraphvizCode(){
        StringBuffer string = new StringBuffer();
        string.append("digraph m{\n");
        string.append("\tnode [shape=square fixedsize= true width=0.8];\n");
        
        //Setting labels
        for(int indexY=0; indexY<=totalRows; indexY++){
            for(int indeX=0; indeX<=totalCols; indeX++){
                NodeMatrix matrix = this.findNode(indeX, indexY);
                if(matrix!=null){
                    String text= (String)matrix.getObject();
                    if(matrix.getObject()==null){
                        if(indexY==0){
                            text = Integer.toString(matrix.getX());
                        }
                        if(indeX==0){
                            text = Integer.toString(matrix.getY());
                        }
                        string.append("\""+matrix+"\""+ " [label=\""+text+"\" group="+indeX+"];\n");
                    }else{
                        string.append("\""+matrix+"\""+ " [label=\""+text+"\" group="+indeX+"];\n");
                    }
                }    
            }
        }
        
        string.append("\n\n //1 \n\n");
        //Setting the first rows
        //Declaring the ranks
        for(int indexY=0; indexY<=totalRows; indexY++){
            boolean oneEntered=false;
            string.append("\trank=same{");
            for(int indeX=0; indeX<=totalCols; indeX++){
                NodeMatrix matrix = this.findNode(indeX, indexY);
                if(matrix!=null){
                    if(oneEntered){
                        string.append(",");
                    }
                    String text= (String)matrix.getObject();
                    string.append("\""+matrix+"\"");
                    oneEntered=true;
                }    
            }
            string.append("}\n");
        }
        string.append("\n\n //2 \n\n");
        //Declaring the rightConnections
        for(int indexY=0; indexY<=totalRows; indexY++){
            boolean oneEntered=false;
            for(int indeX=0; indeX<=totalCols; indeX++){
                NodeMatrix matrix = this.findNode(indeX, indexY);
                if(matrix!=null){
                    if(oneEntered){
                        string.append("->");
                    }
                    String text= (String)matrix.getObject();
                    string.append("\""+matrix+"\"");
                    oneEntered=true;
                }      
            }
            string.append(";\n");
        }
        string.append("\n\n //3 \n\n");
        //Declaring the leftConnections
        //Declaring the connections from 0 to x by rows
         for(int indexY=totalRows; indexY>=0; indexY--){
             boolean oneEntered=false;
            for(int indeX=totalCols; indeX>=0; indeX--){
                NodeMatrix matrix = this.findNode(indeX, indexY);
               if(matrix!=null){
                    if(oneEntered){
                        string.append("->");
                    }
                    String text= (String)matrix.getObject();
                    string.append("\""+matrix+"\"");
                    oneEntered=true;
                }       
            }
            string.append(";\n");
        } 
        string.append("\n\n //4 \n\n");
        //Declaring the upConnections
        for(int indeX=0; indeX<=totalCols; indeX++){
            boolean oneEntered=false;
            for(int indeY=0; indeY<=totalRows; indeY++){
                NodeMatrix matrix = this.findNode(indeX, indeY);
                if(matrix!=null){
                    if(oneEntered){
                        string.append("->");
                    }
                    String text= (String)matrix.getObject();
                    string.append("\""+matrix+"\"");
                    oneEntered=true;
                }          
            }
            string.append(";\n");
        }
        
        string.append("}");
        return string.toString();
    } 
    
    public ArrayList<NodeMatrix> getNodesAsArray(){
        ArrayList<NodeMatrix> nodes = new ArrayList<>();
        
        for(int rows=0; rows<=totalRows; rows++){
            for(int cols=0; cols<=totalCols; cols++){
                NodeMatrix matrix = this.findNode(cols, rows);
                if(matrix!=null && matrix.getX()!=0 && matrix.getY()!=0){
                    nodes.add(matrix);
                }
            }
        }
        
        return nodes;
    }
    
}
