<%-- 
    Document   : Blockhacker
    Created on : 12 Mar, 2019, 10:46:08 PM
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
                        <li><a href="ActivateAccounts.jsp">ACTIVATION REQUESTS</a></li>
                        <li><a href="BlockedAccounts.jsp">BLOCKED ACCOUNTS</a></li>
                        <li><a href="#">HACK ALERTS</a></li>
                        <li><a href="Logout.jsp">LOGOUT</a></li>
                    </ul>
                </nav>               
            </header>
        </div>
          <%
            DB_ACCESS dba = new DB_ACCESS();
            ResultSet rs = dba.Select("select * from hackeralerts");
            if(rs.next()){
        %>
        <form method="get" action="BlockIP">
        <table width="75%" >
            <tr>
                <th>EMail</th>
                <th>IP Address</th>
                <th>Blocked Time</th>
                <th>Status</th>
            </tr>  
        <%}rs = dba.Select("select * from hackeralerts");
            while (rs.next()) {
        %>
          
         <%
                 session.setAttribute("email", rs.getString("Mailid"));
                 session.setAttribute("time", rs.getString("Time"));
                 session.setAttribute("IP", rs.getString("IPAddress"));
        %>
        <tr>
            <td><label id="mail" name="mail"><%=rs.getString("Mailid")%></label></td>
        <td> <%=rs.getString("IPAddress")%></td>
        <td> <%=rs.getTime("Time")%></td>
        <td>
            <input type="submit" value="Block IP"/>
            
        </td>
        </tr>
        
       
        <% }%>
         </table> <%
            rs = dba.Select("select * from hackeralerts");
            if(!rs.next()){
        %>
        <center><h3> No Hack Alerts </h3></center>
        <%}
            rs.close();
        %>
        </form>
        
    </body>
</html>
