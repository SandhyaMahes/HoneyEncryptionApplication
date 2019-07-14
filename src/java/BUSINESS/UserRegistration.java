/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BUSINESS;

/**
 *
 * @author Sandhya
 */
import DATA.DB_ACCESS;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Random;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class UserRegistration extends HttpServlet{
    static int honeycount;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);
   //     try {
            java.text.SimpleDateFormat s = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            java.util.Date d = new java.util.Date();
            String RTime = s.format(d);
            String ipAddress = request.getRemoteAddr();
            System.out.println(ipAddress);
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            DB_ACCESS dba = new DB_ACCESS();
            ResultSet rv = dba.Select("select * from userauthentication where Mailid='" + email + "'");
            Random rand = new Random();
                String userPass = password;
                String pwd1,pwd2,pwd3,pwd4,pwd5,pwd6;   
                int honeyindex=0;
                ArrayList<String> passwordSet = new ArrayList<String>();
                passwordSet.add(userPass); //true password
		//honeyword1 by combining the original password with the random seed - 1
		passwordSet.add(userPass.substring(0,userPass.length()-3) + String.valueOf(rand.nextInt(50)-1)); 
		//honeyword2 by combining the original password with the random seed - 2 and then append 1
		passwordSet.add(userPass.substring(0,userPass.length()-5) + String.valueOf(rand.nextInt(50)-2) + "1"); 
		//honeyword3 by make it lower case
		passwordSet.add(userPass.toLowerCase()+String.valueOf(rand.nextInt(50))); 
		//honeyword4 by make it lower case and add the random seed + 1 and append 3
		passwordSet.add(userPass.toLowerCase() + String.valueOf(rand.nextInt(50) + 1) + String.valueOf(rand.nextInt(50)) ); 
		//honeyword5 by make it upper case
		passwordSet.add(userPass.substring(0, 1).toUpperCase()+userPass.substring(1,userPass.length()-1).toLowerCase()+String.valueOf(rand.nextInt(50))); 
		pwd1=passwordSet.get(rand.nextInt(passwordSet.size()));
                passwordSet.remove(pwd1);
                if(pwd1.equals(userPass))
                    honeyindex=1;
                pwd2=passwordSet.get(rand.nextInt(passwordSet.size()));
                passwordSet.remove(pwd2);
                if(pwd2.equals(userPass))
                    honeyindex=2;
                pwd3=passwordSet.get(rand.nextInt(passwordSet.size()));
                passwordSet.remove(pwd3);
                if(pwd3.equals(userPass))
                    honeyindex=3;
                pwd4=passwordSet.get(rand.nextInt(passwordSet.size()));
                passwordSet.remove(pwd4);
                if(pwd4.equals(userPass))
                    honeyindex=4;
                pwd5=passwordSet.get(rand.nextInt(passwordSet.size()));
                passwordSet.remove(pwd5);
                if(pwd5.equals(userPass))
                    honeyindex=5;
                pwd6=passwordSet.get(rand.nextInt(passwordSet.size()));
                if(pwd6.equals(userPass))
                    honeyindex=6;	
                int Register = dba.Insert("insert into plaintext_table(Username,Mailid,pwd1,pwd2,pwd3,pwd4,pwd5,pwd6) values('"+username+"','"+email+"','"+pwd1+"','"+pwd2+"','"+pwd3+"','"+pwd4+"','"+pwd5+"','"+pwd6+"')");
                int honeycheck=dba.Insert("insert into honeychecker(Mailid,pwd_index) values('"+email+"','"+honeyindex+"')");
                if (Register > 0 && honeycheck>0) {
                    session.setAttribute("email",email);
                    session.setAttribute("username",username);
                    session.setAttribute("HONEY_APP", "Successfully Register");
                    response.sendRedirect("UserProfile.jsp");
                } else {
                    session.setAttribute("HONEY_APP", "Error In Registration");
                    response.sendRedirect("index.jsp");
                }
                hashPassword(username,email,pwd1,pwd2,pwd3,pwd4,pwd5,pwd6);
        out.close();
    }
    
    public String getSHA(String input, int count,int index, String username,String email) 
    { 
        Random rand = new Random();
        DB_ACCESS dba = new DB_ACCESS();
        int uniqueSeed=0;
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
            
            while(uniqueSeed==0)
            {
                int seedValue = rand.nextInt(999999999);
                ResultSet rs = dba.Select("select * from password_distribution where Seedvalue='" + seedValue + "'");
                if (rs.next()) {
                    uniqueSeed=0;
                }
                else
                {
                    uniqueSeed=1;
                    rs=dba.Select("select count(*) AS count from honeyusers ");
                    if (rs.next()) {
                        honeycount=rs.getInt("count");
                        
                    }
                    int honeyindex=rand.nextInt(honeycount);
                        
                    int insert = dba.Insert("insert into password_distribution(Passwordhash,Seedvalue,Honeyindex) values('"+hashtext+"','"+seedValue+"','"+honeyindex+"')");
                    if(count == index)
                    {
                        int trueSeedFlag = 0;
                        while(trueSeedFlag == 0)
                        {
                            int trueSeed = rand.nextInt(999999999);
                            ResultSet rv = dba.Select("select * from honeycheckertable where Trueseed='" + trueSeed + "'");
                            if (rs.next()) {
                                trueSeedFlag = 0;
                            }
                            else
                            {
                                trueSeedFlag=1;
                                int insertTrueSeed = dba.Insert("insert into honeycheckertable(Mailid,Trueseed) values('"+email+"','"+trueSeed+"')");
                    
                            }  
                            int cipherValue = trueSeed ^ seedValue;
                            int insertCipher = dba.Insert("insert into userauthentication(Username,Mailid,Pwd_cipher,Status) values('"+username+"','"+email+"','"+cipherValue+"','Inactive')");
                        }
                    }
                }
            }
            return hashtext;
        }
        catch(SQLException e)
        {
           System.out.println("SQL Exception :" + e.toString());
           return null;
        }
         // For specifying wrong message digest algorithms 
        catch (NoSuchAlgorithmException e) { 
            System.out.println("Exception thrown"+ " for incorrect algorithm: " + e); 
            return null;
        }
    }
    
    public void hashPassword(String username,String email,String pwd1,String pwd2,String pwd3,String pwd4,String pwd5,String pwd6){
        try {
            int index=0;
            int count=1;
            DB_ACCESS dba = new DB_ACCESS();
            ResultSet rv = dba.Select("select pwd_index from honeychecker where Mailid='" + email + "'");
            if (rv.next()) {
                index=rv.getInt("pwd_index");
            }
            String hash1=getSHA(pwd1,count,index,username, email);
            count++;
            String hash2=getSHA(pwd2,count,index,username, email);
            count++;
            String hash3=getSHA(pwd3,count,index, username,email);
            count++;
            String hash4=getSHA(pwd4,count,index,username, email);
            count++;
            String hash5=getSHA(pwd5,count,index,username, email);
            count++;
            String hash6=getSHA(pwd6,count,index,username, email);           
            
            int Register = dba.Insert("insert into user_details(Username,Mailid,pwd1,pwd2,pwd3,pwd4,pwd5,pwd6) values('"+username+"','"+email+"','"+hash1+"','"+hash2+"','"+hash3+"','"+hash4+"','"+hash5+"','"+hash6+"')");
        }
        catch(SQLException e)
        {
           System.out.println("SQL Exception :" + e.toString());
        } 
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(UserRegistration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(UserRegistration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
