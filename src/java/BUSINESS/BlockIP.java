/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUSINESS;

import DATA.DB_ACCESS;
import java.io.IOException;
import java.io.PrintWriter;
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
public class BlockIP extends HttpServlet{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);
        int blocked=0;
        String mailid = (String) session.getAttribute("email");
        String time = (String) session.getAttribute("time");
        String IP = (String) session.getAttribute("IP");
        String Status="Blocked";
        String accessor="Hacker";
        DB_ACCESS dba = new DB_ACCESS();
        blocked = dba.Insert("insert into blocked_accounts(Mailid,blocked_time,Accessor,IPAddress,Status) values('"+mailid+"','"+time+"','"+accessor+"','"+IP+"','"+Status+"')");
        if (blocked > 0) {
                    session.setAttribute("HONEY_APP", "IP Blocked");
                    response.sendRedirect("AdminProfile.jsp");
                } else {
                    session.setAttribute("HONEY_APP", "Error In Blocking");
                    response.sendRedirect("AdminProfile.jsp");
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

}
