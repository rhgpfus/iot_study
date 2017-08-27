package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.UserService;
import service.impl.UserServiceImpl;

public class UserServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private UserService us = new UserServiceImpl();
	
	 public void doGet(HttpServletRequest requst, HttpServletResponse respons) throws IOException, ServletException{
		
	 }
	 
	 public void doPost(HttpServletRequest requst, HttpServletResponse respons) throws IOException, ServletException{
		 requst.setCharacterEncoding("UTF-8");
		 
		 String command = requst.getParameter("command");
		 if(command==null) {
			 doProcess(respons, "잘못된 요청입니다."); 
		 }else if(command.equals("sign")) {
			 String id = requst.getParameter("id");
			 String pwd = requst.getParameter("pwd");
			 String name = requst.getParameter("name");
			 String[] hobbys = requst.getParameterValues("hobby");
			 String hobby = "";
			 for(String h : hobbys) {
				 hobby += h + ",";
			 }
			 hobby = hobby.substring(0, hobby.length()-1);
			 Map<String, String> hm  = new HashMap<String, String>();
			 hm.put("id", id);
			 hm.put("pwd", pwd);
			 hm.put("name", name);
			 hm.put("hobby", hobby);
			 String result = us.insertUser(hm);
			 doProcess(respons, result);
		 }else if(command.equals("login")) {
			 String id = requst.getParameter("id");
			 String pwd = requst.getParameter("pwd");
			 Map<String, String> hm  = new HashMap<String, String>();
			 hm.put("id", id);
			 hm.put("pwd", pwd);
			 Map<String, String> resultMap  = us.selectUser(hm);
			 String url = "location.href='/user/login.jsp'";
			 if(resultMap.get("id")!=null) {
				 HttpSession session = requst.getSession();
				 session.setAttribute("user", resultMap);
				 url = "location.href='/main.jsp'";
			 }
			 String result = "<script>";
			 result += "alert('" + resultMap.get("result") + "');";
			 result += url;
			 result += "</script>";
			 doProcess(respons, result);
		 }else if(command.equals("logout")) {
			 HttpSession session = requst.getSession();
			 session.invalidate();
			 respons.sendRedirect("/user/login.jsp");
		 }else if(command.equals("delete")) {
			 String user_no = requst.getParameter("user_no");
			
			 Map<String, String> hm  = new HashMap<String, String>();
			 hm.put("user_no", user_no);
			int rCnt = us.deleteUser(hm);
			 String result ="삭제실패";
			 if(rCnt==1) {
					result = "회원탈퇴에 성공하셨습니다.";
					result += "<script>";
					result += "alert('회원탈퇴에 성공하셨습니다.');";
					result += "</script>";
				}
			 doProcess(respons, result);
		 }else if(command.equals("update")) {
			 String user_no = requst.getParameter("user_no");
			 String id = requst.getParameter("id");
			 String pwd = requst.getParameter("pwd");
			 String name = requst.getParameter("name");
			 String[] hobbys = requst.getParameterValues("hobby");
			 String hobby = "";
			 for(String h : hobbys) {
				 hobby += h + ",";
			 }
			 hobby = hobby.substring(0, hobby.length()-1);
			
			 Map<String, String> hm  = new HashMap<String, String>();
			 hm.put("user_no", user_no);
			 hm.put("id", id);
			 hm.put("pwd", pwd);
			 hm.put("name", name);
			 hm.put("hobby", hobby);
			 int rCnt = us.updateUser(hm);
			 String result = "수정실패";
			 if(rCnt!=0) {
				 result = "수정성공";
			 }
			 doProcess(respons, result); 
		 }else if(command.equals("list")) {
			 Map<String, String> hm = new HashMap<String, String>();
				List<Map<String,String>> userList = us.selectUserList(hm);
				String result = "<table border='1'>";
				for(Map<String,String> m : userList) {
					result += "<tr>";
					result += "<td>" + m.get("name") +"</td>";
					result += "<td>" + m.get("id") +"</td>";
					result += "<td>" + m.get("hobby") +"</td>";
					result += "</tr>";
				}
				result += "</table>";
				doProcess(respons, result);
			}
	 }
	 
	 public void doProcess(HttpServletResponse respons, String writerStr)throws IOException{
		 respons.setContentType("text/html; charset=UTF-8");
		 PrintWriter out = respons.getWriter();
		 out.println(writerStr);
	 }
	 
	
}
