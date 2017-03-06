package src;

public class Piece {
    private String name;
    private Player player;
    
    public Piece(String name, Player player) {
    	this.name = name;
    	this.player = player;
    }
    
    
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}
	
	public Player getPlayer() {
		return player;
	}


	public void setPlayer(Player player) {
		this.player = player;
	}


	public boolean equals (Object obj) {
		if (obj == this)
			return true;
		
		if (obj == null || obj.getClass() != this.getClass())
			return false;
		
		Piece piece = (Piece) obj;
		
		return name.equals(piece.name);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
