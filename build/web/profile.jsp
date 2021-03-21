<%-- 
    Document   : profile
    Created on : 30 Jan, 2021, 11:27:52 PM
    Author     : Naveen Prasad
--%>

<%@page import="database.getuserDP"%>
<%@page import="database.connect"%>
<%@page import="models.Post"%>
<%@page import="models.Comment"%>
<%@page import="models.user"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profile page</title>
    <link rel="stylesheet" type="text/css" href="./assets/profilepage.css">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<style>
    @import url('https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap');
    body{
        font-family: 'Poppins', sans-serif;
    }
    .sm{
  visibility: hidden;
  display: none;
  
}

.colorlink,.colorlink:hover{
    text-decoration: none;
    color:white;
}

.marg{
    margin-left: 3%;
}


</style>
<script>
        var d={};
        function dk(z)
        {   
            var i = 's'.concat(z);
            if(d[i]===undefined)
            {
                d[i]=1;
                console.log(d);
            }
            else
            {
                d[i]=d[i]+1;
                console.log(d);
                console.log("2nd");
            }
            if(d[i]%2!==0)
            {   
                 var s=document.querySelector("#"+i).style.visibility="visible";
                 var m=document.querySelector("#"+ i).style.display="unset";
            }
            if(d[i]%2===0)
            {    
                 var s=document.querySelector("#"+i).style.visibility="hidden";
                 var m=document.querySelector("#"+ i).style.display="none";
            }
        }

    </script>
<body>
   <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand" href="#">
            <img src="images/logo.png" width="40px" class="d-inline-block align-top" alt="">
             GamesUp
        </a>
        <form class="form-inline my-2 my-lg-0" action='./profile' method="get">
            <div class="form-outline">                
                <input type="search" name="username" id="form1" class="form-control"  placeholder="Search for an user" aria-label="Search" />
                                
              </div>
        </form>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
      
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav ml-auto">
            <li class="nav-item ">
              <a class="nav-link" href="./home">Home <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="http://localhost:3000/index.html">Groups</a>
            </li>
            <li class="nav-item ">
                <a class="nav-link" href="navbar.html">Games</a>
            </li>
            <%                    Cookie[] cookie = request.getCookies();
                                    String uname = "";
                                   if(cookie != null){
                                       for(int j=0;j<cookie.length;j++){
                                         String cName = cookie[j].getName();
                                         String cValue = cookie[j].getValue();
                                        if(cName.equals("username")){
                                              uname = cValue;
                                        }
                                       } %>
            <li class="nav-item active">
                <a class="nav-link" href="./profile?username=<%= uname %>">Profile</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="<%= request.getContextPath() %>/logout">Logout</a>
            </li>
            <% } %>
          </ul>
          
        </div>
      </nav>   
    <%  user userprofile = (user)request.getAttribute("profile");
    System.out.println("Username "+userprofile.getUsername()); 
    %>
    
    <div class="container" style="margin-top: 5%">
<div class="row">
    <div class="col-sm-10 col-sm-offset-2">
        <div class="panel panel-white profile-widget">
            <div class="row">
                <div class="col-sm-12">
                    <div class="image-container bg8 bgcover" style="background:url(<%=userprofile.getCoverimage()%>)">  
                        <img src="<%=userprofile.getProfileimage() %>" class="avatar" alt="avatar"> 
                    </div>
                </div>
                <div class="col-sm-12">
                    <div class="details">
                        <h4><%= userprofile.getUsername() %> <i class="fa fa-sheild"></i></h4>
                        <div><h5>Gamer level : <%= userprofile.getPlayerlevel() %> Gamer</h5></div>
                        <div><h5>About : <%= userprofile.getAbout() %></h5></div>
                        <div><h5>Address : <%= userprofile.getAddress() %></h5></div>
                        <% if(uname.equals(userprofile.getUsername())){ %>
                        <button class="btn btn-info"><a class="colorlink" href="<%=request.getContextPath()%>/updateProfile" >Update Profile</a></button>
                        <% }%>
                    </div>
                </div>
                 
            </div>
        </div>
        <div><h1>Recent Posts</h1></div> 
        <div class="row">
            <div class="col-sm-12">
                <div class="container ">
        <div class="d-flex justify-content-center row">
            <div class="col-md-10">
                <div class="feed p-2">
