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
 a{
text-decoration:none;
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
  <div class="col-sm-2" style="padding-top:20px,;" align="right">
	    <a href="${pageContext.request.contextPath }/main/login.jsp">退&nbsp;出&nbsp;&nbsp;&nbsp;&nbsp;</a>
  </div>
  <body>   
  <form action="addBook.do" method="post">
   <table class="altrowstable" id="alternatecolor" align="center">
   <tr><td>图书编号：</td><td> <input name="bookid"/></td></tr>
   <tr><td>图书名称：</td><td> <input name="bookname"/></td></tr>
   <tr><td>出版社：</td><td>   <input name="chubanshe"/></td></tr>
   <tr><td>作者：</td><td> <input name="zuozhe"/></td></tr>
   <tr><td>借阅期限：</td><td> <input name="qixian"/></td></tr>
   <tr><td>借还状态：</td><td> <input name="bookstate"/></td></tr>
    <tr><td><input type="submit" value="增加图书"></td><td> <input type="reset" value="取消增加"/></td></tr>
    <tr><td><a href="admin/admin.jsp">返回操作主界面</a></td></tr> 
    <td><input type="button" class="btn"  value="返回上一级" onclick="javascript:history.go(-1);"/></td>
   </table>
  </form>
  </body>
</html>
