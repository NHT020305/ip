package Peter.command.commands;

import Peter.command.Command;
import Peter.storage.TaskStorage;
import Peter.task.TaskManager;
import Peter.ui.Ui;

public class InstructionCommand extends Command {

    public void execute(Ui ui, TaskManager taskManager, TaskStorage taskStorage) {
        ui.instruction();
    }

    public boolean isTerminal() {
        return false;
    }
}
