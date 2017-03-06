package src.heuristics;

import java.util.List;

import src.Game;
import src.Node;
import src.Piece;
import src.Player;
import src.State;

public class HeuristicByStackControl extends Heuristic {

	public int evaluate(Node node, Player player) {

		if (node != null && node.getState() != null) {
			State state = node.getState();
			List<Piece>[][] stateGrid = state.getGrid();

			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (node.getState().isBoxValid(i, j)) {
						if (stateGrid[i][j] != null && !stateGrid[i][j].isEmpty()
								&& !stateGrid[i][j].get(stateGrid[i][j].size() - 1).getPlayer().equals(player)) {
							estimate++;
						}
					}
				}
			}
		}
		
		return estimate;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Game game = new Game(2);
		State s = new State(game.getGamePieces());
		s.print();
		
		Heuristic heuristic = new HeuristicByStackControl();
		System.out.println(heuristic.evaluate(new Node(s), game.getPlayers().get(0)));
	}
}
