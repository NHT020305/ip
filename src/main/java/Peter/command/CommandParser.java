package Peter.command;

import Peter.command.commands.*;
import Peter.exception.EmptyTaskException;
import Peter.exception.InvalidTaskFormatException;
import Peter.exception.MeaninglessCommandException;
import Peter.storage.TaskGenerator;

public class CommandParser {

    public Command makeSenseUserCommand(String command) throws MeaninglessCommandException,
            EmptyTaskException, InvalidTaskFormatException {
        if (command.equals("bye")) {
            return new ByeCommand();
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
            return new UnMarkCommand(Integer.parseInt(
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
