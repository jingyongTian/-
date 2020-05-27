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
    <title>学生界面</title>
    
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
a{
text-decoration:none;
}
.left{
float:left;
}
.row1 td {
    background: 00ff20 ;
}
.row2 td {
    background: a0ff20 ;
}
a{
text-decoration:none;
}
 table.altrowstable {
     font-family: verdana,arial,sans-serif;
     font-size:16px;
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
h1{
text-align:center;
}
 </style>

  </head>  
   <div class="col-sm-2" style="padding-top:20px,;" align="right">
	    <a href="${pageContext.request.contextPath }/main/login.jsp">退&nbsp;出&nbsp;&nbsp;&nbsp;&nbsp;</a>
  </div>
  <body>
  
    <thead> <h1>个人信息如下</h1></thead>  
    <div class="left"> 
   <table class="altrowstable" id="alternatecolor" >
    <tr><td>我的基本信息</td><td><a href="listRegist.do"> 查看基本信息</a></td></tr>
    <tr><td>我的生活消费</td><td><a href="listFoodItem.do">食堂消费</a></td></tr>
    <tr><td>我的图书馆：</td><td><a href="listBook.do">借阅图书</a></td></tr>  
    </table>
    </div>
    <div>
   <%Student  s=(Student)request.getAttribute("student");    
    %> 
<%--     <%Student  mm=(String)request.getAttribute("mm"); %> --%>
	<form action="repassword.do" method="post">
    <table class="altrowstable" id="alternatecolor" align="center">
      <tr>            
      <td>
      <p>学号：</p>      
      </td>     
      <td>
      <input  id="sid" name="id" type="text" value="<%=s.getStudentid() %>" readonly>
      </td>      
      </tr>
       <tr>      
      <td><p>姓名：</p></td>
      <td><input id="sname" name="" type="text" value="<%=s.getXingming() %>" readonly/></td>
        </tr>
        <tr>      
      <td><p>密码：</p></td>
      <td><input id="spsword" name="mm" type="password" value="${mm}" /></td>
      <td width = 35px><input id="bc" type="submit"  value="保存"</td>
        </tr>
       <tr>      
      <td>
      <p>性别：</p>      
      
      </td>
      <td><input id="ssex" name="" type="text" value="<%=s.getXingbie() %>" readonly/></td>
      
      </tr>
       <tr>
      
      <td>
      <p>年龄：</p>
      
      </td>
      <td><input id="sage" name="" type="text" value="<%=s.getNianling() %>" readonly/></td>
      
      </tr>
     <tr>
      
      <td>
      <p>班级：</p>
      
      </td>
      <td><input id="sclass" name="" type="text" value="<%=s.getBanji() %>" readonly/></td>
      
      </tr>
       <tr>
      
      <td>
      <p>专业：</p>
      
      </td>
      <td><input id="szy" name="" type="text" value="<%=s.getZhuanye()%>" readonly/></td>
      
      </tr>
       <tr>
      
      <td>
      <p>学院：</p>
      
      </td>
      <td><input id="sxy" name="" type="text" value="<%=s.getXuyuan() %>" readonly/></td>
      
      </tr>
       <tr>
      
      <td>
      <p>籍贯：</p>
      
      </td>
      <td><input id="sjg" name="" type="text" value="<%=s.getJiguan() %>" readonly/></td>
      
      </tr>
       <tr>
      
      <td>
      <p>住址：</p>
      
      </td>
      <td><input id="szz" name="" type="text" value="<%=s.getZhuzhi() %>" readonly/></td>
      
      </tr>
       <tr>
      
      <td>
      <p>入学时间：</p>
      
      </td>
      <td><input id="srxsj" name="" type="text" value="<%=s.getRuxueshijian() %>" readonly/></td>
      
      </tr>
      <tr><td><a href="student/student.jsp">返回学生操作主界面</a></td>
      </tr>
      <td><input type="button" class="btn"  value="返回上一级" onclick="javascript:history.go(-1);"/></td>
       </tr>
    </table>
    </form>
    </div>	
  </body>
</html>
