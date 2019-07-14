<%-- 
    Document   : AdminProfile
    Created on : 1 Mar, 2019, 11:55:27 PM
    Author     : Sandhya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
         <title>Admin</title>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link rel="stylesheet" href="assets/css/main.css" />
       
        <script src="assets/js/ValidationS.js"></script>  
        <script>
             //For Adding Accounts
            function Validate_AccountDetails() {
                var accno = document.getElementById("AccountNO").value;
                var contact = document.getElementById("Contact").value;
                var name = document.getElementById("Name").value;
                var e_mail = document.getElementById("Mailid").value;
                if(ACCOUNT_VLD(accno)) {
                    document.getElementById("AccountNO").style.border = "2px solid green";
                    if (UNAME_VLD(uname)) {
                        document.getElementById("Name").style.border = "2px solid green";
                        if(CONTACT_VLD(contact)) {
                            document.getElementById("Contact").style.border = "2px solid green";
                            if (EMAIL_VLD(e_mail)) {
                                document.getElementById("Mailid").style.border = "2px solid green";
                            }
                            else {
                                document.getElementById("Maild").style.border = "2px solid red";
                                document.getElementById("Maild").focus();
                                return false;
                            }
                         }   
                         else {
                            document.getElementById("Contact").style.border = "2px solid red";
                            document.getElementById("Contact").focus();
                            return false;
                         } 
                    }
                    else {
                         document.getElementById("Name").style.border = "2px solid red";
                         document.getElementById("Name").focus();
                         return false;
                    }
                    }
                }
            
            </script>
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
        <script>alert('<%=honey_app%>');</script>;
        <%
                session.removeAttribute("HONEY_APP");
            }
        %>
          <!-- Wrapper -->
        <div id="wrapper">

            <!-- Header -->
            <header id="header">
                <h1><a href="#">Banking</a></h1>
                <nav class="links">
                    <ul>
                        <li><a href="#">ADD ACCOUNTS</a></li>
                        <li><a href="AddHoneypotAccount.jsp">ADD HONEYPOT ACCOUNT</a></li>
                        <li><a href="ActivateAccounts.jsp">ACTIVATION REQUESTS</a></li>
                        <li><a href="BlockedAccounts.jsp">BLOCKED ACCOUNTS</a></li>
                        <li><a href="HackerAlert.jsp">HACK ALERTS</a></li>
                        <li><a href="Logout.jsp">LOGOUT</a></li>
                    </ul>
                </nav>               
            </header>
        </div>
          <div id="main">

                <!-- Post -->
                <article class="post">
                    <header>
                        <div class="title">
                            <h2><a href="#">Add Accounts</a></h2>                           
                        </div>                       
                    </header>
                    <!-- Registration Form -->
                    <form method="post" onsubmit="return Validate_AccountDetails();" action="AddAccountDetails">
                        <label for="custID"><b>Customer ID</b></label>
                        <input type="number" id="custID" maxlength="65" autofocus="" placeholder="Enter Customer ID" name="custID" required>

                    <label for="AccountNO"><b>Account Number</b></label>
                    <input type="number" placeholder="Enter Account Number" name="AccountNO" required id="AccountNO" maxlength="65" autofocus="">
                    
                    <label for="Name"><b>Name</b></label>
                    <input type="text" placeholder="Enter Name" name="Name" required id="Name" maxlength="65" autofocus="">

                    <label for="Contact"><b>Contact</b></label>
                    <input type="number" placeholder="Enter Contact Number" name="Contact" required id="Contact" maxlength="65" autofocus="">
                    
                    <label for="ModeOfOperation"><b>Mode Of Operation</b></label>
                    <select name="ModeOfOperation" id="ModeOfOperation">
                            <option value="Select">Select Mode Of Operation</option>
                            <option value="Self">Self</option>
                            <option value="Corporate">Corporate</option>
                    </select>
                   
                    <label for="MainBranch"><b>Main Branch</b></label>
                    <select name="MainBranch" id="MainBranch">
                            <option value="Ashoknagar">Ashoknagar</option>
                            <option value="Ekkaduthangal">Ekkaduthangal</option>
                            <option value="Madipakkam">Madipakkam</option>
                            <option value="Medavakkam">Medavakkam</option>
                            <option value="Nanganallur">Nanganallur</option>
                            <option value="Pallikaranai">Pallikaranai</option>
                            <option value="Selaiyur">Selaiyur</option>
                            <option value="Tambaram">Tambaram</option>
                    </select>
                    
                     <label for="Mailid"><b>Mail ID</b></label>
                    <input type="text" placeholder="Enter Mail ID" name="Mailid" required id="Mailid" maxlength="65" autofocus="">

                    <label for="Balance"><b>Account Balance</b></label>
                    <input type="number" placeholder="Account Balance" min="2500" name="Balance" id="Balance" maxlength="65" autofocus=""  required>

                        <ul class="actions vertical">
                            <li><input type="submit" class="button big fit" value="SUBMIT"></li>
                        </ul> 
                    </form>  
                </article>  
         </div>
    </body>
</html>
