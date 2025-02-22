package peter.command.commands;

import peter.command.Command;
import peter.datetime.LocalDateTimeParser;
import peter.exception.InvalidDateTimeFormatException;
import peter.exception.InvalidUpdateException;
import peter.exception.MeaninglessCommandException;
import peter.exception.RepeatedTaskException;
import peter.storage.TaskStorage;
import peter.task.Task;
import peter.task.TaskManager;
import peter.task.type.Deadline;
import peter.task.type.Event;
import peter.ui.Ui;
import peter.utils.ErrorMessage;
import peter.utils.ReplyMessage;

/**
 * Represents a command to update a task from the task list.
 */
public class UpdateCommand extends Command {

    private final int index;
    private final String typeOfUpdate;
    private final String updatedDetails;

    /**
     * Constructs a DeleteCommand with the specified index.
     *
     * @param index The index of the task to be deleted.
     */
    public UpdateCommand(int index, String typeOfUpdate, String updatedDetails) {
        this.index = index;
        this.typeOfUpdate = typeOfUpdate;
        this.updatedDetails = updatedDetails;
    }

    /**
     * Executes the unmark task command.
     *
     * @param ui          The user interface to display messages.
     * @param taskManager The manager handling tasks.
     * @param taskStorage The storage to save tasks.
     */
    public String execute(Ui ui, TaskManager taskManager, TaskStorage taskStorage) throws
            RepeatedTaskException, InvalidDateTimeFormatException, MeaninglessCommandException, InvalidUpdateException {
        Task task = taskManager.getTask(index);
        switch (typeOfUpdate) {
        case "/description" -> task.updateDescription(updatedDetails);
        case "/by" -> {
            if (task instanceof Deadline) {
                task.updateTimeBy(new LocalDateTimeParser(updatedDetails).convertToTime());
            }
            throw new InvalidUpdateException(String.format(ErrorMessage.NOT_DEADLINE, index + 1));
        }
        case "/from" -> {
            if (task instanceof Event) {
                task.updateTimeFrom(new LocalDateTimeParser(updatedDetails).convertToTime());
            }
            throw new InvalidUpdateException(String.format(ErrorMessage.NOT_EVENT, index + 1));
        }
        case "/to" -> {
            if (task instanceof Event) {
                task.updateTimeTo(new LocalDateTimeParser(updatedDetails).convertToTime());
            }
            throw new InvalidUpdateException(String.format(ErrorMessage.NOT_EVENT, index + 1));
        }
        default -> throw new MeaninglessCommandException(ErrorMessage.MEANINGLESS_COMMAND);
        }
        taskStorage.saveTasks(taskManager);
        return String.format(ReplyMessage.UPDATE_MESSAGE, taskManager.getTask(index + 1));
    }

    @Override
    public boolean isTerminal() {
        return false;
    }
}
