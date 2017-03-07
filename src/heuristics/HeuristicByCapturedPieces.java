package src.heuristics;

import java.util.Random;

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
		
		Random rand = new Random();

		int  n = rand.nextInt(3) + 1;
		if (n < 2)
			estimate++;
			
		estimate = 6 - estimate;
		return estimate;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
