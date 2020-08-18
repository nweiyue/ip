import java.util.ArrayList;

public class ToDoList {
    private final ArrayList<Task> list;

    public ToDoList() {
        list = new ArrayList<Task>();
    }

    public void addTask(String description) {
        Task task = new Task(description);
        list.add(task);
    }

    public void doTask(int index) {
        list.get(index).completeTask();
    }

    public Task getTask(int index) {
        return list.get(index);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n\t ");
        int index = 1;
        for (Task task : list) {
            sb.append(index).append(".").append(task.toString()).append("\n\t ");
            index++;
        }
        return sb.delete(sb.length() - 3, sb.length() - 1).toString();
    }
}
