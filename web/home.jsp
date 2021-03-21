<%-- 
    Document   : home
    Created on : 21 Jan, 2021, 10:44:11 AM
    Author     : Naveen Prasad
--%>

<%@page import="models.user"%>
<%@page import="database.getuserDP"%>
<%@page import="models.Comment"%>
<%@page import="java.io.OutputStream"%>
<%@page import="models.Post"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="database.addPost"%>
<%@page import="database.connect"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.io.InputStream"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="assets/homepage.css">
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
    <link href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'>
    <script src="https://kit.fontawesome.com/a076d05399.js"></script>
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" />
    </head>
<style>
    @import url('https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap');
    body {
  background-color: #eee;
  font-family: 'Poppins', sans-serif;
}

.time {
  font-size: 9px !important
}

.socials i {
  margin-right: 14px;
  font-size: 17px;
  color: #d2c8c8;
  cursor: pointer
}

.feed-image img {
  width: 100%;
  height: auto;
}
body {
    background: #eee;
}

.date {
    font-size: 11px;
}

.comment-text {
    font-size: 12px;
}

.fs-12 {
    font-size: 12px;
}

.shadow-none {
    box-shadow: none;
}

.name {
    color: #007bff;
}

.cursor:hover {
    color: blue;
}

.cursor {
    cursor: pointer;
}

.textarea {
    resize: none;
}

#gen {
       margin-right: auto;
      }
#exampleFormControlFile1 {
           margin-top: 3%;
        }
.list{
  padding:2.3%;
 
}
a{
  color: rgb(63, 61, 61);
  font-weight: 500;
}

.list a:hover{
  color: blue;
  text-decoration: none;
}

</style>
<!--<script>
     var count=0;
     function dk(str)
     {
       alert(str);
       console.log(str);
//       if(count%2===0)
//       { 
//         mk();
//         count+=1;
//
//       }
//       else{
//             mk1();
//             count+=1;
//       }
     }
    function mk()
         {  
          var s=document.querySelector("#sm").style.visibility="visible";
          var m=document.querySelector('.sm').style.display="unset";
      }
    function mk1()
    {
      var s=document.querySelector("#sm").style.visibility="hidden";
      var m=document.querySelector(".sm").style.display="none";
      // var k=document.querySelector(".sm").style.margin-left="20%";
    }
      </script>-->
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
        var e={};
        function like(x){
            var i = 'r'.concat(x);
            var j = 'rr'.concat(x);
            if(e[i]===undefined)
            {
                e[i]=1;
                console.log(e);
            }
            else
            {
                e[i]=e[i]+1;
                console.log(e);
                console.log("2nd");
            }
            if(e[i]%2!==0)
            {   
                 var s=document.querySelector("."+i).style.color="blue";
                 var m=document.querySelector("."+j).style.color="blue";
            }
            if(e[i]%2===0)
            {    
                 var s=document.querySelector("."+i).style.color="black";
                 var m=document.querySelector("."+j).style.color="black";
            }
        }
       
    </script>
      <body>
        <% 
          
          Cookie[] cookie = request.getCookies();
          if(cookie != null){
              for(int i=0;i<cookie.length;i++){
                  String cName = cookie[i].getName();
                  String cValue = cookie[i].getValue();
                  if(cName.equals("username")){
                      break;
                  }else if(i == (cookie.length-1)){
                      response.sendRedirect("login.jsp");
                      return;
                  }
                //  i++;
              }
          }else{
              response.sendRedirect("login.jsp");
              return;
          }
            %>

    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand" href="#">
            <img src="images/logo.png" width="40px"  class="d-inline-block align-top" alt="">
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
            <li class="nav-item active">
              <a class="nav-link" href="./home">Home <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="http://localhost:3000/index.html">Groups</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="navbar.html">Games</a>
            </li>
            <%
                                    String uname = "";
                                   if(cookie != null){
                                       for(int j=0;j<cookie.length;j++){
                                         String cName = cookie[j].getName();
                                         String cValue = cookie[j].getValue();
                                        if(cName.equals("username")){
                                              uname = cValue;
                                        }
                                       } %>
            <li class="nav-item">
                <a class="nav-link" href="./profile?username=<%= uname %>">Profile</a>
            </li>
            <% } %>
          </ul>
          
        </div>
      </nav>
        <!--</header>-->
        <%
             user userprofile = (user)request.getAttribute("profile");    
        %>
        <div class="container-fluid " style="background: burlywood;">
        <div class="page-inner no-page-title">
             <!--start page main wrapper--> 
