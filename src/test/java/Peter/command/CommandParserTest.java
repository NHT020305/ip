package Peter.command;

import Peter.command.commands.*;
import Peter.exception.EmptyTaskException;
import Peter.exception.InvalidTaskFormatException;
import Peter.exception.MeaninglessCommandException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CommandParserTest {

    private CommandParser parser;
    private static final String MEANINGLESS_COMMAND =
            "OOPS!!! I'm sorry, but I don't know what that means :-(";

    @BeforeEach
    void setUp() {
        parser = new CommandParser();
    }

    @Test
    void parseByeCommandTest() throws MeaninglessCommandException,
            EmptyTaskException, InvalidTaskFormatException {
        Command command = parser.makeSenseUserCommand("bye");
        assertInstanceOf(ByeCommand.class, command);
    }

    @Test
    void parse_listCommand_success() throws MeaninglessCommandException,
            EmptyTaskException, InvalidTaskFormatException {
        Command command = parser.makeSenseUserCommand("list");
        assertInstanceOf(ListCommand.class, command);
    }

    @Test
    void parseMarkCommandTest() throws MeaninglessCommandException,
            EmptyTaskException, InvalidTaskFormatException {
        Command command = parser.makeSenseUserCommand("mark 1");
        assertInstanceOf(MarkCommand.class, command);
    }

    @Test
    void parseUnMarkCommandTest() throws MeaninglessCommandException,
            EmptyTaskException, InvalidTaskFormatException {
        Command command = parser.makeSenseUserCommand("unmark 1");
        assertInstanceOf(UnMarkCommand.class, command);
    }

    @Test
    void parseDeleteCommandTest() throws MeaninglessCommandException,
            EmptyTaskException, InvalidTaskFormatException {
        Command command = parser.makeSenseUserCommand("delete 1");
        assertInstanceOf(DeleteCommand.class, command);
    }

    @Test
    void parseAddCommandTest() throws MeaninglessCommandException,
            EmptyTaskException, InvalidTaskFormatException {
        Command command = parser.makeSenseUserCommand("todo read book");
        assertInstanceOf(AddCommand.class, command);
    }

    @Test
    void parseMeaninglessCommand_throwsException() {
        Exception exception = assertThrows(MeaninglessCommandException.class, () ->
                parser.makeSenseUserCommand("meaninglessCommand"));
        assertEquals(MEANINGLESS_COMMAND, exception.getMessage());
    }

}
