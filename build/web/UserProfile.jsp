
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="DATA.DB_ACCESS"%>
<%@page import="BUSINESS.FetchUserInformation"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%-- 
    Document   : UserProfile
    Created on : 26 Feb, 2019, 10:49:44 PM
    Author     : Sandhya
--%>
<!DOCTYPE html>
<html>
    <head>
         <title>Honey Encryption</title>
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
             String honey_app = (String) session.getAttribute("HONEY_APP");
            if (honey_app != null) {
        %>
        <script>alert("<%=honey_app%>");</script>
        <%
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
                        <li><a href="#">MY ACCOUNTS</a></li>
                        <li><a href="Profile.jsp">PROFILE</a></li>
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
          
            String status;
            ResultSet rv = dba.Select("select Status from userauthentication where Mailid='" + mailid + "'");
            if (rv.next()) {
                status=rv.getString("Status");
                if(status.equals("Inactive"))
                {
                    session.setAttribute("mailid",mailid);
        %>
    <center><h3> Request for Account activation</h3><br></center>
                <form method="get" action="ActivationRequests">
                    <ul class="actions vertical">
                            <li><input type="submit" class="button big fit" value="Request Activation"></li>
                    </ul>
                </form>    
         <%  
                }
                else if(status.equals("Active"))
                {
                String acc_no = (String) session.getAttribute("acc_no");  
                String contact = (String) session.getAttribute("contact");
                String mode=(String) session.getAttribute("mode");
                String dateofopen = (String) session.getAttribute("dateofopen");
                String branch=(String) session.getAttribute("branch");
                int balance =(Integer) session.getAttribute("balance");
                System.out.println(dateofopen);
            
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
                <td><%=acc_no%></td>
                <td><%=branch%></td>
                <td><%=balance%></td>
                <td>Active</td>
            </tr>
            </table> 
                 </article>
          
                 <article class="post">
                     <section>
                         <table>
                             <tr>
                                 <td><label>Account Holder Name</label><td>
                                 <td><center><%=username%></center></td>
                             </tr>    
                             <tr>
                                 <td><label>Mode of Operation</label></td>
                                 <td><center><%=mode%></center></td>
                             </tr>
                         </table>   
                     </section>
                 </article>
        </div>
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
        <%
        session.setAttribute("username",username);
                        session.setAttribute("acc_no",acc_no);
                        session.setAttribute("mode",mode);
                        session.setAttribute("branch",branch);
                         session.setAttribute("contact",contact);
                        session.setAttribute("dateopen",dateofopen);
                        session.setAttribute("balance", balance);
       
                }
                rv.close();
                
            }
            }
            }
        catch(SQLException e)
        {
           out.println("SQL Exception :" + e.toString());
        }
          
        %>
           
    </body>
</html>
