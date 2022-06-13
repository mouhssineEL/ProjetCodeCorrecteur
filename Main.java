import java.io.FileInputStream;
import java.io.IOException;

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
      
      /* exo1: faire des tests sur differents méthodes  */
	    	
	        // la matrice A
		    	
		    	byte[][] tab1 = {{1,0,0},{0,1,0},{0,0,1}};
		    	Matrix A = new Matrix(tab1);
		    	System.out.println(" la matrice A est: ");
		    	A.display();
		    	A.getCols();
		    	A.getRows();
		    	A.getElem(0, 0);
	        // la matrice B
		    	byte[][] tab2 = {{0,0,1},{0,1,0},{1,0,0}};
		    	Matrix B = new Matrix(tab2);
		    	System.out.println(" la matrice B est: ");
		    	B.display();

	/* exo2: faire les tests sur les methodes d'addition, mutiplication, transpose d'une matrice*/
		    	
	    	
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
		     /*
	   //ajouter L1 à L10
		    hbase.addRow(0, 10); 
		    hbase.display();
		//ajouter C1 à C10
			hbase.addCol(0, 10);
			hbase.display();
			*/
		//test sysTransform() beginning
			byte[][] tab3 = {{1,1,0,0,1,1,1},{0,1,1,1,0,1,0},{1,0,1,0,1,0,1},{1,1,1,1,1,0,0}};
	    	Matrix M = new Matrix(tab3);
	    	System.out.println(" la matrice M est: ");
	    	M.display();
		    M.sysTransform();
		    M.display();
		
		     hbase.display();
		     Matrix H =  hbase.sysTransform();
		     H.display();
		     System.out.println(" la matrice generatice sous forme systémtique :\n");
		     Matrix G =  hbase.genG(); 
		     G.display();
		     // exercice 6: 
		     byte[][] mot = {{1, 0, 1, 0, 1}};
		        Matrix u = new Matrix(mot);
		        System.out.println("Mot binaire u:");
		        u.display();
		        Matrix x = u.multiply(G);
		        System.out.println("Encodage de u (x=u.G) :");
		        x.display();
		        System.out.println("Syndrome de x (s=H.x^t) :");
		        Matrix s0 =H.multiply(x.transpose());
		        Matrix s =  s0.transpose();
		        s.display();
		        TGraph tanner = new TGraph(hbase, 3, 4);
		        System.out.println("Graph de Tanner");
		        tanner.display();
		        
		        int rounds = 100;
		        byte[][] tab01 = {{0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
		        byte[][] tab02 = {{0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
		        byte[][] tab03 = {{0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0}};
		        byte[][] tab04 = {{0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0}};
		        
		        Matrix e1 = new Matrix(tab01);
		        Matrix e2 = new Matrix(tab02);
		        Matrix e3 = new Matrix(tab03);
		        Matrix e4 = new Matrix(tab04);

		        Matrix y1 = x.add(e1);
		        Matrix y2 = x.add(e2);
		        Matrix y3 = x.add(e3);
		        Matrix y4 = x.add(e4);
		        
		        Matrix s1 = hbase.multiply(y1.transpose()).transpose();
		        Matrix s2 = hbase.multiply(y2.transpose()).transpose();
		        Matrix s3 = hbase.multiply(y3.transpose()).transpose();
		        Matrix s4 = hbase.multiply(y4.transpose()).transpose();

//		      System.out.println("Syndrome:");
//		        s1.display();
//		        s2.display();
//		        s3.display();
//		        s4.display();
		        
		        Matrix x1 = tanner.decode(y1, rounds);
		        Matrix x2 = tanner.decode(y2, rounds);
		        Matrix x3 = tanner.decode(y3, rounds);
		        Matrix x4 = tanner.decode(y4, rounds);
		        
		        x.display();
//		        e1.display();
		        y1.display();
		        s1.display();
//		        x.display();
		        x1.display();
		        System.out.printf("x1=x: %b\n", x1.isEqualTo(x));
		        
		        y2.display();
		        s2.display();
		        x2.display();
		        System.out.printf("x2=x: %b\n", x2.isEqualTo(x));
		        
		        y3.display();
		        s3.display();
		        x3.display();
		        System.out.printf("x3=x: %b\n", x3.isEqualTo(x));
		        
		        y4.display();
		        s4.display();
		        x4.display();
		        System.out.printf("x4=x: %b\n", x4.isEqualTo(x));
		    
    }
}
