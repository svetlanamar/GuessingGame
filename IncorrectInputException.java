/**
 * Custom exception class, which is thrown on wrong game input
 */
public class IncorrectInputException extends Exception {
    /**
     * Constructor, which creates exception based on given cause
     * @param e cause exception
     */
    public IncorrectInputException(Exception e) {
        super(e);
    }

    /**
     * Overridden method, which returns appropriate message
     * @return message string
     */
    @Override
    public String getMessage() {
        return "Invalid input. Please, try again";
    }
}
