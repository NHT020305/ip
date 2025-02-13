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
import peter.utils.DateTime;
import peter.utils.TaskKeyword;


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
        assert filePath != null : "File path should not be null";
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
    private Task convertToTask(String task) throws InvalidDateTimeFormatException,
            EmptyTaskException, InvalidTaskFormatException {
        String output;
        if (task.startsWith(TaskKeyword.TODO_TAG)) {
            output = String.format("%s %s", TaskKeyword.TODO, task.substring(
                    TaskKeyword.NUMBER_OF_CHARACTERS_BEFORE_DESCRIPTION));
        } else if (task.startsWith(TaskKeyword.DEADLINE_TAG)) {
            String[] deadlineParts = task.substring(
                    TaskKeyword.NUMBER_OF_CHARACTERS_BEFORE_DESCRIPTION).split(DateTime.BY_LOAD_TASKS);
            String deadlineDescription = deadlineParts[0].trim();
            String date = LocalDateTime.parse(deadlineParts[1].split("\\)")[0].trim()).format(
                    DateTimeFormatter.ofPattern(DateTime.DATE_FORMAT));
            output = String.format("%s %s %s %s ", TaskKeyword.DEADLINE, deadlineDescription,
                    DateTime.BY_COMMAND, date);
        } else {
            String[] eventParts = task.substring(
                    TaskKeyword.NUMBER_OF_CHARACTERS_BEFORE_DESCRIPTION).split(DateTime.FROM_LOAD_TASKS);
            String[] dateParts = eventParts[1].split(DateTime.TO_LOAD_TASKS);
            String eventDescription = eventParts[0].trim();
            String fromPart = LocalDateTime.parse(dateParts[0].trim()).format(
                    DateTimeFormatter.ofPattern(DateTime.DATE_FORMAT));
            String toPart = LocalDateTime.parse(dateParts[1].split("\\)")[0].trim()).format(
                    DateTimeFormatter.ofPattern(DateTime.DATE_FORMAT));
            output = String.format("%s %s %s %s %s %s", TaskKeyword.EVENT, eventDescription,
                    DateTime.FROM_COMMAND, fromPart, DateTime.TO_COMMAND, toPart);
        }
        Task newTask = new TaskGenerator().getTask(output);
        if (task.contains(TaskKeyword.MARK_DONE)) {
            newTask.markDone();
        }
        return newTask;
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
                Task task = convertToTask(line);
                tasks.add(task);
            }
        } catch (IOException | InvalidDateTimeFormatException
                 | EmptyTaskException | InvalidTaskFormatException e) {
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
