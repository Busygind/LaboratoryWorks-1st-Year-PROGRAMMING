package lab7.client.dataController;

import javafx.util.Pair;
import lab7.client.commandDispatcher.RequestBuilder;
import lab7.common.util.requestSystem.requests.Request;
import lab7.common.util.requestSystem.Serializer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class RequestSender {

    private static final int SLEEP_TIME = 500;
    private final Pair<String, String> loginData;
    private final SocketChannel channel;
    private final String input;
    private final Selector selector;
    private final Request request;

    public RequestSender(SocketChannel channel, String input, Selector selector, Pair<String, String> loginData) {
        this.channel = channel;
        this.input = input;
        this.selector = selector;
        this.request = null;
        this.loginData = loginData;
    }

    public RequestSender(SocketChannel channel, Request request, Selector selector, Pair<String, String> loginData) {
        this.channel = channel;
        this.input = null;
        this.request = request;
        this.selector = selector;
        this.loginData = loginData;
    }

    public void send() throws InterruptedException, IOException {
        ByteBuffer buffer;
        if (input != null) {
            buffer = RequestBuilder.buildRequest(input, loginData);
        } else {
            Serializer serializer = new Serializer();
            buffer = serializer.serializeRequest(request);
        }
        channel.write(buffer);
        channel.register(selector, SelectionKey.OP_READ);
        Thread.sleep(SLEEP_TIME);
    }
}
