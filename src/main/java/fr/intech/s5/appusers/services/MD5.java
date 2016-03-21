package fr.intech.s5.appusers.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import fr.intech.s5.appusers.models.Model;

/**
 * 
 * @author Joris
 *
 */
public class MD5 {
    private static MessageDigest digester;

    //Contructeur pour eviter la fatigue de Sonar!!
    private MD5()
    {
    	//Rien a mettre
    }
    
    static {
        try {
            digester = MessageDigest.getInstance("MD5");
        }
        catch (NoSuchAlgorithmException e) {
            Model.printErr(e);
        }
    }

    /**
     * Fonction pour crypter un mot de passe en MD5
     * @param str
     * @return
     */
    public static String crypt(String str) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("String to encript cannot be null or zero length");
        }

        digester.update(str.getBytes());
        byte[] hash = digester.digest();
        StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < hash.length; i++) {
            if ((0xff & hash[i]) < 0x10) {
                hexString.append("0" + Integer.toHexString(0xFF & hash[i]));
            }
            else {
                hexString.append(Integer.toHexString(0xFF & hash[i]));
            }
        }
        return hexString.toString();
    }
}
