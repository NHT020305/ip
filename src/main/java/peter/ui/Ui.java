package peter.ui;

/**
 * Handles user interaction, including displaying messages and reading input.
 */
public class Ui {
    /**
     * Displays a welcome message to the user.
     */
    public String welcome() {
        return "Welcome to PETER chatbot!\n"
                + "Type \"instruction\" to know how to use";
    }

    /**
     * Displays the instruction of the chatbot to the user.
     */
    public String showInstruction() {
        return """
                Here are the instructions for using Peter chatbot:
                    + "list": list all the tasks in your list.
                    + "delete <i>": delete the ith task in your list.
                    + "reset": delete all the tasks in your list.
                    + "count": show the number of tasks in your list.
                    + "mark <i>": mark the ith task in your list as done.
                    + "unmark <i>": mark the ith task in your list as not done.
                    + "find <keyword>": list all the tasks matching keyword.
                    + "bye": exit Peter chatbot.
                    + new Todo: "todo <name>".
                    + new Deadline: "deadline <name> /by <time>".
                    + new event: "event <name> /from <time> /to <time>".
                    + time format: "dd/MM/yyyy HH:mm".""";
    }

    /**
     * Displays a goodbye message to the user.
     */
    public String goodbye() {
        return "Bye. PETER chatbot hopes to see you again soon!";
    }

    /**
     * Displays an error message.
     *
     * @param message The error message to display.
     */
    public String showError(String message) {
        return (message);
    }

    /**
     * Reads and returns a command from the user.
     *
     * @return The command input by the user, converted to lowercase and trimmed.
     */
    public String readCommand(String input) {
        return input.trim().toLowerCase();
    }
}
