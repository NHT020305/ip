package Peter.ui;

import java.util.Scanner;

/**
 * Handles user interaction, including displaying messages and reading input.
 */
public class Ui {

    /**
     * Displays a welcome message to the user.
     */
    public void welcome() {
        System.out.println(" Hello! I'm Peter");
        System.out.println(" Here are the tasks in your list:");
    }

    /**
     * Displays a goodbye message to the user.
     */
    public void goodbye() {
        System.out.println(" Bye. Hope to see you again soon!");
    }

    /**
     * Displays a horizontal line to separate sections in the console output.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays an error message.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Reads and returns a command from the user.
     *
     * @return The command input by the user, converted to lowercase and trimmed.
     */
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().trim().toLowerCase();
    }
}