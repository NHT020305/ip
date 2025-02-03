package Peter;

import Peter.command.Command;
import Peter.command.CommandParser;
import Peter.task.TaskManager;
import Peter.storage.TaskStorage;
import Peter.ui.Ui;

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
    public void run() {
        ui.showLine();
        ui.welcome();
        taskManager.list();
        ui.showLine();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();

                Command command = new CommandParser()
                                        .makeSenseUserCommand(fullCommand);
                command.execute(ui, taskManager, taskStorage);
                isExit = command.isTerminal();
            } catch(Exception e) {
                ui.showError(e.getMessage());
            } finally {
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
