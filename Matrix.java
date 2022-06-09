import java.util.*;

import javax.swing.plaf.synth.SynthOptionPaneUI;

import java.io.*;

public class Matrix {
    private byte[][] data = null;
    private int rows = 0, cols = 0;
    
    public Matrix(int r, int c) {
        data = new byte[r][c];
        rows = r;
        cols = c;
    }
    
    public Matrix(byte[][] tab) {
        rows = tab.length;
        cols = tab[0].length;
        data = new byte[rows][cols];
        for (int i = 0 ; i < rows ; i ++)
            for (int j = 0 ; j < cols ; j ++) 
                data[i][j] = tab[i][j];
    }
    
    public int getRows() {
        return rows;
    }
    
    public int getCols() {
        return cols;
    }
    
    public byte getElem(int i, int j) {
        return data[i][j];
    }
    
    public void setElem(int i, int j, byte b) {
        data[i][j] = b;
    }
    
    public boolean isEqualTo(Matrix m){
        if ((rows != m.rows) || (cols != m.cols))
            return false;
        for (int i = 0; i < rows; i++) 
            for (int j = 0; j < cols; j++) 
                if (data[i][j] != m.data[i][j])
                    return false;
                return true;
    }
    
    public void shiftRow(int a, int b){
        byte tmp = 0;
        for (int i = 0; i < cols; i++){
            tmp = data[a][i];
            data[a][i] = data[b][i];
            data[b][i] = tmp;
        }
    }
    
    public void shiftCol(int a, int b){
        byte tmp = 0;
        for (int i = 0; i < rows; i++){
            tmp = data[i][a];
            data[i][a] = data[i][b];
            data[i][b] = tmp;
        }
    }
     
    public void display() {
        System.out.print("[");
        for (int i = 0; i < rows; i++) {
            if (i != 0) {
                System.out.print(" ");
            }
            
            System.out.print("[");
            
            for (int j = 0; j < cols; j++) {
                System.out.printf("%d", data[i][j]);
                
                if (j != cols - 1) {
                    System.out.print(" ");
                }
            }
            
            System.out.print("]");
            
            if (i == rows - 1) {
                System.out.print("]");
            }
            
            System.out.println();
        }
        System.out.println();
    }
    
    public Matrix transpose() {
        Matrix result = new Matrix(cols, rows);
        
        for (int i = 0; i < rows; i++) 
            for (int j = 0; j < cols; j++) 
                result.data[j][i] = data[i][j];
    
        return result;
    }
    
    public Matrix add(Matrix m){
        Matrix r = new Matrix(rows,m.cols);
        
        if ((m.rows != rows) || (m.cols != cols))
            System.out.printf("Erreur d'addition\n");
        
        for (int i = 0; i < rows; i++) 
            for (int j = 0; j < cols; j++) 
                r.data[i][j] = (byte) ((data[i][j] + m.data[i][j]) % 2);
        return r;
    }
    
    public Matrix multiply(Matrix m){
        Matrix r = new Matrix(rows,m.cols);
        
        if (m.rows != cols)
            System.out.printf("Erreur de multiplication\n");
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < m.cols; j++) {
                r.data[i][j] = 0;
                for (int k = 0; k < cols; k++){
                    r.data[i][j] =  (byte) ((r.data[i][j] + data[i][k] * m.data[k][j]) % 2);
                }
            }
        }
        
        return r;
    }
    
   // exercice 3
    //ajouter les lignes 
    public void addRow(int a, int b) {
        //data = new byte[rows][cols];
    	 for(int i = 0; i < this.cols; i++) {
    		 data[a][i]= (byte) ((data[a][i]+ data[b][i])%2); 
    		 }
    }
    
    //ajouter les colonnes 

    public void addCol(int a, int b) {
        //data = new byte[rows][cols];
   	 for(int i = 0; i < this.rows; i++) {
   		 data[i][a]= (byte) ((data[i][a]+ data[i][b])%2);
   		 }
   }
    
   //exercice 4
    
    public Matrix sysTransform(){
      Matrix result = new Matrix(rows, cols);
    
    //copie le contenu de la matrix vers la matrice resulat
  	for (int i = 0; i < rows; i++) {
          for (  int j = 0; j <cols; j++) {
              result.data[i][j] = data[i][j];}}
  	
    int i = 0,j=(cols-rows);
     while(i < rows && j < cols) {
    	 //chercher la premier ligne qui contient un 1 et changer avec le premier col et row
    	 for(int z = i; z < rows; z++) {
    			if(result.data[z][j] == 1) {
    				result.shiftRow(i, z);
    				//break;
    			}}
        // mettre en zeros 0 tous  les lignes au dessous de notre 1 
    		for (int a = i+1; a < rows; a++) {
    			if(result.data[a][j] == 1) { result.addRow(a, i);}
    		}
        	i++;j++;
        	}
     //initialisation de les variables i et j
     i = rows-1; j = cols-1;
    
     while(i >= 0 && j >= cols-rows) {
    	// mettre en zeros 0 tous  les lignes au dessous de notre 1 
 		for (int a = i-1; a >= 0; a--) {
 			if(result.data[a][j] == 1) { 
 				result.addRow(a, i);}
 		}
 		
    	 i--; j--;
    	 
     }
        
  
     
     
     
       //return le la matric forma systématique
        return result;
        
    }
        
   
}
