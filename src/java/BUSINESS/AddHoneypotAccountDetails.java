/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUSINESS;

import DATA.DB_ACCESS;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
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
public class AddHoneypotAccountDetails extends HttpServlet{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);
       
            int custID = Integer.parseInt(request.getParameter("custID"));
            String acc_no = request.getParameter("AccountNO");
            String contact = request.getParameter("Contact");
            String mode = request.getParameter("ModeOfOperation");
            String branch = request.getParameter("MainBranch");
            String RTime=request.getParameter("DateOfOpening");
            int balance = Integer.parseInt(request.getParameter("Balance"));
            DB_ACCESS dba = new DB_ACCESS();
                int addAccount = dba.Insert("insert into honeyusers(CustomerID,Account_Number,Contact,ModeOfOperation,DateOfOpening,MainBranch,Balance)values('"+custID+"','"+acc_no+"','"+contact+"','"+mode+"','"+RTime+"','"+branch+"','"+balance+"')");
                if (addAccount > 0) {
                    session.setAttribute("HONEY_APP", "Successfully Added");
                    response.sendRedirect("AddHoneypotAccount.jsp");
                } else {
                    session.setAttribute("HONEY_APP", "Error In Registration");
                    response.sendRedirect(".jsp");
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

