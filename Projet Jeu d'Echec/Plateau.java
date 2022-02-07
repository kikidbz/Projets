import java.util.Scanner;

public class Plateau {
	private Case[][] plateau;
	private boolean verifEchec;
	
	public Plateau () {
		this.verifEchec= false;
		this.plateau = new Case[8][8];
		
		Case c;
		String coul = "B";

			for (int i = 0; i < 8; i++)
			{
	            for (int j = 0; j < 2; j++)
	            {
	            	if( j == 1 )
	            	{
	            		c = new Case(new Pion (coul));
	                	this.plateau[i][j] = c;
	            	}
	            	else if( i == 0 || i == 7)
	            	{
	            		c = new Case(new Tour (coul));
	                	this.plateau[i][j] = c;
	            	}
	            	else if( i == 1 || i == 6)
	            	{
	            		c = new Case(new Cavalier (coul));
	                	this.plateau[i][j] = c;
	            	}
	            	else if( i == 2 || i == 5)
	            	{
	            		c = new Case(new Fou (coul));
	                	this.plateau[i][j] = c;
	            	}
	            	else if( i == 4)
	            	{
	            		c = new Case(new Roi (coul));
	                	this.plateau[i][j] = c;
	            	}
	            	else if( i == 3)
	            	{
	            		c = new Case(new Reine (coul));
	                	this.plateau[i][j] = c;
	            	}
	            	else
	            	{
	            		c = new Case(null);
	                	this.plateau[i][j] = c;
	            	}
	            }
			}
			
			coul = "N";
			
			for (int i = 0; i < 8; i++)
			{
	            for (int j = 6; j < 8; j++)
	            {
	            	if( j == 6 )
	            	{
	            		c = new Case(new Pion (coul));
	                	this.plateau[i][j] = c;
	            	}
	            	else if( i == 0 || i == 7)
	            	{
	            		c = new Case(new Tour (coul));
	                	this.plateau[i][j] = c;
	            	}
	            	else if( i == 1 || i == 6)
	            	{
	            		c = new Case(new Cavalier (coul));
	                	this.plateau[i][j] = c;
	            	}
	            	else if( i == 2 || i == 5)
	            	{
	            		c = new Case(new Fou (coul));
	                	this.plateau[i][j] = c;
	            	}
	            	else if( i == 4)
	            	{
	            		c = new Case(new Roi (coul));
	                	this.plateau[i][j] = c;
	            	}
	            	else if( i == 3)
	            	{
	            		c = new Case(new Reine (coul));
	                	this.plateau[i][j] = c;
	            	}
	            	else
	            	{
	            		c = new Case(null);
	                	this.plateau[i][j] = c;
	            	}
	            }
			}
			
			for (int i = 0; i < 8; i++)
			{
	            for (int j = 2; j < 6; j++)
	            {
	            	c = new Case(null);
                	this.plateau[i][j] = c;
	            }
			}
			
	}
	

	public boolean movePion  (int depX, int depY, int finX, int finY) {
		int dx = finX - depX;
		int dy = finY - depY;
		
		Case c = this.plateau[depX][depY];
		Case dest = this.plateau[finX][finY];
		
		boolean bouge = false;

		if(dx == 0)
		{
			if( this.testLigne(depX, depY, finX, finY) )
			{
				
				if( dest.getPiece() == null )
				{
					if(!c.getPiece().getMove())
						c.getPiece().changeMove();
					this.movePiece(c, dest);
					bouge = true;
				}
			}
		}
		else if (dx == -1 || dx == 1)
		{
			if(dy == -1 && c.getPiece().getCoul() == "N")
			{
				if ( this.testDiag(depX, depY, finX, finY) )
				{
					if( dest.getPiece() != null && c.getPiece().getCoul() != dest.getPiece().getCoul() )
					{
						if(!c.getPiece().getMove())
							c.getPiece().changeMove();
						this.movePiece(c, dest);
						bouge = true;
					}
				}
			}
			if(dy == 1 && c.getPiece().getCoul() == "B")
			{
				if ( this.testDiag(depX, depY, finX, finY) )
				{
					if( dest.getPiece() != null && c.getPiece().getCoul() != dest.getPiece().getCoul() )
					{
						if(!c.getPiece().getMove())
							c.getPiece().changeMove();
						this.movePiece(c, dest);
						bouge = true;
					}
				}
			}
		}


		if(bouge && (finY == 0 || finY == 7) && this.verifEchec == false)
		{
			this.promotion(dest);
		}
		
		return bouge;
	}
	

