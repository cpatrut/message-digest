package ro.ne8.messagedigest.hash;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashCalculator {

    public byte[] generateHashValueForFile(final String fileName, final String algorithm) throws IOException {
        final File file = new File(fileName);
        if (!file.exists()) {
            throw new IOException("File does not exists");
        }
        final FileInputStream fileInputStream = new FileInputStream(file);

        final MessageDigest messageDigest = tryToGetMessageDigest(algorithm);
        final byte[] inputByte = new byte[1];
        int noBytes;
        while ((noBytes = fileInputStream.read(inputByte)) != -1) {
            messageDigest.update(inputByte);
        }
        fileInputStream.close();
        return messageDigest.digest();
    }

    private MessageDigest tryToGetMessageDigest(final String algorithm) {
        try {
            return MessageDigest.getInstance(algorithm);
        } catch (final NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
