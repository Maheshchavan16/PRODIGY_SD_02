import java.util.Random;

public class GuessingGame {
    private int numberToGuess;
    private int numberOfAttempts;
    private boolean isGameOver;

    public GuessingGame(int maxNumber) {
        Random random = new Random();
        this.numberToGuess = random.nextInt(maxNumber) + 1;
        this.numberOfAttempts = 0;
        this.isGameOver = false;
    }

    public String makeGuess(int guess) {
        numberOfAttempts++;
        if (guess < numberToGuess) {
            return "Too low!";
        } else if (guess > numberToGuess) {
            return "Too high!";
        } else {
            isGameOver = true;
            return "Correct! You guessed the number in " + numberOfAttempts + " attempts.";
        }
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public int getNumberOfAttempts() {
        return numberOfAttempts;
    }
}
