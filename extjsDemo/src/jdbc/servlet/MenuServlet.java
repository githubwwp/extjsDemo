package jdbc.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.entity.vo.MenuVo;
import jdbc.service.MenuService;
import jdbc.util.StringUtil;

import net.sf.json.JSONObject;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

public class MenuServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	@SuppressWarnings("rawtypes")
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext(); 
		MenuService menuService = (MenuService) wac.getBean("menuService");
		String tid = request.getParameter("tid");
		
		List<MenuVo> menuVoList = new ArrayList<MenuVo>();
		List list = menuService.getByPid(tid);
		for(int i=0; i< list.size(); i++){
			MenuVo menuVo = new MenuVo();
			Map m = (Map) list.get(i);
			menuVo.setId(StringUtil.trimNull(m.get("menu_id")));
			menuVo.setPid(StringUtil.trimNull(m.get("pmenu_id")));
			menuVo.setText(StringUtil.trimNull(m.get("menu_name")));
			menuVo.setUrl(StringUtil.trimNull(m.get("menu_url")));
			
			menuVoList.add(menuVo);
		}
		
		
		JSONObject json = JSONObject.fromObject("{}");
		json.put("menuList", menuVoList);
		response.setHeader("Content-type", "text/html;charset=UTF-8"); 
		response.getWriter().print(json.toString());
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
