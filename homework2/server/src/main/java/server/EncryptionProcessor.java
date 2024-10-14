package server;

import cipher.CipherFactory;
import cipher.MyCipherHandler;
import cipher.rsa.MyRSAKeyPairGenerator;
import protocol.Cipher;
import protocol.MessageProtocol;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Objects;

public class EncryptionProcessor {
    public KeyPair keyPair;

    public String process(final String message, final BufferedOutputStream bufferedOutputStream) {
        String encryptedMessage = null;
        if (message.startsWith(MessageProtocol.REQUIRE_RSA_PUBLIC_KEY.name())) {
            if (Objects.isNull(keyPair)) {
                try {
                    this.keyPair = new MyRSAKeyPairGenerator().generateKeyPair();
                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException(e);
                }
                try {
                    bufferedOutputStream.write(Base64.getEncoder().encode(keyPair.getPublic().getEncoded()));
                    bufferedOutputStream.write("\n".getBytes());
                    bufferedOutputStream.flush();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("RESULT : PUBLIC KEY SENDED");
            return null;
        }

        if (message.startsWith(MessageProtocol.ENCRYPTED_MESSAGE.name())) {
            encryptedMessage = message.split(" ")[1];
            final MyCipherHandler myCipherHandler = getMyCipher("RSA");
            return myCipherHandler.decrypt(encryptedMessage);
        }
        return null;
    }


    private MyCipherHandler getMyCipher(final String protocolName) {
        final Cipher cipher = Cipher.getCipher(protocolName);
        final CipherFactory cipherFactory = new CipherFactory();
        if (cipher.equals(Cipher.RSA)) {
            return new MyCipherHandler(cipherFactory.getRSACipher(keyPair.getPrivate()));
        }
        throw new IllegalArgumentException("지원하지 않는 Cipher Type");
    }
}
