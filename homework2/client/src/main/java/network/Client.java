package network;

import cipher.MyCipherHandler;
import protocol.Cipher;
import protocol.MessageProtocol;
import protocol.ProtocolMessage;

import java.io.IOException;

public class Client {

    private final MyCipherHandler myCipherHandler;
    private final Connection connection;
    private final Cipher cipher;
    private final String key;

    public Client(final MyCipherHandler myCipherHandler, final int targetPort, final Cipher cipher, final String key) throws IOException {
        this.myCipherHandler = myCipherHandler;
        this.connection = new Connection(targetPort);
        this.cipher = cipher;
        this.key = key;
    }

    public void sendProtocolAndKey() throws IOException {
        final ProtocolMessage protocolMessage = new ProtocolMessage(MessageProtocol.CIPHER_TYPE, cipher.name());
        connection.sendMessage(protocolMessage);
    }

    public void sendKey() throws IOException {
        final ProtocolMessage protocolMessage = new ProtocolMessage(MessageProtocol.KEY, key);
        connection.sendMessage(protocolMessage);
    }

    public void sendMessage(final String message) throws IOException {
        final String encryptedMessage = myCipherHandler.encrypt(message);
        final ProtocolMessage protocolMessage = new ProtocolMessage(MessageProtocol.ENCRYPTED_MESSAGE, encryptedMessage);
        connection.sendMessage(protocolMessage);
    }
}
