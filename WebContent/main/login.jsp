<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
  
<html>
  <head>    
    <title>登陆界面</title> 
    <script type="text/javascript" src="../js/myJs.js"></script>
    <script type="text/javascript" src="../js/prototype-1.6.0.3.js"></script>   
    <script type="text/javascript" src="../js/jquery-1.4.3.js"></script>
  <script type="text/javascript">      
    //设置登录变量
    var flag={"idcheck":false,"pwdcheck":false};
    //检查用户名
     $(function(){
        $("#idtext").blur(function(){
         $("#idmsg").html(""); 
         var idtext= $("#idtext").val() ;                    
          if(idtext==""){          
          $("#idmsg").html("用户名不能为空..."); 
          return;       
       }else{ 
         flag.idcheck=true;         
             }         
       }); 
     });     
     //检查密码
     $(function(){
        $("#pwdtext").blur(function(){
           var pwdtext= $("#pwdtext").val();
           $("#pwdmsg").html("");
           if(pwdtext==""){
             $("#pwdmsg").html("密码不能为空...");
             return;
           }else{
             flag.pwdcheck=true;
           }
        });
     
     });
     //取消默认登录
     $(function(){
       $("#loginf").submit(function(){        
        if((!flag.idcheck)||(!flag.pwdcheck)){
         return false;
        }else{
          return true;
        }
       });
     }); 
     
  </script>
  <link href="login.css" rel="stylesheet" type="text/css" />
  </head>  
  <body>   
   <form action="login.do" id="loginf">   
   
   
   
   <div class="login_box">
      <div class="login_l_img"><img src="images/login-img.png" /></div>
      <div class="login">
          <div class="login_logo"><a href="#"><img src="images/login_logo.png" /></a></div>
          <div class="login_name">
               <p>校园一卡通管理平台</p>
          </div>
          <form method="post">
      <div class="formRow user">
        <input type="text" id="idtext" name="id" placeholder="用户名" class="input_text input-big">
        <span id="idmsg" style="font-size: 20px; color: red"></span>
      </div>
      <div class="formRow password">
      	<input type="password" name="mm" id="pwdtext" placeholder="密码" class="input_text input-big">
      	<span id="pwdmsg" style="font-size: 20px; color: red"></span>
      </div>
 <div>  用户类型： 
     学生  <input  name="type" type="radio" checked="checked" value="1">
                       管理员  <input  name="type" type="radio"   value="2">
                         教务人员    <input  name="type" type="radio"   value="3" >                         
     </div>    
              <input id="dl" type="submit"  value="登录" style="width:100%;" type="submit">
                
          </form>
      </div>
</div>

  </body>
</html>
