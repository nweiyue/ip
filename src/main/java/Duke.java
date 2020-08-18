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

            } else {
                list.addTask(command);
                System.out.println(divider.wrapInDivider("added: " + command));
            }
        }
    }
}

