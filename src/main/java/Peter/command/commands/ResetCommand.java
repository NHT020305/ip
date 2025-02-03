package Peter.command.commands;

import Peter.command.Command;
import Peter.storage.TaskStorage;
import Peter.task.TaskManager;
import Peter.ui.Ui;

public class ResetCommand extends Command {

    public void execute(Ui ui, TaskManager taskManager, TaskStorage taskStorage) {
        taskManager.reset();
        System.out.println(" Got it. Now your task list is empty.");
        System.out.println(" Let's create a new task!!!");
        taskStorage.saveTasks(taskManager);
    }

    public boolean isTerminal() {
        return false;
    }
}
