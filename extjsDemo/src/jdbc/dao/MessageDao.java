package jdbc.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class MessageDao {
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	public List getAll(){
		
		String sql = "select * from t_message";
		List list = jdbcTemplate.queryForList(sql);
		
		return list;
	}
	
}
