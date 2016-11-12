package com.securer.nemboru.proto1;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by nemboru on 12/11/16.
 */

public class AEScrypt {
    public static Cipher cipher;

    public static byte[] encrypt(byte[] key, byte[] payload) {
        return work(key, payload, true);
    }

    public static byte[] decrypt(byte[] key, byte[] payload){
        return work(key, payload, false);
    }

    public static byte[] work(byte[] key, byte[] payload,boolean encrypt){
        Security.addProvider(new BouncyCastleProvider());

        try {
            cipher = Cipher.getInstance("AES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
        Key parsedKey = new SecretKeySpec(key,"AES");
        try {
            if(encrypt){
                cipher.init(Cipher.ENCRYPT_MODE, parsedKey);
            }else{
                cipher.init(Cipher.DECRYPT_MODE, parsedKey);
            }
            return cipher.doFinal(payload);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }
}