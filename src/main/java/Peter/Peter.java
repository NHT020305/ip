package Peter;

import Peter.command.Command;
import Peter.command.CommandParser;
import Peter.task.TaskManager;
import Peter.storage.TaskStorage;
import Peter.ui.Ui;


public class Peter {

    private TaskManager taskManager;
    private final TaskStorage taskStorage;
    private final Ui ui;

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

    public static void main(String[] args) {
        new Peter("./data/Peter.txt").run();
    }
}
