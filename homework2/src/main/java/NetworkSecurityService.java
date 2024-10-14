import cipher.CipherFactory;
import cipher.MyCipherHandler;
import test.TestRunner;

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
        testRunner.runTests(networkSecurityService::runDES);
        testRunner.runTests(networkSecurityService::run3DES);
        testRunner.runTests(networkSecurityService::runAES);
    }

    private void run3DES(final String plainMessage) {
        final String tripleDESkey = "qwjndqowjdnqekqokdeqwjndqowjdnqekqokdeqwjndqowjdnqekqokde";
        final MyCipherHandler myCipherHandler = new MyCipherHandler(cipherFactory.getTripleDESCipher(tripleDESkey));

        final String message = plainMessage;
        final String encryptedMessage = myCipherHandler.encrypt(message);
        final String decryptedMessage = myCipherHandler.decrypt(encryptedMessage);

        cipherResultPrinter.printResult("3DES", message, encryptedMessage, decryptedMessage);
    }

    private void runDES(final String plainMessage) {
        final String key = "qwjndqowjdnqekqokde";
        final MyCipherHandler myCipherHandler = new MyCipherHandler(cipherFactory.getDESCipher(key));

        final String message = plainMessage;
        final String encryptedMessage = myCipherHandler.encrypt(message);
        final String decryptedMessage = myCipherHandler.decrypt(encryptedMessage);

        cipherResultPrinter.printResult("DES", message, encryptedMessage, decryptedMessage);
    }

    private void runAES(final String plainMessage) {
        final String key = "aeskey1234567898";
        final MyCipherHandler myCipherHandler = new MyCipherHandler(cipherFactory.getAESCipher(key));

        final String message = plainMessage;
        final String encryptedMessage = myCipherHandler.encrypt(message);
        final String decryptedMessage = myCipherHandler.decrypt(encryptedMessage);

        cipherResultPrinter.printResult("AES", message, encryptedMessage, decryptedMessage);
    }
}
