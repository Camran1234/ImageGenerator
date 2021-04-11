/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imagegenerator.matrix;

import java.util.ArrayList;

/**
 *
 * @author camran1234
 */
public class Image {
    
    public String generateImage(ArrayList<SparseMatrix> matrixs){
        SparseMatrix newImage = new SparseMatrix(0);
        
        for(SparseMatrix matrix:matrixs){
            
        }
        StringBuffer string = new StringBuffer();
        string.append(newImage.getGraphvizCode());
        return string.toString();
    }
    
}