<!--             <div id="main-wrapper"> -->
                <div class="row">
                    <div class="col-lg-4 col-xl-3 centerl" >
                        <div class="card card-white grid-margin">
                            <div class="card-heading clearfix">
                                <h4 class="card-title">User Profile</h4>
                            </div>
                            <div class="card-body user-profile-card mb-3">
                                <img src="<%= userprofile.getProfileimage() %>" class="user-profile-image rounded-circle" alt="" />
                                <h4 class="text-center h6 mt-2"><%= userprofile.getUsername() %></h4>
                                <p class="text-center small"><%= userprofile.getPlayerlevel() %> Gamer</p>
<!--                                <button class="btn btn-theme btn-sm">Follow</button>
                                <button class="btn btn-theme btn-sm">Message</button>-->
                            </div>
                            <hr />
                            <div class="card-heading clearfix mt-3">
                                <h4 class="card-title">User Profile</h4>
                            </div>
                            <div class="card-body mb-3">
                                <a href="#" class="label label-success mb-2">COC</a>
                                <a href="#" class="label label-success mb-2">PUBG</a>
                                <a href="#" class="label label-success mb-2">Call of Duty</a>
                                <a href="#" class="label label-success mb-2">Pro player</a>
                                <a href="#" class="label label-success mb-2">best player</a>
                                <a href="#" class="label label-success mb-2">WWE</a>
