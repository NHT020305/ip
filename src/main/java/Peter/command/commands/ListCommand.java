package Peter.command.commands;

import Peter.command.Command;
import Peter.storage.TaskStorage;
import Peter.task.TaskManager;
import Peter.ui.Ui;

public class ListCommand extends Command {

    public void execute(Ui ui, TaskManager taskManager, TaskStorage taskStorage) {
        if (taskManager.size() == 0) {
            System.out.println(" There are no tasks in this list.");
            System.out.println(" Let's create a new task!!!");
        } else {
            System.out.println(" Here are the tasks in your list:");
            taskManager.list();
        }
    }

    public boolean isTerminal() {
        return false;
    }
}
