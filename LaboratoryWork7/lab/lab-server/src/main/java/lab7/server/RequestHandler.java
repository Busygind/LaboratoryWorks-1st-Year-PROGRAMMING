package lab7.server;

import lab7.common.util.requestSystem.requests.*;
import lab7.common.util.requestSystem.responses.CommandResponse;
import lab7.common.util.requestSystem.responses.Response;
import lab7.common.util.requestSystem.responses.SignInResponse;
import lab7.common.util.requestSystem.responses.SignUpResponse;
import lab7.server.commands.*;
import lab7.server.databaseHandlers.AuthorizationModule;
import lab7.server.databaseHandlers.DatabaseWorker;
import lab7.server.databaseHandlers.UsersChecker;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class RequestHandler {

    private final Connection dbConnection;
    private UsersChecker usersChecker;
    private DatabaseWorker databaseWorker;

    public RequestHandler(Request request, Connection dbConnection) {
        this.dbConnection = dbConnection;
        try {
            this.usersChecker = new UsersChecker(dbConnection, ((CommandRequest) request).getPair());
            this.databaseWorker = new DatabaseWorker(dbConnection, ((CommandRequest) request).getPair());
        } catch (ClassCastException e) {
            this.usersChecker = new UsersChecker(dbConnection, null);
            this.databaseWorker = new DatabaseWorker(dbConnection, null);
        }
    }

    public Response handle(Request request) throws IOException, SQLException {
        try {
            if (request.getType().equals(RequestType.COMMAND_WITHOUT_ARGS)) {
                CommandAbstract command = getCommandWithoutArgs((CommandRequestWithoutArgs) request);
                if (!usersChecker.checkUserInData()) {
                    return new CommandResponse("Your login or password is incorrect!");
                }
                return handleCommand(command);
            } else if (request.getType().equals(RequestType.COMMAND_WITH_DRAGON)) {
                CommandAbstract command = getCommandWithDragon((CommandRequestWithDragon) request);
                if (!usersChecker.checkUserInData()) {
                    return new CommandResponse("Your login or password is incorrect!");
                }
                return handleCommand(command);
            } else if (request.getType().equals(RequestType.COMMAND_WITH_ID)) {
                CommandAbstract command = getCommandWithId((CommandRequestWithId) request);
                if (!usersChecker.checkUserInData()) {
                    return new CommandResponse("Your login or password is incorrect!");
                }
                return handleCommand(command);
            } else if (request.getType().equals(RequestType.COMMAND_WITH_DRAGON_AND_ID)) {
                CommandAbstract command = getCommandWithDragonAndId((CommandRequestWithDragonAndId) request);
                if (!usersChecker.checkUserInData()) {
                    return new CommandResponse("Your login or password is incorrect!");
                }
                return handleCommand(command);
            } else if (request.getType().equals(RequestType.SIGN_IN)) {
                AuthorizationModule authorizationModule =
                        new AuthorizationModule((SignInRequest) request, dbConnection);
                return new SignInResponse(authorizationModule.isCorrectUser(), ((SignInRequest) request).getPair());
            } else if (request.getType().equals(RequestType.SIGN_UP)) {
                AuthorizationModule authorizationModule =
                        new AuthorizationModule((SignUpRequest) request, dbConnection);
                return new SignUpResponse(authorizationModule.isCorrectUser(), ((SignUpRequest) request).getPair());
            }
        } catch (NullPointerException e) {
            ServerConfig.LOGGER.error("Received command is null");
            ServerConfig.LOGGER.error("Response will not write");
            return null;
        }
        return null;
    }

    private CommandAbstract getCommandWithoutArgs(CommandRequestWithoutArgs commandRequestWithoutArgs) {
        switch (commandRequestWithoutArgs.getName()) {
            case "clear":
                return new ClearCommand(databaseWorker);
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

    private CommandResponse handleCommand(CommandAbstract command) {
        ServerConfig.LOGGER.info("Server recieve [" + command.getName() + "] command");
        HistorySaver historySaver = new HistorySaver();
        historySaver.addCommandInHistory(command);
        return (CommandResponse) command.execute(ServerConfig.MANAGER);
    }
}
