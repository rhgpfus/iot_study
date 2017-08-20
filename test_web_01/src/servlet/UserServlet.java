package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialException;

import common.DBConnector;

public class UserServlet extends HttpServlet{

	 public void doGet(HttpServletRequest requst, HttpServletResponse respons) throws IOException, ServletException{
		
	 }
	 
	 public void doPost(HttpServletRequest requst, HttpServletResponse respons) throws IOException, ServletException{
		 requst.setCharacterEncoding("UTF-8");
		 
		 String id = requst.getParameter("id");
		 String pwd = requst.getParameter("pwd");
		 String name = requst.getParameter("name");
		 String[] hobbys = requst.getParameterValues("hobby");
		 String hobby = "";
		 for(String h : hobbys) {
			 hobby += h + ",";
		 }
		 hobby = hobby.substring(0, hobby.length()-1);
		 String result = "실패";
		 Connection con;
		 try {
			 con = DBConnector.getCon();
			 String sql = "insert into user(id,name,password,hobby)" + 
			 		" values(?,?,?,?)";
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setString(1, id);
			 ps.setString(2, pwd);
			 ps.setString(3, name);
			 ps.setString(4, hobby);
			 int resultOk = ps.executeUpdate();
			 if(resultOk!=0) {
				 result = "성공";
				 con.commit();
			 }
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		 doProcess(respons, result);
	 }
	 
	 public void doProcess(HttpServletResponse respons, String writerStr)throws IOException{
		 respons.setContentType("text/html; charset=UTF-8");
		 PrintWriter out = respons.getWriter();
		 out.println(writerStr);
	 }
	 
	
}
