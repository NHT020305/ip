package peter.command;

import peter.command.commands.AddCommand;
import peter.command.commands.ByeCommand;
import peter.command.commands.CountCommand;
import peter.command.commands.DeleteCommand;
import peter.command.commands.FindCommand;
import peter.command.commands.InstructionCommand;
import peter.command.commands.ListCommand;
import peter.command.commands.MarkCommand;
import peter.command.commands.ResetCommand;
import peter.command.commands.UnmarkCommand;
import peter.command.commands.UpdateCommand;
import peter.exception.EmptyTaskException;
import peter.exception.InvalidDateTimeFormatException;
import peter.exception.InvalidTaskFormatException;
import peter.exception.MeaninglessCommandException;
import peter.storage.TaskGenerator;
import peter.task.Task;
import peter.utils.CommandType;
import peter.utils.ErrorMessage;
import peter.utils.TaskKeyword;

/**
 * Parses and interprets user commands.
 */
public class CommandParser {

    /**
     * Interprets the user's input command and returns the corresponding Command object.
     *
     * @param command The user input string.
     * @return The Command object corresponding to the input.
     * @throws MeaninglessCommandException If the command is unrecognized.
     * @throws EmptyTaskException If the task description is missing.
     * @throws InvalidTaskFormatException If the task format is invalid.
     */
    public Command makeSenseUserCommand(String command) throws MeaninglessCommandException,
            EmptyTaskException, InvalidTaskFormatException, InvalidDateTimeFormatException {
        if (command.equals(CommandType.BYE_COMMAND)) {
            return new ByeCommand();
        } else if (command.equals(CommandType.INSTRUCTION_COMMAND)) {
            return new InstructionCommand();
        } else if (command.equals(CommandType.LIST_COMMAND)) {
            return new ListCommand();
        } else if (command.equals(CommandType.RESET_COMMAND)) {
            return new ResetCommand();
        } else if (command.equals(CommandType.COUNT_COMMAND)) {
            return new CountCommand();
        } else if (command.startsWith(CommandType.MARK_COMMAND)) {
            return handleMarkCommand(command);
        } else if (command.startsWith(CommandType.UNMARK_COMMAND)) {
            return handleUnmarkCommand(command);
        } else if (command.startsWith(CommandType.DELETE_COMMAND)) {
            return handleDeleteCommand(command);
        } else if (command.startsWith(CommandType.UPDATE_COMMAND)) {
            return handleUpdateCommand(command);
        } else if (command.startsWith(TaskKeyword.TODO) || command.startsWith(TaskKeyword.DEADLINE)
                || command.startsWith(TaskKeyword.EVENT)) {
            return handleTaskCommand(command);
        } else if (command.startsWith(CommandType.FIND_COMMAND)) {
            return handleFindCommand(command);
        }
        throw new MeaninglessCommandException(ErrorMessage.MEANINGLESS_COMMAND);
    }

    private Command handleMarkCommand(String command) {
        try {
            int index = Integer.parseInt(command.split(" ")[1]) - 1;
            return new MarkCommand(index);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid integer");
        }
    }

    private Command handleUnmarkCommand(String command) {
        try {
            int index = Integer.parseInt(command.split(" ")[1]) - 1;
            return new UnmarkCommand(index);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid integer");
        }

    }

    private Command handleDeleteCommand(String command) {
        try {
            int index = Integer.parseInt(command.split(" ")[1]) - 1;
            return new DeleteCommand(index);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid integer");
        }
    }

    private Command handleUpdateCommand(String command) {
        try {
            String[] parts = command.split(" ");
            int index = Integer.parseInt(parts[1]) - 1;
            String typeOfUpdate = parts[2];
            String updateDetails = parts[3];
            if (parts.length == 5) {
                updateDetails += " " + parts[4];
            }
            return new UpdateCommand(index, typeOfUpdate, updateDetails);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid integer");
        }
    }

    private Command handleTaskCommand(String command) throws EmptyTaskException, InvalidTaskFormatException, InvalidDateTimeFormatException {
        Task task = new TaskGenerator().getTask(command);
        return new AddCommand(task);
    }

    private Command handleFindCommand(String command) {
        return new FindCommand(command.substring(5));
    }

}
