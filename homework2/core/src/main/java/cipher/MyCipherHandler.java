package cipher;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.util.Base64;

public class MyCipherHandler {
    private final MyCipher cipher;
    private final Base64.Decoder decoder;
    private final Base64.Encoder encoder;

    public MyCipherHandler(final MyCipher myCipher) {
        cipher = myCipher;
        this.decoder = Base64.getDecoder();
        this.encoder = Base64.getEncoder();
    }

    public String encrypt(final String message) {
        try {
            return encoder.encodeToString(cipher.encrypt(message.getBytes()));
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        } catch (InvalidAlgorithmParameterException e) {
            throw new RuntimeException(e);
        }
    }

    public String decrypt(final String message) {
        try {
            return new String(cipher.decrypt(decoder.decode(message)));
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        } catch (InvalidAlgorithmParameterException e) {
            throw new RuntimeException(e);
        }
    }
}
