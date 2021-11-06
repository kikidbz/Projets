public class Case {
	private Piece piece;

	public Case(Piece piece) {
		this.piece = piece;
	}

	public Piece getPiece() {
		return this.piece;
	}

	public void changePiece(Piece piece) {
		this.piece = piece;
	}
}
