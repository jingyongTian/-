<%@page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
<%@page import="com.njit.card.entity.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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

<style type="text/css">
.row1 td {
	background: 00ff20;
}

.row2 td {
	background: a0ff20;
}
</style>
</head>
<div class="col-sm-2" style="padding-top:20px,;" align="right">
	    <a href="${pageContext.request.contextPath }/main/login.jsp">退&nbsp;出&nbsp;&nbsp;&nbsp;&nbsp;</a>
  </div>
<body>
	<table class="altrowstable" id="alternatecolor" align="center">
		<tr>
			<td><p>学号</p>
			</td>
			<td><p>姓名</p>
			</td>
			<td><p>性别</p>
			</td>
			<td><p>年龄</p>
			</td>
			<td><p>班级</p>
			</td>
			<td><p>专业</p>
			</td>
			<td><p>学院</p>
			</td>
			<td><p>籍贯</p>
			</td>
			<td><p>住址</p>
			</td>
			<td><p>入学时间</p>
			</td>
			<td colspan="1"><p>操作</p>
			</td>
		</tr>
		<%
			List<Student> students = (List<Student>) request
					.getAttribute("students");
			for (int i = 0; i <= students.size() - 1; i++) {
				Student s = students.get(i);				 
		%>
		<tr class="row<%=i % 2 + 1%>">
			<td><p><%=s.getStudentid()%></p>
			</td>
			<td><p><%=s.getXingming()%></p>
			</td>
			<td><p><%=s.getXingbie()%></p>
			</td>
			<td><p><%=s.getNianling()%></p>
			</td>
			<td><p><%=s.getBanji()%></p>
			</td>
			<td><p><%=s.getZhuanye()%></p>
			</td>
			<td><p><%=s.getXuyuan()%></p>
			</td>
			<td><p><%=s.getJiguan()%></p>
			</td>
			<td><p><%=s.getZhuzhi()%></p>
			</td>
			<td><p><%=s.getRuxueshijian()%></p>
			</td>
			<td><p>
					<a href="listBookRecord.do?id=<%=s.getStudentid()%>">查看</a>
				</p>
			</td>
		</tr>
		<%
			}
		%>
		 <tr><td><a href="count/count.jsp">返回操作主界面</a></td></tr> 
	</table>
</body>
</html>
