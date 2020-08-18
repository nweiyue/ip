public class Duke {
    private static final Divider divider = new Divider();
    private static final Command command = new Command();

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
        command.getCommands();
    }
}
