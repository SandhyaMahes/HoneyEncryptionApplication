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
public class CheckAdmin extends HttpServlet{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);
        try {            
            String adminid = request.getParameter("adminid");
            String password=request.getParameter("password");
            PasswordHash pwd=new PasswordHash();
            String Password = pwd.getSHA(password);
            DB_ACCESS dba = new DB_ACCESS();
            ResultSet rv = dba.Select("select password from admin where adminid='" + adminid + "'");
            if (rv.next()) {
                String pass =rv.getString("password");
                if(password.equals(pass))
                {
                    session.setAttribute("HONEY_APP", "Successful L_O_G_I_N");
                    response.sendRedirect("AdminProfile.jsp");
                }
                else
                {
                    session.setAttribute("HONEY_APP", "Admin login failed");
                    response.sendRedirect("Administrator.jsp");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
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
