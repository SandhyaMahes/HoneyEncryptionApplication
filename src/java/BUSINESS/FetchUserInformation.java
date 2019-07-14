/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BUSINESS;

import DATA.DB_ACCESS;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Sandhya
 */
public class FetchUserInformation extends HttpServlet {
    static String username;
    static String status;
    static int honeyindex;
    public void fetchinfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);
        String acc_no;
        String branch;
        int balance;
        String auth = (String) session.getAttribute("auth");
        System.out.println(auth);
        String password = (String) session.getAttribute("password");
        String mailid = (String) session.getAttribute("email");
        String username = (String) session.getAttribute("username");
        DB_ACCESS dba = new DB_ACCESS();
        try {
            ResultSet rv = dba.Select("select Status from userauthentication where Mailid='" + mailid + "'");
            if (rv.next()) {
                status=rv.getString("Status");
                
                if(status.equals("Inactive"))
                {
                    session.setAttribute("mailid",mailid);
                }
            }
             //Session Id Validation
            String honey_app = (String) session.getAttribute("HONEY_APP");
            if (honey_app != null) {       
                session.removeAttribute("HONEY-APP");
            }
            status=rv.getString("Status");
            if(status.equals("Activation Requested"))
            {
                session.setAttribute("username",username);
                response.sendRedirect("UserProfile.jsp");
            }
            else if(status.equals("Active"))
            {
                if(auth=="true")
                {
                    ResultSet rs=dba.Select("select * from bankusers where Mailid='" + mailid + "'");
                    if(rs.next()) {
                        username=rs.getString("Name");
                        
                        session.setAttribute("username",username);
                        session.setAttribute("acc_no",rs.getString("Account_Number"));
                        session.setAttribute("contact",rs.getString("Contact"));
                        session.setAttribute("mode",rs.getString("ModeOfOperation"));
                        session.setAttribute("dateofopen",rs.getString("DateOfOpening"));
                        session.setAttribute("branch",rs.getString("MainBranch"));
                        session.setAttribute("balance",rs.getInt("Balance"));
                        response.sendRedirect("UserProfile.jsp");
                        rs.close();
                    }
                }
                else if(auth=="false")
                {
                    ResultSet res=dba.Select("select Honeyindex from password_distribution where Passwordhash='" +password+"'");
                    if(res.next())
                        honeyindex=res.getInt("Honeyindex");
                    System.out.println(honeyindex);
                    ResultSet rs=dba.Select("select * from honeyusers where SNO='" + honeyindex + "'");
                    if(rs.next()) {
                        
                        session.setAttribute("username",username);
                        session.setAttribute("acc_no",rs.getString("Account_Number"));
                        session.setAttribute("mode",rs.getString("ModeOfOperation"));
                        session.setAttribute("branch",rs.getString("MainBranch"));
                         session.setAttribute("contact",rs.getString("Contact"));
                        session.setAttribute("dateofopen",rs.getString("DateOfOpening"));
                        session.setAttribute("balance",rs.getInt("Balance"));
                        response.sendRedirect("UserProfile.jsp");
                        rs.close();
                    }
                }
            }
        }
    
    catch(SQLException e)
        {
           System.out.println("SQL Exception :" + e.toString());
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        try {
            fetchinfo(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CheckLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            fetchinfo(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CheckLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}


