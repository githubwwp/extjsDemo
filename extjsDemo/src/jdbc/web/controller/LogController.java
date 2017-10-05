package jdbc.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jdbc.entity.db.LogInfo;
import jdbc.service.LogService;
import net.sf.json.spring.web.servlet.view.JsonView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Scope("prototype")
@RequestMapping(value = "log")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class LogController {
	
	@Autowired
	private LogService logService;
	
	@RequestMapping(value="getAll")
	public ModelAndView getAll(){
		Map map = new HashMap();
		List logs = logService.getAllLog();
		
		map.put("logs", logs);
		map.put("total", 100);
		return new ModelAndView(new JsonView(), map);
	}
	
	@RequestMapping(value="getPageLog")
	public ModelAndView getPageLog(int start, int limit){
		Map map = new HashMap();
		List logs = logService.getPageLog(start, limit);
		int total = logService.getCount();
		
		map.put("logs", logs);
		map.put("total", total);
		return new ModelAndView(new JsonView(), map);
	}
	
	
}
