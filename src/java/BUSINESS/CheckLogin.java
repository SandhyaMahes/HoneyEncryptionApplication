/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BUSINESS;
import DATA.DB_ACCESS;
import java.util.*;
import java.sql.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Sandhya
 */
public class CheckLogin extends HttpServlet {
    static  private String to;
        static int loginCount=0;
        private static final String from=" "; //specify FROM mail address
        static   private String mesg;
        static private String subject;
        static String accessor;
        private static final String smtpServ="smtp.gmail.com";
        int blocked=0;
        static private String username;
        protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);
        String ipAddress = request.getRemoteAddr();
        if(loginCount>=3)
        {
            java.text.SimpleDateFormat s = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            java.util.Date d = new java.util.Date();
            String RTime = s.format(d);
            String EMAIL = request.getParameter("email_l");
            String Status="Blocked";
            DB_ACCESS dba = new DB_ACCESS();
            String ip = request.getHeader("x-forwarded-for"); 
            if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
                ip = request.getHeader("Proxy-Client-IP"); 
            } 
            if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
                ip = request.getHeader("WL-Proxy-Client-IP"); 
            } 
            if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
                ip = request.getRemoteAddr(); 
            } 
            blocked = dba.Insert("insert into hackeralerts(Mailid,IPAddress,Time,Status) values('"+EMAIL+"','"+ip+"','"+RTime+"','"+Status+"')");
             int updatestatus= dba.Update("update userauthentication set Status='Hacked' where Mailid= '" + EMAIL + "'");
              mesg="Your account is temporarily blocked due to 3 unsuccessful login attempts.";
                        subject="Security Alert";
                        try
                        {
                            Properties props = System.getProperties();
                            // -- Attaching to default Session, or we could start a new one --
                            props.put("mail.transport.protocol", "smtp" );
                            props.put("mail.smtp.starttls.enable","true" );
                            props.put("mail.smtp.host", smtpServ);
                            props.put("mail.smtp.auth", "true" );
                            props.put("mail.smtp.port", "587");
                            Authenticator auth = new CheckLogin.SMTPAuthenticator();
                            Session session1 = Session.getInstance(props, auth);
                            // -- Create a new message --
                            Message msg = new MimeMessage(session1);
                            // -- Set the FROM and TO fields --
                            msg.setFrom(new InternetAddress(from));
                            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
                            msg.setSubject(subject);
                            msg.setText(mesg);
                            // -- Set some other header information --
                            msg.setHeader("MyMail", "Mr. XYZ" );
                            msg.setSentDate(new java.util.Date());
                            // -- Send the message --
                            Transport.send(msg);
                            response.sendRedirect("BlockedAccount.jsp");
                        }
                        catch (Exception ex)
                        {
                        out.println("Exception "+ex);
                       } 
        }
        else
        { try {            
            String EMAIL = request.getParameter("email_l");
            to=EMAIL;
            String UP_ASS = request.getParameter("password_l");           
            String password=getSHA(UP_ASS);            
            DB_ACCESS dba = new DB_ACCESS();
            ResultSet r=dba.Select("select * from blocked_accounts where Mailid='" + EMAIL + "'"); 
            if(r.next())
            {
                r.close();
                response.sendRedirect("BlockedAccount.jsp");
            }
            else
            {
                String authuser = "true";
                loginCount=0;
                int trueSeed = 0;
                int seedValue = 0;
                int cipherValue = 0;
                int decryptValue = 0;
                ResultSet rv = dba.Select("select Trueseed from honeycheckertable where Mailid='" + EMAIL + "'");
                if(rv.next())
                    trueSeed = rv.getInt("Trueseed");
                rv.close();
                ResultSet res = dba.Select("select Seedvalue from password_distribution where Passwordhash='" + password + "'");   
                if(res.next())
                        seedValue = res.getInt("Seedvalue");
                res.close();
                ResultSet rs = dba.Select("select * from userauthentication where Mailid='" + EMAIL + "'");
                if(rs.next())
                {
                    cipherValue = rs.getInt("Pwd_cipher");
                    username = rs.getString("Username");
                }
                System.out.println(username);
                rs.close();
                if (trueSeed>0 && seedValue>0 && cipherValue>0) {
                    decryptValue = cipherValue ^ seedValue;
                    session.setAttribute("username", username);
                    session.setAttribute("email", EMAIL);
                    if(decryptValue == trueSeed)
                    { 
                        session.setAttribute("password", password);
                        session.setAttribute("auth",authuser);
                        session.setAttribute("HONEY_APP", "Successful L_O_G_I_N");
                        System.out.println("Session");
                        //RequestDispatcher rd=request.getRequestDispatcher("/FetchUserInformation");  
                        //rd.forward(request, response);
                        response.sendRedirect("FetchUserInformation");
                    }
                    else
                    {
                        java.text.SimpleDateFormat s = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                        java.util.Date d = new java.util.Date();
                        String RTime = s.format(d);
                        String Status="Block IP";
                        accessor = "Hacker";
                        String ip = request.getHeader("x-forwarded-for"); 
                        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
                            ip = request.getHeader("Proxy-Client-IP"); 
                        } 
                        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
                            ip = request.getHeader("WL-Proxy-Client-IP"); 
                        } 
                        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
                            ip = request.getRemoteAddr(); 
                        } 
                        System.out.println("ip"+ip);
                        blocked = dba.Insert("insert into hackeralerts(Mailid,IPAddress,Time,Status) values('"+EMAIL+"','"+ip+"','"+RTime+"','"+Status+"')");
                        loginCount++;
                        authuser="false";
                        mesg="Someone just used a honey word to sign in to your account. Your password is under risk. Please change your password as soon as possible.";
                        subject="Security Alert";
                        try
                        {
                            Properties props = System.getProperties();
                            // -- Attaching to default Session, or we could start a new one --
                            props.put("mail.transport.protocol", "smtp" );
                            props.put("mail.smtp.starttls.enable","true" );
                            props.put("mail.smtp.host", smtpServ);
                            props.put("mail.smtp.auth", "true" );
                            props.put("mail.smtp.port", "587");
                            Authenticator auth = new CheckLogin.SMTPAuthenticator();
                            Session session1 = Session.getInstance(props, auth);
                            // -- Create a new message --
                            Message msg = new MimeMessage(session1);
                            // -- Set the FROM and TO fields --
                            msg.setFrom(new InternetAddress(from));
                            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
                            msg.setSubject(subject);
                            msg.setText(mesg);
                            // -- Set some other header information --
                            msg.setHeader("MyMail", "Mr. XYZ" );
                            msg.setSentDate(new java.util.Date());
                            // -- Send the message --
                            Transport.send(msg);
                            session.setAttribute("password", password);
                            session.setAttribute("auth",authuser);
                            session.setAttribute("username", username);
                            session.setAttribute("HONEY_APP", "Successful L_O_G_I_N");
                            response.sendRedirect("FetchUserInformation");
                        }
                        catch (Exception ex)
                        {
                        out.println("Exception "+ex);
                       } 
                    }
                }
                else
                {
                    accessor="User";
                    loginCount++;
                    session.setAttribute("HONEY_APP", "Wrong Email or Password");
                    response.sendRedirect("index.jsp");
                }
            }
        }  
        catch(SQLException e)
        {
           out.println("SQL Exception :" + e.toString());
        }
       
        finally {
            out.close();
        }
        }
    }
        
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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CheckLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CheckLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private static class SMTPAuthenticator extends Authenticator {
        @Override
        public PasswordAuthentication getPasswordAuthentication() {
            String username =  " ";           // specify your email id here (sender's email id)
            String password = " ";            // specify your password here
            return new PasswordAuthentication(username, password);
        }
    }

}
