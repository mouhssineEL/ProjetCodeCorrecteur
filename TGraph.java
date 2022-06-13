
public class TGraph {
	private int n_r=0, n_c=0, w_r=0, w_c=0;
	private int[][] left=null, right=null;
	
	public TGraph(Matrix H, int wc, int wr) {
		n_c = H.getCols();
		n_r = H.getRows();
		w_c = wc;
		w_r = wr;
		left = new int[n_r][w_r+1];
		right = new int[n_c][w_c+1];
		for (int i=0; i<n_r; i++) {
			for (int j=0; j<w_r+1; j++) {
				if (j==0) {
					left[i][j] = 0;
				}
				else {
					int count=0;
					for (int k=0; k<n_c; k++) {
						if (H.getElem(i, k)==1)
							count++;
						if (count == j ) {
							left[i][j]=k;
							break;
						}
					}	
				}
			}
		}
		for (int i=0; i<n_c; i++) {
			for (int j=0; j<w_c+1; j++) {
				if (j==0) {
					right[i][j] = 0;
				}
				else {
					int count=0;
					for (int k=0; k<n_r; k++) {
						if (H.getElem(k, i)==1)
							count++;
						if (count == j ) {
							right[i][j]=k;
							break;
						}
					}	
				}	
			}
		}
	}

	//exo7
	// afficher 2 tables left et right de Graph Tanner
	public void display() {
		
		// la ligne max entre left et right 
        int lmax=0;
        if (n_r<n_c) {
        	lmax = n_c;
        }
        else {
        	lmax = n_r;
        }
        
        for (int i=0; i<lmax; i++) {  
        	
        	// Print l'indice de la ligne
        	System.out.printf("%3d :",i);
   
        	// Si i< n_r, on affiche normalement 
        	if (i<n_r) {
        		for (int j = 0; j < w_r+1; j++) {
        			if (j==0) {
        				System.out.printf("%2d|", left[i][j]);
        			}
        			else {
        				System.out.printf("%3d", left[i][j]);	
        			}
        		}
        	}
        	else { // Si non, afficher le padding pour que right table est bien affiche
        		for (int j = 0; j< w_r+1;j++) {
        			System.out.print("   ");
        		}
        	}
        	
        	System.out.print("     ");        	
        	
        	
        	// print right table
        	if (i<n_c) {
        		for (int j = 0; j < w_c+1; j++) {
        			if (j==0) {
        				System.out.printf("%2d|", right[i][j]);
        			}
        			else {
        				System.out.printf("%3d", right[i][j]);	
        			}
        		}
        		System.out.println();
        	}
        }
    }
	
	//Exo8
	
	// decoder le code avec rounds iterations
	// renvoie le code corrige, sinon un vecteur de taille n dont tous les coef ont la valeur -1  
	public Matrix decode(Matrix code, int rounds) {
		
		Matrix x = new Matrix(1,n_c);
		
		//Initialisation		
		for (int i = 0; i< n_c; i++) {
			right[i][0]=code.getElem(0, i);
		}
				
		//Boucle principale
		for (int i = 0; i< rounds; i++) {
			
			// Calcul des parites
			for (int j =0; j< n_r; j++) {
				left[j][0]=0;
				for (int k = 1; k < w_r +1; k++) {
					left[j][0] = (left[j][0] + right[(left[j][k])][0]) %2; 		
				}
			}
			
			// Verification
			
			// check si tous les noueds est 0
			int check=0;
			for (int j =0; j<n_r; j++) {
				if (left[j][0] !=0 ) {
					check++;
				}
			}
			
			// code corrige bien --> renvoyer x
			if (check ==0) {
				for (int k=0; k<n_c; k++) {
					x.setElem(0, k, (byte)(right[k][0]));
				}
				return x;
			}
				
			// Calcul de max
			int max =0;
			// tableau de numero des fonctions non-paires pour chaque noeud
			Matrix count = new Matrix(1,n_c);
			
			for (int j=0; j<n_c; j++) {
				
				// initialisation count = 0
				count.setElem(0, j,(byte)0);
				
				// calcul count
				for (int k=1; k<w_c + 1; k++) {
					count.setElem(0, j, (byte)(count.getElem(0, j) + left[right[j][k]][0]));  
				}
				
				if (count.getElem(0, j) > max) {
					max = count.getElem(0, j);
				}
			}
			
			// Renversement de bits de tous les noeuds ont le count = max
			for (int j=0; j<n_c; j++) {
				if (count.getElem(0, j) == max) {
					right[j][0] = (1 + right[j][0])%2; 
				}
			}
			
		}
		
		// si on corriger pas le code apres rounds iteration, renvoi [-1 ... -1]
		for (int i=0; i<n_c;i++) {
			x.setElem(0, i,(byte) (-1));
		}
		return x;
	}
	
	
	
}
