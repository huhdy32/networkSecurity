package server;

import cipher.CipherFactory;
import cipher.MyCipherHandler;
import protocol.Cipher;
import protocol.MessageProtocol;
import protocol.ProtocolMessage;

public class EncryptionProcessor {
    public String process(final String message) {
        final String[] splitedMessage = message.split("\n");
        String cypher = null;
        String key = null;
        String encryptedMessage = null;
        for (String temp : splitedMessage) {
            if (temp.startsWith(MessageProtocol.KEY.name())) {
                key = temp.split(" ")[1];
            }
            if (temp.startsWith(MessageProtocol.CIPHER_TYPE.name())) {
                 cypher = temp.split(" ")[1];
            }
            if (temp.startsWith(MessageProtocol.ENCRYPTED_MESSAGE.name())) {
                encryptedMessage = temp.split(" ")[1];
            }
        }
        if (key == null || cypher == null || encryptedMessage == null) {
            throw new IllegalArgumentException("잘못된 메시지" + message);
        }

        final MyCipherHandler myCipherHandler = getMyCipher(cypher, key);
        return myCipherHandler.decrypt(encryptedMessage);
    }


    private MyCipherHandler getMyCipher(final String protocolName, final String key) {
        final Cipher cipher = Cipher.getCipher(protocolName);
        final CipherFactory cipherFactory = new CipherFactory();

        if (cipher.equals(Cipher.AES)) {
            return new MyCipherHandler(cipherFactory.getAESCipher(key));
        }
        if (cipher.equals(Cipher.DES)) {
            return new MyCipherHandler(cipherFactory.getDESCipher(key));
        }
        if (cipher.equals(Cipher.TRIPLE_DES)) {
            return new MyCipherHandler(cipherFactory.getTripleDESCipher(key));
        }
        throw new IllegalArgumentException("지원하지 않는 Cipher Type");
    }
}
