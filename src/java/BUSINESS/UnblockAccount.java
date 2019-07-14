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
public class UnblockAccount extends HttpServlet{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);
        try {
            String mailid = (String) session.getAttribute("email");
            DB_ACCESS dba = new DB_ACCESS();
            ResultSet rv = dba.Select("select * from blocked_accounts where Mailid='" + mailid + "'");
            if (rv.next()) {
                int Req = dba.Delete("delete from blocked_accounts where Mailid = '" + mailid + "'");
                int updatestatus= dba.Update("update userauthentication set Status='Active' where Mailid= '" + mailid + "'");
             
                if (Req > 0) {
                    session.setAttribute("HONEY_APP", "Account unblocked");
                    response.sendRedirect("AdminProfile.jsp");
                } else {
                    session.setAttribute("HONEY_APP", "Error");
                    response.sendRedirect("AdminProfile.jsp");
                }
            }
        }
        finally {
            out.close();
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

