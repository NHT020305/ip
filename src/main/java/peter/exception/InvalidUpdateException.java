package peter.exception;

/**
 * Exception thrown when an update is not appropriate.
 */
public class InvalidUpdateException extends Exception {

    /**
     * Constructs an InvalidTaskFormatException with the specified detail message.
     *
     * @param message The detail message describing the exception.
     */
    public InvalidUpdateException(String message) {
        super(message);
    }
}
