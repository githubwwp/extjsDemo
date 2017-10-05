package jdbc.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

@SuppressWarnings("rawtypes")
public class JdbcTestDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        try {
            throw new RuntimeException();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sum(int a, float b) {
        
        System.out.println("----jdbcTemplate: " + jdbcTemplate);
    }

    public List getList() {
        String sql = "select * from test.col_test ";
        List list = this.jdbcTemplate.queryForList(sql);

        return list;
    }

    public List getTestColTest() {
        String sql = "SELECT * FROM test_col_test";
        List list = jdbcTemplate.queryForList(sql);
        return list;
    }

    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        JdbcTestDao jdbcTestDao = (JdbcTestDao) ac.getBean("jdbcTestDao");
        List list = jdbcTestDao.getList();

        System.out.println();
        ;
    }

}
