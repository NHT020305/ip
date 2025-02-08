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

    private static final String DEFAULT_FILE_PATH = "./data/Peter.txt";
    private TaskManager taskManager;
    private final TaskStorage taskStorage;
    private final Ui ui;

    /**
     * Constructs a new instance of Peter.
     * Initializes the user interface, task storage, and task manager.
     */
    public Peter() {
        ui = new Ui();
        taskStorage = new TaskStorage(DEFAULT_FILE_PATH);
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
     * Displays a welcome message
     */
    public String getGreeting() {
        return ui.welcome();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            String fullCommand = ui.readCommand(input);
            Command command = new CommandParser()
                    .makeSenseUserCommand(fullCommand);
            return command.execute(ui, taskManager, taskStorage);
        } catch (Exception e) {
            return ui.showError(e.getMessage());
        }
    }

}
