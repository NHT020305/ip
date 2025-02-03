package Peter.command;

import Peter.task.TaskManager;
import Peter.ui.Ui;
import Peter.storage.TaskStorage;

public abstract class Command {

    protected Command() {
    }

    public abstract void execute(Ui ui, TaskManager taskManager,
                                 TaskStorage taskStorage);

    public abstract boolean isTerminal();
}
