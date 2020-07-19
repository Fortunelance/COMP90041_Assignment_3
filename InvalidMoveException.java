/**
 * Extends the Exception class for a specific kind of exception
 * @author Zhejun Lyu 1128727
 */
public class InvalidMoveException extends Exception {

    public InvalidMoveException() {
        super("InvalidMoveException");
    }

    public InvalidMoveException (String message) {
        super(message);
    }

}
