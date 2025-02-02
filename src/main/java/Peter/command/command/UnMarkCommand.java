package Peter.command.command;

import Peter.command.Command;
import Peter.task.TaskManager;
import Peter.ui.Ui;

public class UnMarkCommand extends Command {

    private final int index;

    public UnMarkCommand(int index) {
        this.index = index;
    }

    public void execute(Ui ui, TaskManager taskManager) {
        taskManager.markAsNotDone(index);
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("  " + taskManager.getTask(index));
    }

    public boolean isTerminal() {
        return false;
    }
}
