package duke.command;

import java.util.Comparator;

import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskType;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents an action to sort the Task in a TaskList.
 */
public class SortCommand extends Command {

    /** Custom Comparator which sorts the Tasks base on their types and then by their LocalDateTime if any */
    private static final Comparator<Task> TASK_COMPARATOR = new Comparator<Task>() {
        @Override
        public int compare(Task o1, Task o2) {
            if (o1.getTaskType() == TaskType.TODO) {
                return compareTodo(o2);
            } else if (o1.getTaskType() == TaskType.DEADLINE) {
                return compareDeadline((Deadline) o1, o2);
            } else { // unable to create default branch to throw exception due to override method
                return compareEvent((Event) o1, o2);
            }
        }
    };

    /**
     * Constructs a <code>SortCommand</code> object.
     */
    public SortCommand() {
        super(false);
    }

    /**
     * Compares a Todo with another Task. To be used by TASK_COMPARATOR.
     *
     * @param t2 The other Task to compare with .
     * @return An Integer to represent which Task should go first. 0 represents a tie-breaker
     * while -1 represents the Todo should go first.
     */
    private static int compareTodo(Task t2) {
        if (t2.getTaskType() == TaskType.TODO) {
            return 0;
        }
        return -1;
    }

    /**
     * Compares a Deadline with another Task. To be used by TASK_COMPARATOR.
     *
     * @param t2 The other Task to compare with.
     * @return An Integer to represent which Task should go first. 0 represents a tie-breaker
     * while -1 represents the Deadline should go first and 1 represents it should go after t2.
     */
    private static int compareDeadline(Deadline d1, Task t2) {
        if (t2.getTaskType() == TaskType.TODO) {
            return 1;
        } else if (t2.getTaskType() == TaskType.EVENT) {
            return -1;
        } else {
            Deadline d2 = (Deadline) t2;
            return d1.getDateTime().compareTo(d2.getDateTime());
        }
    }

    /**
     * Compares an Event with another Task. To be used by TASK_COMPARATOR.
     *
     * @param t2 The other Task to compare with.
     * @return An Integer to represent which Task should go first. 0 represents a tie-breaker
     * while -1 represents the Event should go first and 1 represents it should go after t2.
     */
    private static int compareEvent(Event e1, Task t2) {
        if (t2.getTaskType() == TaskType.EVENT) {
            Event e2 = (Event) t2;
            return e1.getDateTime().compareTo(e2.getDateTime());
        }
        return 1;
    }

    /**
     * Sorts the Task in TaskList.
     *
     * @param tasks TaskList to store Task.
     * @param ui Ui to interact with users.
     * @param storage Storage use by Duke to save and load files.
     * @return CommandResponse A response to the user.
     */
    @Override
    public CommandResponse execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.getList().sort(TASK_COMPARATOR);
        storage.save(tasks);
        boolean shouldExit = getIsExit();
        assert !shouldExit : "shouldExit should be false";
        return new CommandResponse(createResponseMessage(tasks), shouldExit);
    }

    /**
     * Generates a response message to construct a CommandResponse.
     * This message will be displayed to the User.
     *
     * @param tasks TaskList to retrieve the Task from.
     * @return A String to represent the message.
     */
    private String createResponseMessage(TaskList tasks) {
        return tasks.toString().replaceFirst(
                "Here are the tasks in your list",
                "Got it! I've sorted your tasks");
    }
}
