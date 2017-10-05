package jdbc.service;

import java.awt.Menu;
import java.util.List;

import jdbc.dao.MenuDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuService {
	
	@Autowired
	private MenuDao menuDao;
	
	public List getAll(){

		return menuDao.getAll();
	}

	public List getByPid(String pid){
		
		return menuDao.getByPid(pid);
	}
	
	public List getAllMenu(){
		
		return menuDao.getAllMenu();
	}
	
}
