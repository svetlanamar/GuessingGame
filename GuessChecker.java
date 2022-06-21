/**
 * Interface, providing method declaration for checking user's guesses
 * @param <T>
 */
public interface GuessChecker<T> {
    /**
     * Returns appropriate GuessResult, for given user guess
     * @param guess to check
     * @return GuessResult instance
     */
    GuessResult makeGuess(T guess);

    /**
     * Enum, containing possible results of comparison
     */
    enum GuessResult {
        /**
         * The guess is greater, than secret value
         */
        GREATER,

        /**
         * The guess is less, than secret value
         */
        LESS,

        /**
         * The guess is correct
         */
        CORRECT;
    }
}
