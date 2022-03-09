package lab6.client.commands;

import lab6.client.handlers.CommandListener;
import lab6.client.handlers.LineSplitter;
import lab6.client.handlers.TextFormatter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static lab6.client.handlers.CommandListener.*;

public class ExecuteScriptCommand extends CommandAbstract {

    public ExecuteScriptCommand() {
        super("execute_script", "Считать и исполнить скрипт из указанного файла", 1);
    }
//todo реализовать
    @Override
    public boolean execute(ArrayList<String> args) {
        try {
            File starting = new File(System.getProperty("user.dir")); // Get current user directory
            File file = new File(starting, args.get(0)); // Initialize file from cmd
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String nextLine = sc.nextLine();
                if (!("execute_script " + args.get(0)).equals(nextLine)) {
                    ArrayList<String> line = LineSplitter.smartSplit(nextLine);
                    CommandListener.invokeCommand(getCommandName(line), getCommandArguments(line));
                } else {
                    TextFormatter.printErrorMessage("Ошибка выполнения. Скрипт вызывает сам себя.");
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            TextFormatter.printErrorMessage("Файла с таким именем в текущей папке нет. Переместите файл и повторите попытку");
            return false;
        }
        return true;
    }
}
