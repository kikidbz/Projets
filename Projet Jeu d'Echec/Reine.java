public class Reine extends Piece {
	
	private final String nom = "D";

	public Reine(String couleur) {
		super(couleur);
	}

	public String toString () {
		return this.nom + this.getCoul();
	}


	public boolean movePiece(int depX, int depY, int finX, int finY) {
		int dx = depX - finX;
		int dy = depY - finY;
		
		if( (dx == dy) || (dx == -dy) 
				|| dx == 0 || dy == 0)
		{
			return true;
		}
		return false;
	}

}
