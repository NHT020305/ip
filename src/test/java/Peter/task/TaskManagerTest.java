package Peter.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import Peter.task.type.ToDo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskManagerTest {

    private TaskManager taskManager;
    private static final String OUT_OF_RANGE_INDEX = "Index out of bounds";

    @BeforeEach
    void setUp() {
        taskManager = new TaskManager();
    }

    @Test
    void addTaskTest() throws IndexOutOfBoundsException {
        assertEquals(0, taskManager.size());
        Task task = new ToDo("todo");
        taskManager.add(task);
        assertEquals(0, taskManager.size() - 1);
        assertEquals(1, taskManager.size());
        assertEquals(task, taskManager.getTask(0));
    }

    @Test
    void getTaskTest() throws IndexOutOfBoundsException {
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
    void markTaskTest() throws IndexOutOfBoundsException {
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
    void unmarkTaskTest() throws IndexOutOfBoundsException {
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
    void deleteTaskTest() throws IndexOutOfBoundsException {
        Task task = new ToDo("Test task");
        taskManager.add(task);
        Task deletedTask = taskManager.delete(0);
        assertEquals(task, deletedTask);
        assertEquals(0, taskManager.size());
    }

    @Test
    void deleteTaskTest_throwException() {
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () ->
                taskManager.getTask(0));
        assertEquals(OUT_OF_RANGE_INDEX, exception.getMessage());
    }
}

