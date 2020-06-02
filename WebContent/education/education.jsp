<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>教务人员界面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<style type="text/css">
    body{ background-image:url(image/xm.jpg);no-repeat left top;background-size:100%;}
    </style>
    <style type="text/css">
    body{ background-image:url(image/timg.jpg);no-repeat left top;background-size:100%;}
    </style>
<!-- 	<link rel="stylesheet" type="text/css" href="styles.css">	 -->
	
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
     font-size:30px;
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
	    <a href="${pageContext.request.contextPath }/main/login.jsp" style="color:red">退&nbsp;出&nbsp;&nbsp;&nbsp;&nbsp;</a>
  </div>
  <body>
    <table class="altrowstable" id="alternatecolor" align="center">
    <tr><td><a href="manageStudent.do">查看/删除学生消费记录</a></td></tr>
    <tr><td><a href="listCards.do">一卡通查询余额/充值</a></td></tr> 
    <tr><td><a href="listStudent.do">图书借阅查询</a></td></tr>           
    </table>
  </body>
</html>
