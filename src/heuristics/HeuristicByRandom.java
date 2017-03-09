package src.heuristics;

import java.util.Random;

import src.Node;
import src.Piece;
import src.Player;

public class HeuristicByRandom extends Heuristic{

	public int evaluate(Node node, Player player) {

		Random rand = new Random();
		estimate = rand.nextInt(50) + 1;
		
		return estimate;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
