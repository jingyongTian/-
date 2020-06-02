<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.njit.card.entity.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">    
    <title>已借图书信息</title>    
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
   <table class="altrowstable" id="alternatecolor">
   <tr>
   <td><p>图书编号</p></td>
   <td><p>图书名称</p></td>
   <td><p>出版社</p></td>
   <td><p>作者</p></td>   
   <td><p>还书操作</p></td>   
   </tr>    
    <%List<Book> books=(List<Book>)request.getAttribute("bookrecords");
     for(int i=0;i<=books.size()-1;i++){
     Book b=books.get(i);     
     %>
     <tr>
     <td><P><%=b.getBookid()%></P></td>
     <td><P><%=b.getBookname() %></P></td>
     <td><P><%=b.getChubanshe() %></P></td>
     <td><P><%=b.getZuozhe() %></P></td>
     <td><P><a href="giveBook.do?id=<%=b.getBookid()%>">还书</a></P></td>    
     </tr>
     <%}%> 
      <c:choose>
	    <c:when test="${type==3}">
	        <tr><td><a href="education/education.jsp">返回操作主界面</a></td></tr> 
	    </c:when>
	    <c:otherwise>
	      <tr><td><a href="listBook.do">点击这里，继续借书</a></td></tr>
    	  <tr><td><a href="student/student.jsp">返回学生操作主界面</a></td></tr> 
	    </c:otherwise>
	</c:choose>  
<!--     <tr><td><a href="listBook.do">点击这里，继续借书</a></td></tr> -->
<!--     <tr><td><a href="student/student.jsp">返回学生操作主界面</a></td></tr>  -->
   </table> 
  </body>
</html>
