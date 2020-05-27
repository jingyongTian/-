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
     font-size:15px;
     color:#333333;
     border-width: 1px;
     border-color: #a9c6c9;
     border-collapse: collapse;
     margin：0;
     float:center;
 }
 table.altrowstable th {
     border-width: 11px;
     padding: 8px;
     border-style: solid;
     border-color: #a9c6c9;
 }
 table.altrowstable td {
     border-width: 3px;
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
    <table class="altrowstable" id="alternatecolor" align="center">
     <div style="margin-top:80px; text-align:center;">
        <h1>图书信息管理</h1>
        </div>
     <tr align="center">
      <td>图书编号</td>
      <td>图书名称</td>
      <td>出版社</td>
      <td>作者</td>
      <td>借阅期限</td>
      <td>借还状态</td>  
      <td colspan="2">操作</td> 
     </tr>
     <%List<Book>books=(List<Book>)request.getAttribute("books");
     for(int i=0;i<books.size();i++){
      Book b=books.get(i);
     %>
     <tr>
      <td> <%=b.getBookid() %></td>
      <td> <%=b.getBookname() %></td>
      <td> <%=b.getChubanshe()%></td>
      <td> <%=b.getZuozhe() %></td>
      <td> <%=b.getQixian() %></td>
      <td> <%=b.getBookstate()%></td>
      <td><a href="delBook.do?bookid=<%=b.getBookid()%>">删除</a></td>
      <td><a href="loadBook.do?id=<%=b.getBookid()%>">修改</a></td>    
     </tr>
     <%}%>
      <tr><td><a href="admin/addBook.jsp">增加图书信息</a></td></tr>
      <tr><td><a href="admin/admin.jsp">返回操作主界面</a></td></tr> 
      <td><input type="button" class="btn"  value="返回上一级" onclick="javascript:history.go(-1);"/></td>
    </table> 
  </body>
</html>
