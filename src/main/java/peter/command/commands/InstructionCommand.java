package peter.command.commands;

import peter.command.Command;
import peter.storage.TaskStorage;
import peter.task.TaskManager;
import peter.ui.Ui;

/**
 * Represents a command to display instructions.
 */
public class InstructionCommand extends Command {

    /**
     * Executes the instruction command, showing usage instructions.
     *
     * @param ui          The user interface to display messages.
     * @param taskManager The manager handling tasks.
     * @param taskStorage The storage to save tasks.
     */
    public void execute(Ui ui, TaskManager taskManager, TaskStorage taskStorage) {
        ui.instruction();
    }

    /**
     * Indicates whether this command is terminal, i.e., should terminate the program.
     *
     * @return {@code false}, since this command does not terminate the program.
     */
    public boolean isTerminal() {
        return false;
    }
}
