package lab.common.util.requestSystem;

import lab.common.util.commands.CommandAbstract;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;

public class Serializer {
    private static final int BUFFER_LENGTH = 4096;

    public static ByteBuffer serializeCommand(CommandAbstract command) throws IOException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream(BUFFER_LENGTH);
        ObjectOutputStream oos = new ObjectOutputStream(bytes);
        oos.writeObject(command);
        oos.flush();
        ByteBuffer bufferToSend = ByteBuffer.wrap(bytes.toByteArray());
        oos.close();
        bytes.close();
        return bufferToSend;
    }

    public static Response deserializeResponse(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        Response response = (Response) objectInputStream.readObject();
        byteArrayInputStream.close();
        objectInputStream.close();
        return response;
    }

    public static ByteBuffer serializeResponse(Response response) throws IOException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream(BUFFER_LENGTH);
        ObjectOutputStream oos = new ObjectOutputStream(bytes);
        oos.writeObject(response);
        oos.flush();
        ByteBuffer bufferToSend = ByteBuffer.wrap(bytes.toByteArray());
        oos.close();
        bytes.close();
        return bufferToSend;
    }

    public static CommandAbstract deserializeCommand(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        CommandAbstract command = (CommandAbstract) objectInputStream.readObject();
        byteArrayInputStream.close();
        objectInputStream.close();
        return command;
    }
}
