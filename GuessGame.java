import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Class implementing Guess Game playing process
 */
public class GuessGame {
    /**
     * Number of best results to remember
     */
    private static final int BEST_TOP_NUM = 10;

    /**
     * Text file to save best results to
     */
    private static final String BEST_RESULTS_FILE = "best.txt";

    /**
     * Instance of guess checker to return result of comparison
     */
    private final GuessChecker<Integer> guessChecker;

    /**
     * List, containing best the lowest number of moves to find solution among all players
     */
    private List<Integer> bestResults;

    /**
     * Constructor. Creates instance of game with integer guess checker
     */
    public GuessGame() {
        guessChecker = new IntegerGuessChecker();
    }

    /**
     * Method, which holds all playing process of Guess game
     */
    public void play() {
        // loading and showing best results so far
        loadBestResults();
        showBestResults();


        Scanner scanner = new Scanner(System.in);
        GuessChecker.GuessResult curr = null;
        int count = 0;
        // keep playing until correct value is guessed. Counting number of guesses in count variable
        while (curr != GuessChecker.GuessResult.CORRECT) {
            try {
                // reading user's guess
                int guess = readGuess(scanner);
                // getting comparison result. Here we call overridden method
                curr = guessChecker.makeGuess(guess);
                count++;
                // showing appropriate message
                if (curr == GuessChecker.GuessResult.CORRECT) {
                    System.out.println("CORRECT! The secret value was: " + guess);
                }
                else if (curr == GuessChecker.GuessResult.LESS) {
                    System.out.println("Your guess is LESS, than secret value");
                }
                else {
                    System.out.println("Your guess is GREATER, than secret value");
                }

            } catch (IncorrectInputException e) {
                // if incorrect input was during reading, showing error message.
                // Here we call overridden method
                System.out.println(e.getMessage());
            }
        }
        scanner.close();

        // showing number of steps, required to guess the secret
        System.out.println("Your result is: " + count);
        System.out.println();

        // updating and saving best results
        bestResults.add(count);
        saveBestResults();
    }

    /**
     * Helper method for reading user's integer guess
     * @param scanner to read input with
     * @return read integer value
     * @throws IncorrectInputException, if one can not parse integer from input
     */
    private int readGuess(Scanner scanner) throws IncorrectInputException {
        try {
            System.out.print("Please, enter your guess: ");
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new IncorrectInputException(e);
        }
    }

    /**
     * Helper method for loading best results from best results file
     */
    private void loadBestResults() {
        bestResults = new ArrayList<>();
        // just reading all lines, and put their content into the list
        try (Scanner scanner = new Scanner(new File(BEST_RESULTS_FILE))) {
            while (scanner.hasNextLine()) {
                bestResults.add(Integer.parseInt(scanner.nextLine()));
            }
        } catch (Exception ignored) {
            // if any error on reading occured, returning empty best result list
        }
    }

    /**
     * Helper method for showing best results so far
     */
    private void showBestResults() {
        System.out.println("Here are the TOP results: ");
        for (int i = 0; i < Math.min(BEST_TOP_NUM, bestResults.size()); i++) {
            System.out.println(String.format("%2d. ", i + 1) + bestResults.get(i));
        }
        System.out.println();
    }

    /**
     * Helper method for saving current best results to file
     */
    private void saveBestResults() {
        try (PrintWriter printWriter = new PrintWriter(BEST_RESULTS_FILE)) {
            Collections.sort(bestResults);
            for (int i = 0; i < Math.min(BEST_TOP_NUM, bestResults.size()); i++) {
                printWriter.println(bestResults.get(i));
            }
        } catch (Exception e) {
            System.out.println("Cannot save best results to file: " + BEST_RESULTS_FILE);
        }
    }

    /**
     * Main driver method of the application.
     * @param args command line arguments (no args required)
     */
    public static void main(String[] args) {
        GuessGame guessGame = new GuessGame();
        guessGame.play();
    }

}
