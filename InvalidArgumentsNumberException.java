/**
 * Extends the Exception class for a specific kind of exception
 * @author Zhejun Lyu 1128727
 */
public class InvalidArgumentsNumberException extends Exception {

    public InvalidArgumentsNumberException() {
        super("Incorrect number of arguments supplied to command.\n\n$");
    }

    public InvalidArgumentsNumberException (String message) {
        super(message);
    }

}
