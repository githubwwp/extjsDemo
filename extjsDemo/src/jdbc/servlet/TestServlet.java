package jdbc.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.dao.JdbcTestDao;
import jdbc.util.SpringConfigUtil;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

public class TestServlet extends HttpServlet {
	
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
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();    
		
		JdbcTestDao jdbcTestDao = (JdbcTestDao)wac.getBean("jdbcTestDao");

		List list = jdbcTestDao.getTestColTest();
		for(Object o: list){
			Map m = (Map)o;
			for(Object o2: m.entrySet()){
				Object key = o2;
				Object val = m.get(o2);
				System.out.println("key: " + o2 + ", val: " + m.get(o2) + ", " + val.getClass());
			}
		}

		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

}
