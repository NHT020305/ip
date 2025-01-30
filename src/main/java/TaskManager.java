public class TaskManager {

    protected String input;

    TaskManager(String input) {
        this.input = input;
    }

    Task getTask() throws EmptyTaskException, InvalidTaskFormatException {
        Task newTask = null;
        if (input.startsWith("todo")) {
            if (input.trim().length() == "todo".length()) {
                throw new EmptyTaskException("OOPS!!! The description of a todo cannot be empty.");
            }
            String description = input.split(" ")[1];
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
