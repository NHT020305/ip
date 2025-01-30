import java.util.ArrayList;

public class TaskManager {

    protected ArrayList<Task> tasks;
    protected TaskStorage taskStorage;

    TaskManager(ArrayList<Task> tasks, TaskStorage taskStorage) {
        this.tasks = tasks;
        this.taskStorage = taskStorage;
    }

    TaskManager(TaskStorage taskStorage) {
        this.tasks = new ArrayList<>();
        this.taskStorage = taskStorage;
    }

    public int size() {
        return tasks.size();
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public void list() {
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.println((i + 1) + "." + this.tasks.get(i));
        }
    }

    public void add(Task task) {
        tasks.add(task);
        taskStorage.saveTasks(tasks);
    }

    public Task delete(int index) {
        if (index < 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        Task task = tasks.remove(index);
        taskStorage.saveTasks(tasks);
        return task;
    }

    public void markAsDone(int index) {
        if (index < 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        tasks.get(index).markDone();
        taskStorage.saveTasks(tasks);
    }

    public void markAsNotDone(int index) {
        if (index < 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        tasks.get(index).markNotDone();
        taskStorage.saveTasks(tasks);
    }
}
