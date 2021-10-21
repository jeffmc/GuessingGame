package mcmillan.jeff.guessing;

import java.util.Scanner;

public class Main {

	public static Scanner scanner;
	
	public static final int GAME_MIN = 0;
	public static final int GAME_MAX = 50;
	
	public static int target;
	public static int guesses;
	public static boolean correct;
	
	public static boolean running;
		
	public static void main(String[] args) {
		
//		System.out.println(new Autoplay(GAME_MIN,GAME_MAX,10000).play());
//		System.exit(0);
		
		// Initialization
		scanner = new Scanner(System.in);
		running = true;
		
		// Game loop
		while (running) {
			
			// Start game
			target = (int) Math.round(Math.random() * 50);
//			System.out.println(target); // for TESTING ONLY
			guesses = 0;
			correct = false;
			
			// Game loop
			while (!correct) {
				promptAndHandleGuess();
			}
			System.out.println("It took you " + guesses + " " + ( guesses > 1 ? "tries" : "try" ) + " to guess correctly.");
			
			// Play again?
			System.out.print("Would you like to play again (Y/N)? ");
			if(readYN()) {
				running = true;
				System.out.println("Let's go again!\n");
			} else {
				running = false;
				System.out.println("Thanks for playing!");
			}
		}
		
		// Clean up
		scanner.close();
		System.exit(0);
	}
	
	// Prompts the user for a guess and handles it, continuing or ending current game.
	public static void promptAndHandleGuess() {
		// Prompt + guess tracking
		System.out.print("Give your best guess " + GAME_MIN + "-" + GAME_MAX + ": ");
		int guess = readInt();
		guesses++;
		
		// Attempt handling
		if (guess == target) {
			System.out.println("Congratulations, " + guess + " was the target!");
			correct = true; // Causes the round loop to end
		} else if (guess < target) {
			System.out.println("Too low...");
		} else if (guess > target) {
			System.out.println("Too high...");
		} else {
			System.err.println("Uh oh, something is very wrong."); // Should never happen.
		}
	}

	// Returns a valid integer, will retry on invalid input
	public static int readInt() {
		while (true) {
			try {
				return Integer.parseInt(scanner.nextLine());
			} catch (Exception e) {
				System.out.print("Not a whole number, try again: ");
			}
		}
	}
	
	// Y is true, N is false
	public static boolean readYN() {
		String r = "";
		char c;
		while (true) {
			try {
				r = scanner.nextLine().toLowerCase();
				
				if (r.length() == 1) {
					c = r.charAt(0);
					if (c == 'y') {
						return true;
					} else if (c == 'n') {
						return false;
					}
				};
				throw new Exception("Not 'Y' or 'N' or valid string.");
			} catch (Exception e) {
				System.out.print("Not the character 'Y' or 'N', try again: ");
			}
		}
		
	}
}
