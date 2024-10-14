import cipher.CipherFactory;
import cipher.MyCipherHandler;
import cipher.rsa.MyRSAKeyPairGenerator;
import test.TestRunner;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;

public class NetworkSecurityService {
    private final CipherResultPrinter cipherResultPrinter;
    private final CipherFactory cipherFactory;

    public NetworkSecurityService(final CipherResultPrinter cipherResultPrinter, final CipherFactory cipherFactory) {
        this.cipherResultPrinter = cipherResultPrinter;
        this.cipherFactory = cipherFactory;
    }

    public static void main(String[] args) {
        final CipherResultPrinter cipherResultPrinter = new CipherResultConsolePrinter();
        final CipherFactory cipherFactory = new CipherFactory();

        final NetworkSecurityService networkSecurityService = new NetworkSecurityService(cipherResultPrinter, cipherFactory);
        final TestRunner testRunner = new TestRunner();
        testRunner.runTests(networkSecurityService::runEncryptWithRSA);
        testRunner.runTests(networkSecurityService::runSignWithRSA);
    }

    private void runEncryptWithRSA(final String plainMessage) {
        try {
            final KeyPair keyPair = new MyRSAKeyPairGenerator().generateKeyPair();
            final MyCipherHandler myRSAPublicHandler = new MyCipherHandler(cipherFactory.getRSACipher(keyPair.getPublic()));
            final MyCipherHandler myRSAPrivateHandler = new MyCipherHandler(cipherFactory.getRSACipher(keyPair.getPrivate()));

            final String message= plainMessage;
            final String encryptedMessage= myRSAPublicHandler.encrypt(message);
            final String decryptedMessage = myRSAPrivateHandler.decrypt(encryptedMessage);

            cipherResultPrinter.printResult("RSA Encrypt", message, encryptedMessage, decryptedMessage);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    private void runSignWithRSA(final String plainMessage) {
        try {
            final KeyPair keyPair = new MyRSAKeyPairGenerator().generateKeyPair();
            final MyCipherHandler myRSAPublicHandler = new MyCipherHandler(cipherFactory.getRSACipher(keyPair.getPublic()));
            final MyCipherHandler myRSAPrivateHandler = new MyCipherHandler(cipherFactory.getRSACipher(keyPair.getPrivate()));

            final String message= plainMessage;
            final String encryptedMessage= myRSAPrivateHandler.encrypt(message);
            final String decryptedMessage = myRSAPublicHandler.decrypt(encryptedMessage);

            cipherResultPrinter.printResult("RSA SIGN", message, encryptedMessage, decryptedMessage);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
