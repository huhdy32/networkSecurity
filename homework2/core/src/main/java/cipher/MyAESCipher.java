package cipher;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

class MyAESCipher implements MyCipher {
    private final Cipher cipher;
    private final SecretKeySpec key;
    private final IvParameterSpec iv;

    public MyAESCipher(String key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeySpecException {

        this.key = new SecretKeySpec(key.getBytes(), "AES");
        this.cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        this.iv = new IvParameterSpec(new byte[16]);
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
