<%-- 
    Document   : Profile
    Created on : 13 Mar, 2019, 5:38:59 AM
    Author     : Sandhya
--%>

<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="DATA.DB_ACCESS"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profile</title>
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link rel="stylesheet" href="assets/css/main.css" />
       
        <script src="assets/js/ValidationS.js"></script>  
        <style>
            #contactdiv{
                opacity:0.92;
                position: absolute;
                top: 0px;
                left: 0px;
                height: 100%;
                width: 100%;
                background: #000;
                display: none;
            }
            #logindiv{
                opacity:0.92;
                position: absolute;
                top: 0px;
                left: 0px;
                height: 100%;
                width: 100%;
                background: #000;
                display: none;
            }
            #contact{
                left: 50%;
                top: 50%;
                margin-left:-210px;
                margin-top:-255px;
            }
            #login{
                left: 50%;
                top: 50%;
                margin-left:-210px;
                margin-top:-158px;
            }
        </style>
    </head>
    <body>
        <%
             String honey_app = (String) session.getAttribute("HONEY_APP");
            if (honey_app != null) {
                 session.removeAttribute("HONEY-APP");
           
            }
        %>
       
           
          <!-- Wrapper -->
        <div id="wrapper">

            <!-- Header -->
            <header id="header">
                <h1><a href="#">Banking</a></h1>
                <nav class="links">
                    <ul>
                        <li><a href="UserProfile.jsp">MY ACCOUNTS</a></li>
                        <li><a href="#">PROFILE</a></li>
                        <li><a href="Logout.jsp">LOGOUT</a></li>
                    </ul>
                </nav>               
            </header>
             <%
               
           String username = (String) session.getAttribute("username");
            try {         
                String ip = request.getHeader("x-forwarded-for"); 
            if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
                ip = request.getHeader("Proxy-Client-IP"); 
            } 
            if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
                ip = request.getHeader("WL-Proxy-Client-IP"); 
            } 
            if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
                ip = request.getRemoteAddr(); 
            } 
            String mailid = (String) session.getAttribute("email");
            
            DB_ACCESS dba = new DB_ACCESS();
            ResultSet r=dba.Select("select * from blocked_accounts where IPAddress='" + ip + "'and Status = 'Blocked'"); 
            if(r.next())
            {
                r.close();
                %>
                <section id="sidebar">
                <!-- Intro -->
                <section id="intro">
                    <header>
                        <center><img src="images/block.jpg" width="150em" height="100em" alt=""/>
                        <h2>Web Page Blocked!</h2>   
                        <p> The page cannot be displayed. Please contact the administrator for further information</p>
                        </center>
                    </header>
                </section>
           </section>    
                <%
            }
            else
            { 
                String acc_no = (String) session.getAttribute("acc_no");  
                String contact = (String) session.getAttribute("contact");
                String mode=(String) session.getAttribute("mode");
                String dateofopen = (String) session.getAttribute("dateofopen");
                String branch=(String) session.getAttribute("branch");
                int balance =(Integer) session.getAttribute("balance");
                
            %>
            <div id="main">
                 <article class="post">
                <label>Profile </label> 
            <table width="75%" >
            <tr>
                <th>Username</th>
                <th>Mail ID</th>
                <th>Contact</th>
                <th>Date Of Opening </th>
            </tr>    
            <tr>
                <td><%=username%></td>
                <td><%=mailid%></td>
                <td><%=contact%></td>
                <td><%=dateofopen%></td>
                
            </tr>
            </table> 
                 </article>
          
               <section id="sidebar">
                <!-- Intro -->
                <section id="intro">
                    <header>
                        <img src="images/profilepic.png" width="150em" height="100em" alt=""/>
                        <h2><%=username%></h2>                        
                    </header>
                </section>
           </section>    
        </div>     
        </div>
        <%
        session.setAttribute("HONEY_APP", null);
        session.setAttribute("username",username);
                        session.setAttribute("acc_no",acc_no);
                        session.setAttribute("mode",mode);
                        session.setAttribute("branch",branch);
                         session.setAttribute("contact",contact);
                        session.setAttribute("dateopen",dateofopen);
                        session.setAttribute("balance", balance);
       
            }  
         }
        catch(SQLException e)
        {
           out.println("SQL Exception :" + e.toString());
        }
          
        %>
                
    </body>
</html>
