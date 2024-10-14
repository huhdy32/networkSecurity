package cipher;

import javax.crypto.*;
import javax.crypto.spec.DESedeKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class My3DESCipher implements MyCipher {
    private final Cipher cipher;
    private final Key key;

    My3DESCipher(String key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException {

        final DESedeKeySpec desKeySpec = new DESedeKeySpec(key.getBytes());
        final SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
        this.cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        this.key = keyFactory.generateSecret(desKeySpec);
    }

    @Override
    public byte[] encrypt(final byte[] message) throws InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(message);
    }

    @Override
    public byte[] decrypt(final byte[] message) throws InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(message);
    }
}
