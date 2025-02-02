package Peter.command.command;

import Peter.command.Command;
import Peter.task.TaskManager;
import Peter.ui.Ui;

public class ListCommand extends Command {

    public void execute(Ui ui, TaskManager taskManager) {
        System.out.println(" Here are the tasks in your list:");
        taskManager.list();
    }

    public boolean isTerminal() {
        return false;
    }
}
