public class Fou extends Piece {

	private final String nom = "F";

	public Fou(String couleur) {
		super(couleur);
	}

	public String toString() {
		return this.nom + this.getCoul();
	}

	public boolean movePiece(int depX, int depY, int finX, int finY) {
		int dx = depX - finX;
		int dy = depY - finY;

		if ((dx == dy) || (dx == -dy)) {
			return true;
		}

		return false;
	}
}
