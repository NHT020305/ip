package Peter.exception;

/**
 * Exception thrown when a task input is empty.
 */
public class EmptyTaskException extends Exception {

    /**
     * Constructs an EmptyTaskException with the specified detail message.
     *
     * @param message The detail message describing the exception.
     */
    public EmptyTaskException(String message) {
        super(message);
    }
}
