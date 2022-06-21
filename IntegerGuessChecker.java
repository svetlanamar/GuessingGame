import java.util.Random;

public class IntegerGuessChecker implements GuessChecker<Integer> {
    /**
     * Game secret value to make comparisons with
     */
    private final int correct;

    /**
     * Constructor, which creates checker with random secret value
     */
    public IntegerGuessChecker() {
        this(new Random().nextInt());
    }

    /**
     * Constructor, which creates checker with given secret value
     * @param correct for setting as a secret value
     */
    public IntegerGuessChecker(int correct) {
        this.correct = correct;
    }

    /**
     * Overridden method, which makes comparison of given guess value with secret value
     * @param guess to check
     * @return GuessResult instance
     */
    @Override
    public GuessResult makeGuess(Integer guess) {
        if (guess < correct) {
            return GuessResult.LESS;
        } else if (guess > correct) {
            return GuessResult.GREATER;
        }
        return GuessResult.CORRECT;
    }
}
