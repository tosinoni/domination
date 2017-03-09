package src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import src.heuristics.Heuristic;
import src.heuristics.HeuristicByRandom;
import src.heuristics.HeuristicByStackControlAndCapturedPieces;

public class Game {
	private List<Player> players;
	private List<Player> AIPlayers;
	private List<Piece>[][] gamePieces;
	private Set<Node> rootsChildren;
	private Node bestMove;
	private int uptoDepth = 4;
	private ProductionSystem productionSystem;
	private Player currentPlayer;
	private Heuristic currentHeuristic;

	public Game(int n) {
		gamePieces = new ArrayList[8][8];
		players = new ArrayList<>();
		createPlayers(n);
		createInitialGameState();
		rootsChildren = new LinkedHashSet<>();
		productionSystem = new ProductionSystem();
		currentPlayer = players.get(0);
	}

	private boolean isGameOver(List<Piece>[][] board, List<Player> players) {
		for (Player player : players) {
			if (player.getOpponentPieces().size() == 6 || isBoardControlledByOnePlayer(board, player)) {
				return true;
			}
		}

		return false;
	}

	public List<Piece>[][] cloneBoard(List<Piece>[][] gamePieces) {
		List<Piece>[][] initialGamePieces = new ArrayList[8][8];
		for (int i = 0; i < gamePieces.length; i++) {
			for (int j = 0; j < gamePieces.length; j++) {
				if (isBoxValid(i, j) && gamePieces[i][j] != null) {
					initialGamePieces[i][j] = new ArrayList<>(gamePieces[i][j]);
				}
			}
		}

		return initialGamePieces;
	}

