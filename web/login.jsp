<%-- 
    Document   : login
    Created on : 17 Jan, 2021, 5:17:48 PM
    Author     : Naveen Prasad
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html>
<head>
	<title>Login and Registration Page</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" type="text/css" href="loginpage.css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:400,600,700,800&display=swap" rel="stylesheet">
  <script type="text/javascript">
  
  function passwordValidation(str)
  {
   var ob=new XMLHttpRequest();
  // console.log("printing.....");
   ob.onreadystatechange=function ss()
   {
    if(ob.readyState==4)
    {
        document.getElementById("passError2").innerHTML=ob.responseText;
        //alert("change password");
       console.log("print");
    }
   }
   //console.log("printing.....");
   ob.open("GET","passValidate?pass="+str,true);
   ob.send();
  }
  </script>
</head>
<body style="background-image: url('assets/images/bf.jpg')">
    <%  String value = (String) request.getAttribute("Validate");         %>
  <div class="cont">
    <div class="form sign-in">
       <form name="loginForm" method = "post"  action="<%= request.getContextPath() %>/login" >
           <h2 >Log In</h2>
      <label>
        <span>Email Address</span>
        <input type="email" name="email" required>
      </label>
      <label>
        <span>Password</span>
        <input type="password" name="password" required>
      </label>
           <button class="submit"  type="submit" value="Submit" >Log In</button>
           
      
      <div id='passError1'>
            
      </div>
     </form>
          
       <p class="forgot-pass" style="color:red"><%= value %></p>
    
    </div>

    <div class="sub-cont">
      <div class="img">
        <div class="img-text m-up">
          <h2>New here?</h2>
          <p>Register and start playing people!</p>
        </div>
        <div class="img-text m-in">
          <h2>One of us?</h2>
          <p>If you already have an account, just login in. We've missed you!</p>
        </div>
        <div class="img-btn">
          <span class="m-up">Register</span>
          <span class="m-in">LogIn</span>
        </div>
      </div>
      <div class="form sign-up">
          <form method = "post" action="<%= request.getContextPath() %>/register">
        <h2 >Register</h2>
<!--        <label>
          <span>Name</span>
          <input type="text">
        </label>-->
        <label>
          <span>Email</span>
          <input type="email" name="email"  required>
        </label>
        <label>
          <span>Password</span>
          <input type="password" name="password" onkeyup="passwordValidation(this.value)" required >
        </label>
        <label>
          <span>Age</span>
          <input type="number" name="age" required>
        </label>
        <button type="submit" value="Submit" class="submit">Register!</button>
        </form>
        <div id='passError2' style="color:red" >
            
        </div>
      </div>
    </div>
  </div>
<script type="text/javascript" src="assets/loginscript.js"> </script>
</body>
</html>
