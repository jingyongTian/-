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
    
    <title>教务人员界面</title>
    
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
 .oddrowcolor{
     background-color:#d4e3e5;
 }
  a{
text-decoration:none;
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
   <td><p>卡号</p></td>
   <td><p>密码</p></td>    
   <td><p>持卡人</p></td>
   <td><p>余额 </p></td>
   <td><p>卡状态 </p></td>  
   <td><p>操作</p></td> 
   </tr>
   <%List<Card>cards=(List<Card>)request.getAttribute("cards");   
   for(int i=0;i<=cards.size()-1;i++){
       Card  c=cards.get(i);
   %>
   <tr class="row<%=i%2+1%>">
   <td><p><%=c.getCardid() %></p></td>
   <td><p><%=c.getMm()%></p></td>
   <td><p><%=c.getUsername() %></p></td>
   <td><p><%=c.getBalance()  %></p></td>
   <td><p><%=c.getCardstate()%></p></td>
   <td width = 35px><a href="count/addValue.jsp?id=<%=c.getCardid()%>">充值</a></td>
   </tr>   
   <%}%>
   <tr><td><p><a  href="count/count.jsp">返回一卡通主管理界面</a></p></td></tr>
   </table> 
  </body>
</html>
