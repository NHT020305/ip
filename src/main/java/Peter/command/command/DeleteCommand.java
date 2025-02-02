package Peter.command.command;

import Peter.command.Command;
import Peter.task.Task;
import Peter.task.TaskManager;
import Peter.ui.Ui;

public class DeleteCommand extends Command {

    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    public void execute(Ui ui, TaskManager taskManager) {
        Task task = taskManager.delete(index);
        System.out.println(" Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println(" Now you have " + taskManager.size() + " tasks in the list.");
    }

    public boolean isTerminal() {
        return false;
    }
}
