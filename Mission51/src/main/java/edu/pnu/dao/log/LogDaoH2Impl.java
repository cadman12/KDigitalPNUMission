package edu.pnu.dao.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LogDaoH2Impl implements LogDao {

	private JdbcTemplate jdbcTemplate;	
	
	@Autowired
	public LogDaoH2Impl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
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
