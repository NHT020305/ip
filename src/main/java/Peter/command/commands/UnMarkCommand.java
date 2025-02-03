package Peter.command.commands;

import Peter.command.Command;
import Peter.storage.TaskStorage;
import Peter.task.TaskManager;
import Peter.ui.Ui;

public class UnMarkCommand extends Command {

    private final int index;

    public UnMarkCommand(int index) {
        this.index = index;
    }

    public void execute(Ui ui, TaskManager taskManager, TaskStorage taskStorage) {
        taskManager.markAsNotDone(index);
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("  " + taskManager.getTask(index));
        taskStorage.saveTasks(taskManager);
    }

    public boolean isTerminal() {
        return false;
    }
}
