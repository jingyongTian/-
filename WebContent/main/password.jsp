<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
<title>信息内容功能</title>
	<style type="text/css">
    body{  background: #93defe url(images/bgimage.jpg) no-repeat fixed top;}
    </style>
	<link rel="stylesheet" type="text/css" href="styles.css">	
	<style type="text/css">
 table.altrowstable {
     font-family: verdana,arial,sans-serif;
     font-size:11px;
     color:#333333;
     border-width: 1px;
     border-color: #a9c6c9;
     border-collapse: collapse;
 }
 table.altrowstable th {
     border-width: 1px;
     padding: 8px;
     border-style: solid;
     border-color: #a9c6c9;
 }
 table.altrowstable td {
     border-width: 1px;
     padding: 8px;
     border-style: solid;
     border-color: #a9c6c9;
 }
 .oddrowcolor{
     background-color:#d4e3e5;
 }
 .evenrowcolor{
     background-color:#c3dde0;
}
 </style>
	
</head>
<body>
	<form id="form1" name="form1" action="repassword.do" method="post">
		<center>
			<table class="mobile" >
				<tr class="pageheader">
					<td colspan="2"><strong>密码修改</strong></td>
				</tr>
				<tr height="25">
					<td class="outDetail" style="width: 30px">旧密码： <label
						style="font-weight: bold; color: red"> * </label></td>
					<td class="outDetail2"><input type="password" id="oldPassword"
						 name="oldPassword" />
						<span id="oldmsg" style="font-size: 20px; color: red"></span>
					</td>
				</tr>
				<tr height="25">
					<td class="outDetail" style="width: 30%">新密码： <label
						style="font-weight: bold; color: red"> * </label></td>
					<td class="outDetail2"><input type="password" id="newPassword"
						 name="newPassword" />
						<span id="newmsg" style="font-size: 20px; color: red"></span>
					</td>
				</tr>
				<tr height="25">
					<td class="outDetail" style="width: 30%">新密码确认： <label
						style="font-weight: bold; color: red"> * </label></td>
					<td class="outDetail2"><input type="password" id="newPasswordConfirm"
						 name="newPasswordConfirm" />
						<span id="newconfmsg" style="font-size: 20px; color: red"></span>
					</td>
				</tr>
				
			</table>
		</center>
		<p align="center">
			<input type="submit" class="btn" value="保  存" /> 
		</p>
	</form>
</body>
	<script type="text/javascript" src="../js/myJs.js"></script>
    <script type="text/javascript" src="../js/prototype-1.6.0.3.js"></script>   
    <script type="text/javascript" src="../js/jquery-1.4.3.js"></script>
<script type="text/javascript">
	var flag={"oldcheck":false,"newcheck":false,"newconfcheck":false};
	$(function(){
	    $("#oldPassword").blur(function(){
	     $("#oldmsg").html(""); 
	     var oldPassword= $("#oldPassword").val() ;                    
	      if(oldPassword==""){          
	      $("#oldmsg").html("旧密码不能为空..."); 
	      return;       
	   }else{ 
	     flag.oldcheck=true;         
	         }         
	   }); 
	 });     
	 $(function(){
	        $("#newPassword").blur(function(){
	         $("#newmsg").html(""); 
	         var newPassword= $("#newPassword").val() ;                    
	          if(newPassword==""){          
	          $("#newmsg").html("新密码不能为空..."); 
	          return;       
	       }else{ 
	         flag.newcheck=true;         
	             }         
	       }); 
	     });     
	 $(function(){
	        $("#newPasswordConfirm").blur(function(){
	         $("#newconfmsg").html(""); 
	         var newPassword= $("#newPassword").val() ;
	         var newPasswordConfirm= $("#newPasswordConfirm").val() ;                    
	          if(newPasswordConfirm==""){          
	          $("#newconfmsg").html("确认密码不能为空..."); 
	          return;       
	       }else if(newPasswordConfirm !== newPassword){          
		          $("#newconfmsg").html("两次密码不同..."); 
		          return;
	      		 }
		         else{ 
			        flag.newconfcheck=true;         
			         }         
	       }); 
	     });  
	 $(function(){
	       $("#form1").submit(function(){        
	        if((!flag.oldcheck)||(!flag.newcheck)||(!flag.newconfcheck)){
	         return false;
	        }else{
	          return true;
	        }
	       });
	     }); 
	 
</script>
</html>
