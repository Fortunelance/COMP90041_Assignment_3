public class InvalidMoveException extends Exception {

    public InvalidMoveException() {
        super("InvalidArgumentsNumberException");
    }

    public InvalidMoveException (String message) {
        super(message);
    }

}
