package Peter.command.command;

import Peter.command.Command;
import Peter.task.TaskManager;
import Peter.ui.Ui;

public class ResetCommand extends Command {

    public void execute(Ui ui, TaskManager taskManager) {
        taskManager.reset();
        System.out.println(" Got it. Now your task list is empty.");
        System.out.println(" Let's create a new task!!!");
    }

    public boolean isTerminal() {
        return false;
    }
}
