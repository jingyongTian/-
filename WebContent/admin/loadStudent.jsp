<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%@ page import="com.njit.card.entity.*"%>

<html>
  <head>
      
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

  </head>
  <div class="col-sm-2" style="padding-top:20px,;" align="right">
	    <a href="${pageContext.request.contextPath }/main/login.jsp">退&nbsp;出&nbsp;&nbsp;&nbsp;&nbsp;</a>
  </div>   
  <body>
  <p>您可以修改学生信息，然后提交</p>
  <%Student s=(Student)request.getAttribute("student"); %>
  <form action="updateStudent.do" method="post">
     <table class="altrowstable" id="alternatecolor">
     <tr>     
     <td><p>学生编号：</p></td> <td><p><input name="studentid" readonly value="<%=s.getStudentid() %>"/></p></td>
     </tr>
     <tr>
     <td><p>学生姓名：</p></td>  <td><p><input name="xingming"  value="<%=s.getXingming() %>"/></p></td>
      </tr>
      <tr>
     <td><p>密码：</p></td>  <td><p><input   name="mm"    value="${mm}"/></p></td>
      </tr>
       <tr>
     <td><p>性别：</p></td>  <td><p><input   name="xingbie"    value="<%=s.getXingbie() %>"/></p></td>
      </tr>
       <tr>
     <td><p>年龄：</p></td>  <td><p><input name="nianling"   value="<%=s.getNianling() %>"/></p></td>
      </tr>
       <tr>
     <td><p>班级：</p></td>  <td><p><input name="banji"      value="<%=s.getBanji() %>"/></p></td>
      </tr>
       <tr>
     <td><p>专业：</p></td>  <td><p><input name="zhuanye"    value="<%=s.getZhuanye() %>"/></p></td>
     </tr>
        <tr>
     <td><p>学院：</p></td>  <td><p><input name="xueyuan"    value="<%=s.getXuyuan() %>"/></p></td>
      </tr>
      <tr>
     <td><p>籍贯：</p></td>  <td><p><input name="jiguan"     value="<%=s.getJiguan() %>"/></p></td>
      </tr>
       <tr>
     <td><p>住址：</p></td><td><p><input name="zhuzhi"       value="<%=s.getZhuzhi() %>"/></p></td>
      </tr>
       <tr>
     <td><p>入学时间：</p></td><td><p><input name="ruxueshijian" value="<%=s.getRuxueshijian() %>"/></p></td>
     </tr>
      <tr><td><p><input type="submit" value="提交修改信息"></p></td></tr>     
     <tr><td><p><a href="../../Card/admin/addStudent.jsp">增加学生信息</a></p></td></tr>
     <tr><td><a href="admin/admin.jsp">返回操作主界面</a></td></tr> 
     </table>
     </form>
  </body>
</html>
