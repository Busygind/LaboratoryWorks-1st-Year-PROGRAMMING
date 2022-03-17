package lab.common.util.commands;

import java.util.ArrayList;

public class ExitCommand extends CommandAbstract {

    public ExitCommand() {
        super("exit", "Выход из программы без сохранения", 0);
    }

    @Override
    public boolean execute(ArrayList<String> args) {
        System.exit(0);
        return true;
    }
}
