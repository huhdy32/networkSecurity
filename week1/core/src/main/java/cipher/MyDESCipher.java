package cipher;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

class MyDESCipher implements MyCipher{
    private final Cipher cipher;
    private final Key key;
    private final IvParameterSpec iv;

    MyDESCipher(final String key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException {
        final String algorithm = "DES";
        this.cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");

        final SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(algorithm);
        final KeySpec keySpec = new DESKeySpec(key.getBytes());
        this.key = secretKeyFactory.generateSecret(keySpec);
        this.iv = new IvParameterSpec(new byte[8]);
    }

    @Override
    public byte[] encrypt(final byte[] message) throws InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);
        return cipher.doFinal(message);
    }
    @Override
    public byte[] decrypt(final byte[] message) throws InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        cipher.init(Cipher.DECRYPT_MODE, key, iv);
        return cipher.doFinal(message);
    }
}
