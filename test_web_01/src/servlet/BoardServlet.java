package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dto.Board;
import service.BoardService;
import service.impl.BoardServiceImpl;

public class BoardServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	private BoardService bs = new BoardServiceImpl();
	private Gson g = new Gson();
	
	
	 
	 public void doPost(HttpServletRequest request, HttpServletResponse respons) throws IOException, ServletException{
		 request.setCharacterEncoding("UTF-8");
		 String command = request.getParameter("command");
			if(command.equals("list")) {
				List<Board> boardList = bs.selectBoardList();
				String result = g.toJson(boardList);
				doProcess(respons, result);
			}
	}
	 
	 public void doGet(HttpServletRequest requst, HttpServletResponse respons) throws IOException, ServletException{
			
		 respons.setContentType("text/html;charset=utf-8");
		 String command = requst.getParameter("command");
		 if(command.endsWith("list")) {
			 RequestDispatcher rd = requst.getRequestDispatcher("/board/board_list.jsp");
			List<Board> boardList = bs.selectBoardList();
			requst.setAttribute("boardList", boardList);
			rd.forward(requst, respons);
		 }
	 }
	 
	 public void doProcess(HttpServletResponse respons, String writerStr)throws IOException{
		 respons.setContentType("text/html; charset=UTF-8");
		 PrintWriter out = respons.getWriter();
		 out.println(writerStr);
	 }
	 
	
}
