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
public class AddAccountDetails extends HttpServlet{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);
        try {
            java.text.SimpleDateFormat s = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            java.util.Date d = new java.util.Date();
            String RTime = s.format(d);
            int custID = Integer.parseInt(request.getParameter("custID"));
            String acc_no = request.getParameter("AccountNO");
            String name = request.getParameter("Name");
            String contact = request.getParameter("Contact");
            String mode = request.getParameter("ModeOfOperation");
            String email = request.getParameter("Mailid");
            String branch = request.getParameter("MainBranch");
            int balance = Integer.parseInt(request.getParameter("Balance"));
            DB_ACCESS dba = new DB_ACCESS();
            ResultSet rv = dba.Select("select * from bankusers where Mailid='" + email + "'");
            if (rv.next()) {
                session.setAttribute("HONEY_APP", "E-Mail Id Already Exists");
                response.sendRedirect("index.jsp");
            } else {
                int addAccount = dba.Insert("insert into bankusers(CustomerID,Account_Number,Name,Contact,ModeOfOperation,DateOfOpening,MainBranch,Mailid,Balance)values('"+custID+"','"+acc_no+"','"+name+"','"+contact+"','"+mode+"','"+RTime+"','"+branch+"','"+email+"','"+balance+"')");
                if (addAccount > 0) {
                    session.setAttribute("HONEY_APP", "Successfully Added");
                    response.sendRedirect("AddHoneypotAccount.jsp");
                } else {
                    session.setAttribute("HONEY_APP", "Error In Registration");
                    response.sendRedirect("AddHoneypotAccount.jsp");
                }
            }
        }
        catch(SQLException e)
        {
           out.println("SQL Exception :" + e.toString());
        }
        out.close();
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

