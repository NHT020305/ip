package peter.storage;

import peter.datetime.LocalDateTimeParser;
import peter.exception.EmptyTaskException;
import peter.exception.InvalidDateTimeFormatException;
import peter.exception.InvalidTaskFormatException;
import peter.task.Task;
import peter.task.type.Deadline;
import peter.task.type.Event;
import peter.task.type.ToDo;
import peter.utils.DateTime;
import peter.utils.ErrorMessage;
import peter.utils.TaskKeyword;

/**
 * Generates task objects based on user input.
 */
public class TaskGenerator {

    private static final String UNKNOWN_TASK_TYPE_MESSAGE = "Unknown task type.";
    /**
     * Parses the input string and generates the corresponding Task object.
     *
     * @param input The input string describing the task.
     * @return The generated Task object.
     * @throws EmptyTaskException         If the task description is empty.
     * @throws InvalidTaskFormatException If the task input format is invalid.
     */
    public Task getTask(String input) throws EmptyTaskException,
            InvalidTaskFormatException, InvalidDateTimeFormatException {
        if (input.startsWith(TaskKeyword.TODO)) {
            return processToDo(input);
        } else if (input.startsWith(TaskKeyword.DEADLINE)) {
            return processDeadline(input);
        } else if (input.startsWith(TaskKeyword.EVENT)) {
            return processEvent(input);
        } else {
            throw new InvalidTaskFormatException(UNKNOWN_TASK_TYPE_MESSAGE);
        }
    }

    private Task processToDo(String input) throws EmptyTaskException {
        if (input.trim().length() == TaskKeyword.TODO.length()) {
            throw new EmptyTaskException(String.format(ErrorMessage.EMPTY_TASK, TaskKeyword.TODO));
        }
        String description = input.split(" ", 2)[1];
        return new ToDo(description);
    }

    private Task processDeadline(String input) throws EmptyTaskException,
            InvalidTaskFormatException, InvalidDateTimeFormatException {
        if (input.trim().length() == TaskKeyword.DEADLINE.length()) {
            throw new EmptyTaskException(String.format(ErrorMessage.EMPTY_TASK, TaskKeyword.DEADLINE));
        }
        String[] deadlineParts = input.substring(TaskKeyword.DEADLINE.length() + 1)
                .trim().split(DateTime.BY_COMMAND);
        if (deadlineParts.length != 2) {
            throw new InvalidTaskFormatException(String.format(ErrorMessage.INVALID_TASK_FORMAT, TaskKeyword.DEADLINE));
        }
        String description = deadlineParts[0].trim();
        if (description.isEmpty()) {
            throw new EmptyTaskException(String.format(ErrorMessage.EMPTY_TASK, TaskKeyword.DEADLINE));
        }
        String by = deadlineParts[1].trim();
        return new Deadline(description, new LocalDateTimeParser(by).convertToTime());
    }

    private Task processEvent(String input) throws EmptyTaskException,
            InvalidTaskFormatException, InvalidDateTimeFormatException {
        if (input.trim().length() == TaskKeyword.EVENT.length()) {
            throw new EmptyTaskException(String.format(ErrorMessage.EMPTY_TASK, TaskKeyword.EVENT));
        }
        String[] eventParts = input.substring(TaskKeyword.EVENT.length() + 1)
                .split(DateTime.FROM_COMMAND);
        if (eventParts.length != 2) {
            throw new InvalidTaskFormatException(String.format(ErrorMessage.INVALID_TASK_FORMAT, TaskKeyword.EVENT));
        }
        String description = eventParts[0].trim();
        if (description.isEmpty()) {
            throw new EmptyTaskException(String.format(ErrorMessage.EMPTY_TASK, TaskKeyword.EVENT));
        }
        String[] date = eventParts[1].split(DateTime.TO_COMMAND);
        if (date.length != 2) {
            throw new InvalidTaskFormatException(String.format(ErrorMessage.INVALID_TASK_FORMAT, TaskKeyword.EVENT));
        }
        String from = date[0].trim();
        if (from.isEmpty()) {
            throw new InvalidTaskFormatException(String.format(ErrorMessage.INVALID_TASK_FORMAT, TaskKeyword.EVENT));
        }
        String to = date[1].trim();
        return new Event(description, new LocalDateTimeParser(from).convertToTime(),
                new LocalDateTimeParser(to).convertToTime());
    }
}
