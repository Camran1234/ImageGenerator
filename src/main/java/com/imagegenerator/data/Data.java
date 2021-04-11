/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imagegenerator.data;

import com.imagegenerator.avltree.NodeAvl;
import com.imagegenerator.matrix.NodeMatrix;
import com.imagegenerator.matrix.SparseMatrix;
import com.imagegenerator.treebb.NodeBB;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author camran1234
 */
public class Data {
    private FileManager file ;
    
    
    public Data(){
        this.file = new FileManager();
    }
    
    public ArrayList<String> getLayers(){
        return file.getBbTreeLayers().getImages();
    }
    
    public void addUser(String nombre){
        file.getTreeUsers().addNode(new NodeAvl(nombre));
    }
    
    public void addImage(String user, String image){
        file.getTreeUsers().findNode(user).setNodeImage(file.getCircleListImages().findNode(Integer.valueOf(image)));
    }
    
    public void createImage(int id){
        file.getCircleListImages().insert(id);
    }
    
    public void createLayer(int image, int layer){
        NodeBB node = file.getBbTreeLayers().findNode(layer);
        file.getCircleListImages().findNode(image).insertLayer(node);
    }
    
    public void printImageByRoute(String path, int noLayers,String route){
        ArrayList<NodeBB> nodes = new ArrayList<>();
        //Getting the layers depending in the route
        if(route.equalsIgnoreCase("inorden")){
            file.getBbTreeLayers().getInorderLayer(nodes);
        }else if(route.equalsIgnoreCase("preorden")){
            file.getBbTreeLayers().getPreOrderLayer(nodes);
        }else if(route.equalsIgnoreCase("postorden")){
            file.getBbTreeLayers().getPostOrderLayer(nodes);
        }
        //Reducing
        ArrayList<NodeBB> auxNodes = new ArrayList<>();
        for(int index=0; index<noLayers; index++){
            auxNodes.add(nodes.get(index));
        }
        this.printImageMultipleLayers(path, auxNodes);
    }
    
    public void printImageList(String path){
        file.getGenerator().printImageList(path, file.getCircleListImages());
    }
    
    public void printLayerTree(String path){
        file.getGenerator().printLayerTree(path, file.getBbTreeLayers());
    }
    
    public void printLayer(String path, int idLayer){
        file.getGenerator().printLayer(path, file.getBbTreeLayers().findNode(idLayer).getMatrix());
    }
    
    public void printUserTree(String path){
        file.getGenerator().printUserTree(path, file.getTreeUsers());
    }
    
    public void printImageTree(String path, int image){
        String cluster = file.getCircleListImages().generateClusterCode(file.getCircleListImages().findNode(image));
        file.getGenerator().printImageWithLayer(path, file.getBbTreeLayers().generateGraphvizCodeSubGraphs(cluster));
    }
    
    public void printImage(String path,int layer){
        NodeBB node = file.getBbTreeLayers().findNode(layer);
        String text = node.getMatrix().generateGraphvizImage();
        file.getGenerator().printMatrix(path, text);
    }
    
    public void printImageWithNodes(String path, int image){
        ArrayList<NodeBB> nodes = file.getCircleListImages().findNode(image).getNodesAsArray();
        this.printImageMultipleLayers(path, nodes);
    }
    
    public void printImageMultipleLayers(String path, ArrayList<NodeBB> nodes){
        SparseMatrix matrixPrint = new SparseMatrix(0);
        ArrayList<NodeBB> auxnode = new ArrayList<>();
        for(int index=nodes.size()-1; index>=0; index--){
            auxnode.add(nodes.get(index));
        }
        ArrayList<NodeMatrix> nodeMatrix = new ArrayList<>();
        //We get an array with al the nodes of the layers
        for(NodeBB node:auxnode){
            ArrayList<NodeMatrix> auxiliar = node.getMatrix().getNodesAsArray();
            for(int indexAuxiliar=0; indexAuxiliar<auxiliar.size(); indexAuxiliar++){
                nodeMatrix.add(auxiliar.get(indexAuxiliar));
            }
        }
        //We add the nodes from the matrixes
        for(int indexMatrix =0; indexMatrix<nodeMatrix.size(); indexMatrix++){
            try {
                int column = nodeMatrix.get(indexMatrix).getX();
                int row = nodeMatrix.get(indexMatrix).getY();
                String color = (String) nodeMatrix.get(indexMatrix).getObject();
                matrixPrint.insert(column, row, color);
            } catch (Exception ex) {
                Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Error en printImageMultipleLayers: "+ex.getMessage());
                ex.printStackTrace();
            }
        }
        
        String text = matrixPrint.generateGraphvizImage();
        file.getGenerator().printMatrix(path, text);
    }
    
    
    public void getImages(JComboBox combo){
        ArrayList<String> list = file.getCircleListImages().getImages();
        for(String text:list){
            combo.addItem(text);
        }
    }
    
    public void getUsers(JComboBox combo){
        ArrayList<String> list = file.getTreeUsers().getUsers();
        for(String text:list){
            combo.addItem(text);
        }
    }
    
    public void getLayers(JComboBox combo){
        ArrayList<String> list = file.getBbTreeLayers().getImages();
        for(String text:list){
            combo.addItem(text);
        }
    }
    
    /*Code for print the Image with tree Layer
    
    /**
     * Read the text of a file
     * If the option is 1 will read a layer
     * option 2 will read a image
     * option 3 will read a user
     * @param path
     * @param option 
     */
    public void read(String path, int option){
        String text;
        File directory = new File(path);
        System.out.println(directory.getAbsolutePath());
        StringBuffer fileContent = new StringBuffer();
        try(BufferedReader br = new BufferedReader(new FileReader(path))){
            //Reading file
            String sCurrentLine;
            while((sCurrentLine=br.readLine())!=null){
                fileContent.append(sCurrentLine).append("\n");
            }
            text = fileContent.toString();
            this.selectImport(text,option);
        }catch(Throwable e){
            System.out.println("Error: "+e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Selecciona a donde importar
     * @param text
     * @param option 
     */
    private void selectImport(String text, int option){
        switch(option){
            case 1:
                file.readCapa(text);
                break;
            case 2:
                file.readImage(text);
                break;
            case 3:
                file.readUser(text);
                break;
        }
    }

    /**
     * Obtiene las imagenes del usuario solicitado
     * @param user
     * @param jComboBoxLayersUser 
     */
    public void getSpecificImage(String user, JComboBox<String> jComboBoxLayersUser) {
        NodeAvl node = file.getTreeUsers().findNode(user);
        ArrayList<String> list = new ArrayList<>();
        node.getImages(list);
        for(String text:list){
            jComboBoxLayersUser.addItem(text);
        }
    }
    
    /**
     * Genera una imagen de graphviz de la representacion del nodo solicitado
     * @return 
     */
    
}
