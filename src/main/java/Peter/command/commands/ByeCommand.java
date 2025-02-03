package Peter.command.commands;

import Peter.command.Command;
import Peter.storage.TaskStorage;
import Peter.task.TaskManager;
import Peter.ui.Ui;

public class ByeCommand extends Command {

    public void execute(Ui ui, TaskManager taskManager, TaskStorage taskStorage) {
        ui.goodbye();
    }

    public boolean isTerminal() {
        return true;
    }
}
