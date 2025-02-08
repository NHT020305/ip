package peter.command.commands;

import peter.command.Command;
import peter.storage.TaskStorage;
import peter.task.TaskManager;
import peter.ui.Ui;

/**
 * Represents a command to find tasks containing a specified keyword.
 */
public class FindCommand extends Command {

    /**
     * The keyword to search for.
     */
    private final String keyWord;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param keyWord The keyword to search for.
     */
    public FindCommand(String keyWord) {
        this.keyWord = keyWord;
    }

    /**
     * Executes the find task command.
     *
     * @param ui          The user interface to display messages.
     * @param taskManager The manager handling tasks.
     * @param taskStorage The storage to save tasks.
     */
    public void execute(Ui ui, TaskManager taskManager, TaskStorage taskStorage) {
        System.out.println(" Here are the tasks in your list matching \"" + keyWord + "\":");
        TaskManager newTaskManager = new TaskManager(taskManager.search(keyWord));
        newTaskManager.list();
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

