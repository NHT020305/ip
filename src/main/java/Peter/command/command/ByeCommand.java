package Peter.command.command;

import Peter.command.Command;
import Peter.task.TaskManager;
import Peter.ui.Ui;

public class ByeCommand extends Command {

    public void execute(Ui ui, TaskManager taskManager) {
        ui.goodbye();
    }

    public boolean isTerminal() {
        return true;
    }
}
