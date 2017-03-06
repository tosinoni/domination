package src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Node {
	public static String pieceMoveType = "piece";
	private State state;
	private List<Piece> capturedPieces;
	private Position movePosition;
	private String moveType;
	private int estimateCost;

	public Node(State state) {
		this.state = state;
		capturedPieces = new ArrayList<>();
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Position getMovePosition() {
		return movePosition;
	}

	public void setMovePosition(Position movePosition) {
		this.movePosition = movePosition;
	}

	public String getMoveType() {
		return moveType;
	}

	public void setMoveType(String moveType) {
		this.moveType = moveType;
	}

	public List<Piece> getCapturedPieces() {
		return capturedPieces;
	}

	public void setCapturedPieces(List<Piece> capturedPieces) {
		if (capturedPieces != null && !capturedPieces.isEmpty())
			this.capturedPieces.addAll(capturedPieces);
	}

	public static String getPieceMoveType() {
		return pieceMoveType;
	}

	public static void setPieceMoveType(String pieceMoveType) {
		Node.pieceMoveType = pieceMoveType;
	}

	public int getEstimateCost() {
		return estimateCost;
	}

	public void setEstimateCost(int estimateCost) {
		this.estimateCost = estimateCost;
	}

	public boolean equals(Object obj) {
		if (obj == this)
			return true;

		if (obj == null || obj.getClass() != this.getClass())
			return false;

		Node node = (Node) obj;

		return node.state.equals(this.state) && node.capturedPieces.equals(capturedPieces)
				&& node.movePosition.equals(movePosition) && node.estimateCost == estimateCost
				&& node.moveType.equals(moveType);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + state.hashCode();
		result = prime * result + movePosition.hashCode();
		result = prime * result + capturedPieces.hashCode();
		result = prime * result + Integer.hashCode(estimateCost);

		return result;
	}

}
