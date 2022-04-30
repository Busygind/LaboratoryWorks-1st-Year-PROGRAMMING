package lab7.client.commandDispatcher;

import lab7.common.util.requestSystem.requests.CommandRequest;
import lab7.common.util.requestSystem.Serializer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;

public class RequestBuilder {

    private RequestBuilder() {
        //never used
    }

    public static ByteBuffer buildRequest(String input, String username) throws IOException {
        CommandRequest request = initCommand(input, username);
        if (request == null) {
            throw new NullPointerException("Command is incorrect. Try again");
        }
        Serializer serializer = new Serializer();
        return serializer.serializeRequest(request);
    }

    private static CommandRequest initCommand(String line, String username) throws IOException {
        List<String> commandWithArgs = LineSplitter.smartSplit(line);
        String commandName = getCommandName(commandWithArgs);
        List<String> commandArgs = getCommandArguments(commandWithArgs);
        CommandFactory factory = new CommandFactory(username);
        return factory.createCommand(commandName, commandArgs);
    }

    /**
     * Метод, извлекающий из полученного массива аргументов данные, которые являются аргументами
     *
     * @param line разделенная строка
     * @return массив аргументов
     */
    private static List<String> getCommandArguments(List<String> line) {
        line.remove(0);
        return line;
    }

    /**
     * Метод, извлекающий из полученного массива строк имя команды
     *
     * @param line разделенная строка
     * @return имя команды
     */
    private static String getCommandName(List<String> line) {
        return line.get(0);
    }
}
