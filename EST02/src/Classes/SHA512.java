package Classes;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mello
 */
public class SHA512 {
    public byte[] obtainSHA(String texto) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
        
        return messageDigest.digest(texto.getBytes(StandardCharsets.UTF_8));
    }
    
    public String toHexStr(byte[] hash){
        BigInteger num = new BigInteger(1, hash);
        String hexString = num.toString(16);
        
        return hexString;
    }
}
