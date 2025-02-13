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
import peter.exception.EmptyTaskException;
import peter.exception.InvalidDateTimeFormatException;
import peter.exception.InvalidTaskFormatException;
import peter.exception.MeaninglessCommandException;
import peter.storage.TaskGenerator;
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
            return new MarkCommand(Integer.parseInt(
                    command.split(" ")[1]) - 1);
        } else if (command.startsWith(CommandType.UNMARK_COMMAND)) {
            return new UnmarkCommand(Integer.parseInt(
                    command.split(" ")[1]) - 1);
        } else if (command.startsWith(CommandType.DELETE_COMMAND)) {
            return new DeleteCommand(Integer.parseInt(
                    command.split(" ")[1]) - 1);
        } else if (command.startsWith(TaskKeyword.TODO) || command.startsWith(TaskKeyword.DEADLINE_TAG)
                || command.startsWith(TaskKeyword.DEADLINE)) {
            return new AddCommand(new TaskGenerator().getTask(command));
        } else if (command.startsWith(CommandType.FIND_COMMAND)) {
            return new FindCommand(command.substring(5));
        }
        throw new MeaninglessCommandException(ErrorMessage.MEANINGLESS_COMMAND);
    }
}
