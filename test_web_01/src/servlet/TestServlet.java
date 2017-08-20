package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialException;

public class TestServlet extends HttpServlet{

	 public void doGet(HttpServletRequest requst, HttpServletResponse respons) throws IOException, ServletException{
		Map<String,String[]> m = requst.getParameterMap();
		Iterator<String> i = m.keySet().iterator();
		String result = "끼야~~~~~~~~~~꺄르르륵";
		 while(i.hasNext()) {
			 String key = i.next();
			 result += key + " : " + requst.getParameter(key);
		 }
		 doProcess(respons, result);
		 
	 }
	 
	 public void doPost(HttpServletRequest requst, HttpServletResponse respons) throws IOException, ServletException{
		 
		 System.out.println(requst.getParameterMap());
		 String result = "끼야";
		 doProcess(respons, result);
	 }
	 
	 public void doProcess(HttpServletResponse respons, String writerStr)throws IOException{
		 respons.setContentType("text/html; charset=UTF-8");
		 PrintWriter out = respons.getWriter();
		 out.println(writerStr);
	 }
	 
	
}
