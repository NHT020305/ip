import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;

public class TaskStorage {

    protected String filePath;

    public TaskStorage(String filePath) {
        this.filePath = filePath;
    }

    private String convert(String task) {
        if (task.startsWith("[T]")) {
            return "todo " + task.substring(7);
        } else if (task.startsWith("[D]")) {
            String[] parts = task.substring(7).split( "\\(by:");
            String date = parts[1].split("\\)")[0];
            return "deadline " + parts[0].trim() + " /by " + date.trim();
        } else {
            String[] parts = task.substring(7).split("\\(from:");
            String[] datePart = parts[1].split("to:");
            String fromPart = datePart[0];
            String toPart = datePart[1].split("\\)")[0];
            return "event " + parts[0].trim() + " /from "
                    + fromPart.trim() + " /to " + toPart.trim();
        }
    }

    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                Task task = new TaskManager(convert(line)).getTask();
                tasks.add(task);
            }
        } catch (IOException | EmptyTaskException | InvalidTaskFormatException e) {
            throw new RuntimeException(e);
        }
        return tasks;
    }

    public void saveTasks(ArrayList<Task> tasks) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, false))) {
            for (Task task : tasks) {
                bw.write(task.toString());
                bw.newLine();
            }
            System.out.println("saved");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
