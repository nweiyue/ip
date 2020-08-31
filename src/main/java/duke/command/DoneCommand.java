package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents an action to mark a Task from TaskList as done.
 */
public class DoneCommand extends Command {

    /** Index of Task to be marked as done */
    private int taskNumber;

    /**
     * Constructs a <code>DoneCommand</code> object.
     *
     * @param taskNumber Index of Task to be marked as done.
     */
    public DoneCommand(int taskNumber) {
        super(false);
        this.taskNumber = taskNumber;
    }

    /**
     * Marks a Task from the TaskList as done and notify the user.
     *
     * @param tasks TaskList to store Task.
     * @param ui Ui to interact with users.
     * @param storage Storage use by Duke to save and load files.
     */
    @Override
    public CommandResponse execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.doTask(taskNumber);
        storage.save(tasks);
        String responseMessage = "Nice! I've marked this task as done:\n\t   "
                + tasks.getTask(taskNumber);
        boolean shouldExit = getIsExit();
        return new CommandResponse(responseMessage, shouldExit);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof DoneCommand) {
            DoneCommand c = (DoneCommand) obj;
            return c.taskNumber == this.taskNumber && c.getIsExit() == this.getIsExit();
        } else {
            return false;
        }
    }

}
