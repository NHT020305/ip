package peter.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import peter.exception.EmptyTaskException;
import peter.exception.InvalidDateTimeFormatException;
import peter.exception.InvalidTaskFormatException;
import peter.task.Task;
import peter.task.TaskManager;


/**
 * Handles storage and retrieval of tasks to and from a data file.
 */
public class TaskStorage {

    private final String filePath;

    /**
     * Constructs a TaskStorage with the specified file path.
     *
     * @param filePath The file path where task data is stored.
     */
    public TaskStorage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Creates the data file and its directory if they do not exist.
     */
    public void createDataFile() {
        File file = new File(filePath);
        String fileName = file.getName();
        String directoryName = file.getParent();

        Path dataDirectory = Paths.get(directoryName);
        Path dataFile = dataDirectory.resolve(fileName);
        try {
            if (!Files.exists(dataDirectory)) {
                Files.createDirectories(dataDirectory);
            }
            if (!Files.exists(dataFile)) {
                Files.createFile(dataFile);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Converts a task string from file format to a command format for parsing.
     *
     * @param task The task string to convert.
     * @return The converted task string.
     */
    private String convert(String task) {
        if (task.startsWith("[T]")) {
            return "todo " + task.substring(7);
        } else if (task.startsWith("[D]")) {
            String[] parts = task.substring(7).split("\\(by:");
            String date = parts[1].split("\\)")[0].trim();
            date = LocalDateTime.parse(date).format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
            return "deadline " + parts[0].trim() + " /by " + date;
        } else {
            String[] parts = task.substring(7).split("\\(from:");
            String[] datePart = parts[1].split("to:");
            String fromPart = datePart[0].trim();
            String toPart = datePart[1].split("\\)")[0].trim();
            fromPart = LocalDateTime.parse(fromPart).format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
            toPart = LocalDateTime.parse(toPart).format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
            return "event " + parts[0].trim() + " /from "
                    + fromPart + " /to " + toPart;
        }
    }

    /**
     * Loads tasks from the data file.
     *
     * @return A list of tasks loaded from the file.
     * @throws RuntimeException If an error occurs during file reading or task parsing.
     */
    public ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                Task task = new TaskGenerator().getTask(convert(line));
                if (line.contains("X")) {
                    task.markDone();
                }
                tasks.add(task);
            }
        } catch (IOException | EmptyTaskException | InvalidTaskFormatException
                 | InvalidDateTimeFormatException e) {
            throw new RuntimeException(e);
        }
        return tasks;
    }

    /**
     * Saves the current tasks to the data file.
     *
     * @param taskManager The task manager containing the tasks to save.
     */
    public void saveTasks(TaskManager taskManager) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, false))) {
            for (Task task : taskManager.getTaskList()) {
                bw.write(task.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