<!--                    <div class="d-flex flex-row justify-content-between align-items-center p-4 bg-white border">
                         
                        <div class="form-group">
                            <form method="post" action="/home" enctype="multipart/form-data" >
                            <textarea name="caption" class="form-control" id="exampleFormControlTextarea1" rows="3" cols="100" placeholder="Start a post"></textarea>
                            <input type="file" class="form-control-file" id="exampleFormControlFile1" name="img" accept="image/*">
                                <div class="form-group p-3">
                                    <button type="submit" class="btn btn-primary float-right" id="gen" value="Submit">
                                        Post
                                    </button>
                                    <button class="btn btn-default float-right">
                                        Cancel
                                    </button>
                                </div>
                            </form>
                          </div>
                        <div class="feed-icon px-2"><i class="fa fa-long-arrow-up text-black-50"></i></div>
                    </div>-->
                    <%  Post FeedArray[] = userprofile.getPosts(); %>
                    <% if(FeedArray.length > 0 ) { %>
                    <%  for(int i = 0;i<FeedArray.length;i++) { %>
                    <%  String id = "s"+String.valueOf(i); %>
                    <div class="bg-white border mt-2">
                        <div>
                            <div class="d-flex flex-row justify-content-between align-items-center p-2 border-bottom">
                                <div class="d-flex flex-row align-items-center feed-text px-2"><img class="rounded-circle" src="<%= FeedArray[i].getProfilepic() %>" width="50" height="45S">
                                    <div class="d-flex flex-column flex-wrap ml-2"><span class="font-weight-bold"> <%= FeedArray[i].getUsername() %>                 </span><span class="text-black-50 time"></span></div>
                                </div>
                                <div class="feed-icon px-2"><i class="fa fa-ellipsis-v text-black-50"></i></div>
                            </div>
                        </div>
                        <div class="p-2 px-3"><span>  <%= FeedArray[i].getCaption() %>                    </span></div>
                        <div class="feed-image p-2 px-3 marg"><img class="img-fluid img-responsive" src="data:image/jpg;base64,<%= FeedArray[i].getBase64Image() %>" width="650"></div>
                        <div class="bg-white">
                            <div class="d-flex flex-row fs-12">
                                <div class="like p-2 cursor"><i class="fa fa-thumbs-up" aria-hidden="true"></i><span class="ml-1">Like</span></div>
                                <div class="like p-2 cursor"><i  class="fa fa-comment" onclick="dk(<%= i %>)"></i><span onclick="dk(<%= i %>)" class="ml-1">Comment</span></div>
                                <div class="like p-2 cursor"><i class="fa fa-share"></i><span class="ml-1">Share</span></div>
                            </div>
                        </div>
                         
                        <div class="bg-light p-2">
                            <div  class="sm" id="<%= id %>">
                               <% Comment comments[] =  FeedArray[i].getComments(); %>
                               <% if(comments.length > 0){ %>
                               <% for(int k=0;k<comments.length;k++){ %>

                                <div class="comment-widgets">
                                    <div class="d-flex flex-row comment-row m-t-0">
                                        <div class="p-2"><img src="<%= comments[k].getProfileimg() %>" alt="user" width="50" class="rounded-circle"></div>
                                       <div class="comment-text w-100">
                                           <h6 class="font-medium">   <%= comments[k].getCommentedUser()  %>          </h6> <span class="m-b-15 d-block">  <%= comments[k].getComment() %>           </span>
                                       </div>
                                    </div>
                                </div>
                               <%  } %>
                               <% } %>
                               
<!--                                <div class="comment-widgets">
                                    <div class="d-flex flex-row comment-row m-t-0">
                                       <div class="p-2"><img src="https://res.cloudinary.com/dxfq3iotg/image/upload/v1574583336/AAA/4.jpg" alt="user" width="50" class="rounded-circle"></div>
                                       <div class="comment-text w-100">
                                           <h6 class="font-medium">James Thomas</h6> <span class="m-b-15 d-block">This is awesome website. I would love to comeback again. </span>
                                       </div>
                                    </div>
                                </div>-->
                            </div>
                              
                         <div style="padding: 10px;">
                            <form method="post" action="<%= request.getContextPath() %>/homeAddcomment">

                            <div class="d-flex flex-row align-items-start">
                                <img class="rounded-circle" src="<%= new getuserDP().getDP(new connect().getCon(), uname)  %>" width="35" height="30">
                                <textarea name="comment" class="form-control ml-1 shadow-none textarea" placeholder="Add a comment"></textarea>
                            </div>
                                <input type="hidden" name="authorname" value="<%= FeedArray[i].getUsername()%>" />
                                <input type="hidden" name="postno" value="<%= FeedArray[i].getPostNo() %>" />
                                <input type="hidden" name="commentTablename" value="<%= FeedArray[i].getCommentTableName() %>" />

                            <div class="mt-2 text-right">
                                <%  
                                    String username = "";
                                   if(cookie != null){
                                       for(int j=0;j<cookie.length;j++){
                                         String cName = cookie[j].getName();
                                         String cValue = cookie[j].getValue();
                                        if(cName.equals("username")){
                                              username = cValue;
                                        }
                                       } %>
                             <button class="btn btn-primary btn-sm shadow-none" type="submit" name="commentername" value="<%= username %>">comment</button>
                             <% } %>
                                <button class="btn btn-outline-primary btn-sm ml-1 shadow-none" type="button" >Cancel</button>
                            </div>
                            </form>
                         </div>
                        </div>
                            
                    </div>
                             <% }%>
                            <%  } %>
                </div>
                               
                   
        </div>
            </div>
        </div>
<!--                <div class="panel panel-white post">
                    <div class="post-heading">
                        <div class="pull-left image">
                            <img src="ux interview.jpg" class="img-circle avatar" alt="user profile image">
                        </div>
                        <div class="pull-left meta">
                            <div class="title h5">
                                <a href="#"><b>John Doe</b></a>
                                uploaded a photo.
                            </div>
                            <h6 class="text-muted time">5 seconds ago</h6>
                        </div>
                    </div>
                    <div class="post-image">
                        <img src=""><img src="hum.jpeg" class="image" alt="image post">
                    </div>
                </div>-->
            </div>
        </div>
        
    </div>

</div>
</div>
</body>
</html>