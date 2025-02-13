package peter.exception;

/**
 * Exception thrown when the date & time part of a task input has an invalid format.
 */
public class InvalidDateTimeFormatException extends Exception {

    /**
     * Constructs an InvalidDateTimeFormatException with the specified detail message.
     *
     * @param message The detail message describing the exception.
     */
    public InvalidDateTimeFormatException(String message) {
        super(message);
    }
}