<!--                                <a href="#" class="label label-success"></a>
                                <a href="#" class="label label-success">UX</a>-->
                            </div>
                            <hr />
                            <div class="card-heading clearfix mt-3">
                                <h4 class="card-title">About</h4>
                            </div>
                            <div class="card-body mb-3">
                                <p class="mb-0"><%= userprofile.getAbout() %></p>
                            </div>
                            <hr />
                            <div class="card-heading clearfix mt-3">
                                <h4 class="card-title">Contact Information</h4>
                            </div>
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table class="table table-borderless mb-0 text-muted">
                                        <tbody>
                                            <tr>
                                                <th scope="row">Email:</th>
                                                <td><%= uname %></td>
                                            </tr>
                                            <tr>
                                                <th scope="row">Phone:</th>
                                                <td><%= userprofile.getPhone() %></td>
                                            </tr>
                                            <tr>
                                                <th scope="row">Address:</th>
                                                <td><%= userprofile.getAddress() %></td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                    
        <div class="col-lg-6">             
        <div class="container ">
        <div class="d-flex justify-content-center row">
            <div class="col-md-8">
                <div class="feed p-2">
                    <div class="d-flex flex-row justify-content-between align-items-center p-4 bg-white border">
                         
                        <div class="form-group">
                            <form method="post" action="<%= request.getContextPath() %>/home" enctype="multipart/form-data" >
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
                    </div>
                    <%  Post FeedArray[] = (Post[])request.getAttribute("feed"); %>
                    <% if(FeedArray.length > 0 ) { %>
                    <%  for(int i = 0;i<FeedArray.length;i++) { %>
                    <%  String id = "s"+String.valueOf(i); %>
                    <%  String id2 = "r"+String.valueOf(i); %>
                    <%  String id3 = "rr"+String.valueOf(i); %>
                    <%  System.out.println("LIKES "+FeedArray[i].getLikes());%>
                    <%  System.out.println(id);%>
                    <div class="bg-white border mt-2">
                        <div>
                            <div class="d-flex flex-row justify-content-between align-items-center p-2 border-bottom">
                                <div class="d-flex flex-row align-items-center feed-text px-2"><img class="rounded-circle" src="<%= FeedArray[i].getProfilepic() %>" width="50" height="45S">
                                    <div class="d-flex flex-column flex-wrap ml-2"><span class="font-weight-bold"> <%= FeedArray[i].getUsername() %>                 </span><span class="text-black-50 time">40 minutes ago</span></div>
                                </div>
                                <div class="feed-icon px-2"><i class="fa fa-ellipsis-v text-black-50"></i></div>
                            </div>
                        </div>
                        <div class="p-2 px-3"><span>  <%= FeedArray[i].getCaption() %>  </span></div>
                        <div class="feed-image p-2 px-3"><img class="img-fluid img-responsive" src="data:image/jpg;base64,<%= FeedArray[i].getBase64Image() %>" width="650"></div>
                        <div class="bg-white">
                            <div class="d-flex flex-row fs-12">
                                <div class="like p-2 cursor"><i class="fa fa-thumbs-up <%= id2%>" onclick="like(<%= i %>)"  ></i><span onclick="like(<%= i %>)"  class="ml-1 <%= id3%>"> <%=FeedArray[i].getLikes()%> Likes</span></div>
                                <div class="like p-2 cursor"><i onclick="" class="fa fa-comment"></i><span onclick="dk(<%= i %>)" class="ml-1">Comment</span></div>
                                <div class="like p-2 cursor"><i class="fa fa-share"></i><span class="ml-1">Share</span></div>
                            </div>
                        </div>
                         
                        <div class="bg-light p-2">
                            <div id="<%= id %>" class="sm" >
                               <% Comment comments[] =  FeedArray[i].getComments(); %>
                               <% if(comments.length > 0){ %>
                               <% for(int k=0;k<comments.length;k++){ %>

                                <div class="comment-widgets">
                                    <div class="d-flex flex-row comment-row m-t-0">
                                       <div class="p-2"><img src="<%=  comments[k].getProfileimg() %>" alt="user" width="50" class="rounded-circle"></div>
                                       <div class="comment-text w-100">
                                           <h6 class="font-medium">   <%= comments[k].getCommentedUser()  %>  </h6> <span class="m-b-15 d-block">  <%= comments[k].getComment() %>           </span>
                                       </div>
                                    </div>
                                </div>
                               <%  } %>
                               <% }else if(comments.length == 0){ %>
                                     <div class="comment-widgets">
                                    <div class="d-flex flex-row comment-row m-t-0">
                                       <!--<div class="p-2"><img src="https://res.cloudinary.com/dxfq3iotg/image/upload/v1574583336/AAA/4.jpg" alt="user" width="50" class="rounded-circle"></div>-->
                                       <div class="comment-text w-100">
                                           <h6 class="font-medium"></h6> <span class="m-b-15 d-block p-2">No comments available </span>
                                       </div>
                                    </div>
                                </div>
                               
                               
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
    </div>
        <div class="col-lg-1 col-xl-3">
        <div class="card card-white grid-margin">
            <div class="card-heading clearfix">
                <h4 class="card-title">Gamesup News</h4>
            </div>
            <div class="card-body" style="padding-top: 30px;">
                <ul >
                  <li class="list"><a href="https://www.bgr.in/gaming/faug-launch-in-india-fearless-and-united-guards-game-hit-5-million-downloads-and-becomes-top-free-game-on-google-play-store-know-how-to-download-faug-on-android-smartphone-936871/">FAU-G crosses 5 million downloads on Google Play store, becomes top free game app</a></li>
                  <li class="list"><a href="https://www.bgr.in/gaming/tesla-model-s-cyberpunk-witcher-3-sony-ps5-gaming-elon-musk-936570/">Elon Musk says his car can play Cyberpunk 2077</a></li>
                  <li class="list"><a href="https://www.bgr.in/gaming/pubg-mobile-global-championship-2020-how-to-watch-live-935577/">PUBG Mobile Global Championship 2020: How to watch live, everything you need to know</a></li>
                  <li class="list"><a href="https://www.bgr.in/gaming/ces-2021-razer-unveils-smart-mask-and-gaming-chair-with-a-rollout-display-933458/">CES 2021: Razer unveils smart mask and gaming chair with a rollout display</a></li>
                  <li class="list"><a href="https://www.bgr.in/gaming/gta-6-grand-theft-auto-female-protagonist-story-mode-launch-933264/">Grand Theft Auto 6 story mode could let you play as a female lead</a></li>
                  <li class="list"><a href="https://www.bgr.in/gaming/among-us-is-the-most-downloaded-mobile-game-for-2020-933041/">Among Us was the most downloaded mobile game for 2020</a></li>
                  <li class="list"><a href="https://www.bgr.in/gaming/among-us-is-the-most-downloaded-mobile-game-for-2020-933041/">Minecraft Earth by Microsoft is shutting down on 30 June</a></li>
                  <li class="list"><a href="https://www.bgr.in/news/minecraft-earth-by-microsoft-is-shutting-down-on-30-june-931768/">Assassin's Creed Valhalla is the best selling game in the franchise</a></li>
                  <li class="list"><a href="https://www.bgr.in/gaming/garena-free-fire-update-weapons-new-training-map-and-more-926679/">Garena Free Fire update: Revamped training map, new Vector Akimbo SMG & more</a></li>
                  <li class="list"><a href="https://www.bgr.in/news/minecraft-earth-by-microsoft-is-shutting-down-on-30-june-931768/">FarmVille to be shut down this year; will still be playable until December 31</a></li>
                  <li class="list"><a href="https://www.bgr.in/gaming/assassins-creed-valhalla-most-sold-game-929534/">Ubisoft Forward: Prince of Persia The Sands of Time Remake, Fenyx Rising announced</a></li>
                  <li class="list"><a href="https://www.bgr.in/gaming/watch-dogs-legion-review-price-gameplay-review-india-922228/">Epic Games to stop “Sign-in with Apple” logging from September 11</a></li>
                  <!-- <li><a href=""></a></li>
                  <li><a href=""></a></li>
                  <li><a href=""></a></li>
                  <li><a href=""></a></li> -->
                </ul>

            </div>
        </div>
        </div>
  </div>  
             </div>
        </div>                   
    </body>
</html>
















