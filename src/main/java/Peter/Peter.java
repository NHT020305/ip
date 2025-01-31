package Peter;

import Peter.command.CommandParser;
import Peter.task.TaskManager;
import Peter.storage.TaskStorage;
import Peter.ui.Ui;

import java.util.Scanner;

public class Peter {

    private TaskManager taskManager;
    private final Ui ui;

    public Peter(String filePath) {
        ui = new Ui();
        TaskStorage taskStorage = new TaskStorage(filePath);
        taskStorage.createDataFile();
        try {
            taskManager = new TaskManager(taskStorage.loadTasks(), taskStorage);
        } catch (Exception e) {
            ui.showError(e.getMessage());
            taskManager = new TaskManager(taskStorage);
        }
    }

    public void run() {
        ui.showLine();
        ui.welcome();
        taskManager.list();
        ui.showLine();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                String command = scanner.nextLine().trim().toLowerCase();
                ui.showLine();
                if (command.equals("bye")) {
                    ui.goodbye();
                    break;
                }
                CommandParser commandParser = new CommandParser(command);
                commandParser.makeSenseUserCommand(taskManager);
            } catch(Exception e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        new Peter("./data/Peter.txt").run();
    }
}
