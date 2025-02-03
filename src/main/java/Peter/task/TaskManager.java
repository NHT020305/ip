package Peter.task;

import java.util.ArrayList;

public class TaskManager {

    protected ArrayList<Task> tasks;

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    public TaskManager(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTaskList() {
        return tasks;
    }

    public int size() {
        return tasks.size();
    }

    public Task getTask(int index) {
        if (index < 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return tasks.get(index);
    }

    public void list() {
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, this.tasks.get(i));
        }
    }

    public void reset() {
        this.tasks.clear();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task delete(int index) {
        if (index < 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return tasks.remove(index);
    }

    public void markAsDone(int index) {
        if (index < 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        tasks.get(index).markDone();
    }

    public void markAsNotDone(int index) {
        if (index < 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        tasks.get(index).markNotDone();
    }

    public ArrayList<Task> search(String keyWord) {
        ArrayList<Task> newTaskList = new ArrayList<>();
        for(Task task: tasks) {
            if (task.getDescription().toLowerCase().contains(keyWord.toLowerCase())) {
                newTaskList.add(task);
            }
        }
        return newTaskList;
    }
}
