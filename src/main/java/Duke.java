import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm TUNG");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else if (input.equals("list")) {
                System.out.println("____________________________________________________________");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + "." + tasks.get(i));
                }
                System.out.println("____________________________________________________________");
                continue;
            } else if (input.matches("mark \\d+")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                tasks.get(index).markDone();
                Task task = tasks.get(index);
                System.out.println("____________________________________________________________");
                System.out.println(" Nice! I've marked this task as done:");
                System.out.println("   " + task);
                System.out.println("____________________________________________________________");
                continue;
            } else if (input.matches("unmark \\d+")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                tasks.get(index).markNotDone();
                Task task = tasks.get(index);
                System.out.println("____________________________________________________________");
                System.out.println("  OK, I've marked this task as not done yet:");
                System.out.println("   " + task);
                System.out.println("____________________________________________________________");
                continue;
            } else if (input.matches("delete \\d+")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                Task task = tasks.remove(index);
                System.out.println("____________________________________________________________");
                System.out.println("  Noted. I've removed this task:");
                System.out.println("   " + task);
                System.out.println("  Now you have " + tasks.size() +" tasks in the list.");
                System.out.println("____________________________________________________________");
                continue;
            }
            tasks.add(new Task(input));
            System.out.println("____________________________________________________________");
            System.out.println(" added: " + input);
            System.out.println("____________________________________________________________");
        }

        scanner.close();
    }
}
