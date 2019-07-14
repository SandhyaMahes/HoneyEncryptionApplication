/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//Login Username Validation
function Lin_Uname(Lin_Name)
{
    var Lin_Name1 = /^([\w-]+(?:\.[\w-]+)*)@((?:[a-z\-]+\.)*[a-z][a-z]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i.test(Lin_Name);
    if (Lin_Name == null)
    {
        alert("Email Should Not Be An EMPTY");
        return false;
    }
    else if (!Lin_Name1)
    {
        alert("Please Enter Valid Email Address");
        return false;
    }
    return true;
}

//Login Password Validation
function Lin_UPass(Lin_Pass)
{
    var ll = Lin_Pass.length;
    if (Lin_Pass == "")
    {
        alert("Password Should Not Be EMPTY");
        return false;
    }
    else if ((ll < 8) || (ll > 20))
    {
        alert("Password Should Contain 8 to 20 characters");
        return false;
    }
    return true;
}

//User FirstName Validation
function UNAME_VLD(uname)
{
    var fun = /^[a-zA-Z]+$/.test(uname);
    if (uname == "")
    {
        alert("Please Enter User Name");
        return false;
    }
    else if (!fun)
    {
        alert("UserName Should Contain Only ALPHABETS");
        return false;
    }
    return true;
}

//Account Number Validation
function ACCNO_VLD(accno)
{
    var fun = /^[0-9]+$/.test(accno);
    if (accno == "")
    {
        alert("Please Enter Account Number");
        return false;
    }
    else if (!fun)
    {
        alert("Account number Should Contain Only DIGITS");
        return false;
    }
    return true;
}

//Phone Number Validation
function CONTACT_VLD(contact)
{
    var len = contact.length;
    var fun = /^[0-9]+$/.test(contact);
    if (contact == "")
    {
        alert("Please Enter Contact Number");
        return false;
    }
    else if (!fun)
    {
        alert("Contact number Should Contain Only DIGITS");
        return false;
    }
    else if(len!=10)
    {
        alert("Invalid Contact number");
        return false;
    }    
    return true;
}

//Mail ID Validation
function EMAIL_VLD(e_mail)
{
    var mail2 = /^([\w-]+(?:\.[\w-]+)*)@((?:[a-z\-]+\.)*[a-z][a-z]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i.test(e_mail);
    if (e_mail == null)
    {
        alert("E-Mail Field Should Not Be An EMPTY");
        return false;
    }
    else if (!mail2)
    {
        alert("Please Enter Valid E-Mail Address");
        return false;
    }
    return true;
}

//Password Validation
function PASS_VLD(p_ass)
{
    var ll = p_ass.length;
    var count=0;
    if (p_ass == "")
    {
        alert("Password Should Not Be EMPTY");
        return false;
    }
    else if ((ll < 8) || (ll > 20))
    {
        alert("Password Should be of 8 to 20 length");
        return false;
    }
     re = /[0-9]/;
      var result = p_ass.match(re);
      var len=result.length;
      if(len<1) {
        alert("Error: password must contain at least three numbers (0-9)!");
       // alert(result);
        return false;
      }
    return true;
}
