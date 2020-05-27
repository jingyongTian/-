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
  <body>
    <table class="altrowstable" id="alternatecolor" align="center" cellpadding="20px"> 
    <tr>
    <td align="center"><P>卡号</P></td>
    <td align="center"><P>食品编号</P></td>
    <td align="center"><P>食品名称</P></td>
    <td align="center"><P>食品单价</P></td>
    <td align="center"><P>消费窗口</P></td>
    <td align="center"><P>消费时间</P></td>
    </tr>
      <%List<FoodItem> foodItems=(List<FoodItem>)request.getAttribute("foodItems");
      List<CostRecord>  records= (List<CostRecord>)request.getAttribute("records");
       for(int i=0;i<foodItems.size()-1;i++){
         FoodItem item=foodItems.get(i);
         CostRecord c =records.get(i);      
       %>
       <tr class="row<%=i%2+1%>">
       <td align="center"><p><%=c.getCardid()%></p></td>
       <td align="center"><p><%=c.getFoodid()%></p></td>
       <td align="center"><p><%=item.getFoodname()%></p></td>
       <td align="center"><p><%=item.getDanjia() %></p></td>
       <td align="center"><p><%=item.getChuangkou()%></p></td>
       <td align="center"><p><%=c.getCosttime() %></p></td> 
       <td><p><a href="delFoodRecord.do?foodid=<%=c.getFoodid()%>&cardid=<%=c.getCardid()%>">删除</a></p></td>      
       </tr>
       <%}%>
      <tr><td><a href=""></a></td></tr>   
    </table>
  </body>
</html>
