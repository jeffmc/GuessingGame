package mcmillan.jeff.guessing;

// I think this is called a minimax algorithm, I can't really remember though.
public class Autoplay {
	public int gameMin, gameMax;
	public int iterations;
	
	public Autoplay(int _min, int _max, int _iters) {
		if (_min < 0 || _min >= _max || _max-_min < 2) {
			System.err.println("Invalid game min/max: " + _min + " - " + _max);
		}
		gameMin = _min;
		gameMax = _max;
		iterations = _iters;
	}
	
	public float play() {
		long totalGuesses = 0;
		for (int i=0;i<iterations;i++) {
			// setup round
			int guesses = 0;
			boolean correct = false;
			int target = (int) Math.round(Math.random() * 50);
			System.out.println("Range: " + gameMin + "-" + gameMax + ", Target: " + target);
			
			// only variable accessible to both algorithm and game.
			boolean tooLow = false;
			
			// setup algorithm
			int min = gameMin;
			int max = gameMax;
			
			int lastGuess = -1;
			
			// start round loop
			while (!correct) {
				// formulate guess
				int guess = min + ((int)Math.floor((max - min) / 2));
				if (guess == lastGuess) guess++;
				lastGuess = guess;
				System.out.println("("+ guesses +") [" + min +"-"+ max + "] Guess: " + guess );
				
				// get response
				guesses++;
				if (guess == target) {
					correct = true;
					System.out.println("Finished in " + guesses + " " + (guesses > 1 ? "guesses" : "guess") + ".");
					break;
				} else if (guess < target) {
					tooLow = true;
				} else if (guess > target) {
					tooLow = false;
				}
				
				// adjust algorithm based on results
				if (tooLow) {
					min = guess;
				} else {
					max = guess;
				}
			}
			totalGuesses += guesses;
		}
		System.out.println("\n Statistics:");
		System.out.println(totalGuesses + " total guesses");
		System.out.println(iterations + " iterations");
		
		return ((float) totalGuesses ) / ((float) iterations);
	}
}
