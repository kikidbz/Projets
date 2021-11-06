import java.util.Scanner;
import java.io.*;

public class Jeu {

	private Scanner sc;
	private Plateau plateau;
	private Joueur joueur;
	private int tours;
	private int x_depart;
	private int y_depart;
	private int x_fin; 
	private int y_fin;
	private String nj1;
	private String nj2;
	private String histo;

	public Jeu() {
		this.plateau = new Plateau();
		this.sc = new Scanner(System.in);
		this.x_depart = -1;
		this.x_fin = -1;
		this.y_depart = -1;
		this.y_fin = -1;
		this.histo = "";
		this.nj1 = "";
		this.nj2 = "";
		this.tours = 0;
	}

	public void jouer() {
		String c0 = "B";
		String c1 = "N";
		boolean joue = true;
		System.out.println("Nom du Joueur 1");
		this.nj1 = sc.nextLine();
		System.out.println("Nom du Joueur 2");
		this.nj2 = sc.nextLine();

		Joueur j1= new Joueur (nj1,c0);
		Joueur j2= new Joueur (nj2,c1);

		while (joue) {
			if (this.tours % 2 == 0) {
					if (j1.getcol()== "B") {
					if (plateau.Echec("B") == true){
						joue = this.entreePiece(this.plateau, c0, this.tours);
						if (plateau.Echec("B") == true){
							System.out.println("ECHEC ET MAT FIN DU JEU VICTOIRE DE : " + nj2);
							joue=false;
						}
						
						
					}
					else{
						joue = this.entreePiece(this.plateau, c0, this.tours);
						if(plateau.trouverR("B") == new Point(9,9)){
								joue=false;
						}
					}
					if(plateau.Echec("N")){
						System.out.println(this.nj1 + " vous a mis en Echec !!!");
					}
				}
					else{
					joue= false;
				}
			}
				else {
					if (j2.getcol()== "N") {
					if (plateau.Echec("N") == true){	
						joue = this.entreePiece(this.plateau, c1, this.tours);
						if (plateau.Echec("N") == true){
							System.out.println("ECHEC ET MAT FIN DU JEU VICTOIRE DE : " + nj1);
							joue=false;
						}
					}
					else{
						joue = this.entreePiece(this.plateau, c1, this.tours);
						if(plateau.trouverR("B") == new Point(9,9)){
							joue = false;
						}
					}
					if (plateau.Echec("B")){
						System.out.println(this.nj2 + " vous a mis en Echec !!!");
					}
				}
				else {
					joue= false;
				}
			}

		this.tours++;
	}
}


	public boolean entreePiece(Plateau plateau, String couleur, int tours) {
		String coul_piece_depart;
		Piece piece_depart;
		Piece piece_fin;

		do {
			this.plateau.afficher(this.tours);
			if(this.tours % 2 == 0){
				System.out.println(this.nj1 +": " +"Veuillez saisir les coordonnees :Vous etes Blanc, SAVE pour Sauvegarder ");
			}
			else{
				System.out.println(this.nj2 +": " +"Veuillez saisir les coordonnees :Vous etes Noir, SAVE pour Sauvegarder ");
			}
			String str = sc.nextLine();


			if(str.equals("SAVE")){
				this.sauvegarde();
				return false;

			}
			else{
			this.x_depart = str.charAt(0) - 'a';
			this.y_depart = str.charAt(1) - '1';
			this.x_fin = str.charAt(2) - 'a';
			this.y_fin = str.charAt(3) - '1';
			String histo = "";
			this.historique(str);
		}
			coul_piece_depart = "Gris";
			if (plateau.getPiece(this.x_depart, this.y_depart) != null) {
				coul_piece_depart = plateau.getPiece(this.x_depart, this.y_depart).getCoul();
			}

			piece_depart = plateau.getPiece(this.x_depart, this.y_depart);
			piece_fin = plateau.getPiece(this.x_fin, this.y_fin);
		}
 
		while (!plateau.inMap(this.x_depart, this.y_depart, this.x_fin, this.y_fin)
				|| coul_piece_depart != couleur
				|| !plateau.movePiece(this.x_depart, this.y_depart, this.x_fin, this.y_fin));


		return true;
	}


	public void historique(String his){
		if (tours % 2 == 0) {
		 	this.histo = histo + "\n" + plateau.getPiece(this.x_depart, this.y_depart)+" : "+his;
			System.out.println(histo);		
		}
		else{
			this.histo = histo +" - " + plateau.getPiece(this.x_depart, this.y_depart)+" : "+ his;
			System.out.println(histo);	
		}
	}

	public void sauvegarde(){

		System.out.println("Veuillez saisir le nom de la Sauvegarde");
		String fich = sc.nextLine();
		File file = new File("C:/Users/kikid/Desktop/Projet Final/Sauvegarde/" + fich + ".txt");
		if(!file.exists()){
			try{
				file.createNewFile();
			}catch(IOException e){
				System.out.println(e);
			}
		}
		try{
			FileWriter writer = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(writer);
			for (int i = 0; i < 8; i++){
	            for (int j = 0; j < 8; j++){
				if (plateau.getPiece(i,j) != null){
				bw.write(plateau.getCase(i,j).getPiece().toString());
				bw.newLine();
				bw.write(""+i+j);
				bw.newLine();
				}
			}
		}
		bw.close();
		writer.close();
	}catch(IOException e){
		System.out.println(e);
	}
}
/*
	public Piece create(String zz ){
		String pp = zz.charAt(0);
		String vv = zz.charAt(1);
		int kk;
		if(vv == "0"){
			kk= 0;
		}
		else{
			kk= 1;
		}
		Piece npiece = new piece(kk);

		return pp
	}

	public Plateau charger(){
		try{
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8") );
			Plateau plt = new Plateau();
			String line1 = reader.readLine();
			String line2 = reader.readLine();
			while(line != null){
				line1 = reader.readLine();
				line2 = reader.readLine();
				line2 = plt.getCase(x,y);
				line1 = line2.getPiece();


			}
			reader.close();
		}
	}
*/

}

