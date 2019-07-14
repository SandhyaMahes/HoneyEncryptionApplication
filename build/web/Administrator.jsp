<%-- 
    Document   : Administrator
    Created on : 1 Mar, 2019, 9:32:19 PM
    Author     : Sandhya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link rel="stylesheet" href="assets/css/main.css" />
        <script>
             //For Login
            function Validate_Login() {
                var inname = document.getElementById("adminid").value;
                var inpass = document.getElementById("password").value;
                if(inname!=null)
                    document.getElementById("adminid").style.border = "2px solid green";
                if (inpass!=null) {
                        document.getElementById("password").style.border = "2px solid green";
                    } else {
                        document.getElementById("password").style.border = "2px solid red";
                        document.getElementById("password").focus();
                        return false;
                    }
                
            }
            </script>
        <title>Admin Login</title>
    </head>
    <body>
        <div id="main">

                <!-- Post -->
                <article class="post">
                    <header>
                        <div class="title">
                            <h2><a href="#">ADMIN </a></h2>                           
                        </div>                       
                    </header>
                    <!-- Admin Form -->
                    <form method="post" onsubmit="return Validate_Login();" action="CheckAdmin">
                        <label for="adminid"><b>Admin ID</b></label>
                         <input type="text" id="adminid" maxlength="65" autofocus="" placeholder="Enter ADMIN ID" name="adminid" required>

                    <label for="psw"><b>Password</b></label>
                    <input type="password" placeholder="Enter ADMIN Password" name="password" id="password" maxlength="65" autofocus=""  required>

                        <ul class="actions vertical">
                            <li><input type="submit" class="button big fit" value="SUBMIT"></li>
                        </ul> 
                    </form>  
                </article>  
         </div>
    </body>
</html>
