package server;

import cipher.MyCipherHandler;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final EncryptionProcessor encryptionProcessor;

    public Server(EncryptionProcessor cipherMapper) {
        this.encryptionProcessor = cipherMapper;
    }

    public void init(final int port) throws IOException {
        ServerSocket socket = new ServerSocket(port);
        System.out.println("서버 포트 : " + port);
        while (true) {
            final Socket receivedSocket = socket.accept();
            final BufferedInputStream bufferedInputStream = new BufferedInputStream(receivedSocket.getInputStream());
            final byte[] encodedBytes = bufferedInputStream.readAllBytes();
            final String receivedMessage = new String(encodedBytes);

            System.out.println("======= RECEIVED MESSAGE =======");
            System.out.println(receivedMessage);

            final String result = encryptionProcessor.process(receivedMessage);
            System.out.println("RESULT = " + result);
        }
    }


}

