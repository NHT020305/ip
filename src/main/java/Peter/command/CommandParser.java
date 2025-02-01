package Peter.command;

import Peter.exception.EmptyTaskException;
import Peter.exception.InvalidTaskFormatException;
import Peter.exception.MeaninglessCommandException;
import Peter.storage.TaskGenerator;
import Peter.task.Task;
import Peter.task.TaskManager;

public class CommandParser {

    protected String command;

    public CommandParser(String command) {
        this.command = command;
    }

    public void makeSenseUserCommand(TaskManager taskManager) throws MeaninglessCommandException,
            EmptyTaskException, InvalidTaskFormatException {
        if (command.equals("list")) {
            System.out.println(" Here are the tasks in your list:");
            taskManager.list();
        } else if (command.equals("reset")) {
            taskManager.reset();
            System.out.println(" Got it. Now your task list is empty.");
            System.out.println(" Let's create a new task!!!");
        } else if (command.equals("count")) {
            System.out.println(" You have " + taskManager.size() + " tasks in the list.");
        } else if (command.startsWith("mark")) {
            int index = Integer.parseInt(command.split(" ")[1]) - 1;
            taskManager.markAsDone(index);
            System.out.println(" Nice! I've marked this task as done:");
            System.out.println("   " + taskManager.getTask(index));
        } else if (command.startsWith("unmark")) {
            int index = Integer.parseInt(command.split(" ")[1]) - 1;
            taskManager.markAsNotDone(index);
            System.out.println(" OK, I've marked this task as not done yet:");
            System.out.println("  " + taskManager.getTask(index));
        } else if (command.startsWith("delete")) {
            int index = Integer.parseInt(command.split(" ")[1]) - 1;
            Task task = taskManager.delete(index);
            System.out.println(" Noted. I've removed this task:");
            System.out.println("  " + task);
            System.out.println(" Now you have " + taskManager.size() + " tasks in the list.");
        } else if (command.startsWith("todo") || command.startsWith("deadline") || command.startsWith("event")) {
            Task task = new TaskGenerator().getTask(command);
            taskManager.add(task);
            System.out.println(" Got it. I've added this task:");
            System.out.println("  " + task);
            System.out.println(" Now you have " + taskManager.size() + " tasks in the list.");
        } else {
            throw new MeaninglessCommandException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
