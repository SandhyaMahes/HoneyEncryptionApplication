<%-- 
    Document   : UserFakeProfile
    Created on : 26 Feb, 2019, 10:50:01 PM
    Author     : Sandhya
--%>

<%@page import="DATA.DB_ACCESS"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
       <title>User Home - Honey Encryption</title>
        <meta charset="utf-8" />
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
            String mailid = (String) session.getAttribute("email");
            String username = (String) session.getAttribute("username");
            int register = (Integer) session.getAttribute("Register");
            DB_ACCESS dba = new DB_ACCESS();
             //Session Id Validation
            if (username != null) {
                 String honey_app = (String) session.getAttribute("HONEY_APP");
                    if (honey_app != null) {
        %>
        <script>alert('<%=honey_app%>');
        </script>
        <%
                session.removeAttribute("HONEY_APP");
            }
            }
        %>
          <!-- Wrapper -->
        <div id="wrapper">

            <!-- Header -->
            <header id="header">
                <h1><a href="#">Banking</a></h1>
                <nav class="links">
                    <ul>
                        <li><a href="#">MY ACCOUNTS</a></li>
                        <li><a href="#menu">PAYMENTS/TRANSFERS</a></li>
                        <li><a href="APP_USER_FNDFRND.jsp">BILL PAYMENTS</a></li>
                        <li><a href="APP_USER_CHAT.jsp">PROFILE</a></li>
                        <li><a href="Logout.jsp">LOGOUT</a></li>
                    </ul>
                </nav>               
            </header>
              <%
            ResultSet rs = dba.Select("select * from honeyusers where Mailid='" + mailid + "'");
            if(rs.next()) {
            %>
            <div id="main">
                 <article class="post">
                <label>My Accounts / Account Summary </label> 
            <table width="75%" >
            <tr>
                <th>Account Number</th>
                <th>Branch</th>
                <th>Available Balance</th>
                <th>Account Status </th>
            </tr>    
            <tr>
                <td><%=rs.getString("Account_Number")%></td>
                <td><%=rs.getString("MainBranch")%></td>
                <td><%=rs.getInt("Balance")%></td>
                <td>Active</td>
            </tr>
            </table> 
                 </article>
          
                 <article class="post">
                     <section>
                         <table>
                             <tr>
                                 <td><label>Account Holder Name</label><td>
                                 <td><center><%=rs.getString("Name")%></center></td>
                             </tr>    
                             <tr>
                                 <td><label>Mode of Operation</label></td>
                                 <td><center><%=rs.getString("ModeOfOperation")%></center></td>
                             </tr>
                         </table>   
                     </section>
                 </article>
        </div>
        <%
            rs.close();
            }
        %>
           <section id="sidebar">

                <!-- Intro -->
                <section id="intro">
                    <header>
                        <img src="images/profilepic.png" width="200em" height="150em" alt="">
                        <h2><%=username%></h2>                        
                    </header>
                </section>
           </section>
        </div>
    </body>
   
</html>
