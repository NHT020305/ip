package Peter.storage;

import Peter.dateTime.LocalDateTimeParser;
import Peter.exception.EmptyTaskException;
import Peter.exception.InvalidTaskFormatException;
import Peter.task.Task;
import Peter.task.type.Deadline;
import Peter.task.type.Event;
import Peter.task.type.ToDo;

/**
 * Generates task objects based on user input.
 */
public class TaskGenerator {

    /**
     * Constructs a TaskGenerator.
     */
    public TaskGenerator() {
    }

    /**
     * Parses the input string and generates the corresponding Task object.
     *
     * @param input The input string describing the task.
     * @return The generated Task object.
     * @throws EmptyTaskException If the task description is empty.
     * @throws InvalidTaskFormatException If the task input format is invalid.
     */
    public Task getTask(String input) throws EmptyTaskException, InvalidTaskFormatException {
        Task newTask = null;
        if (input.startsWith("todo")) {
            if (input.trim().length() == "todo".length()) {
                throw new EmptyTaskException("OOPS!!! The description of a todo cannot be empty.");
            }
            String description = input.split(" ", 2)[1];
            newTask = new ToDo(description);
        } else if (input.startsWith("deadline")) {
            if (input.trim().length() == "deadline".length()) {
                throw new EmptyTaskException("OOPS!!! The description of a deadline cannot be empty.");
            }
            String task = input.substring("deadline ".length()).trim();
            String[] parts = task.split("/by");
            if (parts.length != 2) {
                throw new InvalidTaskFormatException("OOPS!!! Invalid deadline format.");
            }
            String description = parts[0].trim();
            String by = parts[1].trim();
            newTask = new Deadline(description, new LocalDateTimeParser(by).convertToTime());
        } else if (input.startsWith("event")) {
            if (input.trim().length() == "event".length()) {
                throw new EmptyTaskException("OOPS!!! The description of an event cannot be empty.");
            }
            String task = input.substring("event ".length());
            String[] parts = task.split("/from");
            if (parts.length != 2) {
                throw new InvalidTaskFormatException("OOPS!!! Invalid event format.");
            }
            String description = parts[0].trim();
            String[] date = parts[1].split("/to");
            if (date.length != 2) {
                throw new InvalidTaskFormatException("OOPS!!! Invalid event format.");
            }
            String from = date[0].trim();
            if (from.isEmpty()) {
                throw new InvalidTaskFormatException("OOPS!!! Invalid event format.");
            }
            String to = date[1].trim();
            newTask = new Event(description, new LocalDateTimeParser(from).convertToTime(),
                        new LocalDateTimeParser(to).convertToTime());
        }
        return newTask;
    }
}
