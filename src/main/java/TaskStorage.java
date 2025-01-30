import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
import java.time.format.DateTimeFormatter;

public class TaskStorage {

    protected String filePath;

    public TaskStorage(String filePath) {
        this.filePath = filePath;
    }

    public void createDataFile() {
        File file = new File(filePath);
        String fileName = file.getName();
        String directoryName = file.getParent();

        Path dataDirectory = Paths.get(directoryName);
        Path dataFile = dataDirectory.resolve(fileName);

        try {
            if (!Files.exists(dataDirectory)) {
                Files.createDirectories(dataDirectory);
            } if (!Files.exists(dataFile)) {
                Files.createFile(dataFile);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String convert(String task) {
        if (task.startsWith("[T]")) {
            return "todo " + task.substring(7);
        } else if (task.startsWith("[D]")) {
            String[] parts = task.substring(7).split( "\\(by:");
            String date = parts[1].split("\\)")[0].trim();
            date = LocalDateTime.parse(date).format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
            return "deadline " + parts[0].trim() + " /by " + date;
        } else {
            String[] parts = task.substring(7).split("\\(from:");
            String[] datePart = parts[1].split("to:");
            String fromPart = datePart[0].trim();
            String toPart = datePart[1].split("\\)")[0].trim();
            fromPart = LocalDateTime.parse(fromPart).format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
            toPart = LocalDateTime.parse(toPart).format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
            return "event " + parts[0].trim() + " /from "
                    + fromPart + " /to " + toPart;
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
