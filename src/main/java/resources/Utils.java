package resources;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {

    /**
     * Hashes the password improving the security of user data
     * @param password as a plain text
     * @return a hash value of a password
     */
    public static String hashPassword(String password) {

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());
            byte[] bytArray = md.digest();
            StringBuilder stringBuilder = new StringBuilder();

            for (byte b: bytArray) {
                stringBuilder.append(String.format("%02x", b));
            }

            return stringBuilder.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        
        return null;
    }

    /**
     * Compares the hashed password from the database, to a hash of a plaintext password
     * @param plainPassword password entered by the user
     * @param hashedPassword password from the database
     * @return true if password matches,  else false
     */
    public static boolean passwordMatcher(String plainPassword, String hashedPassword) {
        return hashPassword(plainPassword).equals(hashedPassword);
    }
}
