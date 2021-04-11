/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imagegenerator.data;

import com.imagegenerator.avltree.AvlTree;
import com.imagegenerator.circledoublelist.CircleDoubleList;
import com.imagegenerator.datareader.CapaSyntax;
import com.imagegenerator.datareader.ImageLex;
import com.imagegenerator.datareader.ImageSyntax;
import com.imagegenerator.datareader.UsuarioSyntax;
import com.imagegenerator.treebb.BBTree;
import java.io.Reader;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import static javax.swing.JFileChooser.APPROVE_OPTION;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author camran1234
 */
public class FileManager {
    private BBTree bbTreeLayers=null;
    private CircleDoubleList circleListImages=null;
    private AvlTree treeUsers = null; 
    private ImageGenerator generatorRex = new ImageGenerator();
    
    public ImageGenerator getGenerator(){
        return generatorRex;
    }
    
    
    
    public void readCapa(String text){
        try {
            //Reading file
            Reader reader = new StringReader(text);
            ImageLex lexic = new ImageLex(reader);
            CapaSyntax syntax = new CapaSyntax(lexic);
            syntax.parse();
            bbTreeLayers=syntax.getTree();
            System.out.println("Capass");
        } catch (Exception ex) {
            System.out.println("Error en readCapa: "+ex.getMessage());
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    
    public void readImage(String text){
        if(bbTreeLayers!=null){
            try {
                //Reading file            
                Reader reader = new StringReader(text);        
                ImageLex lexic = new ImageLex(reader);        
                ImageSyntax syntax = new ImageSyntax(lexic);        
                syntax.setBBTree(bbTreeLayers);
                syntax.parse();   
                circleListImages = syntax.getList();
            } catch (Exception e) {
                System.out.println("Eror en readImage: "+e.getMessage());
                e.printStackTrace();
            }
        }
    }
    
    public void readUser(String text){
        if(circleListImages!=null){
            try {
                //Reading file
                Reader reader = new StringReader(text);
                ImageLex lexic = new ImageLex(reader);
                UsuarioSyntax syntax = new UsuarioSyntax(lexic);
                syntax.setCircleDoubleList(circleListImages);
                syntax.parse();
                this.treeUsers = syntax.getAvlTree();
            } catch (Exception ex) {
                Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Error in readUser: "+ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    public BBTree getBbTreeLayers() {
        return bbTreeLayers;
    }

    public CircleDoubleList getCircleListImages() {
        return circleListImages;
    }
    
    

    public AvlTree getTreeUsers() {
        return treeUsers;
    }
    
    
}
