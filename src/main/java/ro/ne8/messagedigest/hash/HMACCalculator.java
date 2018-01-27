package ro.ne8.messagedigest.hash;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class HMACCalculator {

    public byte[] generateHMACValueForFile(final String fileName, final String algorithm, final String secret) throws IOException, InvalidKeyException {
        final Mac mac = tryToGetMacInstance(algorithm);

        final Key key = new SecretKeySpec(secret.getBytes(), algorithm);
        mac.init(key);
        final FileInputStream fileInputStream = tryToGetFileInputStream(fileName);

        final byte[] bufferInput = new byte[1];
        int noBytes;
        while ((noBytes = fileInputStream.read(bufferInput)) != -1) {
            mac.update(bufferInput);
        }
        fileInputStream.close();
        return mac.doFinal();
    }


    private FileInputStream tryToGetFileInputStream(final String fileName) {
        try {
            return new FileInputStream(fileName);
        } catch (final FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Mac tryToGetMacInstance(final String algorithm) {
        try {
            return Mac.getInstance(algorithm);
        } catch (final NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
