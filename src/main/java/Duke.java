import java.util.Scanner;
public class Duke {
    private static final Divider divider = new Divider();
    private static final ToDoList list = new ToDoList();

    public static void main(String[] args) {
        sendGreeting();
        receiveCommands();
    }

    public static void sendGreeting() {
        String logo = " ____        _        \n\t "
                + "|  _ \\ _   _| | _____ \n\t "
                + "| | | | | | | |/ / _ \\\n\t "
                + "| |_| | |_| |   <  __/\n\t "
                + "|____/ \\__,_|_|\\_\\___|\n\t ";
        System.out.println(divider.wrapInDivider(logo + "\n\t Hello! I'm Duke\n\t What can I do for you?"));
    }

    public static void receiveCommands() {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()) {
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                System.out.println(divider.wrapInDivider("Bye. Hope to see you again soon!"));
                scanner.close();
                System.exit(0);
            } else if (command.equals("list")) {
                System.out.println(divider.wrapInDivider(list.toString()));
            } else if (command.startsWith("done")) {
                String[] split = command.split("\\s+");
                if (split.length == 2 && split[1].matches("[0-9]+")) {
                    int taskNumber = Integer.parseInt(split[1]) - 1;
                    list.doTask(taskNumber);
                    System.out.println(divider.wrapInDivider("Nice! I've marked this task as done:\n\t   " +
                            list.getTask(taskNumber)));
                }
            } else {
                list.addTask(command);
                System.out.println(divider.wrapInDivider("added: " + command));
            }
        }
    }

}
