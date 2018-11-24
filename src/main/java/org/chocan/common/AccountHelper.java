package org.chocan.common;

import java.security.MessageDigest;

public class AccountHelper {


    public static final String KEY = "!@#$%^&*()_+=-{}][;\";/?<>.,";


    /**
     * Cipher the given ASCII byte[] using SHA-512 algorithm
     *
     * @param toHash string to convert
     * @return the hashed string the given string represents
     */

    public static String generateHash(String toHash) {
        byte[] hash = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            hash = md.digest(toHash.getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return convertToHex(hash);
    }

    /**
     * Converts the given byte[] to a hex string.
     *
     * @param raw the byte[] to convert
     * @return the string the given byte[] represents
     */
    private static String convertToHex(byte[] raw) {
        StringBuilder sb = new StringBuilder();
        for (byte aRaw : raw) {
            sb.append(Integer.toString((aRaw & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

}
