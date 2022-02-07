public abstract class Piece {
	private String couleur;
	private boolean move;
	
	public Piece (String couleur) {
		this.couleur = couleur;
		this.move = false;
	}

	public boolean getMove () {
		return this.move;
	}
	
	public void changeMove () {
		this.move = true;
	}

	public String getCoul () {
		return this.couleur;
	}
	
	public abstract boolean movePiece(int depX, int depY, int finX, int finY);
}
