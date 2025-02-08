package peter.exception;

/**
 * Exception thrown when a task input has an invalid format.
 */
public class InvalidTaskFormatException extends Exception {

    /**
     * Constructs an InvalidTaskFormatException with the specified detail message.
     *
     * @param message The detail message describing the exception.
     */
    public InvalidTaskFormatException(String message) {
        super(message);
    }
}
