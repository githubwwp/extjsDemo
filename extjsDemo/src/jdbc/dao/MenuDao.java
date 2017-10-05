package jdbc.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MenuDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List getAll() {
		String sql = "select * from demo_menu";
		List list = jdbcTemplate.queryForList(sql);
		return list;
	}

	public List getByPid(String pid) {
		String sql = "select * from demo_menu where pmenu_id = ?";
		List list = jdbcTemplate.queryForList(sql, new Object[] { pid });

		return list;
	}

	public List getAllMenu() {
		String sql = "select * from (SELECT menu_id as menu_id, pmenu_id as pmenu_id, menu_name as text, menu_url as menu_url,"
				+ " leaf as leaf, level as level, menu_remark as menu_remark, `order` as `order` FROM demo_menu) aa";
		List list = jdbcTemplate.queryForList(sql);

		return list;
	}

}
