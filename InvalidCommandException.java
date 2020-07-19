/**
 * Extends the Exception class for a specific kind of exception
 * @author Zhejun Lyu 1128727
 */
public class InvalidCommandException extends Exception {

    private String invalidCommand;

    public InvalidCommandException (String message, String invalidCommand) {
        this(message);
        this.invalidCommand = invalidCommand;
    }

    public InvalidCommandException () {
        super ("InvalidCommandException");
    }

    public InvalidCommandException (String message) {
        super(message);
    }

    public String getInvalidCommand () {
        return "'"+invalidCommand+"'";
    }

}
