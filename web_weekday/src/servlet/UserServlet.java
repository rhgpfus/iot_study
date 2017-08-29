package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.DBCon;
import service.UserService;
import service.UserServiceImpl;

public class UserServlet extends HttpServlet{

	
	private static final long serialVersionUID = 1L;
	private UserService us = new UserServiceImpl();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
			request.setCharacterEncoding("utf-8");
			String id = request.getParameter("id");
			System.out.println(id);
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter pw = response.getWriter();
			pw.println("입력하신 ID : " + id);
	} 
 
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String command = request.getParameter("command");
		System.out.println(command);
		if(command.equals("login")) {
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			Map<String, String> hm = us.getUserLogin(id, pwd);
			String result = "로긴실패";
			if(hm!=null) {
				HttpSession session = request.getSession();
				session.setAttribute("user", hm);
				result = "로긴에 성공";
				response.sendRedirect("/main.jsp");
			}
			doProcess(response, result);
		}else if(command.equals("signin")) {
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String[]  hobbies = request.getParameterValues("hobby");
			String hobby="";
			for(String h : hobbies) {
				hobby += h+",";
			}
			hobby = hobby.substring(0, hobby.length()-1);
			
			Map<String, String> hm = new HashMap<String, String>();
			hm.put("id", id);
			hm.put("pwd", pwd);
			hm.put("name", name);
			hm.put("hobby", hobby);
			String result = "회원가입 실패";
			int rCnt = us.insertUser(hm);
			if(rCnt==1) {
				result = "<script>";
				result += "alert('회원가입 성공');";
				result += "location.href='/main.jsp';";
				result += "</script>";
			}
			
			doProcess(response, result);
		}else if(command.equals("logout")) {
			HttpSession session = request.getSession();
			session.invalidate();
			//session이 가지고있는 키값 날림.
			response.sendRedirect("/login.jsp");
		}else if(command.equals("modify")) {
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String[]  hobbies = request.getParameterValues("hobby");
			String hobby="";
			for(String h : hobbies) {
				hobby += h+",";
			}
			hobby = hobby.substring(0, hobby.length()-1);
			
			Map<String, String> hm = new HashMap<String, String>();
			hm.put("id", id);
			hm.put("pwd", pwd);
			hm.put("name", name);
			hm.put("hobby", hobby);
			String result = "수정 실패";
			HttpSession session = request.getSession();
			Map<String, String> user = (Map)session.getAttribute("user");
			hm.put("user_no", user.get("user_no"));
			int rCnt = us.updateUser(hm);
			if(rCnt==1) {
				session.invalidate();
				result = "<script>";
				result += "alert('회원탈퇴 성공');";
				result += "location.href='/login.jsp';";
				result += "</script>";
			}
			doProcess(response, result);
			
		}else if(command.equals("delete")) {
			Map<String, String> hm = new HashMap<String, String>();
			String result = "회원탈퇴 실패";
			HttpSession session = request.getSession();
			Map<String, String> user = (Map)session.getAttribute("user");
			hm.put("user_no", user.get("user_no"));
			int rCnt = us.deleteUser(hm);
			if(rCnt==1) {
				session.invalidate();
				result = "<script>";
				result += "alert('회원탈퇴 성공');";
				result += "location.href='/login.jsp';";
				result += "</script>";
			}
			doProcess(response, result);
		}
	}
	
	public void doProcess(HttpServletResponse response, String result)throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		pw.println(result);
	}
}
