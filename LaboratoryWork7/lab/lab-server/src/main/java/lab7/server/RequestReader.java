package lab7.server;

import lab7.common.util.requestSystem.Serializer;
import lab7.common.util.requestSystem.requests.*;
import lab7.common.util.requestSystem.responses.CommandResponse;
import lab7.common.util.requestSystem.responses.SignInResponse;
import lab7.common.util.requestSystem.responses.SignUpResponse;
import lab7.server.commands.*;
import lab7.server.databaseHandlers.AuthorizationModule;
import lab7.server.databaseHandlers.DatabaseWorker;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.sql.Connection;
import java.sql.SQLException;

public class RequestReader {

    private final Request request;
    private final SocketChannel socketChannel;
    private final Connection dbConnection;
    private DatabaseWorker databaseWorker;

    public RequestReader(Request request, SocketChannel socketChannel, Connection dbConnection) {
        this.request = request;
        this.socketChannel = socketChannel;
        this.dbConnection = dbConnection;
        try {
            this.databaseWorker = new DatabaseWorker(dbConnection, ((CommandRequest) request).getUsername());
        } catch (ClassCastException e) {
            this.databaseWorker = new DatabaseWorker(dbConnection, "");
        }

    }

    public void read() throws IOException {
        try {
            if (request.getType().equals(RequestType.COMMAND_WITHOUT_ARGS)) {
                CommandAbstract command = getCommandWithoutArgs((CommandRequestWithoutArgs) request);
                ServerConfig.logger.info("Server recieve [" + command.getName() + "] command");
                HistorySaver.addCommandInHistory(command);
                CommandResponse commandResponse = IOController.buildResponse(command, ServerConfig.manager);
                ByteBuffer buffer = Serializer.serializeResponse(commandResponse);
                socketChannel.write(buffer);
                ServerConfig.logger.info("Server wrote response to client");
            } else if (request.getType().equals(RequestType.COMMAND_WITH_DRAGON)) {
                CommandAbstract command = getCommandWithDragon((CommandRequestWithDragon) request);
                ServerConfig.logger.info("Server recieve [" + command.getName() + "] command");
                HistorySaver.addCommandInHistory(command);
                CommandResponse commandResponse = IOController.buildResponse(command, ServerConfig.manager);
                ByteBuffer buffer = Serializer.serializeResponse(commandResponse);
                socketChannel.write(buffer);
                ServerConfig.logger.info("Server wrote response to client");
            } else if (request.getType().equals(RequestType.COMMAND_WITH_ID)) {
                CommandAbstract command = getCommandWithId((CommandRequestWithId) request);
                ServerConfig.logger.info("Server recieve [" + command.getName() + "] command");
                HistorySaver.addCommandInHistory(command);
                CommandResponse commandResponse = IOController.buildResponse(command, ServerConfig.manager);
                ByteBuffer buffer = Serializer.serializeResponse(commandResponse);
                socketChannel.write(buffer);
                ServerConfig.logger.info("Server wrote response to client");
            } else if (request.getType().equals(RequestType.COMMAND_WITH_DRAGON_AND_ID)) {
                CommandAbstract command = getCommandWithDragonAndId((CommandRequestWithDragonAndId) request);
                ServerConfig.logger.info("Server recieve [" + command.getName() + "] command");
                HistorySaver.addCommandInHistory(command);
                CommandResponse commandResponse = IOController.buildResponse(command, ServerConfig.manager);
                ByteBuffer buffer = Serializer.serializeResponse(commandResponse);
                socketChannel.write(buffer);
                ServerConfig.logger.info("Server wrote response to client");
            } else if (request.getType().equals(RequestType.SIGN_IN)) {
                try {
                    AuthorizationModule authorizationModule =
                            new AuthorizationModule((SignInRequest) request, dbConnection);
                    SignInResponse signInResponse = new SignInResponse(authorizationModule.isCorrectUser(), ((SignInRequest) request).getLogin());
                    ByteBuffer buffer = Serializer.serializeResponse(signInResponse);
                    socketChannel.write(buffer);
                    ServerConfig.logger.info("Server wrote response to client");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if (request.getType().equals(RequestType.SIGN_UP)) {
                try {
                    AuthorizationModule authorizationModule =
                            new AuthorizationModule((SignUpRequest) request, dbConnection);
                    SignUpResponse signUpResponse = new SignUpResponse(authorizationModule.isCorrectUser(), ((SignUpRequest) request).getLogin());
                    ByteBuffer buffer = Serializer.serializeResponse(signUpResponse);
                    socketChannel.write(buffer);
                    ServerConfig.logger.info("Server wrote response to client");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (NullPointerException e) {
            ServerConfig.logger.error("Received command is null");
            ServerConfig.logger.error("Response will not write");
        }
    }

    private CommandAbstract getCommandWithoutArgs(CommandRequestWithoutArgs commandRequestWithoutArgs) {
        switch (commandRequestWithoutArgs.getName()) {
            case "clear":
                return new ClearCommand(databaseWorker);
            case "exit":
                return new ExitCommand(databaseWorker);
            case "help":
                return new HelpCommand(databaseWorker);
            case "history":
                return new HistoryCommand(databaseWorker);
            case "info":
                return new InfoCommand(databaseWorker);
            case "show":
                return new ShowCommand(databaseWorker);
            case "max_by_cave":
                return new MaxByCaveCommand(databaseWorker);
            case "print_ascending":
                return new PrintAscendingCommand(databaseWorker);
            case "print_descending":
                return new PrintDescendingCommand(databaseWorker);
            default:
                return null;
        }
    }

    private CommandAbstract getCommandWithDragon(CommandRequestWithDragon commandRequestWithDragon) {
        switch (commandRequestWithDragon.getName()) {
            case "add":
                return new AddCommand(commandRequestWithDragon.getDragon(), databaseWorker);
            case "add_if_min":
                return new AddIfMinCommand(commandRequestWithDragon.getDragon(), databaseWorker);
            case "add_if_max":
                return new AddIfMaxCommand(commandRequestWithDragon.getDragon(), databaseWorker);
            default:
                return null;
        }
    }

    private CommandAbstract getCommandWithId(CommandRequestWithId commandRequestWithId) {
        switch (commandRequestWithId.getName()) {
            case "remove_by_id":
                return new RemoveByIdCommand(commandRequestWithId.getId(), databaseWorker);
            default:
                return null;
        }
    }

    private CommandAbstract getCommandWithDragonAndId(CommandRequestWithDragonAndId commandRequestWithDragonAndId) {
        switch (commandRequestWithDragonAndId.getName()) {
            case "update_by_id":
                return new UpdateByIdCommand(commandRequestWithDragonAndId.getId(),
                                                    commandRequestWithDragonAndId.getDragon(), databaseWorker);
            default:
                return null;
        }
    }
}
