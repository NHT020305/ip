import java.util.ArrayList;
import java.util.Scanner;

public class Peter {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        //ArrayList<String> taskString = new ArrayList<>();
        TaskStorage taskStorage = new TaskStorage("./data/Peter.txt");

        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Peter");
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
                    tasks = taskStorage.load();
                    tasks.get(index).markDone();
                    taskStorage.saveTasks(tasks);
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
                    tasks = taskStorage.load();
                    tasks.get(index).markNotDone();
                    taskStorage.saveTasks(tasks);
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
                    tasks = taskStorage.load();
                    Task task = tasks.remove(index);
                    taskStorage.saveTasks(tasks);
                    System.out.println("____________________________________________________________");
                    System.out.println(" Noted. I've removed this task:");
                    System.out.println("  " + task);
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } else if (command.startsWith("todo") || command.startsWith("deadline") || command.startsWith("event")) {
                    Task task = new TaskManager(command).getTask();
                    if (! tasks.isEmpty()) {
                        tasks = taskStorage.load();
                    }
                    tasks.add(task);
                    taskStorage.saveTasks(tasks);
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

}
