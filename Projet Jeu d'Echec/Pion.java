
public class Pion extends Piece {
	
	private final String nom = "P";
	

	public Pion (String couleur) {
		super(couleur);
	}
	
	public String toString () {
		return this.nom + this.getCoul();
	}
	
	public boolean movePiece(int depX, int depY, int finX, int finY) {
		int dx = finX - depX;
		int dy = finY - depY;
		
		if( this.getCoul() == "B" )
		{
			if( dx == 0 )
			{
				if (this.getMove() && dy == 1)
				{
					return true;
				}
				else if(!this.getMove() && (dy == 1 || dy == 2) )
				{
					return true;
				}
				else
					return false;
			}
			else if ( (dx == 1 || dx == -1) && dy == 1)
				return true;
			
			return false;
		}
		else if( this.getCoul() == "N" )
		{
			if( dx == 0 )
			{
				if (this.getMove() && dy == -1)
				{
					return true;
				}
				else if( !this.getMove() && (dy == -1 || dy == -2) )
				{
					return true;
				}
				else
					return false;
			}
			else if ( (dx == -1 || dx == 1) && dy == -1)
				return true;
			
			return false;
		}
		return false;
	}
}
