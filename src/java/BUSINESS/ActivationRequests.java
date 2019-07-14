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
public class ActivationRequests extends HttpServlet{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);
        try {
            String mailid = (String) session.getAttribute("email");
            DB_ACCESS dba = new DB_ACCESS();
            ResultSet rv = dba.Select("select * from userauthentication where Mailid='" + mailid + "'");
            if (rv.next()) {
                String status=rv.getString("Status");
                if(status=="Activated")
                { 
                    session.setAttribute("HONEY_APP", "Account already active");
                    response.sendRedirect("index.jsp");
                } 
                else {
                int Req = dba.Update("update userauthentication set Status = 'Activation Requested' where Mailid = '" + mailid + "'");
                if (Req > 0) {
                    session.setAttribute("HONEY_APP", "Request Sent");
                    response.sendRedirect("UserProfile.jsp");
                } else {
                    session.setAttribute("HONEY_APP", "Error In Request");
                    response.sendRedirect("UserProfile.jsp");
                }
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
