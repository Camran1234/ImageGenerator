/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imagegenerator.data;

import com.imagegenerator.circledoublelist.CircleDoubleList;
import com.imagegenerator.treebb.BBTree;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import com.imagegenerator.matrix.SparseMatrix;
import com.imagegenerator.avltree.AvlTree;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author camran1234
 */
public class ImageGenerator {
    
    /*Command to generate Graphviz:
        dot -Tpng matrix.dot -o try.png
    */
    
    public void printImageList(String path, CircleDoubleList listDouble){
        try(BufferedWriter br = new BufferedWriter(new FileWriter(path))){
            //Reading file
            String text = listDouble.getGraphvizCode();
            br.append(text);
            br.close();
        }catch(Throwable e){
            System.out.println("Error: "+e.getMessage());
            e.printStackTrace();
        }
        if(new File(path).getName().contains(".png")){
            
            executeGraphviz(new File(path), new File(path).getName());
        }else{
            
            executeGraphviz(new File(path), new File(path).getName()+".png");
        }
    }
    
    public void printLayerTree(String path, BBTree tree){
        try(BufferedWriter br = new BufferedWriter(new FileWriter(path))){
            //Reading file
            String text = tree.generateGraphvizCode();
            br.append(text);
            br.close();
        }catch(Throwable e){
            System.out.println("Error: "+e.getMessage());
            e.printStackTrace();
        }
        if(new File(path).getName().contains(".png")){
            
            executeGraphviz(new File(path), new File(path).getName());
        }else{
            
            executeGraphviz(new File(path), new File(path).getName()+".png");
        }
    }
    
    public void printLayer(String path, SparseMatrix matrix){
        try(BufferedWriter br = new BufferedWriter(new FileWriter(path))){
            //Reading file
            String text = matrix.getGraphvizCode();
            br.append(text);
            br.close();
        }catch(Throwable e){
            System.out.println("Error: "+e.getMessage());
            e.printStackTrace();
        }
        if(new File(path).getName().contains(".png")){
            
            executeGraphviz(new File(path), new File(path).getName());
        }else{
            
            executeGraphviz(new File(path), new File(path).getName()+".png");
        }
    }
    
    public void printUserTree(String path, AvlTree tree){
        try(BufferedWriter br = new BufferedWriter(new FileWriter(path))){
            //Reading file
            String text = tree.getGraphvizCode();
            br.append(text);
            br.close();
        }catch(Throwable e){
            System.out.println("Error: "+e.getMessage());
            e.printStackTrace();
        }
        if(new File(path).getName().contains(".png")){
            
            executeGraphviz(new File(path), new File(path).getName());
        }else{
            
            executeGraphviz(new File(path), new File(path).getName()+".png");
        }
    }
    
    public void printImageWithLayer(String path, String graphvizCode){
        try(BufferedWriter br = new BufferedWriter(new FileWriter(path))){
            //Reading file
            br.append(graphvizCode);
            br.close();
        }catch(Throwable e){
            System.out.println("Error: "+e.getMessage());
            e.printStackTrace();
        }
        if(new File(path).getName().contains(".png")){
            
            executeGraphviz(new File(path), new File(path).getName());
        }else{
            
            executeGraphviz(new File(path), new File(path).getName()+".png");
        }
    }
    
    void printMatrix(String path, String text) {
        try(BufferedWriter br = new BufferedWriter(new FileWriter(path))){
            //Reading file
            br.append(text);
            br.close();
        }catch(Throwable e){
            System.out.println("Error: "+e.getMessage());
            e.printStackTrace();
        }
        if(new File(path).getName().contains(".png")){
            
            executeGraphviz(new File(path), new File(path).getName());
        }else{
            
            executeGraphviz(new File(path), new File(path).getName()+".png");
        }
        
    }
    
    private void executeGraphviz(File file, String outPutName){
        try {
            
            ProcessBuilder pbuilder;
            pbuilder = new ProcessBuilder( "dot", "-Tpng",file.getAbsolutePath(), "-o",   file.getParent()+"/"+outPutName);
            pbuilder.redirectErrorStream( true );
            //Ejecuta el proceso
            pbuilder.start();
            JOptionPane.showMessageDialog(null, "Se creo tu imagen en: "+file.getParent()+"/"+outPutName);
            if(!file.getName().contains(".png")){
                file.delete();
            }
        } catch (IOException ex) {
            Logger.getLogger(ImageGenerator.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            System.out.println("Error en executeGraphviz: "+ex.getMessage());
        }
    }    

    
    
}
