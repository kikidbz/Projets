public class Tour extends Piece{
	
	private final String nom = "T";
	
	public Tour(String couleur) {
		super(couleur);
	}
	
	public String toString () {
		return this.nom + this.getCoul();
	}

	public boolean movePiece (int depX, int depY, int finX, int finY) {
		if( (depX == finX) 
				|| (depY == finY) )
		{
			return true;
		}
			
		return false;
	}
}
