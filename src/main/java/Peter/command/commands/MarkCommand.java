package Peter.command.commands;

import Peter.command.Command;
import Peter.storage.TaskStorage;
import Peter.task.TaskManager;
import Peter.ui.Ui;

public class MarkCommand extends Command {

    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    public void execute(Ui ui, TaskManager taskManager, TaskStorage taskStorage) {
        taskManager.markAsDone(index);
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + taskManager.getTask(index));
        taskStorage.saveTasks(taskManager);
    }

    public boolean isTerminal() {
        return false;
    }
}