	public boolean moveCavalier (int depX, int depY, int finX, int finY) {
		
		Case c = this.plateau[depX][depY];
		Case dest = this.plateau[finX][finY];
		
		if( dest.getPiece() == null)
		{
			this.movePiece(c, dest);
			return true;
		}
		else if( c.getPiece().getCoul() != dest.getPiece().getCoul() )
		{
			this.movePiece(c, dest);
			return true;
		}
		return false;
	}
	

	public boolean movePiece (int depX, int depY, int finX, int finY) {
		int dx = finX - depX;
		int dy = finY - depY;
		
		Case c = this.plateau[depX][depY];
		Case dest = this.plateau[finX][finY];

		if(c.getPiece() != null 
				&& c.getPiece().movePiece(depX, depY, finX, finY)
				&& this.inMap(depX, depY, finX, finY)
				&& !( dx == 0 && dy == 0))
		{	
			
			
			if(c.getPiece().getClass() == Pion.class)
			{
				return this.movePion(depX, depY, finX, finY);
			}
			
			else if(c.getPiece().getClass() == Cavalier.class)
			{
				return this.moveCavalier(depX, depY, finX, finY);
			}
			
			else if(dx == 0 || dy == 0)
			{
				if( this.testLigne(depX, depY, finX, finY) )
				{
					
					if( dest.getPiece() == null )
					{
						this.movePiece(c, dest);
						return true;
					}
					
					else if ( c.getPiece().getCoul() != dest.getPiece().getCoul() )
					{
						this.movePiece(c, dest);
						return true;
					}
				}
			}
			else if(dx == dy || dx == -dy)
			{
				if ( this.testDiag(depX, depY, finX, finY) )
				{
					if( dest.getPiece() == null )
					{
						this.movePiece(c, dest);
						return true;
					}
					
					else if ( c.getPiece().getCoul() != dest.getPiece().getCoul() )
					{
						this.movePiece(c, dest);
						return true;
					}
				}
			}
		}
		return false;
	}
	
	
	public boolean inMap (int depX, int depY, int finX, int finY) {
		if (depX >= 0 && depX < 8 && finX >= 0 && finX < 8
				&& depY >= 0 && depY < 8 && finY >= 0 && finY < 8)
		{
			return true;
		}
		return false;
	}
	
	
	
	public boolean testDiag (int depX, int depY, int finX, int finY) {
		int dx = finX - depX;
		int dy = finY - depY;

		// Test diagonale /
		if(dx == dy)
		{
			if(dx > 0)
			{
				return this.testDiagHautDroite (depX, depY, finX, finY);
			}
			else
			{
				return this.testDiagBasGauche (depX, depY, finX, finY);
			}
		}
		// Test diagonale \
		else if(dx == -dy)
		{
			if(dx > 0)
			{
				return this.testDiagBasDroite (depX, depY, finX, finY);
			}
			else
			{
				return this.testDiagHautGauche (depX, depY, finX, finY);
			}
		}
		return false;
	}
	
	public boolean testDiagHautDroite (int depX, int depY, int finX, int finY) {
		Case c;
		for(int i = depX + 1, j = depY + 1; i < finX && j < finY; i++, j++)
		{
			c = this.plateau[i][j];
			if(c.getPiece() != null)
			{
				return false;
			}
		}
		return true;
	}
	
	public boolean testDiagBasGauche (int depX, int depY, int finX, int finY) {
		Case c;
		for(int i = depX - 1, j = depY - 1; i > finX && j > finY; i--, j--)
		{
			c = this.plateau[i][j];
			if(c.getPiece() != null)
			{
				return false;
			}
		}
		return true;
	}
	
	public boolean testDiagHautGauche (int depX, int depY, int finX, int finY) {
		Case c;
		for(int i = depX - 1, j = depY + 1; i > finX && j < finY; i--, j++)
		{
			c = this.plateau[i][j];
			if(c.getPiece() != null)
			{
				return false;
			}
		}
		return true;
	}
	
	public boolean testDiagBasDroite (int depX, int depY, int finX, int finY) {
		Case c;
		for(int i = depX + 1, j = depY - 1; i < finX && j > finY; i++, j--)
		{
			c = this.plateau[i][j];
			if(c.getPiece() != null)
			{
				return false;
			}
		}
		return true;
	}
	
	
	
