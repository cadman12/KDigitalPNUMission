package edu.pnu.dao.log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LogDaoH2Impl implements LogDao {
	private Connection con = null;
	
	public LogDaoH2Impl() {
        try {
            // JDBC 드라이버 로드
            Class.forName("org.h2.Driver");
            
            con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/springboot", "sa", "");
        }
        catch (Exception e) {            
            e.printStackTrace();
        }
	}

	@Override
	public void addLog(String method, String sqlstring, boolean success) {
		
		PreparedStatement psmt = null;
		try {
			psmt = con.prepareStatement("insert into dblog (method,sqlstring,success) values (?,?,?)");
			psmt.setString(1,  method);
			psmt.setString(2, sqlstring);
			psmt.setBoolean(3,  success);
			psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				psmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
