package Peter.command;

import Peter.task.TaskManager;
import Peter.ui.Ui;

public abstract class Command {

    protected Command() {
    }

    public abstract void execute(Ui ui, TaskManager taskManager);

    public abstract boolean isTerminal();
}
