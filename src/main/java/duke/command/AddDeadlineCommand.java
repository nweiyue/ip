package duke.command;

import java.time.format.DateTimeParseException;

import duke.exception.InvalidTaskDateTimeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;



/**
 * Represents an action to add new Deadline.
 */
public class AddDeadlineCommand extends Command {

    /** Description of Deadline to be added */
    private final String description;

    /** String representation of date and time of Deadline to be added */
    private final String by;

    /**
     * Constructs a <code>AddDeadlineCommand</code> object.
     *
     * @param description Description of Deadline to be added.
     * @param by String representation of date and time of Deadline to be added.
     */
    public AddDeadlineCommand(String description, String by) {
        super(false);
        this.description = description;
        this.by = by;
    }

    /**
     * Adds a Deadline to the TaskList and notify the user if successful.
     *
     * @param tasks TaskList to store Task.
     * @param ui Ui to interact with users.
     * @param storage Storage use by Duke to save and load files.
     * @throws InvalidTaskDateTimeException If date and time format is invalid.
     */
    @Override
    public CommandResponse execute(TaskList tasks, Ui ui, Storage storage) throws InvalidTaskDateTimeException {
        try {
            Task task = new Deadline(description, by);
            tasks.addTask(task);
            storage.save(tasks);
            String responseMessage = "Got it. I've added this deadline: \n\t   "
                    + task + "\n\t "
                    + "Now you have "
                    + getTaskDescription(tasks.getNumberOfTask())
                    + " in the list.";
            boolean shouldExit = getIsExit();
            assert !shouldExit : "shouldExit should be false";
            return new CommandResponse(responseMessage, shouldExit);
        } catch (DateTimeParseException e) {
            throw new InvalidTaskDateTimeException();
        }
    }

    /**
     * Returns a String description of the number of Task.
     *
     * @param noOfTask Number of Task in TaskList
     * @return String description of the number of Task.
     */
    public static String getTaskDescription(int noOfTask) {
        String taskDescription = "";
        if (noOfTask > 1) {
            taskDescription = noOfTask + " tasks";
        } else {
            taskDescription = noOfTask + " task";
        }
        return taskDescription;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof AddDeadlineCommand) {
            AddDeadlineCommand c = (AddDeadlineCommand) obj;
            return c.description.equals(this.description) && c.getIsExit() == this.getIsExit();
        } else {
            return false;
        }
    }
}
