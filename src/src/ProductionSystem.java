package src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class ProductionSystem {

	private Move move;
	
	public ProductionSystem() {
		move = new Move();
	}
	
	public Set<Node> expand(Node node, Player player) {
		Set<Node> nodes = new LinkedHashSet<>();

		nodes.addAll(getAllPossibleMoveUsingBoardState(node, player));
		
		Set<Node> newNodes = getAllPossibleMoveUsingReservePieces(node, player);
		
		if (newNodes != null && !newNodes.isEmpty())
			nodes.addAll(newNodes);

		return nodes;
	}
	
	private Set<Node> getAllPossibleMoveUsingReservePieces(Node node, Player player) {
		Set<Node> nodes = null;
		if (node != null && !player.getPieces().isEmpty()) {
			nodes = new LinkedHashSet<>();

			for (Piece piece : player.getPieces()) {
				for (int i = 0; i < 8; i++) {
					for (int j = 0; j < 8; j++) {
						List<Piece>[][] stateGrid = node.getState().getCloneGrid();
						
						if (node.getState().isBoxValid(i, j)) {
							if (stateGrid[i][j] == null)
								stateGrid[i][j] = new ArrayList<>();
							

							
							stateGrid[i][j].add(piece);
							
							
							State s = new State(stateGrid);
							Node n = new Node(s);
							n.setMovePosition(new Position(i, j));
							if (stateGrid[i][j].size() == 5) {
								n.setCapturedPieces(Arrays.asList(stateGrid[i][j].remove(0)));

							}
							n.setMoveType(Node.pieceMoveType);
							nodes.add(n);
 						} 
					}
				}
			}
		}
		
		return nodes;
	}
	

	private Set<Node> getAllPossibleMoveUsingBoardState(Node node, Player player) {
		if (node != null) {
			Set<Node> nodes = new LinkedHashSet<>();
			State state = node.getState();
			List<Piece>[][] stateGrid = state.getGrid();
			
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (stateGrid[i][j] != null && !stateGrid[i][j].isEmpty()
							&& stateGrid[i][j].get(stateGrid[i][j].size() - 1).getPlayer().equals(player)) {
						nodes.addAll(move.getAllMoves(state, i, j));
					}
				}
			}
						
			return nodes;
		}
		return null;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Game game = new Game(2);
		State s = new State(game.getGamePieces());
		List<Piece> pieces = new ArrayList<>();
		for (int i=0; i<4; i++) {
			pieces.add(new Piece("2", game.getPlayers().get(1)));
		}
		
		game.getPlayers().get(0).addPiece(new Piece("1", game.getPlayers().get(0)));
		s.getGrid()[1][1].addAll(pieces);
		s.print();
		
		Node node = new Node(s);
		ProductionSystem productionSystem = new ProductionSystem();
		Set<Node> nodes = productionSystem.expand(node, game.getPlayers().get(0));
		for (Node n : nodes) {
			n.getState().print();
		}
		
		System.out.println(nodes.size());
	}

}