	private boolean isBoardControlledByOnePlayer(List<Piece>[][] board, Player player) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (board[i][j] != null && !board[i][j].isEmpty()
						&& !board[i][j].get(board[i][j].size() - 1).getPlayer().equals(player)) {
					return false;
				}

			}
		}

		return true;
	}

	private List<Piece>[][] playMove(List<Piece>[][] board, List<Player> players, Node node) {
		if (node != null) {
			board = node.getState().getCloneGrid();
			if (node.getCapturedPieces() != null && !node.getCapturedPieces().isEmpty()) {
				for (Piece piece : node.getCapturedPieces()) {
					if (piece.getPlayer().getIndex() == players.get(0).getIndex())
						players.get(0).addPiece(piece);
					else
						players.get(0).addOponentPiece(piece);
				}
			}

			if (!players.get(0).getPieces().isEmpty() && node.getMoveType() != null
					&& node.getMoveType().equals(Node.pieceMoveType)) {
				players.get(0).getPieces().remove(0);
			}
			
			System.out.println("Player " + players.get(0).getIndex() + " played move " + node.getMovePosition());

			return board;
		}

		return null;
	}

	public int alphaBetaMinimax(List<Player> players, Node node, int alpha, int beta, int depth, int turn) {
		Player player = players.get(turn - 1);

		if (depth == uptoDepth || isGameOver(node.getState().getGrid(), players)) {
			return currentHeuristic.evaluate(node, player);
		}

		Set<Node> expandedNodes = productionSystem.expand(node, player);

		if (expandedNodes.isEmpty()) {
			return currentHeuristic.evaluate(node, player);
		}

		int currentScore;

		if (turn == 1) {
			for (Node newNode : expandedNodes) {
				currentScore = alphaBetaMinimax(players, newNode, alpha, beta, depth + 1, 2);

				if (currentScore > alpha) {
					alpha = currentScore;
					if (depth == 1)
						bestMove = newNode;
				}
				if (alpha >= beta)
					return alpha;
			}
			return alpha;
		} else {
			for (Node newNode : expandedNodes) {

				currentScore = alphaBetaMinimax(players, newNode, alpha, beta, depth + 1, 1);
				if (currentScore < beta) {
					beta = currentScore;
					if (depth == 1)
						bestMove = newNode;
				}
				if (beta <= alpha)
					return beta;
			}
			return beta;
		}
	}

	private void createPlayers(int n) {
		for (int i = 1; i <= n; i++) {
			Player player;
			if (i == 1)
				player = new Player("" + i, i, new HeuristicByRandom());
			else
				player = new Player("" + i, i, new HeuristicByStackControlAndCapturedPieces());

			players.add(player);
		}
	}

	private void createInitialGameState() {
		int count = 0;
		int num = 0;
		Player currentPlayer = players.get(1);
		for (int i = 1; i < 7; i++) {
			for (int j = 1; j < 7; j++) {
				if (players.size() == 2 && isBoxValid(i, j)) {

					if (count % 2 == 0 && currentPlayer.equals(players.get(0))) {
						num = 1;
					} else if (count % 2 == 0 && currentPlayer.equals(players.get(1))) {
						num = 0;
					}
					currentPlayer = players.get(num);
					Piece piece = new Piece(Integer.toString(num + 1), players.get(num));
					// players.get(num).addPiece(piece);
					gamePieces[i][j] = new ArrayList<>(Arrays.asList(piece));
					count++;
				}
			}
		}
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public List<Piece>[][] getGamePieces() {
		return gamePieces;
	}

	public void setGamePieces(List<Piece>[][] gamePieces) {
		this.gamePieces = gamePieces;
	}

	public boolean isBoxValid(int i, int j) {
		// top row
		if ((i == 0 || i == 7) && (j == 0 || j == 1 || j == 6 || j == 7))
			return false;

		return !((i == 1 || i == 6) && (j == 0 || j == 7));
	}

	public static List<Player> cloneList(List<Player> players) {
		List<Player> clone = new ArrayList<Player>(players.size());
		for (Player item : players)
			clone.add(new Player(item));
		return clone;
	}

	public void play(Scanner sc) {
		Node n = new Node(new State(gamePieces));
		n.getState().print();
		currentHeuristic = currentPlayer.getHeuristic();

		while (!isGameOver(gamePieces, players)) {
			String input = null;

			while (input == null) {
				System.out.println("\nplease enter any character to see the next players move: ");
				input = sc.nextLine();
			}
			
			alphaBetaMinimax(cloneList(players), n, Integer.MIN_VALUE, Integer.MAX_VALUE, 1, 1);
			gamePieces = playMove(gamePieces, players, bestMove);
			n = bestMove;
			
			bestMove.getState().print();

			currentPlayer = players.get(0);
			System.out.println("player " + currentPlayer.getIndex() + " ----> captured pieces: "
					+ currentPlayer.getOpponentPieces().size() + "  reserve: " + currentPlayer.getPieces().size());

			setNextPlayer();
		}
		
		System.out.println("****************************************************************************");
		System.out.println("******************  Player " + players.get(1).getIndex() + " wins ***************************");
		System.out.println("****************************************************************************");
	}

	private void setNextPlayer() {
		Collections.reverse(players);
		currentPlayer = players.get(0);

		currentHeuristic = currentPlayer.getHeuristic();

	}

	public Node returnBestMove() {
		Node node = null;

		System.out.println(rootsChildren.size());
		for (Node n : rootsChildren) {
			if ((node == null || node.getEstimateCost() <= n.getEstimateCost())) {
				node = n;
			}
		}

		return node;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Game game = new Game(2);
		State s = new State(game.getGamePieces());
		Move m = new Move();
		List<Piece> pieces = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			pieces.add(new Piece("1", game.players.get(0)));
		}
		s.getGrid()[1][1].addAll(pieces);
		Set<Node> nodes = m.moveLeft(s, 1, 2);

		Node n = nodes.iterator().next();
		//game.gamePieces = game.playMove(game.gamePieces, game.players, n, 0);
		System.out.println(n.getCapturedPieces().size());

		System.out.println(game.players.get(0).getPieces());
		s = new State(game.getGamePieces());
		s.print();
		// for (Node n : nodes) {
		// n.getState().print();
		// }
	}

}
