package src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public class State {
	public static final Map<Integer, List<Integer>> boundaries = new HashMap<>();

	static {
		boundaries.put(0, Arrays.asList(2,4));
		boundaries.put(1, Arrays.asList(1,6));
		boundaries.put(2, Arrays.asList(0,8));
		boundaries.put(3, Arrays.asList(0,8));
		boundaries.put(4, Arrays.asList(0,8));
		boundaries.put(5, Arrays.asList(0,8));
		boundaries.put(6, Arrays.asList(1,6));
		boundaries.put(7, Arrays.asList(2,4));

	};
	private List<Piece>[][] grid;

	public State(List<Piece>[][] gamePieces) {
		grid = new ArrayList[8][8];

		if (gamePieces != null) {
			for (int i = 0; i < grid.length; i++) {
				for (int j = 0; j < grid.length; j++) {
					if (isBoxValid(i, j)) {
						grid[i][j] = gamePieces[i][j];
					}
				}
			}
		}
	}

	public List<Piece>[][] getCloneGrid () {
		List<Piece>[][] newGrid = new ArrayList[8][8];
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				if (isBoxValid(i, j) && grid[i][j] != null) {
					newGrid[i][j] = new ArrayList<>(grid[i][j]);
				}
			}
		}
		
		return newGrid;
	}
	public List<Piece>[][] getGrid() {
		return grid;
	}

	public void setGrid(List<Piece>[][] grid) {
		this.grid = grid;
	}

	public boolean equals(Object obj) {
		if (obj == this)
			return true;

		if (obj == null || obj.getClass() != this.getClass())
			return false;

		State state = (State) obj;

		return Arrays.deepEquals(grid, state.grid);
	}

	private void printPieces(List<Piece> pieces) {
		for (int i = 0; i < 5; i++) {
			if (pieces == null || i >= pieces.size())
				System.out.print(" ");
			else
				System.out.print(pieces.get(i));
		}
	}

	private void printLine(int index) {
		int num = index;
		int numberOfSpaces = 0;
		if (num == 0) {
			num = 25;
			numberOfSpaces = 12;
		} else if (num == 1) {
			num = 37;
			numberOfSpaces = 6;
		} else if (num == 6) {
			num = 49;
			numberOfSpaces = 6;
		} else if (num == 7) {
			num = 37;
			numberOfSpaces = 6;
		} else
			num = 49;

		if (index != 6)
		printSpaces(numberOfSpaces);

		printDashes(num);

		if (index == 7) {
			numberOfSpaces = 12;
			num = 25;
		}
		printSpaces(numberOfSpaces);
	}
	
	private void printDashes(int num) {
		for (int i = 0; i < num; i++)
			System.out.print("-");
		System.out.println();
	}

	public void printSpaces(int numberOfSpaces) {
		if (numberOfSpaces != 0)
			System.out.print(String.format("%" + numberOfSpaces + "s", ""));
	}
	public void print() {
		int count;
		for (int i = 0; i < grid.length; i++) {
			count = 0;
			for (int j = 0; j < grid.length; j++) {
				if (isBoxValid(i, j)) {
					if (count == 0) {
						printLine(i);
						count++;
					}

					System.out.print("|");
					printPieces(grid[i][j]);
					if (((i == 0 || i == 7) && j == 5) || ((i == 1 || i == 6) && j == 6) || j == 7)
						System.out.print("|");

				} else if (i == 7 && j == 7) {
					System.out.println();
					printSpaces(12);
					printDashes(25);
				}

			}
			System.out.print("\n");
		}
	}

	public boolean isBoxValid(int i, int j) {
		// top row
		if ((i == 0 || i == 7) && (j == 0 || j == 1 || j == 6 || j == 7))
			return false;

		return !((i == 1 || i == 6) && (j == 0 || j == 7));
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Piece>[][] pieces = new ArrayList[8][8];
		State s = new State(pieces);
		s.print();
	}

}
