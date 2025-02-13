package peter.utils;

/**
 * Handle different kinds of reply message;
 */
public class ReplyMessage {

    public static final String WELCOME_MESSAGE = """
            Welcome to PETER chatbot!
            Type "instruction" to know how to use""";

    public static final String INSTRUCTION_MESSAGE = """
            Here are the instructions for using Peter chatbot:
                + list all the tasks in your list: list
                + delete the ith task in your list: delete <i>
                + delete all the tasks in your list: reset
                + show the number of tasks in your list: count
                + mark the ith task in your list as done: mark <i>
                + mark the ith task in your list as not done: unmark <i>
                + list all the tasks matching keyword: find <keyword>
                + update the ith task description in your list: update <i> /description <details>
                + update the ith task deadline in your list: update <i> /by <details>
                + update the ith task start time in your list: update <i> /from <details>
                + update the ith task end time in your list: update <i> /to <details>
                + exit Peter chatbot.
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
            Noted. I've removed this task:
                %s
            Now your task list is empty!!!""";

    public static final String DELETE_MESSAGE = """
            Noted. I've removed this task:
                %s
            Now you have %d task%s in the list.""";

    public static final String FIND_MESSAGE = """
            Here are the tasks in your list matching "%s":
            %sNumber of results: %d""";

    public static final String LIST_ZERO_MESSAGE = """
            There are no tasks in this list.
            Let's create a new task!!!""";

    public static final String LIST_MESSAGE = """
            Here are the tasks in your list:.
            %sYou have %d task%s in the list.""";

    public static final String MARK_MESSAGE = """
            Nice! I've marked this task as done:
                %s""";

    public static final String UNMARK_MESSAGE = """
            OK, I've marked this task as not done yet:
                %s""";

    public static final String RESET_MESSAGE = """
            Got it. Now your task list is empty."
            Let's create a new task!!!""";

    public static final String UPDATE_MESSAGE = """
            Nice! I've updated this task as following:
                %s""";
}
