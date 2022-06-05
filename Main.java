import java.util.*;
import java.io.*;

public class Main {
    
    public static Matrix loadMatrix(String file, int r, int c) {
        byte[] tmp =  new byte[r * c];
        byte[][] data = new byte[r][c];
        try {
            FileInputStream fos = new FileInputStream(file);
            fos.read(tmp);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(int i = 0; i < r; i++)
            for (int j = 0; j< c; j++)
                data[i][j] = tmp[i * c + j];
            return new Matrix(data);
    }
    
    public static void main(String[] arg){
        
       /* byte[][] tab = {{1,0,0},{0,1,0},{0,0,0}};
        Matrix m = new Matrix(tab);
        m.display();*/
        
        // la matrice A
	    	
	    	byte[][] tab1 = {{1,0,0},{0,1,0},{0,0,1}};
	    	Matrix A = new Matrix(tab1);
	    	System.out.println(" la matrice A est: ");
	    	A.display();
	    	
        // la matrice B
	    	byte[][] tab2 = {{0,0,1},{0,1,0},{1,0,0}};
	    	Matrix B = new Matrix(tab2);
	    	System.out.println(" la matrice B est: ");
	    	B.display();
	    	
	    // les transposés des matrices  A et B
	    	System.out.println(" le transpoée de A est: ");
		    Matrix A_t = A.transpose();
		    A_t.display();
		    System.out.println(" le transpoée de B est: ");
		    
		    Matrix B_t = B.transpose();
		    B_t.display();
		    
        // la multiplication de A et B
	    	System.out.println(" la multiplication entre A et B est :  ");
	    	Matrix A_mul_B =  A.multiply(B);
	    	A_mul_B.display();
	    // addition A et B
		    System.out.println(" l'addition entre A et B est :  ");
		    Matrix A_add_B =  A.add(B);
		    A_add_B.display();
        //test addrows
		    A.addRow(0, 1);
	    	System.out.println(" la matrice A L1 = L1 + L10 apres ajouter est: ");
	    	A.display();
	    //test addrows
		    A.addCol(0, 1);
	    	System.out.println(" la matrice A C1 = C1 + C10 apres ajouter est: ");
	    	A.display();
	 // le test sur la matrice 15-20-3-4
		     Matrix hbase = loadMatrix("data/matrix-15-20-3-4", 15, 20);
		    System.out.println(" la matrice 'matrix-15-20-3-4 ' est : ");
		     hbase.display();
	   //ajouter L1 à L10
		    hbase.addRow(0, 10); 
		    hbase.display();
		//ajouter C1 à C10
			hbase.addCol(0, 10);
			hbase.display();
		    
    }
}
