package edu.pnu.dao.log;

public interface LogDao {

	void addLog(String method, String sqlstring, boolean success);

}