package jdbc.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jdbc.entity.vo.CompanyVo;
import jdbc.service.MenuService;
import jdbc.util.SequenceUtil;
import net.sf.json.spring.web.servlet.view.JsonView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Scope("prototype")
@RequestMapping(value = "test")
public class TestController {

	@Autowired
	private MenuService menuService;

	@RequestMapping(value="aa")
	public ModelAndView testModel() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("a", "aa");
		map.put("b", 123);
		map.put("c", false);
		map.put("d", null);

		return new ModelAndView(new JsonView(), map);
	}
	
	@RequestMapping(value="model")
	public ModelAndView getModel(){
		Map<String, Object> map = new HashMap<String, Object>();
		List<CompanyVo> companyVos = new ArrayList<CompanyVo>();
		for(int i=0; i<5; i++){
			CompanyVo companyVo = new CompanyVo();
			companyVo.setName("name"+ SequenceUtil.geneBaseSequence(""));
			companyVo.setAddr("addr" + SequenceUtil.geneBaseSequence(""));
			
			companyVos.add(companyVo);
		}
		map.put("companies", companyVos);
		
		return new ModelAndView(new JsonView(), map);
	}
	
	/**
	 * 获取所有菜单列表
	 * @return
	 */
	@RequestMapping(value="getAllMenu")
	public ModelAndView getAllMenu(){
		Map<String, Object> map = new HashMap<String, Object>();
		List menus = menuService.getAllMenu();
		map.put("menus", menus);
		
		return new ModelAndView(new JsonView(), map);
	}

}
