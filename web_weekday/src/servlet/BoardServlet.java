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

import com.google.gson.Gson;

import service.BoardService;
import service.BoardServiceImpl;

public class BoardServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private BoardService bs = new BoardServiceImpl();
	private Gson g = new Gson();
	
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
		if(command.equals("list")) {
			List<Map<String, String>> boardList = bs.selectBoardList();
			Map<String, Object> rHm = new HashMap<String, Object>();
			rHm.put("list", boardList);
			String result = g.toJson(rHm);
			System.out.println(result);
			doProcess(response, result);
		}else if(command.equals("write")) {
			String param = request.getParameter("param");
			Map<String, String> hm = g.fromJson(param, HashMap.class);
			System.out.println(hm);
			System.out.println(hm.get("title"));
			System.out.println(hm.get("content"));
			HttpSession session = request.getSession();
			Map<String, String> user = (Map<String, String>)session.getAttribute("user");
			hm.put("writer", user.get("user_no"));
			int row = bs.insertBoard(hm);
			Map<String, String> rhm = new HashMap<String, String>();
			rhm.put("msg", "저장에 실패");
			rhm.put("url", "/board/board_writer.jsp");
			if(row==1) {
				rhm.put("msg", "저장에 성공");
				rhm.put("url", "/board/board_list.jsp");
			}
			String result = g.toJson(rhm);
			doProcess(response, result);
		}else if(command.equals("view")) {
			String param = request.getParameter("param");
			Map<String, String> hm = g.fromJson(param, HashMap.class);
			Map<String, String> rhm = bs.selectBoard(hm);
			String result = g.toJson(rhm);
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
