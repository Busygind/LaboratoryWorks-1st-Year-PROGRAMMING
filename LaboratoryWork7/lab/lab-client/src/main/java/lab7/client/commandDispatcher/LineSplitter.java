package lab7.client.commandDispatcher;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс, отвечающий за корректное разделение строки при чтении команд и их аргументов от пользователя
 */
public final class LineSplitter {

    private LineSplitter() {
        //not called
    }
    /**
     * Метод, разделяющий полученную строку по пробелам, учитывая при этом экранизацию
     *
     * @param line строка, которую необходимо разделить
     * @return разделенный список строк
     */
    public static List<String> smartSplit(String line) {
        List<String> splittedLine = new ArrayList<>();
        StringBuilder currentString = new StringBuilder();
        boolean screeningStarted = false;
        for (char ch : line.toCharArray()) {
            if (ch == ' ' && !screeningStarted) {
                splittedLine.add(currentString.toString());
                currentString.setLength(0);
            } else if (ch == '"') {
                screeningStarted = !screeningStarted;
            } else {
                currentString.append(ch);
            }
        }
        splittedLine.add(currentString.toString());
        return splittedLine;
    }

}
