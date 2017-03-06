package src;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import src.heuristics.Heuristic;

public class Player {

	private String name;
	private List<Piece> pieces;
	private List<Piece> opponentPieces;
	private Heuristic heuristic;
	private int index;
	private Set<Node> moves;
	
	public Player (Player p) {
		this.name = p.name;
		pieces = new ArrayList<>(p.pieces);
		opponentPieces = new ArrayList<>(p.opponentPieces);
		this.heuristic = p.heuristic;
		this.index = p.index;
		moves = new LinkedHashSet<>(p.moves);
	}
	public Player(String name, int index, Heuristic heuristic) {
		this.name = name;
		pieces = new ArrayList<>();
		opponentPieces = new ArrayList<>();
		this.heuristic = heuristic;
		this.index = index;
		moves = new LinkedHashSet<>();
	}
	
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public List<Piece> getPieces() {
		return pieces;
	}


	public void setPieces(List<Piece> pieces) {
		this.pieces = pieces;
	}
	
	public void addPiece(Piece piece) {
		if (piece != null) {
			pieces.add(piece);
		}
	}
	
	public void addOponentPiece(Piece piece) {
		if (piece != null) {
			opponentPieces.add(piece);
		}
	}

    public boolean equals(Object obj) {
    	if (obj == this)
			return true;
		
		if (obj == null || obj.getClass() != this.getClass())
			return false;
		
		Player player = (Player) obj;
		
		return name.equals(player.name) && pieces.equals(player.pieces) && opponentPieces.equals(player.opponentPieces);
    }
    
    
	public Heuristic getHeuristic() {
		return heuristic;
	}


	public void setHeuristic(Heuristic heuristic) {
		this.heuristic = heuristic;
	}

	

	public List<Piece> getOpponentPieces() {
		return opponentPieces;
	}


	public void setOpponentPieces(List<Piece> opponentPieces) {
		this.opponentPieces = opponentPieces;
	}


	public int getIndex() {
		return index;
	}


	public void setIndex(int index) {
		this.index = index;
	}


	public Set<Node> getMoves() {
		return moves;
	}


	public void setMoves(Set<Node> moves) {
		this.moves = moves;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
