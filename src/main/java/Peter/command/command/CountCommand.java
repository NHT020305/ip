package Peter.command.command;

import Peter.command.Command;
import Peter.task.TaskManager;
import Peter.ui.Ui;

public class CountCommand extends Command {

    public void execute(Ui ui, TaskManager taskManager) {
        System.out.println(" You have " + taskManager.size() + " tasks in the list.");
    }

    public boolean isTerminal() {
        return false;
    }
}
