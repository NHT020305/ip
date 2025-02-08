package peter;

import peter.command.Command;
import peter.command.CommandParser;
import peter.storage.TaskStorage;
import peter.task.TaskManager;
import peter.ui.Ui;

/**
 * Main entry point for the Peter application, a task management system.
 */
public class Peter {

    private TaskManager taskManager;
    private final TaskStorage taskStorage;
    private final Ui ui;

    /**
     * Constructs a new instance of Peter.
     * Initializes the user interface, task storage, and task manager.
     *
     * @param filePath The file path where task data is stored.
     */
    public Peter(String filePath) {
        ui = new Ui();
        taskStorage = new TaskStorage(filePath);
        taskStorage.createDataFile();
        try {
            taskManager = new TaskManager(taskStorage.loadTasks());
        } catch (Exception e) {
            ui.showError(e.getMessage());
            taskManager = new TaskManager();
        }
    }

    /**
     * Starts and runs the task management system.
     * Displays a welcome message, processes user commands, and handles errors.
     */
    @SuppressWarnings("checkstyle:WhitespaceAround")
    public void run() {
        ui.showLine();
        ui.space();
        ui.welcome();
        ui.space();
        ui.showLine();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.space();
                Command command = new CommandParser()
                        .makeSenseUserCommand(fullCommand);
                command.execute(ui, taskManager, taskStorage);
                isExit = command.isTerminal();
            } catch (Exception e) {
                ui.showError(e.getMessage());
            } finally {
                ui.space();
                ui.showLine();
            }
        }
    }

    /**
     * Main method to launch the Peter application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Peter("./data/Peter.txt").run();
    }
}
