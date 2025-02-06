package Peter.command.commands;

import Peter.command.Command;
import Peter.storage.TaskStorage;
import Peter.task.Task;
import Peter.task.TaskManager;
import Peter.ui.Ui;

public class DeleteCommand extends Command {

    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    public void execute(Ui ui, TaskManager taskManager, TaskStorage taskStorage) {
        Task task = taskManager.delete(index);
        System.out.println(" Noted. I've removed this task:");
        System.out.println("  " + task);
        if (taskManager.size() == 0) {
            System.out.println(" Now your task list is empty!!!");
        } else {
            String isMany = taskManager.size() > 1 ? "s" : "";
            System.out.println(" Now you have " + taskManager.size() +
                    " task" + isMany + " in the list.");
        }
        taskStorage.saveTasks(taskManager);
    }

    public boolean isTerminal() {
        return false;
    }
}
