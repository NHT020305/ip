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
        if (command.equals("bye")) {
            return new ByeCommand();
        } else if (command.equals("instruction")) {
            return new InstructionCommand();
        } else if (command.equals("list")) {
            return new ListCommand();
        } else if (command.equals("reset")) {
            return new ResetCommand();
        } else if (command.equals("count")) {
            return new CountCommand();
        } else if (command.startsWith("mark")) {
            return new MarkCommand(Integer.parseInt(
                    command.split(" ")[1]) - 1);
        } else if (command.startsWith("unmark")) {
            return new UnmarkCommand(Integer.parseInt(
                    command.split(" ")[1]) - 1);
        } else if (command.startsWith("delete")) {
            return new DeleteCommand(Integer.parseInt(
                    command.split(" ")[1]) - 1);
        } else if (command.startsWith("todo") || command.startsWith("deadline")
                || command.startsWith("event")) {
            return new AddCommand(new TaskGenerator().getTask(command));
        } else if (command.startsWith("find")) {
            return new FindCommand(command.substring(5));
        }
        throw new MeaninglessCommandException(
                "OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
