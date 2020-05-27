<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">    
    <title>管理员界面</title>    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<style type="text/css">
    body{  background: #93defe url(images/bgimage.jpg) no-repeat fixed top;}
    </style>
	<link rel="stylesheet" type="text/css" href="styles.css">	
	
	<script type="text/javascript">
function altRows(id){
     if(document.getElementsByTagName){  
         
         var table = document.getElementById(id);  
         var rows = table.getElementsByTagName("tr"); 
          
         for(i = 0; i < rows.length; i++){          
             if(i % 2 == 0){
                 rows[i].className = "evenrowcolor";
             }else{
                 rows[i].className = "oddrowcolor";
             }      
         }
     } }
 
 window.onload=function(){
     altRows('alternatecolor');
 }
 </script>
 
 
 <!-- CSS goes in the document HEAD or added to your external stylesheet -->
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
  a{
text-decoration:none;
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
  <div class="col-sm-2" style="padding-top:20px,;" align="right">
	    <a href="${pageContext.request.contextPath }/main/login.jsp">退&nbsp;出&nbsp;&nbsp;&nbsp;&nbsp;</a>
  </div>    
  <body>
  <form action="addCard.do" method="post">  
    <table class="altrowstable" id="alternatecolor">
    <tr><td><p>卡编号：   </p></td>  <td><p><input name="cardid"/></p></td></tr>
    <tr><td><p>密码：       </p></td>  <td><p><input name="mm"/></p></td></tr>
    <tr><td><p>持卡人：   </p></td>  <td><p><input name="username"/></p></td></tr>
    <tr><td><p>卡上余额：</p></td>  <td><p><input name="balance"/></p></td></tr>
    <tr><td><p>卡的状态：</p></td>  <td><p><input name="cardstate"/></p></td></tr>
    <tr><td><p>学生学号：</p></td>  <td><p><input name="studentid"/></p></td></tr>
     <tr><td><p><input type="submit"/></p></td> <td><p><input type="reset"/></p></td></tr>
    </table>
    <tr><td><a href="admin/admin.jsp">返回操作主界面</a></td></tr> 
  </form>
  </body>
</html>
