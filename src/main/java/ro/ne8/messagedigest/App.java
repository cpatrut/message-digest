package ro.ne8.messagedigest;


import ro.ne8.messagedigest.hash.HMACCalculator;
import ro.ne8.messagedigest.hash.HashCalculator;

import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.security.InvalidKeyException;

public class App {

    public static void main(final String[] args) throws IOException, InvalidKeyException {
        final HashCalculator hashCalculator = new HashCalculator();
        final byte[] hashValue = hashCalculator.generateHashValueForFile("file.txt", "SHA1");

        System.out.println(DatatypeConverter.printHexBinary(hashValue));

        final HMACCalculator hmacCalculator = new HMACCalculator();
        final byte[] hmac = hmacCalculator.generateHMACValueForFile("file.txt", "HmacSHA1", "secret2");
        System.out.println(DatatypeConverter.printHexBinary(hmac));

    }
}
