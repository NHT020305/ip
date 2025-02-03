package Peter.command.commands;

import Peter.command.Command;
import Peter.storage.TaskStorage;
import Peter.task.TaskManager;
import Peter.ui.Ui;

public class CountCommand extends Command {

    public void execute(Ui ui, TaskManager taskManager, TaskStorage taskStorage) {
        System.out.println(" You have " + taskManager.size() + " tasks in the list.");
        taskStorage.saveTasks(taskManager);
    }

    public boolean isTerminal() {
        return false;
    }
}
