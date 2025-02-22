package peter.utils;

/**
 * Handle different kinds of error messages;
 */
public class ErrorMessage {

    public static final String EMPTY_TASK = "OOPS!!! The description of %s cannot be empty.";
    public static final String INVALID_TASK_FORMAT = "OOPS!!! Invalid %s format.";
    public static final String INVALID_TASK_NUMBER = "OOPS!!! %d is not a valid task number.";
    public static final String ALREADY_EXISTS = "OOPS!!! This task already exists in your list.";
    public static final String MEANINGLESS_COMMAND = "OOPS!!! I'm sorry, but I don't know what that means :-(";
    public static final String INVALID_DATE_TIME = "OOPS!!! Invalid date & time format.";
    public static final String NOT_DEADLINE = "OOPS!!! %d is not a deadline";
    public static final String NOT_EVENT = "OOPS!!! %d is not an event";
}
