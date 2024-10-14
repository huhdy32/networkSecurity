package network;

import cipher.MyCipherHandler;
import cipher.MyRSACipher;
import protocol.Cipher;
import protocol.MessageProtocol;
import protocol.ProtocolMessage;

import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class Client {

    private MyCipherHandler myCipherHandler;
    private final Connection connection;
    private final Cipher cipher;
    private PublicKey publicKey;

    public Client(final int targetPort, final Cipher cipher) throws IOException {

        this.connection = new Connection(targetPort);
        this.cipher = cipher;
    }

    public void initWithServer() throws IOException, InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException {
        final ProtocolMessage protocolMessage = new ProtocolMessage(MessageProtocol.REQUIRE_RSA_PUBLIC_KEY, "");
        connection.sendMessage(protocolMessage);
        final byte[] keyBytes = Base64.getDecoder().decode(connection.readLine().getBytes());
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        publicKey = keyFactory.generatePublic(spec);
        myCipherHandler = new MyCipherHandler(new MyRSACipher(publicKey));
    }

    public void sendProtocolAndKey() throws IOException {
        final ProtocolMessage protocolMessage = new ProtocolMessage(MessageProtocol.CIPHER_TYPE, cipher.name());
        connection.sendMessage(protocolMessage);
    }

    public void sendMessage(final String message) throws IOException {
        final String encryptedMessage = myCipherHandler.encrypt(message);
        final ProtocolMessage protocolMessage = new ProtocolMessage(MessageProtocol.ENCRYPTED_MESSAGE, encryptedMessage);
        connection.sendMessage(protocolMessage);
    }
}
