package lab6.client.handlers;

import java.io.PrintStream;

public class TextFormatter {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_BLUE = "\u001B[34m";

    private static PrintStream printStream = System.out;

    public static void changePrintStream(PrintStream stream) {
        printStream = stream;
    }

    public static void printMessage(String message) {
        printStream.println(ANSI_BLUE + message + ANSI_RESET);
    }

    public static void printErrorMessage(String message) {
        printStream.println(ANSI_RED + message + ANSI_RESET);
    }

    public static void printInfoMessage(String message) {
        printStream.println(ANSI_GREEN + message + ANSI_RESET);
    }
}
