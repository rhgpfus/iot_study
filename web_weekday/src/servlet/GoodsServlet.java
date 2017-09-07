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

import dto.GoodsInfo;
import dto.VendorInfo;
import service.GoodsService;
import service.GoodsServiceImpl;

public class GoodsServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private GoodsService gs = new GoodsServiceImpl();
	private Gson g = new Gson();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
			request.setCharacterEncoding("utf-8");
	} 
 
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String command = request.getParameter("command");
		if(command.equals("list")) {
			List<GoodsInfo> giList = gs.selectGoodsList(null);
			List<VendorInfo> viList = gs.selectVendorList(null);
			
			request.setAttribute("goodsList", giList);
			request.setAttribute("vendorList", viList);
			String result = "<table border='1'>";
			for(GoodsInfo gi : giList) {
				result += "<tr>";
				result += "<td>" + gi.getGiNum() + "</td>";
				result += "<td>"+ gi.getGiName() + "</td>";
				result += "<td>"+ gi.getGiDesc() + "</td>";
				result += "<td><select>";
				for(VendorInfo vi : viList) {
					String sel = "";
					if(vi.getViNum()==gi.getViNum()) {
						sel = "selected";
					}
					result += "<option" + sel + ">" + vi.getViName() + "</option>";
				}
				result += "</select></td>";
				result += "<td>" + gi.getViNum() + "</td>";
				result += "</tr>";
			}
			result += "</table>";
			doProcess(response, result);
			
			//String url = "/goods/goods_list.jsp";
			//RequestDispatcher rd = request.getRequestDispatcher(url);
			//url을 변경하지않고 내부적으로 동작하는 자바를 변경한다. 내용만 goods_list로 되는것.
			//rd.forward(request, response);
			
		}
		
	}
	
	public void doProcess(HttpServletResponse response, String result)throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		pw.println(result);
	}
}
