package peter.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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


public class CommandParserTest {

    private static final String MEANINGLESS_COMMAND =
            "OOPS!!! I'm sorry, but I don't know what that means :-(";
    private CommandParser parser;

    @BeforeEach
    void setUp() {
        parser = new CommandParser();
    }

    @Test
    void parseByeCommandTest() throws MeaninglessCommandException,
            EmptyTaskException, InvalidTaskFormatException, InvalidDateTimeFormatException {
        Command command = parser.makeSenseUserCommand("bye");
        assertInstanceOf(ByeCommand.class, command);
    }

    @Test
    void parse_listCommand_success() throws MeaninglessCommandException,
            EmptyTaskException, InvalidTaskFormatException, InvalidDateTimeFormatException {
        Command command = parser.makeSenseUserCommand("list");
        assertInstanceOf(ListCommand.class, command);
    }

    @Test
    void parseMarkCommandTest() throws MeaninglessCommandException,
            EmptyTaskException, InvalidTaskFormatException, InvalidDateTimeFormatException {
        Command command = parser.makeSenseUserCommand("mark 1");
        assertInstanceOf(MarkCommand.class, command);
    }

    @Test
    void parseUnMarkCommandTest() throws MeaninglessCommandException,
            EmptyTaskException, InvalidTaskFormatException, InvalidDateTimeFormatException {
        Command command = parser.makeSenseUserCommand("unmark 1");
        assertInstanceOf(UnmarkCommand.class, command);
    }

    @Test
    void parseDeleteCommandTest() throws MeaninglessCommandException,
            EmptyTaskException, InvalidTaskFormatException, InvalidDateTimeFormatException {
        Command command = parser.makeSenseUserCommand("delete 1");
        assertInstanceOf(DeleteCommand.class, command);
    }

    @Test
    void parseAddCommandTest() throws MeaninglessCommandException,
            EmptyTaskException, InvalidTaskFormatException, InvalidDateTimeFormatException {
        Command command = parser.makeSenseUserCommand("todo read book");
        assertInstanceOf(AddCommand.class, command);
    }

    @Test
    void parseCountCommandTest() throws MeaninglessCommandException,
            EmptyTaskException, InvalidTaskFormatException, InvalidDateTimeFormatException {
        Command command = parser.makeSenseUserCommand("count");
        assertInstanceOf(CountCommand.class, command);
    }

    @Test
    void parseResetCommandTest() throws MeaninglessCommandException,
            EmptyTaskException, InvalidTaskFormatException, InvalidDateTimeFormatException {
        Command command = parser.makeSenseUserCommand("reset");
        assertInstanceOf(ResetCommand.class, command);
    }

    @Test
    void parseFindCommandTest() throws MeaninglessCommandException,
            EmptyTaskException, InvalidTaskFormatException, InvalidDateTimeFormatException {
        Command command = parser.makeSenseUserCommand("find book");
        assertInstanceOf(FindCommand.class, command);
    }

    @Test
    void parseInstructionCommandTest() throws MeaninglessCommandException,
            EmptyTaskException, InvalidTaskFormatException, InvalidDateTimeFormatException {
        Command command = parser.makeSenseUserCommand("instruction");
        assertInstanceOf(InstructionCommand.class, command);
    }

    @Test
    void parseMeaninglessCommand_throwsException() {
        Exception exception = assertThrows(MeaninglessCommandException.class, () ->
                parser.makeSenseUserCommand("meaninglessCommand"));
        assertEquals(MEANINGLESS_COMMAND, exception.getMessage());
    }
}
