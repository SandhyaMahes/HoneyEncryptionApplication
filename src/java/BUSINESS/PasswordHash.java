/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BUSINESS;
import DATA.DB_ACCESS;
import java.math.BigInteger; 
import java.security.MessageDigest; 
import java.security.NoSuchAlgorithmException; 
import java.sql.ResultSet;
import java.util.Random;

/**
 *
 * @author Sandhya
 */
public class PasswordHash {
    public String getSHA(String input) 
    { 
        try { 
            // Static getInstance method is called with hashing SHA 
            MessageDigest md = MessageDigest.getInstance("SHA-256"); 
            // digest() method called 
            // to calculate message digest of an input 
            // and return array of byte 
            byte[] messageDigest = md.digest(input.getBytes());  
            // Convert byte array into signum representation 
            BigInteger no = new BigInteger(1, messageDigest);  
            // Convert message digest into hex value 
            String hashtext = no.toString(16); 
            while (hashtext.length() < 32) { 
                hashtext = "0" + hashtext; 
            } 
            return hashtext;
        } 
        // For specifying wrong message digest algorithms 
        catch (NoSuchAlgorithmException e) { 
            System.out.println("Exception thrown"+ " for incorrect algorithm: " + e); 
            return null;
        } 
    } 
    public void hashPassword(String username,String email,String pwd1,String pwd2,String pwd3,String pwd4,String pwd5,String pwd6){
        String hash1=getSHA(pwd1);
        String hash2=getSHA(pwd2);
        String hash3=getSHA(pwd3);
        String hash4=getSHA(pwd4);
        String hash5=getSHA(pwd5);
        String hash6=getSHA(pwd6);
        DB_ACCESS dba = new DB_ACCESS();
        Random rand = new Random();
        String status="Inactive";
        int Register = dba.Insert("insert into user_details(Username,Mailid,pwd1,pwd2,pwd3,pwd4,pwd5,pwd6,Status) values('"+username+"','"+email+"','"+hash1+"','"+hash2+"','"+hash3+"','"+hash4+"','"+hash5+"','"+hash6+"','"+status+"')");
        
    }
}
