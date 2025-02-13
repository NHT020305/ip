package peter.utils;

/**
 * Handle different kinds of reply message;
 */
public class ReplyMessage {

    public static final String INSTRUCTION_MESSAGE = """
            Here are the instructions for using Peter chatbot:
                + "list": list all the tasks in your list.
                + "delete <i>": delete the ith task in your list.
                + "reset": delete all the tasks in your list.
                + "count": show the number of tasks in your list.
                + "mark <i>": mark the ith task in your list as done.
                + "unmark <i>": mark the ith task in your list as not done.
                + "find <keyword>": list all the tasks matching keyword.
                + "bye": exit Peter chatbot.
                + add new todo: "todo <name>".
                + add new deadline: "deadline <name> /by <time>".
                + add new event: "event <name> /from <time> /to <time>".
                + time format: "dd/MM/yyyy HH:mm".""";

    public static final String BYE_MESSAGE = "Bye. PETER chatbot hopes to see you again soon!";

    public static final String ADD_MESSAGE = """
            Got it. I've added this task:
                %s
            Now you have %d task%s in the list.""";

    public static final String COUNT_ZERO_MESSAGE = """
            There are no tasks in this list.
            Let's create a new task!!!""";

    public static final String COUNT_MESSAGE =
            "You have %d task%s in the list.";

    public static final String DELETE_ZERO_MESSAGE = """
            Noted. I've removed this task::
                %s
            Now your task list is empty!!!""";

    public static final String DELETE_MESSAGE = """
            Noted. I've removed this task::
                %s
            Now you have %d task%s in the list.""";

    public static final String FIND_MESSAGE = """
            Here are the tasks in your list matching "%s"
                %s
            Number of results: %d""";

    public static final String LIST_ZERO_MESSAGE = """
            There are no tasks in this list.
            Let's create a new task!!!""";

    public static final String LIST_MESSAGE = """
            Here are the tasks in your list:.
            %s
            You have %d task%s in the list.""";

    public static final String MARK_MESSAGE = """
            Nice! I've marked this task as done:
                %s""";

    public static final String UNMARK_MESSAGE = """
            OK, I've marked this task as not done yet:
                %s""";

    public static final String RESET_MESSAGE = """
            Got it. Now your task list is empty."
            Let's create a new task!!!""";
}
