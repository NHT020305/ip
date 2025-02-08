package peter.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import peter.exception.RepeatedTaskException;
import peter.task.type.ToDo;


public class TaskManagerTest {

    private static final String OUT_OF_RANGE_INDEX = "OOPS!!! 1 is not a valid task number.";
    private static final String ALREADY_EXISTS_TASK = "OOPS!!! This task already exists in your list.";
    private TaskManager taskManager;


    @BeforeEach
    void setUp() {
        taskManager = new TaskManager();
    }

    @Test
    void addTaskTest() throws IndexOutOfBoundsException, RepeatedTaskException {
        assertEquals(0, taskManager.countTasks());
        Task task = new ToDo("todo");
        taskManager.add(task);
        assertEquals(0, taskManager.countTasks() - 1);
        assertEquals(1, taskManager.countTasks());
        assertEquals(task, taskManager.getTask(0));
    }

    @Test
    void addTaskTest_throwException() throws RepeatedTaskException {
        assertEquals(0, taskManager.countTasks());
        Task task = new ToDo("todo");
        taskManager.add(task);
        Exception exception = assertThrows(RepeatedTaskException.class, () ->
                taskManager.add(new ToDo("todo")));
        assertEquals(ALREADY_EXISTS_TASK, exception.getMessage());
    }

    @Test
    void getTaskTest() throws IndexOutOfBoundsException, RepeatedTaskException {
        Task task = new ToDo("todo");
        taskManager.add(task);
        assertEquals(task, taskManager.getTask(0));
    }

    @Test
    void getTaskTest_throwException() {
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () ->
                taskManager.getTask(0));
        assertEquals(OUT_OF_RANGE_INDEX, exception.getMessage());
    }


    @Test
    void markTaskTest() throws IndexOutOfBoundsException, RepeatedTaskException {
        Task task = new ToDo("task");
        taskManager.add(task);
        taskManager.markAsDone(0);
        assertTrue(taskManager.getTask(0).isDone());
    }

    @Test
    void markTaskTest_throwException() {
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () ->
                taskManager.getTask(0));
        assertEquals(OUT_OF_RANGE_INDEX, exception.getMessage());
    }


    @Test
    void unmarkTaskTest() throws IndexOutOfBoundsException, RepeatedTaskException {
        Task task = new ToDo("task");
        task.markDone();
        taskManager.add(task);
        taskManager.markAsNotDone(0);
        assertFalse(taskManager.getTask(0).isDone());
    }

    @Test
    void unmarkTaskTest_throwException() {
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () ->
                taskManager.getTask(0));
        assertEquals(OUT_OF_RANGE_INDEX, exception.getMessage());
    }

    @Test
    void deleteTaskTest() throws IndexOutOfBoundsException, RepeatedTaskException {
        Task task = new ToDo("Test task");
        taskManager.add(task);
        Task deletedTask = taskManager.delete(0);
        assertEquals(task, deletedTask);
        assertEquals(0, taskManager.countTasks());
    }

    @Test
    void deleteTaskTest_throwException() {
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () ->
                taskManager.getTask(0));
        assertEquals(OUT_OF_RANGE_INDEX, exception.getMessage());
    }
}

