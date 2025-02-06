package Peter.command.commands;

import Peter.command.Command;
import Peter.storage.TaskStorage;
import Peter.task.TaskManager;
import Peter.ui.Ui;

public class CountCommand extends Command {

    public void execute(Ui ui, TaskManager taskManager, TaskStorage taskStorage) {
        if (taskManager.countTasks() == 0) {
            System.out.println(" There are no tasks in this list.");
            System.out.println(" Let's create a new task!!!");
        }
        String isMany = taskManager.countTasks() > 1 ? "s" : "";
        System.out.println(" You have " + taskManager.countTasks()
                + " task" + isMany + " in the list.");
        taskStorage.saveTasks(taskManager);
    }

    public boolean isTerminal() {
        return false;
    }
}
