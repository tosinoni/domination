package src;

import java.util.Scanner;

public class GameUI {

	private static boolean isNumber(String input) {
		int num;
		try {
			num = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			return false;
		}
		
		if (num != 2)
			return false;
		return true;
	}

	private static String getNumber(Scanner sc, String message) {
		System.out.println(message);
		String input = sc.nextLine();

		return input;
	}
	
	public static int getValidNumber(Scanner sc, String message) {
		boolean numOfPlayersEntered = false;
		String input = "";
		while (!numOfPlayersEntered) {
			input = getNumber(sc, message);
			numOfPlayersEntered = isNumber(input);
		}
		return Integer.parseInt(input);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Welcome to FOCUS game");
		
		Scanner sc = new Scanner(System.in);
		int numOfPlayers = getValidNumber(sc, "How many players do you want in the game: ");
		
		Game game = new Game(numOfPlayers);
		game.play(sc);
	}

}
