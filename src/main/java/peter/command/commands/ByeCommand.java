package peter.command.commands;

import peter.command.Command;
import peter.storage.TaskStorage;
import peter.task.TaskManager;
import peter.ui.Ui;
import peter.utils.ReplyMessage;

/**
 * Represents a command to exit the application.
 */
public class ByeCommand extends Command {

    /**
     * Executes the bye command, signaling the application to terminate.
     *
     * @param ui          The user interface to display messages.
     * @param taskManager The manager handling tasks.
     * @param taskStorage The storage to save tasks.
     */
    public String execute(Ui ui, TaskManager taskManager, TaskStorage taskStorage) {
        return ReplyMessage.BYE_MESSAGE;
    }

    /**
     * Indicates whether this command is terminal, i.e., should terminate the program.
     *
     * @return {@code true}, since this command terminates the program.
     */
    public boolean isTerminal() {
        return true;
    }
}
