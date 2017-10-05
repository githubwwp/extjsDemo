package jdbc.dao;

import java.util.List;

import jdbc.entity.db.LogInfo;
import jdbc.entity.db.Menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class LogDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List getAllLog(){
		String str = "select id, account, userId, menu1 as menu1, menu2, logDesc, resultCode, msg, exceptionInfo, params, createTime from t_log_info";
		List list =jdbcTemplate.queryForList(str);
		return list;
	}
	
	// 分页查询
	public List getPageLog(int start, int limit){
		String str = "select id, account, userId, menu1 as menu1, menu2, logDesc, resultCode, msg, exceptionInfo, params, createTime " +
				" from t_log_info order by createTime desc limit ?, ?";
		List list = jdbcTemplate.queryForList(str, new Object[]{start, limit});
		return list;
	}
	
	// 计数
	public int getCount(){
		String str = "select count(*) from t_log_info";
		int count = jdbcTemplate.queryForObject(str, Integer.class);
		return count;
	}
	
}
