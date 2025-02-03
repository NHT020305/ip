package Peter.exception;

/**
 * Exception thrown when an unrecognized or unsupported command is entered by the user.
 */
public class MeaninglessCommandException extends Exception {

    /**
     * Constructs a MeaninglessCommandException with the specified detail message.
     *
     * @param message The detail message describing the exception.
     */
    public MeaninglessCommandException(String message) {
        super(message);
    }
}
