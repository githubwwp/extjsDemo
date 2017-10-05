package jdbc.service;

import java.util.List;

import jdbc.dao.LogDao;
import jdbc.entity.db.LogInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings({ "rawtypes", "unchecked" })
public class LogService {

	@Autowired
	private LogDao logDao;
	
	public List getAllLog(){
		return logDao.getAllLog();
	}
	
	public List getPageLog(int start, int limit){
		return logDao.getPageLog(start, limit);
	}
	
	public int getCount(){
		return logDao.getCount();
	}
	
}
