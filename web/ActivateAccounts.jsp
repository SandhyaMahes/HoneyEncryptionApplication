<%-- 
    Document   : ActivateAccounts
    Created on : 2 Mar, 2019, 3:50:35 PM
    Author     : Sandhya
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="DATA.DB_ACCESS"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="assets/css/main.css" />
        <title>Admin</title> 
    </head>
    <body>
          <!-- Wrapper -->
        <div id="wrapper">

            <!-- Header -->
            <header id="header">
                <h1><a href="#">Banking</a></h1>
                <nav class="links">
                    <ul>
                        <li><a href="AdminProfile.jsp">ADD ACCOUNTS</a></li>
                        <li><a href="#">ACTIVATION REQUESTS</a></li>
                        <li><a href="BlockedAccounts.jsp">BLOCKED ACCOUNTS</a></li>
                        <li><a href="HackerAlert.jsp">HACK ALERTS</a></li>
                        <li><a href="Logout.jsp">LOGOUT</a></li>
                    </ul>
                </nav>               
            </header>
        </div>
        <%
            DB_ACCESS dba = new DB_ACCESS();
            ResultSet rs = dba.Select("select Mailid,Status from userauthentication where Status='Activation Requested'");
            if (rs.next()) {
                
    %>
        <form method="get" action="ActivateAccount">
        <table width="75%" >
            <tr>
                <th>EMail</th>
                <th>Status</th>
                <th>Activate</th>
            </tr>    
        <%
        }
            rs = dba.Select("select Mailid,Status from userauthentication where Status='Activation Requested'");
            while (rs.next()) {
                session.setAttribute("email", rs.getString("Mailid"));
        %>
        <tr>
            <td><label id="mail" name="mail"><%=rs.getString("Mailid")%></label></td>
        <td><%=rs.getString("Status")%></td>
        <td>
            <input type="submit" value="Activate"/>
            
        </td>
        </tr>
        <% } %>
        </table>
        <%
           rs = dba.Select("select Mailid,Status from userauthentication where Status='Activation Requested'");
            if(!rs.next()){
        %>
        <center><h3> No Activation Requests </h3></center>
        <%}
            rs.close();
        %> 
        </table> 
        </form>
    </body>
</html>
