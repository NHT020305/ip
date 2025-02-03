package Peter.command.commands;

import Peter.command.Command;
import Peter.storage.TaskStorage;
import Peter.task.TaskManager;
import Peter.ui.Ui;

public class FindCommand extends Command{

    private final String keyWord;

    public FindCommand(String keyWord) {
        this.keyWord = keyWord;
    }

    public void execute(Ui ui, TaskManager taskManager, TaskStorage taskStorage) {
        System.out.println(" Here are the tasks in your list:");
        TaskManager newTaskManager = new TaskManager(taskManager.search(keyWord));
        newTaskManager.list();
    }

    public boolean isTerminal() {
        return false;
    }
}
