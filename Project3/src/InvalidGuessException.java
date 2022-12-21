/**
 * InvalidGuessException
 * <p>
 * Creates exception if user inputs malformed guess input
 *
 * @author Gaurav Singh, 29939
 * @version October 20 ,2022
 */
public class InvalidGuessException extends Exception {
    public InvalidGuessException(String message) {
        super(message);
    }
}
