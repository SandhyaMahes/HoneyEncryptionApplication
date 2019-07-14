<%-- 
    Document   : Logout
    Created on : 2 Mar, 2019, 3:21:16 PM
    Author     : Sandhya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="DATA.DB_ACCESS"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Log Out</title>
    </head>
    <body>
        <%      
            session.invalidate();
            response.sendRedirect("index.jsp");
       %>
    </body>
</html>
