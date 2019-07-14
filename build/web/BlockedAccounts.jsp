<%-- 
    Document   : BlockedAccounts
    Created on : 2 Mar, 2019, 3:50:58 PM
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
                        <li><a href="#">BLOCKED ACCOUNTS</a></li>
                        <li><a href="HackerAlert.jsp">HACK ALERTS</a></li>
                        <li><a href="Logout.jsp">LOGOUT</a></li>
                    </ul>
                </nav>               
            </header>
        </div>
          <%
            DB_ACCESS dba = new DB_ACCESS();
            ResultSet rs = dba.Select("select * from blocked_accounts");
            if (rs.next()) {
        %>
        <form method="get" action="UnblockAccount">
          <table width="75%" >
            <tr>
                <th>EMail</th>
                <th>Blocked Time</th>
                <th>Accessor</th>
                <th>IP Address</th>
                <th>Status</th>
                <th>Activate</th>
            </tr>  
        <%
            }rs = dba.Select("select * from blocked_accounts");
            while (rs.next()) {
                
        %>
        
        <%    
                 session.setAttribute("email", rs.getString("Mailid"));
        %>
        <tr>
            <td><label id="mail" name="mail"><%=rs.getString("Mailid")%></label></td>
        <td> <%=rs.getTime("blocked_time")%></td>
        <td> <%=rs.getString("Accessor")%></td>
        <td> <%=rs.getString("IPAddress")%></td>
        <td><%=rs.getString("Status")%></td>
        <td>
            <input type="submit" value="Unblock"/>
            
        </td>
        </tr>
        <% }
            %>
            </table> 
            <%
            rs = dba.Select("select * from blocked_accounts");
            if(!rs.next()){
        %>
        <center><h3> No Blocked Accounts </h3></center>
        <%}
            rs.close();
        %>
        
        </form>    
    </body>
</html>
