package jdbc.web.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jdbc.service.MenuService;
import jdbc.util.StringUtil;

import net.sf.json.spring.web.servlet.view.JsonView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Scope("prototype")
@RequestMapping(value = "menu")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MenuController {

	@Autowired
	private MenuService menuService;

	/**
	 * 获取全部的数结构
	 */
	@RequestMapping(value = "getAllTreeMenus")
	public ModelAndView getAllTreeMenus() {
		Map treeMap = new HashMap();
		List allMenus = menuService.getAllMenu();
		
		// 使用排序功能
		Collections.sort(allMenus, new Comparator<Map>() {
			@Override
			public int compare(Map o1, Map o2) {
				String order1 = StringUtil.trimNull(o1.get("order"));
				String order2 = StringUtil.trimNull(o1.get("order2"));
				return order1.compareTo(order2);
			}
			
		});
		
		// pid=0 is the hightest item;
		this.getAllSubMenus(treeMap, "0", allMenus);

		return new ModelAndView(new JsonView(), treeMap);
	}

	// 递归获取所有子目录
	private void getAllSubMenus( Map curMap, String curId, List allMenus) {
		List subMenus = new ArrayList();
		for (Object o : allMenus) {
			Map m = (Map) o;
			String id = StringUtil.trimNull(m.get("menu_id"));
			String pId = StringUtil.trimNull(m.get("pmenu_id"));

			if (curId.equals(pId)) {
				subMenus.add(m);
				// 递归查询所有子目录
				this.getAllSubMenus(m, id, allMenus);
			}
		}
		curMap.put("children", subMenus);
		curMap.put("leaf", subMenus.size() > 0 ? false : true); // 获取是否为子节点(不从数据库读取)
	}

}
