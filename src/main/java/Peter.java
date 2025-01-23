import java.util.ArrayList;
import java.util.Scanner;


public class Peter {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm TUNG");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {

            String command = scanner.nextLine().trim().toLowerCase();

            try {
                if (command.equals("bye")) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" Bye. Hope to see you again soon!");
                    System.out.println("____________________________________________________________");
                    break;
                } else if (command.equals("list")) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + "." + tasks.get(i));
                    }
                    System.out.println("____________________________________________________________");
                } else if (command.startsWith("mark")) {
                    int index = Integer.parseInt(command.split(" ")[1]) - 1;
                    if (index < 0 || index >= tasks.size()) {
                        throw new IndexOutOfBoundsException("Index out of bounds");
                    }
                    tasks.get(index).markDone();
                    Task task = tasks.get(index);
                    System.out.println("____________________________________________________________");
                    System.out.println(" Nice! I've marked this task as done:");
                    System.out.println("   " + task);
                    System.out.println("____________________________________________________________");
                } else if (command.startsWith("unmark")) {
                    int index = Integer.parseInt(command.split(" ")[1]) - 1;
                    if (index < 0 || index >= tasks.size()) {
                        throw new IndexOutOfBoundsException("Index out of bounds");
                    }
                    tasks.get(index).markNotDone();
                    Task task = tasks.get(index);
                    System.out.println("____________________________________________________________");
                    System.out.println(" OK, I've marked this task as not done yet:");
                    System.out.println("  " + task);
                    System.out.println("____________________________________________________________");
                } else if (command.startsWith("delete")) {
                    int index = Integer.parseInt(command.split(" ")[1]) - 1;
                    if (index < 0 || index >= tasks.size()) {
                        throw new IndexOutOfBoundsException("Index out of bounds");
                    }
                    Task task = tasks.remove(index);
                    System.out.println("____________________________________________________________");
                    System.out.println(" Noted. I've removed this task:");
                    System.out.println("  " + task);
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } else if (command.startsWith("todo") || command.startsWith("deadline") || command.startsWith("event")) {
                    Task task = getTask(command);
                    tasks.add(task);
                    System.out.println("____________________________________________________________");
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("  " + task);
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } else {
                    throw new MeaninglessCommandException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }

            } catch(Exception e) {
                System.out.println("____________________________________________________________");
                System.out.println(e.getMessage());
                System.out.println("____________________________________________________________");
            }
        }

        scanner.close();
    }


    private static Task getTask(String input) throws Exception {

        Task newTask = null;

        if (input.startsWith("todo")) {
            if (input.trim().length() == "todo".length()) {
                throw new EmptyTaskException("OOPS!!! The description of a todo cannot be empty.");
            }
            String description = input.split(" ")[1];
            newTask = new ToDo(description);

        } else if (input.startsWith("deadline")) {
            if (input.trim().length() == "deadline".length()) {
                throw new EmptyTaskException("OOPS!!! The description of a deadline cannot be empty.");
            }
            String task = input.substring("deadline ".length()).trim();
            String[] parts = task.split("/by");
            if (parts.length != 2) {
                throw new InvalidTaskFormatException("OOPS!!! Invalid deadline format.");
            }
            String description = parts[0].trim();
            String by = parts[1].trim();
            newTask = new Deadline(description, by);

        } else if (input.startsWith("event")) {
            if (input.trim().length() == "event".length()) {
                throw new EmptyTaskException("OOPS!!! The description of an event cannot be empty.");
            }
            String task = input.substring("event ".length());
            String[] parts = task.split("/from");
            if (parts.length != 2) {
                throw new InvalidTaskFormatException("OOPS!!! Invalid event format.");
            }
            String description = parts[0].trim();
            String[] date = parts[1].split("/to");
            if (date.length != 2) {
                throw new InvalidTaskFormatException("OOPS!!! Invalid event format.");
            }
            String from = date[0].trim();
            if (from.isEmpty()) {
                throw new InvalidTaskFormatException("OOPS!!! Invalid event format.");
            }
            String to = date[1].trim();
            newTask = new Event(description, from, to);
        }

        return newTask;
    }
}
