package src.heuristics;

import src.Node;
import src.Player;

public class Heuristic {
	//1 - based on how many top piece i have
	//1 - based on how many stacks i control
	//2-based on total of captured pieces
	protected int estimate;

	public int evaluate(Node node, Player player) {
		return estimate;
	}

	public int getEstimate() {
		return estimate;
	}

	public void setEstimate(int estimate) {
		this.estimate = estimate;
	}

}
