<%-- 
    Document   : index
    Created on : 25 Feb, 2019, 8:08:28 PM
    Author     : Sandhya
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="DATA.DB_ACCESS"%>
<%@page import="java.sql.SQLException"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html> 
    <head>
        <title>Honey Encryption</title>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link rel="stylesheet" href="assets/css/main.css" />
        <script src="assets/js/ValidationS.js"></script>
        <script>
             //For Registration
            function Validate_Registration() {
                var uname = document.getElementById("username").value;
                var e_mail = document.getElementById("email").value;
                var p_ass = document.getElementById("password").value;
                if (UNAME_VLD(uname)) {
                    document.getElementById("username").style.border = "2px solid green";
                     if (EMAIL_VLD(e_mail)) {
                            document.getElementById("email").style.border = "2px solid green";
                            if (PASS_VLD(p_ass)) {
                                document.getElementById("password").style.border = "2px solid green";
                            } else {
                                document.getElementById("password").style.border = "2px solid red";
                                document.getElementById("password").focus();
                                return false;
                            }
                        } else {
                            document.getElementById("email").style.border = "2px solid red";
                            document.getElementById("email").focus();
                            return false;
                        }
                    } 
            }
            //For Login
            function Validate_Login() {
                var inname = document.getElementById("email_l").value;
                var inpass = document.getElementById("password_l").value;
                
                    document.getElementById("email_l").style.border = "2px solid green";
                    if (Lin_UPass(inpass)) {
                        document.getElementById("password_l").style.border = "2px solid green";
                    } else {
                        document.getElementById("password_l").style.border = "2px solid red";
                        document.getElementById("password_l").focus();
                        return false;
                    }
                
            }
            </script>
    </head>
    <body>
         <%
            String honey_app = (String) session.getAttribute("HONEY_APP");
            if (honey_app != null) {
        %>
        <script>alert('<%=honey_app%>');</script>;
        <%
                session.removeAttribute("HONEY-APP");
            }
                    
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
            ResultSet r=dba.Select("select * from blocked_accounts where IPAddress='" + ip + "' and Accessor = 'Hacker'" ); 
            if(r.next())
            {  
         %>
                <section id="sidebar">
                <!-- Intro -->
                <section id="intro">
                    <header>
                        <center>
                            <img src="images/block.jpg" width="150em" height="100em" alt=""/>
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
         %>  
         <div id="wrapper">

            <!-- Header -->
            <header id="header">
                <h1><a href="#">Detecting Password Breaches in Cloud Using Honey Encryption</a></h1>
   
                <nav class="main">
                    <ul>                       
                        <li class="menu">
                            <a class="fa-bars" href="#menu">Menu</a>
                        </li>
                    </ul>
                </nav>
            </header>

            <!-- Menu -->
            <section id="menu">              
                <!-- Actions -->
                <section style="margin-top: 40%;">
                    <h1 style="font-size: large;">LOGIN</h1>
                    <form method="post" onsubmit="return Validate_Login();" action="CheckLogin">
                        <input type="text" name="email_l" id="email_l" required="" maxlength="65" autofocus="" placeholder="Email" /><br/>
                        <input type="password" name="password_l" id="password_l" required="" maxlength="50" autofocus="" placeholder="Password" /><br/>
                        <ul class="actions vertical">
                            <li><input type="submit" class="button big fit" value="LOGIN"></li>
                        </ul> 
                    </form>                                    
                </section>
            </section>
        
         <div id="main">

                <!-- Post -->
                <article class="post">
                    <header>
                        <div class="title">
                            <h2><a href="#">SIGN-UP</a></h2>                           
                        </div>                       
                    </header>
                    <!-- Registration Form -->
                    <form method="post" onsubmit="return Validate_Registration();" action="UserRegistration">
                        <label for="username"><b>Username</b></label>
                         <input type="text" id="username" maxlength="65" autofocus="" placeholder="Enter Username" name="username" required>

                    <label for="email"><b>Email</b></label>
                    <input type="text" placeholder="Enter Email" name="email" required id="email" maxlength="65" autofocus="">

                    <label for="psw"><b>Password</b></label>
                    Password must contain atleast one number, one uppercase letter
                    <input type="password" placeholder="Enter Password" name="password" id="password" maxlength="65" autofocus=""  required>

                    <label for="psw-repeat"><b>Repeat Password</b></label>
                    <input type="password" placeholder="Repeat Password" name="psw-repeat" id="psw-repeat" maxlength="65" autofocus=""  required>

                        <ul class="actions vertical">
                            <li><input type="submit" class="button big fit" value="SUBMIT"></li>
                        </ul> 
                    </form>  
                     <footer>                          
                        <p style="margin-left: 40%;">If You Are Already Have An Account Then.</p>
                        <ul class="actions">
                            <li><a class="button big" href="#menu">Login Now</a></li>
                        </ul>
                    </footer>
                </article>  
         </div>
                 <section id="sidebar">

                <!-- Intro -->
                <section id="intro">                   
                    <header>
                        <h2>Honey Encryption</h2>                        
                    </header>
                </section>
             <!-- Mini Posts -->
                <section>
                    <div class="mini-posts">

                        <!-- Mini Post -->
                        <article class="mini-post">                           
                            <a href="#" class="image"><img src="images/password.jpg" alt="" /></a>
                        </article>
                    </div>
                </section> 
            </section>
            

         </div>
         <%
         }
            %>
          <!-- Scripts -->
        <script src="assets/js/jquery.min.js"></script>
        <script src="assets/js/skel.min.js"></script>
        <script src="assets/js/util.js"></script>
        <!--[if lte IE 8]><script src="assets/js/ie/respond.min.js"></script><![endif]-->
        <script src="assets/js/main.js"></script>
        
    </body>
</html>
