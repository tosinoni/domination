package src.heuristics;

import src.Node;
import src.Piece;
import src.Player;

public class HeuristicByCapturedPieces extends Heuristic{

	public int evaluate(Node node, Player player) {
		estimate = 0;

		if (node != null) {
			for (Piece piece : node.getCapturedPieces()) {
				if (piece != null && !piece.getPlayer().equals(player))
					estimate++;
			}
		}
		
		estimate = 6 - estimate;
		return estimate;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
