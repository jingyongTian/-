package com.njit.card.servlet.student;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.njit.card.entity.Login;

public class StudentAction extends HttpServlet {
public void service(HttpServletRequest request, HttpServletResponse response)
	 throws ServletException, IOException {
request.setCharacterEncoding("utf-8"); 
String uri = request.getRequestURI();
String action = uri.substring(uri.lastIndexOf("/"), uri
		.lastIndexOf(".")); 
if(action.equals("/listRegist")){
	 

}
}
}
