package edu.pnu.dao.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LogDaoH2Impl implements LogDao {

	private static final Logger log = LoggerFactory.getLogger(LogDaoH2Impl.class);

	private JdbcTemplate jdbcTemplate;	
	
	public LogDaoH2Impl() {
		log.info("LogDaoH2Impl() 생성자 호출됨.");
	}

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		log.info("LogDaoH2Impl - setJdbcTemplate() 호출됨.");
	}
	
	@Override
	public void addLog(String method, String sqlstring, boolean success) {

		String sqlString = "insert into dblog (method,sqlstring,success) values (?,?,?)";
		try {
			jdbcTemplate.update(sqlString, method, sqlstring, success);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
