package peter.ui;

/**
 * Handles user interaction, including displaying messages and reading input.
 */
public class Ui {

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
