package lab6.client.commands;

import java.util.ArrayList;

public class HelpCommand extends CommandAbstract {

    public HelpCommand() {
        super("help", "Вывести список доступных команд", 0);
    }

    //todo реализовать
    @Override
    public boolean execute(ArrayList<String> args) {
        return false;
    }
}
