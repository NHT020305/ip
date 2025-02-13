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
    private static final String NULL_CLASS_NAME = "Unknown";
    private final TaskManager taskManager;
    private final TaskStorage taskStorage;
    private final Ui ui;
    private String commandType;

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
            throw new RuntimeException(e);
        }
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            String fullCommand = ui.readCommand(input);
            Command command = new CommandParser()
                    .makeSenseUserCommand(fullCommand);
            commandType = command.getClass().getSimpleName();
            return command.execute(ui, taskManager, taskStorage);
        } catch (Exception e) {
            commandType = NULL_CLASS_NAME;
            return ui.showError(e.getMessage());
        }
    }

    public String getCommandType() {
        return commandType;
    }

}
