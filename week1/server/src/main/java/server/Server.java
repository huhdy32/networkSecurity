package server;

import cipher.MyCipherHandler;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int PORT = 17872;
    private final EncryptionProcessor encryptionProcessor;

    public Server(EncryptionProcessor cipherMapper) {
        this.encryptionProcessor = cipherMapper;
    }

    public void init() throws IOException {
        ServerSocket socket = new ServerSocket(PORT);
        while (true) {
            final Socket receivedSocket = socket.accept();
            final BufferedInputStream bufferedInputStream = new BufferedInputStream(receivedSocket.getInputStream());
            final byte[] encodedBytes = bufferedInputStream.readAllBytes();
            final String receivedMessage = new String(encodedBytes);

            final String result = encryptionProcessor.process(receivedMessage);
            System.out.println(result);
        }
    }


}

