<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.njit.card.entity.*" %>
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
 table.altrowstable td {
     border-width: 1px;
     padding: 8px;
     border-style: solid;
     border-color: #a9c6c9;
 }
 a{
text-decoration:none;
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
  <%FoodItem f=(FoodItem)request.getAttribute("f"); %>
   <form action="updateFoodItem.do" method="post">
     <table class="altrowstable" id="alternatecolor">
      <tr><td>菜品编号：</td><td><input type="hidden" name="foodid" value="<%=f.getFoodid() %>"/></td></tr>
      <tr><td>菜品名称：</td><td><input name="foodname" value="<%=f.getFoodname() %>"/></td></tr>
      <tr><td>菜品单价：</td><td><input name="danjia"  value="<%=f.getDanjia() %>"/></td></tr>
      <tr><td>所在窗口：</td><td><input name="chuangkou" value="<%=f.getChuangkou() %>"/></td></tr>
      <tr><td><input type="submit"   value="修改菜品"/></td></tr>
      <tr><td><a href="admin/admin.jsp">返回操作主界面</a></td></tr> 
      <td><input type="button" class="btn"  value="返回上一级" onclick="javascript:history.go(-1);"/></td>
     </table>
   </form>
  </body>
</html>