	public boolean testLigne (int depX, int depY, int finX, int finY) {
		int dx = finX - depX;
		int dy = finY - depY;
		
		// Test Verticale
		if(dx == 0)
		{
			if(dy > 0)
			{
				return this.testVerticaleLigneHaut (depX, depY, finX, finY);
			}
			else
			{
				return this.testVerticaleLigneBas (depX, depY, finX, finY);
			}
		}
		
		
		if(dy == 0)
		{
			if(dx > 0)
			{
				return this.testHorizontaleLigneDroite(depX, depY, finX, finY);
			}
			else
			{
				return this.testHorizontaleLigneGauche(depX, depY, finX, finY);
			}
		}
		return false;
	}
	
	public boolean testVerticaleLigneHaut (int depX, int depY, int finX, int finY) {
		Case c;
		for(int i = depY + 1; i < finY; i++)
		{
			c = this.plateau[depX][i];
			if(c.getPiece() != null)
			{
				return false;
			}
		}
		return true;
	}
	
	public boolean testVerticaleLigneBas (int depX, int depY, int finX, int finY) {
		Case c;
		for(int i = depY - 1; i > finY; i--)
		{
			c = this.plateau[depX][i];
			if(c.getPiece() != null)
			{
				return false;
			}
		}
		return true;
	}
	
	public boolean testHorizontaleLigneDroite (int depX, int depY, int finX, int finY) {
		Case c;
		for(int i = depX + 1; i < finX; i++)
		{
			c = this.plateau[i][depY];
			if(c.getPiece() != null)
			{
				return false;
			}
		}
		return true;
	}
	
	public boolean testHorizontaleLigneGauche (int depX, int depY, int finX, int finY) {
		Case c;
		for(int i = depX - 1; i > finX; i--)
		{
			c = this.plateau[i][depY];
			if(c.getPiece() != null)
			{
				return false;
			}
		}
		return true;
	}
	
	

	public void afficher( int tours){
		System.out.println("\nTours " + tours + " :\n");
        for (int j = 7; j >=0; j--){
            for (int i = 0; i < 8; i++){
            	if(i == 0)
            	{
            		System.out.print(j+1 + " ");
            	}
                if(this.plateau[i][j].getPiece() != null){
                    System.out.print("[" + this.plateau[i][j].getPiece()+ "]");
                }
                else{
                    System.out.print("[  ]");
                }
            }
            System.out.println(" ");
        }
        System.out.print("   a   b   c   d   e   f   g   h");
        System.out.println(" ");
    }
	
	public Piece getPiece (int x, int y) {
		return this.plateau[x][y].getPiece();
	}
	
	public Case getCase (int x, int y) {
		return this.plateau[x][y];
	}
	
	public void movePiece (Case c, Case dest) {
		if (this.verifEchec == false){
			dest.changePiece(c.getPiece());
			c.changePiece(null);
		}
	}



	public void promotion(Case dest) {

		System.out.println("\nPromotion du pion :\n( C : Cavalier ), ( T : Tour ), ( F : Fou ), ( AUTRE : Dame/Reine )");
		Scanner scan = new Scanner(System.in);
		String str = scan.nextLine();
		if (str.charAt(0) == 'C') {
			dest.changePiece(new Cavalier(dest.getPiece().getCoul()));
			System.out.println("Pion -> Cavalier");
		} else if (str.charAt(0) == 'T') {
			dest.changePiece(new Tour(dest.getPiece().getCoul()));
			System.out.println("Pion -> Tour");
		} else if (str.charAt(0) == 'F') {
			dest.changePiece(new Fou(dest.getPiece().getCoul()));
			System.out.println("Pion -> Fou");
		} else {
			dest.changePiece(new Reine(dest.getPiece().getCoul()));
			System.out.println("Pion -> Dame/Reine");
		}
	}

	public Point trouverR(String couleur) {
		
		for( int i = 0 ; i < 8 ; i++) 
		{
			for( int j = 0 ; j < 8 ; j++) 
			{
			if(this.plateau[i][j].getPiece()!= null){
				if(this.plateau[i][j].getPiece().getClass() == Roi.class
					&& this.plateau[i][j].getPiece().getCoul() == couleur){
					return new Point(i,j);
					}
				}
			}
		}
		
		return new Point(9,9);
	}



	public boolean Echec(String ct){
		Point k = trouverR(ct);
		int x = k.getX();
		int y = k.getY();
		this.verifEchec= true;
		for( int i = 0 ; i < 8 ; i++){
			for( int j = 0 ; j < 8 ; j++){
			if(this.plateau[i][j].getPiece()!= null){
				if(this.plateau[i][j].getPiece().getCoul() != ct){
				if(this.movePiece(i,j,x,y)){
					this.verifEchec= false;
					return true;
					}	
				} 
			}

		}
	}
	this.verifEchec= false;
	return false;
	}
}
