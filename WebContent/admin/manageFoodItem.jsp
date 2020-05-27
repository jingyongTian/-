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

<style type="text/css">
    .row1 td {
        background: 00ff20 ;
    }
    .row2 td {
        background: a0ff20 ;
    }
    </style>
  </head>
  <div class="col-sm-2" style="padding-top:20px,;" align="right">
	    <a href="${pageContext.request.contextPath }/main/login.jsp">退&nbsp;出&nbsp;&nbsp;&nbsp;&nbsp;</a>
  </div>
  <body>
    <table class="altrowstable" id="alternatecolor">
      <tr>
      <td>食品编号</td>
      <td>食品名称</td>
      <td>单价</td>
      <td>窗口</td>
      <td colspan="2">操作</td>       
      </tr>
      <%List<FoodItem>foodItems=(List<FoodItem>)request.getAttribute("foodItems");
       for(int i=0;i<foodItems.size();i++){
       FoodItem f=foodItems.get(i);
       %>
       <tr class="row<%=i%2+1%>">
         <td><%=f.getFoodid() %></td>
         <td><%=f.getFoodname() %></td>
         <td><%=f.getDanjia() %></td>
         <td><%=f.getChuangkou() %></td>
         <td><a href="delFoodItem.do?id=<%=f.getFoodid()%>">删除</a></td>
         <td><a href="loadFoodItem.do?id=<%=f.getFoodid()%>">修改</a></td>     
       </tr>       
       <% } %>
      <tr><td><a href="admin/addFoodItem.jsp">增加菜品</a></td></tr>
       <tr><td><a href="admin/admin.jsp">返回操作主界面</a></td></tr> 
       <td><input type="button" class="btn"  value="返回上一级" onclick="javascript:history.go(-1);"/></td>
      
    </table>
  </body>
</html>
