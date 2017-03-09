package src;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Move {
	private boolean isRight(State state, int i, int j, int num) {
		List<Piece> pieces = state.getGrid()[i][j];
		
		return pieces != null && !pieces.isEmpty() && num <= 5 &&  (j + num) < State.boundaries.get(i).get(1);
	}
	
	private boolean isLeft(State state, int i, int j, int num) {
		List<Piece> pieces = state.getGrid()[i][j];
		
		return pieces != null && !pieces.isEmpty() && num <= 5 &&  (j - num) >= State.boundaries.get(i).get(0);
	}
	
	private boolean isUp(State state, int i, int j, int num) {
		List<Piece> pieces = state.getGrid()[i][j];
		
		return pieces != null && !pieces.isEmpty() && num <= 5 &&  (i - num) >= State.boundaries.get(j).get(0);
	}
	
	private boolean isDown(State state, int i, int j, int num) {
		List<Piece> pieces = state.getGrid()[i][j];
		
		return pieces != null && !pieces.isEmpty() && num <= 5 &&  (i + num) < State.boundaries.get(j).get(1);
	}
	
	public Set<Node> moveRight(State state, int i, int j) {
		List<Piece> pieces = state.getGrid()[i][j];
		if (pieces != null && !pieces.isEmpty()) {
			Set<Node> nodes = new LinkedHashSet<>();
			for (int index =1; index <= pieces.size(); index++) {
				if (isRight(state, i, j, index)) {

					List<Piece>[][] gamePieces =  state.getCloneGrid();
					
					if (gamePieces[i][j+index] == null) {
						gamePieces[i][j+index] = new ArrayList<>();
					}

					List<Piece> removedPieces = getRemovedPieces(gamePieces, i, j, index);
					List<Piece> capturedPieces = null;
					
					if (gamePieces[i][j+index] != null && !gamePieces[i][j+index].isEmpty() && (gamePieces[i][j+index].size() + removedPieces.size()) > 5) {
						capturedPieces = getCapturedPieces(gamePieces, i, j+index, (gamePieces[i][j+index].size() + removedPieces.size() - 5));
					}

					gamePieces[i][j+index].addAll(removedPieces);
					State s = new State (gamePieces);
					Node n = new Node(s);
					n.setCapturedPieces(capturedPieces);
					n.setMovePosition(new Position(i, j, i, j+index));
					nodes.add(n);
				}
			}
			return nodes;
		}
		return null;
	}
	
	public Set<Node> moveLeft(State state, int i, int j) {
		List<Piece> pieces = state.getGrid()[i][j];
		if (pieces != null && !pieces.isEmpty()) {
			Set<Node> nodes = new LinkedHashSet<>();
			for (int index =1; index <= pieces.size(); index++) {
				if (isLeft(state, i, j, index)) {
					int num = j-index;
					
					List<Piece>[][] gamePieces =  state.getCloneGrid();
					
					if (gamePieces[i][num] == null) {
						gamePieces[i][num] = new ArrayList<>();
					}

					List<Piece> removedPieces = getRemovedPieces(gamePieces, i, j, index);
					List<Piece> capturedPieces = null;
					if (gamePieces[i][num] != null && !gamePieces[i][num].isEmpty() && (gamePieces[i][num].size() + removedPieces.size()) > 5) {
						capturedPieces = getCapturedPieces(gamePieces, i, num, (gamePieces[i][num].size() + removedPieces.size() - 5));
					}

					gamePieces[i][num].addAll(removedPieces);
					State s = new State (gamePieces);
					Node n = new Node(s);
					n.setCapturedPieces(capturedPieces);
					n.setMovePosition(new Position(i, j, i, num));
					nodes.add(n);
				}
			}
			return nodes;
		}
		return null;
	}
	
	public Set<Node> moveUp(State state, int i, int j) {
		List<Piece> pieces = state.getGrid()[i][j];
		if (pieces != null && !pieces.isEmpty()) {
			Set<Node> nodes = new LinkedHashSet<>();
			for (int index =1; index <= pieces.size(); index++) {
				if (isUp(state, i, j, index)) {
					int num = i-index;
					
					List<Piece>[][] gamePieces =  state.getCloneGrid();
					
					if (gamePieces[num][j] == null) {
						gamePieces[num][j] = new ArrayList<>();
					}

					List<Piece> removedPieces = getRemovedPieces(gamePieces, i, j, index);
					List<Piece> capturedPieces = null;
					if (gamePieces[num][j] != null && !gamePieces[num][j].isEmpty() && (gamePieces[num][j].size() + removedPieces.size()) > 5) {
						capturedPieces = getCapturedPieces(gamePieces, num, j, (gamePieces[num][j].size() + removedPieces.size() - 5));
					}
					gamePieces[num][j].addAll(removedPieces);
					State s = new State (gamePieces);
					Node n = new Node(s);
					n.setCapturedPieces(capturedPieces);
					n.setMovePosition(new Position(i, j, num, j));
					nodes.add(n);
				}
			}
			return nodes;
		}
		return null;
	}
	
	public Set<Node> moveDown(State state, int i, int j) {
		List<Piece> pieces = state.getGrid()[i][j];
		if (pieces != null && !pieces.isEmpty()) {
			Set<Node> nodes = new LinkedHashSet<>();
			for (int index =1; index <= pieces.size(); index++) {
				if (isDown(state, i, j, index)) {
					int num = i + index;
					
					List<Piece>[][] gamePieces =  state.getCloneGrid();
					
					if (gamePieces[num][j] == null) {
						gamePieces[num][j] = new ArrayList<>();
					}

					List<Piece> removedPieces = getRemovedPieces(gamePieces, i, j, index);
					List<Piece> capturedPieces = null;
					if (gamePieces[num][j] != null && !gamePieces[num][j].isEmpty() && (gamePieces[num][j].size() + removedPieces.size()) > 5) {
						capturedPieces = getCapturedPieces(gamePieces, num, j, (gamePieces[num][j].size() + removedPieces.size() - 5));
					}
					gamePieces[num][j].addAll(removedPieces);
					State s = new State (gamePieces);
					Node n = new Node(s);
					n.setCapturedPieces(capturedPieces);
					n.setMovePosition(new Position(i, j, num, j));

					nodes.add(n);
				}
			}
			return nodes;
		}
		return null;
	}
	
	public List<Piece> getRemovedPieces(List<Piece>[][] gamePieces, int i, int j, int total) {
		List<Piece> removedPieces = new ArrayList<>();
		for (int num = total; num > 0; num--) {
			Piece piece = gamePieces[i][j].remove(gamePieces[i][j].size() - 1);
			removedPieces.add(piece);
		}
		
		Collections.reverse(removedPieces);
		return removedPieces;
	}
	
	public List<Piece> getCapturedPieces(List<Piece>[][] gamePieces, int i, int j, int total) {
		List<Piece> removedPieces = new ArrayList<>();
		for (int num = 0; num < total; num++) {
			Piece piece = gamePieces[i][j].remove(0);
			removedPieces.add(piece);
		}
		
		return removedPieces;
	}
	
	public Set<Node> getAllMoves(State state, int i, int j) {
		Set<Node> nodes = new LinkedHashSet<>();
		
		nodes.addAll(moveRight(state, i, j));
		nodes.addAll(moveLeft(state, i, j));
		nodes.addAll(moveUp(state, i, j));
		nodes.addAll(moveDown(state, i, j));
		
		nodes.removeAll(Collections.singleton(null));
		return nodes;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
