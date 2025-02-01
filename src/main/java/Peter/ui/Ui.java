package Peter.ui;

public class Ui {

    public void welcome() {
        System.out.println(" Hello! I'm Peter");
        System.out.println(" Here are the tasks in your list:");
    }

    public void goodbye() {
        System.out.println(" Bye. Hope to see you again soon!");
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showError(String message) {
        System.out.println(message);
    }
}
