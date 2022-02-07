public class Joueur {
	
	private String nom; 
	private String col;

	public Joueur (String nom, String col) {
		this.nom = nom;
		this.col = col;
	}

	public String getnom () {
		return this.nom;
	}
	
	public String getcol () {
		return this.col;
	}

	public String toString(){
		return "Joueur [nom=" + nom + ", couleur=" + col + "]";
	}
}
