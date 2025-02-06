package Peter.exception;

/**
 * Exception thrown when the new added task is already exists in the list.
 */
public class RepeatedTaskException extends Exception {

    /**
     * Constructs an RepeatedTaskException with the specified detail message.
     *
     * @param message The detail message describing the exception.
     */
    public RepeatedTaskException(String message) {
        super(message);
    }
}
