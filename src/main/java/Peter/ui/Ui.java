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
        System.out.println(" Type \"instruction\" to know how to use");
    }

    /**
     * Displays the instruction of the chatbot to the user.
     */
    public void instruction() {
        System.out.println(" Here are the instructions for using Peter chatbot:");
        System.out.println("    \"list\": list all the tasks in your list.");
        System.out.println("    \"delete <i>\": delete the ith task in your list.");
        System.out.println("    \"reset\": delete all the tasks in your list.");
        System.out.println("    \"count\": show the number of tasks in your list.");
        System.out.println("    \"mark <i>\": mark the ith task in your list as done.");
        System.out.println("    \"unmark <i>\": mark the ith task in your list as not done.");
        System.out.println("    \"bye\": exit the program.");
        System.out.println("    Create a Todo: \"todo <task name>\".");
        System.out.println("    Create a Deadline: \"deadline <task name> " +
                                                    "/by <date & time>\".");
        System.out.println("    Create an event: \"event <task name> " +
                                    "/from <date & time> /to <date & time>\".");
        System.out.println("    Date & time format: \"dd/MM/yyyy HH:mm\".");
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
        System.out.println(" " + message);
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