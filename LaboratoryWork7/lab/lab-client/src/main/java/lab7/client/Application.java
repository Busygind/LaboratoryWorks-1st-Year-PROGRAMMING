package lab7.client;

import lab7.client.commandDispatcher.LineSplitter;
import lab7.client.dataController.RequestSender;
import lab7.client.dataController.ResponseReceiver;
import lab7.client.usersController.AuthorizationModule;
import lab7.client.usersController.UserAcceptor;
import lab7.common.util.handlers.TextFormatter;
import lab7.common.util.requestSystem.requests.Request;
import lab7.common.util.requestSystem.responses.*;

import java.io.IOException;
import java.io.StreamCorruptedException;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.channels.UnresolvedAddressException;
import java.util.*;

public class Application {

    private static final int PORT = 45846;
    private static final AuthorizationModule authorizationModule = new AuthorizationModule();
    private static final Scanner SCANNER = new Scanner(System.in);
    private static Selector selector;
    private static String username;

    public static void startClient() {
        TextFormatter.printMessage("Enter hostname: ");
        String hostname = SCANNER.nextLine();
        InetSocketAddress hostAddress = new InetSocketAddress(hostname, PORT);

        try {
            selector = Selector.open();

            SocketChannel client = SocketChannel.open(hostAddress);
            TextFormatter.printMessage("Connected!");
            client.configureBlocking(false);
            client.register(selector, SelectionKey.OP_WRITE);
            startSelectorLoop(client, SCANNER);
        } catch (ConnectException e) {
            TextFormatter.printErrorMessage("Server with this host is temporarily unavailable. Try again later");
            startClient();
        } catch (StreamCorruptedException e) {
            TextFormatter.printErrorMessage("Disconnected.");
        } catch (ClassNotFoundException e) {
            TextFormatter.printErrorMessage("Trying to serialize non-serializable object");
        } catch (InterruptedException e) {
            TextFormatter.printErrorMessage("Thread was interrupt while sleeping. Restart client");
        } catch (UnresolvedAddressException e) {
            TextFormatter.printErrorMessage("Server with this host not found. Try again");
            startClient();
        } catch (IOException e) {
            TextFormatter.printErrorMessage("Server invalid or closed. Try to connect again");
            startClient();
        }
    }

    private static void startSelectorLoop(SocketChannel channel, Scanner sc) throws IOException, ClassNotFoundException, InterruptedException {
        while (true) {
            selector.select();
            if (!startIteratorLoop(channel, sc)) {
                break;
            }
        }
    }

    private static boolean startIteratorLoop(SocketChannel channel, Scanner sc) throws IOException, ClassNotFoundException, InterruptedException {
        Set<SelectionKey> readyKeys = selector.selectedKeys();
        Iterator<SelectionKey> iterator = readyKeys.iterator();
        while (iterator.hasNext()) {
            SelectionKey key = iterator.next();
            iterator.remove();

            if (key.isReadable()) {
                ResponseReceiver responseReceiver = new ResponseReceiver(channel, key, selector);
                Response response = responseReceiver.receive();
                if (response.getType().equals(ResponseType.SIGN_IN)) {
                    SignInResponse signInResponse = (SignInResponse) response;
                    UserAcceptor acceptor = new UserAcceptor(authorizationModule, signInResponse);
                    username = acceptor.acceptAuthorization();
                    System.out.println(username);
                } else if (response.getType().equals(ResponseType.SIGN_UP)) {
                    SignUpResponse signUpResponse = (SignUpResponse) response;
                    UserAcceptor acceptor = new UserAcceptor(authorizationModule, signUpResponse);
                    username = acceptor.acceptRegistration();
                    System.out.println(username);
                } else {
                    CommandResponse commandResponse = (CommandResponse) response;
                    TextFormatter.printInfoMessage(commandResponse.getMessage());
                    if (commandResponse.getDragons() != null) {
                        TextFormatter.printMessage(commandResponse.getDragons().toString());
                    }
                }
            } else if (key.isWritable()) {
                //authorization
                if (!authorizationModule.isAuthorizationDone()) {
                    Request authorizationRequest = authorizationModule.greet();
                    RequestSender requestSender = new RequestSender(channel, authorizationRequest, selector, username);
                    requestSender.send();
                    continue;
                }

                TextFormatter.printInfoMessage("Enter command (to check available commands type \"help\"): ");
                try {
                    String input = sc.nextLine();
                    List<String> splittedLine = LineSplitter.smartSplit(input);
                    if (splittedLine.get(0).equalsIgnoreCase("execute_script") && splittedLine.size() == 2) {
                        ScriptReader scriptReader = new ScriptReader(input);
                        startSelectorLoop(channel, new Scanner(scriptReader.getPath()));
                        scriptReader.stopScriptReading();
                    }
                    try {
                        RequestSender requestSender = new RequestSender(channel, input, selector, username);
                        requestSender.send();
                    } catch (NullPointerException | IOException e) {
                        TextFormatter.printErrorMessage(e.getMessage());
                    }
                } catch (NoSuchElementException e) {
                    TextFormatter.printErrorMessage(e.getMessage());
                    return false;
                } catch (IllegalArgumentException e) {
                    TextFormatter.printErrorMessage(e.getMessage());
                }
            }
        }
        return true;
    }
}
